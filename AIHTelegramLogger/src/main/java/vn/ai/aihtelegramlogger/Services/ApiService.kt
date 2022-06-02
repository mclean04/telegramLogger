package vn.ai.aihtelegramlogger.Services

import android.content.Context
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    private var mInstance: ApiEndpoint? = null

    private const val API_ENDPOINT = "https://api.telegram.org/"

    fun getInstance(context: Context): ApiEndpoint {
        if (mInstance == null) {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(1, TimeUnit.MINUTES)
            httpClient.readTimeout(1, TimeUnit.MINUTES)

            if (Constants.env == BuildEnv.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                //.addConverterFactory(ApiConverterFactory(Gson()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build()

            mInstance = retrofit.create(ApiEndpoint::class.java)
        }
        return mInstance as ApiEndpoint
    }
}