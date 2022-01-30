package com.walter.reddit_client.di

import androidx.paging.ExperimentalPagingApi
import com.walter.reddit_client.data.remote.repository.RedditRepositoryImpl
import com.walter.reddit_client.domain.repository.RedditRepository
import org.koin.dsl.module

@ExperimentalPagingApi
val dataModule = module {
    single<RedditRepository> { RedditRepositoryImpl(get()) }
}