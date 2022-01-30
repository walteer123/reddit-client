package com.walter.reddit_client.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.walter.reddit_client.data.local.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity")
    fun getAllPosts(): PagingSource<Int, PostEntity>

    @Query("DELETE FROM PostEntity")
    suspend fun deleteAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(repos: List<PostEntity>)

    @Query("SELECT COUNT() FROM PostEntity")
    suspend fun getTotalPosts(): Int
}
