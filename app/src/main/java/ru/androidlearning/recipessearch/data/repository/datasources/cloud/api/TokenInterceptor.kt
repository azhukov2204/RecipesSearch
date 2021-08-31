package ru.androidlearning.recipessearch.data.repository.datasources.cloud.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.androidlearning.recipessearch.BuildConfig

object TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            request
                .url
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.SPOONACULAR_API_KEY)
                .build()
                .let { httpUrl ->
                    return chain.proceed(
                        request
                            .newBuilder()
                            .url(httpUrl)
                            .build()
                    )
                }
        }
    }
}
