package com.example.error
import retrofit2.Response

sealed class UrlError(url: String) : Throwable(url)

class HttpError(val code: Int, url: String) : UrlError(url)

object ResponseHandler {
    fun <T> handleResponse(response: Response<T>): Throwable {
        val code = response.code()
        val url = response.raw().request().url().toString()
        return HttpError(code, url)
    }
}