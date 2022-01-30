package com.walter.reddit_client.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.walter.reddit_client.data.local.entity.PostEntity

data class PostRemote(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
) {
    fun transform() = PostEntity(
        id = id,
        title = title
    )
}
