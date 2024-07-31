package com.testtaskpoq.core.network.di

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main:CoroutineDispatcher
    val default: CoroutineDispatcher
}