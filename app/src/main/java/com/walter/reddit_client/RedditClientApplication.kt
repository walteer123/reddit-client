package com.walter.reddit_client

import android.app.Application

class RedditClientApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        start(this)
    }

}