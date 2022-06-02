package vn.ai.aihtelegramlogger.Services

import java.util.*

data class ResponseModel (
    val ok:Boolean,
    val result: ResultModel,
    val from: From,
    val chat: Chat,
    val date:Int,
    val text:String
)

data class ResultModel(
    val message_id:Int,
)

data class From(
    val id:Int,
    val is_bot:Boolean,
    val first_name:String,
    val userName:String,
)

data class Chat(
    val id:Int,
    val title:String,
    val type:String,
    val all_members_are_administrators:Boolean,
)

data class SendMessage(
    val env: BuildEnv = BuildEnv.DEBUG,
    val currentTime: Calendar? = null,
    val functionName:String?,
    val className:String?,
    val os:String = "Android",
    val appName:String?,
    val appVersion:String?,
    val errorText:String?
)