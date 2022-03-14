package com.ofek.superheroes.common

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


// an interceptor that limits caching to a certain amount of time
class SetCacheExpirationInterceptor(
    private val expirationLengthMilliseconds: Int
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .cacheControl(
                    CacheControl.Builder()
                        .maxStale(expirationLengthMilliseconds, TimeUnit.MILLISECONDS)
                        .build()
                ).build()
        )
    }
}