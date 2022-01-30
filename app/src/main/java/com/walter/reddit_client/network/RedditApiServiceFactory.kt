package com.walter.reddit_client.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RedditApiServiceFactory(
    okHttpClientFactory: OkHttpClientFactory,
) : ServiceFactory {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://reddit.com/")
        .client(okHttpClientFactory.create())
        .build()

    override fun <T> create(service: Class<T>): T = retrofit.create(service)
}