package dev.ltag.stone_payments.usecases

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import br.com.stone.posandroid.providers.PosPrintReceiptProvider
import br.com.stone.posandroid.providers.PosTransactionProvider
import dev.ltag.stone_payments.StonePaymentsPlugin
import io.flutter.plugin.common.MethodChannel
import stone.application.enums.Action
import stone.application.enums.InstalmentTransactionEnum
import stone.application.enums.ReceiptType
import stone.application.enums.TransactionStatusEnum
import stone.application.enums.TypeOfTransactionEnum
import stone.application.interfaces.StoneActionCallback
import stone.application.interfaces.StoneCallbackInterface
import stone.database.transaction.TransactionDAO
import stone.database.transaction.TransactionObject
import stone.providers.CancellationProvider
import stone.providers.CaptureTransactionProvider
import stone.utils.Stone
import java.io.ByteArrayOutputStream


class PaymentUsecase(
    private val stonePayments: StonePaymentsPlugin,
) {
    private val context = stonePayments.context
    private val tag: String = "STONE_PAYMENTS"

    private fun TransactionObject.toJson(): Map<String, *> {
        return mapOf(
            "acquirerTransactionKey" to acquirerTransactionKey,
            "initiatorTransactionKey" to initiatorTransactionKey,
            "amount" to amount,
            "typeOfTransaction" to typeOfTransaction.name,
            "instalmentTransaction" to instalmentTransaction.name,
            "instalmentType" to instalmentType.name,
            "cardHolderNumber" to cardHolderNumber,
            "cardBrandName" to cardBrandName,
            "cardHolderName" to cardHolderName,
            "authorizationCode" to authorizationCode,
            "transactionStatus" to transactionStatus.name,
            "date" to date,
            "time" to time,
            "entryMode" to entryMode.toString(),
            "aid" to aid,
            "arcq" to arcq,
            "shortName" to shortName,
            "userModel" to userModel.toString(),
            "pinpadUsed" to pinpadUsed,
            "balance" to balance,
            "isCapture" to isCapture,
            "subMerchantCategoryCode" to subMerchantCategoryCode,
            "subMerchantAddress" to subMerchantAddress
        )
    }

    fun doPayment(
        value: Double,
        type: Int,
        installment: Int,
        print: Boolean?,
        result: MethodChannel.Result,
    ) {
        try {
            stonePayments.transactionObject = TransactionObject()

            val transactionObject = stonePayments.transactionObject

            transactionObject.instalmentTransaction =
                InstalmentTransactionEnum.getAt(installment - 1)
            transactionObject.typeOfTransaction =
                if (type == 1) TypeOfTransactionEnum.CREDIT else if (type == 2) TypeOfTransactionEnum.PIX else TypeOfTransactionEnum.DEBIT
            transactionObject.isCapture = false
            val newValue: Int = (value * 100).toInt()
            transactionObject.amount = newValue.toString()

            stonePayments.providerPosTransaction = PosTransactionProvider(
                context,
                transactionObject,
                Stone.getUserModel(0),
            )

            val provider = stonePayments.providerPosTransaction!!

            provider.setConnectionCallback(object : StoneActionCallback {

                override fun onSuccess() {
                    if (provider.transactionStatus == TransactionStatusEnum.APPROVED && print == true) {
                        printReceipt(transactionObject)
                    }
                    stonePayments.providerPosTransaction = null
                    result.success(transactionObject.toJson())
                }

                override fun onError() {
                    Log.d(tag, "ERROR")

                    result.error(
                        provider.authorizationCode,
                        provider.messageFromAuthorize,
                        provider.transactionStatus.name
                    )

                    stonePayments.providerPosTransaction = null
                }

                override fun onStatusChanged(p0: Action?) {

                    if (p0 == Action.TRANSACTION_WAITING_QRCODE_SCAN) {
                        sendAQRCode(transactionObject.qrCode)
                    }
                }
            })

            provider.execute()
        } catch (exception: Exception) {
            Log.d(tag, exception.toString())
            result.error("ERROR", exception.message, exception.stackTrace.toString())
        }
    }

    private fun printReceipt(transactionObject: TransactionObject) {
        val posPrintReceiptProvider =
            PosPrintReceiptProvider(
                context, transactionObject,
                ReceiptType.MERCHANT,
            )

        posPrintReceiptProvider.connectionCallback = object :
            StoneCallbackInterface {

            override fun onSuccess() {

                Log.i(tag, "Impresso comprovante com sucesso")
            }

            override fun onError() {

                Log.e(tag, "Erro ao imprimir comprovante")
            }
        }

        posPrintReceiptProvider.execute()
    }

    fun abortPayment(result: MethodChannel.Result) {
        try {
            if (stonePayments.providerPosTransaction == null) {
                result.success(false)
                return
            }
            stonePayments.providerPosTransaction?.abortPayment()
            result.success(true)

        } catch (exception: Exception) {
            Log.d("ERROR", exception.toString())
            result.error("ERROR", exception.message, exception.stackTrace.toString())
        }
    }

    fun captureTransaction(
        transactionId: String,
        result: MethodChannel.Result
    ) {
        val transactionDAO = TransactionDAO(context)
        val selectedTransaction =
            transactionDAO.findTransactionWithInitiatorTransactionKey(transactionId)
        if (selectedTransaction == null) {
            result.error("NOT FOUND", "NOT FOUND", "NOT FOUND")
            return
        }
        if (selectedTransaction.isCapture) {
            result.success(true)
            return
        }
        val provider = CaptureTransactionProvider(context, selectedTransaction)
        provider.connectionCallback = object : StoneCallbackInterface {

            override fun onSuccess() {
                result.success(true)
            }

            override fun onError() {
                Log.d("RESULT", "ERROR")

                result.error("ERROR", provider.messageFromAuthorize, "ERROR")
            }
        }
        provider.execute()

    }

    fun cancel(
        acquirerTransactionKey: String,
        print: Boolean?,
        result: MethodChannel.Result
    ) {
        try {
            val transactionDAO = TransactionDAO(context)
            val selectedTransaction =
                transactionDAO.findTransactionWithAtk(acquirerTransactionKey)

            if(selectedTransaction == null) {
                result.error("NOT FOUND", "NOT FOUND", "NOT FOUND")
                return
            }

            val provider = CancellationProvider(
                context,
                selectedTransaction,
            )

            provider.connectionCallback = object : StoneCallbackInterface {

                override fun onSuccess() {
                    try {
                        if (print == true) {
                            val posPrintReceiptProvider =
                                PosPrintReceiptProvider(
                                    context, selectedTransaction,
                                    ReceiptType.MERCHANT,
                                )

                            posPrintReceiptProvider.connectionCallback = object :
                                StoneCallbackInterface {

                                override fun onSuccess() {

                                    Log.d("SUCCESS", selectedTransaction.toString())

                                }

                                override fun onError() {
                                    Log.d("ERRORPRINT", selectedTransaction.toString())

                                }
                            }

                            posPrintReceiptProvider.execute()
                        }
                    } catch (e: Exception) {
                        Log.e("ERROR", e.toString())
                    }
                    result.success(true)
                }

                override fun onError() {

                    Log.d("RESULT", "ERROR")

                    result.error("ERROR", provider.messageFromAuthorize, "ERROR")
                }
            }

            provider.execute()


        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            result.error("ERROR", e.message, e.stackTrace.toString())
        }

    }
    private fun sendAMessage(message: String) {
        Handler(Looper.getMainLooper()).post {
            val channel = MethodChannel(
                StonePaymentsPlugin.flutterBinaryMessenger!!,
                "stone_payments",
            )
            channel.invokeMethod("message", message)
        }
    }
    
    private fun sendAQRCode(message: Bitmap) {
        Handler(Looper.getMainLooper()).post {
            val channel = MethodChannel(
                StonePaymentsPlugin.flutterBinaryMessenger!!,
                "stone_payments",
            )
            channel.invokeMethod("qrcode", BitMapToString(message))
        }
    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}