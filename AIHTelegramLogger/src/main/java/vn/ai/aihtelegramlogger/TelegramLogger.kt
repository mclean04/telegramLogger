package vn.ai.aihtelegramlogger


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import vn.ai.aihtelegramlogger.Services.BuildEnv
import vn.ai.aihtelegramlogger.Services.Constants
import vn.ai.aihtelegramlogger.Services.SendMessage
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class TelegramLogger(
    private val activity: AppCompatActivity,
    private val teleBotToken: String,
    private val chat_Id: Int,
    private val env: BuildEnv
) {

    init {
        initConstant()
    }

    private val viewModel: TelegramViewModel by lazy {
        ViewModelProvider(activity)[TelegramViewModel::class.java]
    }

    private fun initConstant() {
        Constants.env = env
        Constants.chat_id = chat_Id
        Constants.teleBotToken = teleBotToken
    }

    fun sendMessage(sendModel: SendMessage) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val today = calendar.time
        val currentDay = sdf.format(today)
        val temp =
            "${sendModel.os} - ${sendModel.appName} - Version:${sendModel.appVersion}] \nEnvironment: [${sendModel.env.name}]" +
                    "\n[${currentDay}] - ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}  " +
                    "class: ${sendModel.className} ; fun: ${sendModel.functionName} " +
                    " Error: ${sendModel.errorText}"
        viewModel.sendMessage(temp)
    }
}