package com.walter.reddit_client.di

import androidx.room.Room
import com.walter.reddit_client.database.RedditDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            RedditDatabase::class.java,
            "RedditClient.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<RedditDatabase>().getPostDao() }
    single { get<RedditDatabase>().getKeysDao() }
}
