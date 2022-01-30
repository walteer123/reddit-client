package com.walter.reddit_client.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.walter.reddit_client.data.local.dao.PostDao
import com.walter.reddit_client.domain.entity.Post
import com.walter.reddit_client.domain.repository.RedditRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RedditRepositoryImpl(private val redditPostsDao: PostDao) : RedditRepository {

    override fun getRedditPostsPagingFlow(): Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 50,
            prefetchDistance = 10
        ),
        remoteMediator = RedditEntriesRemoteMediator()
    ) {
        redditPostsDao.getAllPosts()
    }.flow.map { pagingData ->
        pagingData.map { it.transform() }
    }
}
