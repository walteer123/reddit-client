package com.walter.reddit_client.domain.repository

import androidx.paging.PagingData
import com.walter.reddit_client.data.local.dao.PostDao
import com.walter.reddit_client.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface RedditRepository {
    fun getRedditPostsPagingFlow(): Flow<PagingData<Post>>
}
