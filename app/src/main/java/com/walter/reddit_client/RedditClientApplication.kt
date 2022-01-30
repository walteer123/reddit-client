package com.walter.reddit_client

import android.app.Application
import androidx.paging.ExperimentalPagingApi

@ExperimentalPagingApi
class RedditClientApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        start(this)
    }

}