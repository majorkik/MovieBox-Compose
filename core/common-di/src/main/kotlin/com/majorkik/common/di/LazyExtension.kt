package com.majorkik.common.di

fun <T> uiLazy(block: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, block)
