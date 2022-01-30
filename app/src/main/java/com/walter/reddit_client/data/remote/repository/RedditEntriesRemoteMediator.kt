package com.walter.reddit_client.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.walter.reddit_client.data.local.dao.PostDao
import com.walter.reddit_client.data.local.dao.RemoteKeyDao
import com.walter.reddit_client.data.local.entity.PostEntity
import com.walter.reddit_client.data.local.entity.RemoteKey
import com.walter.reddit_client.data.remote.service.RedditService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@ExperimentalPagingApi
class RedditEntriesRemoteMediator : RemoteMediator<Int, PostEntity>(), KoinComponent {

    private val service: RedditService by inject()
    private val keyDao: RemoteKeyDao by inject()
    private val repoDao: PostDao by inject()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        return try {
            val loadKey = getKeyPageData(loadType, state)
            val page = when (loadKey) {
                is MediatorResult.Success -> {
                    return loadKey
                }
                else -> {
                    loadKey as RemoteKey?
                }
            }

            val responseData = service.getTopEntries(
                limit = state.config.pageSize,
                next = page?.nextKey,
                before = page?.prevKey
            )
                .responseData

            val posts = responseData.items
                .map { it.data.transform() }

            val endOfList = posts.isEmpty()

            if (loadType == LoadType.REFRESH && shouldDeleteAllData()) {
                keyDao.deleteAll()
                repoDao.deleteAllPosts()
            }

            val remoteKeys = posts.map { post ->
                RemoteKey(
                    postIdHash = post.id.hashCode(),
                    nextKey = responseData.after,
                    prevKey = responseData.before
                )
            }

            keyDao.insertAll(remoteKeys)
            repoDao.insertAllPosts(posts)

            return MediatorResult.Success(endOfPaginationReached = endOfList)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun shouldDeleteAllData() =
        repoDao.getTotalPosts() > 0 && keyDao.getTotalKeys() > 0

    private suspend fun getClosestKey(state: PagingState<Int, PostEntity>): RemoteKey? {
        return state?.anchorPosition?.let {
            state?.closestItemToPosition(it)?.run {
                keyDao.remoteKeysRepoId(id.hashCode())
            }
        }
    }

    private suspend fun getLastKey(state: PagingState<Int, PostEntity>): RemoteKey? {
        state.pages
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.run {
                keyDao.remoteKeysRepoId(id.hashCode())
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, PostEntity>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.run { keyDao.remoteKeysRepoId(id.hashCode()) }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                getClosestKey(state)
            }
            LoadType.APPEND -> {
                return getLastKey(state) ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val prevKey = getFirstRemoteKey(state) ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }
}