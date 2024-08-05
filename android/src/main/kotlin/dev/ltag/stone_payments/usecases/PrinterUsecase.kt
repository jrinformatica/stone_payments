package dev.ltag.stone_payments.usecases

import android.util.Log
import br.com.stone.posandroid.providers.PosPrintProvider
import br.com.stone.posandroid.providers.PosPrintReceiptProvider
import dev.ltag.stone_payments.StonePaymentsPlugin
import dev.ltag.stone_payments.helper.getMessageOfErrorInPortuguese
import dev.ltag.stone_payments.model.MyResult
import stone.application.enums.ReceiptType
import stone.application.interfaces.StoneCallbackInterface

class PrinterUsecase(
    private val stonePayments: StonePaymentsPlugin,
    private val result: MyResult,
) {
    private val context = stonePayments.context

    fun printFile(imgBase64: String) {
        try {
            val posPrintProvider = PosPrintProvider(context)
            posPrintProvider.addBase64Image(imgBase64)

            posPrintProvider.execute()
            result.success(true)
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            result.error("ERROR", "Error on Print", e.toString())
        }
    }

    fun print(items: List<Map<String, Any>>) {
        try {
            val posPrintProvider = PosPrintProvider(context)
            for (item in items) {
                if (item["type"] == 0) {
                    posPrintProvider.addLine(item["data"].toString())
                } else {
                    posPrintProvider.addBase64Image(item["data"].toString())
                }
            }

            posPrintProvider.execute()
            result.success(true)
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            result.error("ERROR", "Error on Print", e.toString())
        }

    }

    fun printReceipt(type: Int) {

        val transactionObject = stonePayments.transactionObject

        if (transactionObject.amount == null) {
            result.error("NOT FOUND", "Transaction not found", "Transaction not found")
            return
        }
        val posPrintReceiptProvider =
            PosPrintReceiptProvider(
                context,
                transactionObject,
                if (type == 1) ReceiptType.MERCHANT else ReceiptType.CLIENT,
            )

        posPrintReceiptProvider.connectionCallback = object :
            StoneCallbackInterface {

            override fun onSuccess() {
                Log.d("SUCCESS", transactionObject.toString())
                result.success(true)
            }

            override fun onError() {

                Log.d("ERRORPRINT", transactionObject.toString())
                result.error(
                    "ERROR",
                    posPrintReceiptProvider.getMessageOfErrorInPortuguese(),
                    ""
                )
            }
        }

        posPrintReceiptProvider.execute()

    }
}