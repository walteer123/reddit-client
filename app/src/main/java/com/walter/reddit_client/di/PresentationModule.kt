package com.walter.reddit_client.di

import com.walter.reddit_client.presentation.RedditEntriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {  RedditEntriesViewModel(get()) }
}
