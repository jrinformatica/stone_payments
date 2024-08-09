package dev.ltag.stone_payments.usecases

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import br.com.stone.posandroid.providers.PosPrintReceiptProvider
import br.com.stone.posandroid.providers.PosTransactionProvider
import dev.ltag.stone_payments.StonePaymentsPlugin
import dev.ltag.stone_payments.helper.getMessageOfErrorInPortuguese
import dev.ltag.stone_payments.model.MyResult
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
import stone.providers.ReversalProvider
import stone.utils.Stone
import java.io.ByteArrayOutputStream


class PaymentUsecase(
    private val stonePayments: StonePaymentsPlugin,
    private val channel: MethodChannel,
    private val result: MyResult
) {
    private val context = stonePayments.context
    private val tag: String = "STONE_PAYMENTS"

    private fun TransactionObject.toJson(): Map<String, *> {
        return mapOf(
            "acquirerTransactionKey" to acquirerTransactionKey,
            "initiatorTransactionKey" to initiatorTransactionKey,
            "amount" to amount,
            "typeOfTransaction" to typeOfTransaction.ordinal,
            "instalmentTransaction" to instalmentTransaction.name,
            "instalmentType" to instalmentType?.name,
            "cardHolderNumber" to cardHolderNumber,
            "cardBrandName" to cardBrandName,
            "cardHolderName" to cardHolderName,
            "authorizationCode" to authorizationCode,
            "transactionStatus" to transactionStatus.name,
            "date" to date,
            "time" to time,
            "entryMode" to entryMode?.toString(),
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

    private fun reverseTransactionsWithError() {
        try {
            val reversalProvider = ReversalProvider(context)

            reversalProvider.connectionCallback = object : StoneCallbackInterface {
                override fun onSuccess() {
                    Log.d(tag, "ReversalProvider success")
                }

                override fun onError() {
                    Log.e(tag, "ReversalProvider error ")
                }
            }
            reversalProvider.execute()
        } catch (e: Exception) {
            Log.e(tag, e.toString())
        }
    }

    fun doPayment(
        value: Double, type: Int, installment: Int, print: Boolean?
    ) {
        try {
            stonePayments.transactionObject = TransactionObject()

            val transactionObject = stonePayments.transactionObject

            transactionObject.instalmentTransaction =
                InstalmentTransactionEnum.getAt(installment - 1)
            transactionObject.typeOfTransaction =
                TypeOfTransactionEnum.values()[type]
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
                    result.success(transactionObject.toJson())
                }

                override fun onError() {
                    Log.d(tag, "PAYMENT_ERROR")
                    result.error(
                        "PAYMENT_ERROR",
                        provider.getMessageOfErrorInPortuguese(),
                        transactionObject.toJson()
                    )
                }

                override fun onStatusChanged(status: Action?) {
                    sendAPaymentStatus(status.toString())
                    if (status == Action.TRANSACTION_WAITING_QRCODE_SCAN) {
                        sendAQRCode(transactionObject.qrCode)
                    }
                }
            })

            provider.execute()
        } catch (exception: Exception) {
            Log.d(tag, exception.toString())
            result.error("ERROR", exception.message, exception.stackTraceToString())
        }
    }

    private fun printReceipt(transactionObject: TransactionObject) {
        val posPrintReceiptProvider = PosPrintReceiptProvider(
            context, transactionObject,
            ReceiptType.MERCHANT,
        )

        posPrintReceiptProvider.connectionCallback = object : StoneCallbackInterface {

            override fun onSuccess() {

                Log.i(tag, "Impresso comprovante com sucesso")
            }

            override fun onError() {

                Log.e(tag, "Erro ao imprimir comprovante")
            }
        }

        posPrintReceiptProvider.execute()
    }

    fun abortPayment() {
        try {
            stonePayments.providerPosTransaction?.abortPayment()
            result.success(true)
        } catch (exception: Exception) {
            Log.d("ERROR", exception.toString())

            result.error("ERROR", exception.message, exception.stackTraceToString())
        }
    }

    fun captureTransaction(
        acquirerTransactionKey: String,
    ) {
        try {
            val transactionDAO = TransactionDAO(context)
            val selectedTransaction = transactionDAO.findTransactionWithAtk(acquirerTransactionKey)
            if (selectedTransaction == null) {
                result.error("ERROR", "NOT FOUND", "NOT FOUND")
                return
            }
            if (selectedTransaction.isCapture) {
                result.success(selectedTransaction.toJson())
                return
            }
            val provider = CaptureTransactionProvider(context, selectedTransaction)
            provider.connectionCallback = object : StoneCallbackInterface {

                override fun onSuccess() {
                    result.success(selectedTransaction.toJson())
                }

                override fun onError() {
                    Log.d("RESULT", "ERROR")

                    result.error("ERROR", provider.getMessageOfErrorInPortuguese(), "ERROR")
                }
            }
            provider.execute()
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            result.error("ERROR", e.message, e.stackTraceToString())
        }
    }

    fun cancel(
        acquirerTransactionKey: String, print: Boolean?
    ) {
        try {
            val transactionDAO = TransactionDAO(context)
            val selectedTransaction = transactionDAO.findTransactionWithAtk(acquirerTransactionKey)

            if (selectedTransaction == null) {
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
                            val posPrintReceiptProvider = PosPrintReceiptProvider(
                                context, selectedTransaction,
                                ReceiptType.MERCHANT,
                            )

                            posPrintReceiptProvider.connectionCallback =
                                object : StoneCallbackInterface {

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

                    result.error(
                        "ERROR",
                        provider.getMessageOfErrorInPortuguese(),
                        null,
                    )
                }
            }

            provider.execute()
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            result.error("ERROR", e.message, e.stackTraceToString())
        }

    }

    private fun sendAPaymentStatus(status: String) {
        Handler(Looper.getMainLooper()).post {
            channel.invokeMethod("payment-action", status)
        }
    }

    private fun sendAQRCode(message: Bitmap) {
        Handler(Looper.getMainLooper()).post {
            channel.invokeMethod("qrcode", bitMapToImagePngBytes(message))
        }
    }

    private fun bitMapToImagePngBytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }
}