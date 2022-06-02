package vn.ai.aihtelegramlogger.Services

import okhttp3.Request
import okhttp3.Response

class HttpErrorException(val request: Request?, val response: String?)