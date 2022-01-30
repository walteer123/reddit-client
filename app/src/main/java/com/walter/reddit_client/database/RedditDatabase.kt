package com.walter.reddit_client.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.walter.reddit_client.data.local.dao.PostDao
import com.walter.reddit_client.data.local.dao.RemoteKeyDao
import com.walter.reddit_client.data.local.entity.PostEntity
import com.walter.reddit_client.data.local.entity.RemoteKey


@Database(version = 2, entities = [PostEntity::class, RemoteKey::class])
abstract class RedditDatabase: RoomDatabase() {
    abstract fun getPostDao(): PostDao
    abstract fun getKeysDao(): RemoteKeyDao
}