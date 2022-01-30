package com.walter.reddit_client.network

import okhttp3.OkHttpClient

interface OkHttpClientFactory {
    fun create(): OkHttpClient
}
