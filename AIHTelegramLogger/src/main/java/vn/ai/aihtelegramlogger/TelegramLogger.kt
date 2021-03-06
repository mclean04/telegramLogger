package vn.ai.aihtelegramlogger

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import vn.ai.aihtelegramlogger.Services.BuildEnv
import vn.ai.aihtelegramlogger.Services.Constants
import vn.ai.aihtelegramlogger.Services.SendMessage
import java.text.SimpleDateFormat
import java.util.*


object TelegramLogger {

    private const val API_ENDPOINT = "https://api.telegram.org/"
    data class Builder(
        var teleBotToken: String,
        var chatId: Int,
        var userName: String,
        var userPhone: String,
        var allowSendMessage:Boolean,
        var env: BuildEnv = BuildEnv.DEBUG,
    ) {
        fun initialize() {
            if (Constants.teleBotToken == null) {
                Constants.teleBotToken = teleBotToken
                Constants.chatId = chatId
                Constants.env = env
                Constants.userPhone = userPhone
                Constants.userName = userName
                Constants.allowSendMessage = allowSendMessage
            }
        }
    }

    fun sendMessage(sendModel: SendMessage) {
        if (Constants.allowSendMessage) {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val today = calendar.time
            val currentDay = sdf.format(today)
            val temp =
                        "${sendModel.os} - ${sendModel.appName} - Version:${sendModel.appVersion}] " +
                        "\nEnvironment: [${Constants.env.name}]" +
                        "\n[${currentDay}]-${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}  " +
                        "\nUser: ${Constants.userName}, user phone: ${Constants.userPhone}" +
                        "\nclass: ${sendModel.className} ; fun: ${sendModel.functionName} " +
                        "\nError: ${sendModel.errorText}"

            val body = RequestBody.create(null, byteArrayOf())
            val request = Request.Builder()
                .url("${API_ENDPOINT}${Constants.teleBotToken}/sendMessage?chat_id=${Constants.chatId}&text=${temp}")
                .post(body)
                .build()

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    OkHttpClient.Builder()
                        .build()
                        .newCall(request)
                        .execute()
                } catch (e: Exception) {}
            }
        }
    }
}