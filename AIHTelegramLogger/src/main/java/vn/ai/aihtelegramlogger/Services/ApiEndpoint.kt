package vn.ai.aihtelegramlogger.Services

import io.reactivex.Maybe
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    @POST("/{token}/sendMessage")
    fun sendMessage(
        @Path("token") bot:String = Constants.teleBotToken,
        @Query("chat_id") id:Int = Constants.chat_id,
        @Query("text") message:String
    ) :Maybe<ResponseModel>
}