package com.walter.reddit_client.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RedditChildrenItems<T>(
    @SerializedName("kind") val kind: String,
    @SerializedName("data") val data: T
)