package dev.ltag.stone_payments.model

import io.flutter.plugin.common.MethodChannel


class MyResult(private val result: MethodChannel.Result) : MethodChannel.Result {
    private var alreadyReply = false
    override fun success(value: Any?) {
        if (alreadyReply) return
        result.success(value)
        alreadyReply = true
    }

    override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
        if (alreadyReply) return
        result.error(errorCode, errorMessage, errorDetails)
        alreadyReply = true
    }

    override fun notImplemented() {
        if (alreadyReply) return
        result.notImplemented()
        alreadyReply = true
    }

}