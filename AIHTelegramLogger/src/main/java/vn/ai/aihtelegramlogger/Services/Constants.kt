package vn.ai.aihtelegramlogger.Services

import com.google.gson.annotations.SerializedName

object Constants {
    var chatId: Int? = null
    var teleBotToken :String? = null
    var env:BuildEnv = BuildEnv.NONE
    var userName: String? = "Not yet login"
    var userPhone: String? = ""
    var allowSendMessage:Boolean = false
}

enum class BuildEnv{
    @SerializedName("DEBUG")
    DEBUG,
    @SerializedName("STAGING")
    STAGING,
    @SerializedName("PRODUCTION")
    PRODUCTION,
    @SerializedName("NONE")
    NONE
}