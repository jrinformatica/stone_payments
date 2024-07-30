package dev.ltag.stone_payments

import android.app.Activity
import android.content.Context
import br.com.stone.posandroid.providers.PosTransactionProvider
import dev.ltag.stone_payments.model.MyResult
import dev.ltag.stone_payments.usecases.ActivateUsecase
import dev.ltag.stone_payments.usecases.PaymentUsecase
import dev.ltag.stone_payments.usecases.PrinterUsecase
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import stone.database.transaction.TransactionObject
import io.flutter.plugin.common.MethodChannel.Result as Res


/** StonePaymentsPlugin */
class StonePaymentsPlugin : FlutterPlugin, MethodCallHandler, Activity() {
    private lateinit var channel: MethodChannel
    var transactionObject = TransactionObject()
    var providerPosTransaction: PosTransactionProvider? = null

    var context: Context = this

    companion object {
        var flutterBinaryMessenger: BinaryMessenger? = null
    }

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        flutterBinaryMessenger = flutterPluginBinding.binaryMessenger
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "stone_payments")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Res) {
        val myResult = MyResult(result)
        val activateUsecase = ActivateUsecase(context, myResult)
        val paymentUsecase = PaymentUsecase(this, myResult)
        val printerUsecase = PrinterUsecase(this, myResult)

        when (call.method) {
            "activateStone" -> {
                try {
                    activateUsecase.doActivate(
                        call.argument("appName")!!,
                        call.argument("stoneCode")!!,
                        call.argument("qrCodeAuthorization"),
                        call.argument("qrCodeProviderId"),
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Activate", e.toString())
                }
            }

            "payment" -> {
                try {
                    paymentUsecase.doPayment(
                        call.argument("value")!!,
                        call.argument("typeTransaction")!!,
                        call.argument("installment")!!,
                        call.argument("printReceipt")
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Pay", e.toString())
                }
            }

            "abortPayment" -> {
                try {
                    paymentUsecase.abortPayment()
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Abort", e.toString())
                }
            }

            "printFile" -> {
                try {
                    printerUsecase.printFile(
                        call.argument("imgBase64")!!
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Activate", e.toString())
                }
            }

            "print" -> {
                try {
                    printerUsecase.print(
                        call.argument("items")!!
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Activate", e.toString())
                }
            }

            "printReceipt" -> {
                try {
                    printerUsecase.printReceipt(
                        call.argument("type")!!
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot Activate", e.toString())
                }
            }

            "cancel-payment" -> {
                try {
                    paymentUsecase.cancel(
                        call.argument("acquirerTransactionKey")!!,
                        call.argument("printReceipt")
                    )
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot cancel", e.toString())
                }
            }

            "capture" -> {
                try {
                    paymentUsecase.captureTransaction(call.argument("transactionId")!!)
                } catch (e: Exception) {
                    myResult.error("UNAVAILABLE", "Cannot capture", e.toString())
                }

            }

            else -> {
                myResult.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}