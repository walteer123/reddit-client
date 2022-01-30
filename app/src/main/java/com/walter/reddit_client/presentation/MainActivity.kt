package com.walter.reddit_client.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.walter.reddit_client.databinding.ActivityMainBinding
import com.walter.reddit_client.presentation.adapter.RedditEntriesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: RedditEntriesViewModel by viewModel()
    private val listAdapter  by lazy { RedditEntriesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.entriesRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        lifecycleScope.launch {
            viewModel.getRedditPosts().collectLatest { pagingData ->
                listAdapter.submitData(pagingData)
            }
        }

        setContentView(binding.root)
    }
}
