package com.william.template.network

import com.william.template.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author WeiYi Yu
 * @date 2020-07-08
 */
internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}