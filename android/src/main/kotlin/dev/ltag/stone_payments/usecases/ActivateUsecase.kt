package dev.ltag.stone_payments.usecases

import android.content.Context
import android.util.Log
import dev.ltag.stone_payments.model.MyResult
import stone.application.StoneStart
import stone.application.interfaces.StoneCallbackInterface
import stone.providers.ActiveApplicationProvider
import stone.user.UserModel
import stone.utils.Stone
import stone.utils.keys.StoneKeyType

class ActivateUsecase(
    private val context: Context,
    private val result: MyResult,
) {
    fun doActivate(
        appName: String,
        stoneCode: String,
        qrCodeAuthorization: String?,
        qrCodeProviderId: String?
    ) {
        Stone.setAppName(appName)


        val userList: List<UserModel>?
        if (qrCodeAuthorization != null && qrCodeProviderId != null) {
            val stoneKeysHashed = HashMap<StoneKeyType, String>()
            stoneKeysHashed[StoneKeyType.QRCODE_AUTHORIZATION] = qrCodeAuthorization
            stoneKeysHashed[StoneKeyType.QRCODE_PROVIDERID] = qrCodeProviderId
            userList = StoneStart.init(context, stoneKeysHashed)
        } else {
            userList = StoneStart.init(context)
        }

        if (userList == null) {
            val activeApplicationProvider = ActiveApplicationProvider(context)
            activeApplicationProvider.dialogMessage = "Ativando o Stone Code"
            activeApplicationProvider.dialogTitle = "Aguarde"
            activeApplicationProvider.connectionCallback = object : StoneCallbackInterface {

                override fun onSuccess() {
                    // SDK ativado com sucesso

                    result.success(true)
                    Log.d("SUCESSO", "SUCESSO")
                }

                override fun onError() {
                    // Ocorreu algum erro na ativação

                    Log.d("ERROR", "ERRO")
                    result.error("ERROR", "Error on Activate", "Error on Activate")
                }
            }
            activeApplicationProvider.activate(stoneCode)
        } else {
            result.success(true)
        }
    }
}
