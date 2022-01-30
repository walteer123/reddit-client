package com.walter.reddit_client.di

import com.walter.reddit_client.data.remote.service.RedditService
import com.walter.reddit_client.network.OkHttpClientFactory
import com.walter.reddit_client.network.RedditApiServiceFactory
import com.walter.reddit_client.network.RedditOkHttpClientFactory
import org.koin.dsl.module

val networkModule = module {
    factory <OkHttpClientFactory>{ RedditOkHttpClientFactory() }
    single { RedditApiServiceFactory(get()) }
    single { get<RedditApiServiceFactory>().create(RedditService::class.java) }
}
