package com.walter.reddit_client.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.walter.reddit_client.data.local.entity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM  RemoteKey WHERE postIdHash = :postHash")
    suspend fun remoteKeysRepoId(postHash: Int): RemoteKey?

    @Query("DELETE FROM remotekey")
    suspend fun deleteAll()

    @Query("SELECT COUNT() FROM RemoteKey")
    suspend fun getTotalKeys(): Int
}
