package com.walter.reddit_client.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.walter.reddit_client.domain.repository.RedditRepository

class RedditEntriesViewModel(private val repository: RedditRepository) : ViewModel() {

    fun getRedditPosts() = repository.getRedditPostsPagingFlow().cachedIn(viewModelScope)

}
