package com.walter.reddit_client.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.walter.reddit_client.databinding.RedditEntriesItemBinding
import com.walter.reddit_client.domain.entity.Post

class RedditEntriesAdapter :
    PagingDataAdapter<Post, RedditEntriesAdapter.RedditEntriesViewHolder>(
        DiffCallBack
    ) {

    object DiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedditEntriesViewHolder {
        return RedditEntriesViewHolder(
            RedditEntriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RedditEntriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class RedditEntriesViewHolder(private val binding: RedditEntriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.entryTitle.text = post.title
        }
    }
}