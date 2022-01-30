package com.walter.reddit_client.network

interface ServiceFactory {
    fun <T> create(service: Class<T>): T
}
