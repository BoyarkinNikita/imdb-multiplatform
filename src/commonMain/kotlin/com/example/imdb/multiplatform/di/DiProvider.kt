package com.example.imdb.multiplatform.di

import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools

inline fun <reified T> provide(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = KoinPlatformTools.defaultContext().get().get(qualifier, parameters)

inline fun <reified T> inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> = KoinPlatformTools.defaultContext().get().inject(qualifier, mode, parameters)