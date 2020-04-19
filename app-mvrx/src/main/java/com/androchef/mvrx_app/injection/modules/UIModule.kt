package com.androchef.mvrx_app.injection.modules

import com.androchef.cleanarc.UiThread
import com.androchef.domain.executor.PostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UIModule {

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread) : PostExecutionThread {
        return uiThread
    }
}