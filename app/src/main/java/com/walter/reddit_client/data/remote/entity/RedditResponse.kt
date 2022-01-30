package com.walter.reddit_client.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RedditResponse<T>(
    @SerializedName("kind") val responseKind: String,
    @SerializedName("data") val responseData: T
)
