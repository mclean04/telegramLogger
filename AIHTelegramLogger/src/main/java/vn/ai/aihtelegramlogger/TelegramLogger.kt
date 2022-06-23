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


class TelegramLogger(
    private val teleBotToken: String? = null,
    var chatId: Int? = null,
    var env: BuildEnv? = null
) {

    data class Builder(
        var teleBotToken: String? = null,
        var chatId: Int? = null,
        var env: BuildEnv = BuildEnv.DEBUG
    ) {


        fun teleBotToken(token: String) = apply { this.teleBotToken = token }
        fun chatId(chatId: Int) = apply { this.chatId = chatId }
        fun env(environment: BuildEnv) = apply { this.env = environment }

        fun initialize() {
            if (Constants.teleBotToken == null) {
                Constants.teleBotToken = teleBotToken ?: ""
                Constants.chat_id = chatId?: -1
                Constants.env = env
            }
        }
    }

    companion object {
        private var mInstant: TelegramLogger? = null
        private const val API_ENDPOINT = "https://api.telegram.org/"

        fun getInstance(): TelegramLogger {
            if (mInstant == null) {
                mInstant = TelegramLogger(Constants.teleBotToken,Constants.chat_id,Constants.env)
            }
            return mInstant as TelegramLogger
        }
    }

    fun sendMessage(sendModel: SendMessage) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val today = calendar.time
        val currentDay = sdf.format(today)
        val temp =
            "${sendModel.os} - ${sendModel.appName} - Version:${sendModel.appVersion}] \nEnvironment: [${env?.name}]" +
                    "\n[${currentDay}] - ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}  " +
                    "class: ${sendModel.className} ; fun: ${sendModel.functionName} " +
                    "Error: ${sendModel.errorText}"

        val body = RequestBody.create(null, byteArrayOf())
        val request = Request.Builder()
            .url("${API_ENDPOINT}${teleBotToken}/sendMessage?chat_id=${chatId}&text=${temp}")
            .post(body)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                OkHttpClient.Builder()
                    .build()
                    .newCall(request)
                    .execute()
            } catch (e: Exception) {

            }
        }
    }
}