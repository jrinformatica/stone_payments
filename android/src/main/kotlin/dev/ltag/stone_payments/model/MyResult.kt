package dev.ltag.stone_payments.model

import android.util.Log
import io.flutter.plugin.common.MethodChannel


class MyResult(private val result: MethodChannel.Result) : MethodChannel.Result {
    private val tag = "MyResult"
    private var alreadyReply = false
    private val lock = Object()

    override fun success(value: Any?) {
        Log.d(tag, "success: $value")
        synchronized(lock) {
            if (alreadyReply) return
            alreadyReply = true
        }
        result.success(value)
    }

    override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
        Log.d(tag, "error: $errorCode, $errorMessage, $errorDetails")
        synchronized(lock) {
            if (alreadyReply) return
            alreadyReply = true
        }
        result.error(errorCode, errorMessage, errorDetails)
    }

    override fun notImplemented() {
        Log.d(tag, "notImplemented")
        synchronized(lock) {
            if (alreadyReply) return
            alreadyReply = true
        }
        result.notImplemented()
    }

}