package com.walter.reddit_client.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RedditListingResponse<T>(
    @SerializedName("after") val after: String,
    @SerializedName("dist") val dist: Int,
    @SerializedName("children") val items: List<RedditChildrenItems<T>>
)
