package vn.ai.aihtelegramlogger.Services

import com.google.gson.annotations.SerializedName

object Constants {
    var chat_id: Int? = null
    var teleBotToken :String? = null
    var env:BuildEnv = BuildEnv.NONE
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