package vn.ai.aihtelegramlogger


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import vn.ai.aihtelegramlogger.Services.BuildEnv
import vn.ai.aihtelegramlogger.Services.Constants
import vn.ai.aihtelegramlogger.Services.SendMessage
import java.text.SimpleDateFormat
import java.util.*


object TelegramLogger {
    private lateinit var activity: AppCompatActivity

    private val viewModel :TelegramViewModel by lazy {
        ViewModelProvider(activity)[TelegramViewModel::class.java]
    }

    fun initTelegramLogger(activity: AppCompatActivity, teleBotToken: String, chat_Id: Int, env: BuildEnv){
        this.activity = activity
        Constants.teleBotToken = teleBotToken
        Constants.env = env
        Constants.chat_id = chat_Id
    }

    fun sendMessage(sendModel: SendMessage) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val today = calendar.time
        val currentDay = sdf.format(today)
        val temp = "${sendModel.os} - ${sendModel.appName} - Version:${sendModel.appVersion}] \nEnvironment: [${sendModel.env.name}]" +
                "\n[${currentDay}] - ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}  error: ${sendModel.errorText} "
        viewModel.sendMessage(temp)
    }
}