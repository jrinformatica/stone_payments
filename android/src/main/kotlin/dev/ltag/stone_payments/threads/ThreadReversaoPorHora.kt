package dev.ltag.stone_payments.threads

import android.content.Context
import android.util.Log
import dev.ltag.stone_payments.helper.getMessageOfErrorInPortuguese
import stone.application.interfaces.StoneCallbackInterface
import stone.providers.ReversalProvider

class ThreadReversaoPorHora(private val context: Context) : Thread() {
    private val TAG = "Reversal"

    override fun run() {
        do {
            try {
                val reversalProvider = ReversalProvider(context)
                Log.i(TAG, "Executando reversal")
                reversalProvider.connectionCallback = object : StoneCallbackInterface {
                    override fun onSuccess() {
                        Log.i(TAG, "Reversal executado com sucesso")
                    }

                    override fun onError() {
                        Log.e(
                            TAG,
                            reversalProvider.messageFromAuthorize
                                ?: reversalProvider.getMessageOfErrorInPortuguese()
                        )
                    }
                }
                reversalProvider.execute()
            } catch (exception: Exception) {
                Log.e(TAG, "Erro ao executar reversal", exception)
            } finally {
                sleep(1000 * 60 * 60) // 1 hora
            }
        } while (true)
    }
}