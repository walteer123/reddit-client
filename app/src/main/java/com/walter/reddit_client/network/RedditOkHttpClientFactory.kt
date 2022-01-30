package com.walter.reddit_client.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RedditOkHttpClientFactory : OkHttpClientFactory {
    override fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .header("Accept", "application/vnd.github.v3+json")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }
}
