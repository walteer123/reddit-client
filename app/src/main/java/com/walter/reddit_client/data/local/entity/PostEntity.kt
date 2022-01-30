package com.walter.reddit_client.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.walter.reddit_client.domain.entity.Post

@Entity
data class PostEntity(
    @PrimaryKey val id: String,
    val title: String
) {
    fun transform() = Post(
        title = title
    )
}
