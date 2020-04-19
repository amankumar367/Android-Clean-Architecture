package com.androchef.mvrx_app

import android.app.Application
import com.androchef.mvrx_app.injection.AppComponent
import com.androchef.mvrx_app.injection.DaggerAppComponent
import com.facebook.stetho.Stetho

class MainApplication : Application() {

    init {
        instance = this
    }

    var appComponent: AppComponent =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        setDebugLogging()
    }

    private fun setDebugLogging() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        var instance: MainApplication? = null
        fun appComponent(): AppComponent {
            return instance!!.appComponent
        }
    }
}