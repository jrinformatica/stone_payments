package dev.ltag.stone_payments.usecases

import android.content.Context
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import stone.application.StoneStart
import stone.application.interfaces.StoneCallbackInterface
import stone.providers.ActiveApplicationProvider
import stone.user.UserModel
import stone.utils.Stone
import stone.utils.keys.StoneKeyType

class ActivateUsecase(
    private val context: Context,
) {
    fun doActivate(
        appName: String,
        stoneCode: String,
        stoneKeys: List<String>,
        result: MethodChannel.Result
    ) {
        Stone.setAppName(appName)

        if (stoneKeys.isNotEmpty() && stoneKeys.size != 2) {
            result.error("ERROR", "Invalid Stone Keys", "Invalid Stone Keys")
        }

        val stoneKeysHashed: HashMap<StoneKeyType, String> = HashMap<StoneKeyType, String>()
        if(stoneKeys.size == 2){
            stoneKeysHashed[StoneKeyType.QRCODE_AUTHORIZATION] = stoneKeys[0]
            stoneKeysHashed[StoneKeyType.QRCODE_PROVIDERID] = stoneKeys[1]
        }
        
        val userList: List<UserModel>? = StoneStart.init(context, stoneKeysHashed)

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
