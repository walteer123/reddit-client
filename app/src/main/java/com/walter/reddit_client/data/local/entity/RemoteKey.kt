package com.walter.reddit_client.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKey(
    @PrimaryKey val postIdHash: Int,
    val prevKey: String?,
    val nextKey: String?
)
