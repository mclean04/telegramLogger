package vn.ai.aihtelegramlogger.Services

import com.google.gson.annotations.SerializedName

object Constants {
    var chat_id: Int = 0
    var teleBotToken = ""
    var env:BuildEnv = BuildEnv.DEBUG
}

enum class BuildEnv{
    @SerializedName("DEBUG")
    DEBUG,
    @SerializedName("STAGING")
    STAGING,
    @SerializedName("PRODUCTION")
    PRODUCTION
}