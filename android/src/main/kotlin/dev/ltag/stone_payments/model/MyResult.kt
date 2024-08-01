package dev.ltag.stone_payments.model

import android.util.Log
import io.flutter.plugin.common.MethodChannel


class MyResult(private val result: MethodChannel.Result) : MethodChannel.Result {
    private val tag = "MyResult"
    private var alreadyReply = false
    override fun success(value: Any?) {
        Log.d(tag, "success: $value")
        if (alreadyReply) return
        result.success(value)
        alreadyReply = true
    }

    override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
        Log.d(tag, "error: $errorCode, $errorMessage, $errorDetails")
        if (alreadyReply) return
        result.error(errorCode, errorMessage, errorDetails)
        alreadyReply = true
    }

    override fun notImplemented() {
        Log.d(tag, "notImplemented")
        if (alreadyReply) return
        result.notImplemented()
        alreadyReply = true
    }

}