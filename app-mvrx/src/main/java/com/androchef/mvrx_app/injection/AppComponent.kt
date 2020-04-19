package com.androchef.mvrx_app.injection

import android.app.Application
import com.androchef.mvrx_app.injection.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, CacheModule::class, DomainModule::class, RemoteModule::class, UIModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}