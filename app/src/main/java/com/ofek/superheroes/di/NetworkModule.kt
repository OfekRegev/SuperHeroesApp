package com.ofek.superheroes.di

import android.content.Context
import com.ofek.superheroes.common.SetCacheExpirationInterceptor
import com.ofek.superheroes.network.SuperHeroesService
import com.ofek.superheroes.searchscreen.network.NetworkConstants
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun getPicasso(
        @ApplicationContext context: Context,
    ): Picasso {
        // setting a custom cache to allow expiration of 1 day
        val client = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir,NetworkConstants.picasso_cache_limit_bytes))
            .addInterceptor(SetCacheExpirationInterceptor(NetworkConstants.picasso_cache_expiration_milliseconds))
            .build()
        return Picasso.Builder(context)
            .downloader(
                OkHttp3Downloader(client)
            )
            .build()
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            .build()
    }

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Provides
    fun getSuperHeroesService(retrofit: Retrofit.Builder): SuperHeroesService {
        return retrofit.baseUrl(NetworkConstants.superheroes_api_base_url)
            .build()
            .create(SuperHeroesService::class.java)
    }
}