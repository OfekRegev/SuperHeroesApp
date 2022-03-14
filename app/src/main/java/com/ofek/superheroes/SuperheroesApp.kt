package com.ofek.superheroes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.plugins.RxJavaPlugins

@HiltAndroidApp
class SuperheroesApp : Application() {


    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }
}