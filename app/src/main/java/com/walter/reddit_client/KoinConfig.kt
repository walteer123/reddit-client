package com.walter.reddit_client

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.walter.reddit_client.di.dataModule
import com.walter.reddit_client.di.databaseModule
import com.walter.reddit_client.di.networkModule
import com.walter.reddit_client.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

@ExperimentalPagingApi
fun start(myApplication: Application) {
    startKoin { androidContext(myApplication) }
    injectModules()
}

@ExperimentalPagingApi
fun injectModules() = loadKoinModules

@ExperimentalPagingApi
private val loadKoinModules by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            databaseModule,
            dataModule,
            presentationModule
        )
    )
}