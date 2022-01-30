package com.walter.reddit_client.data.remote.service

import com.walter.reddit_client.data.remote.entity.PostRemote
import com.walter.reddit_client.data.remote.entity.RedditListingResponse
import com.walter.reddit_client.data.remote.entity.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("top.json")
    suspend fun getTopEntries(
        @Query("limit") limit: Int,
        @Query("after") next: String?,
        @Query("before") before: String?,
    ): RedditResponse<RedditListingResponse<PostRemote>>
}
