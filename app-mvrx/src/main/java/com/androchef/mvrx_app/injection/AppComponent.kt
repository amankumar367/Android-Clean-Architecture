package com.androchef.mvrx_app.injection

import android.app.Application
import com.androchef.mvrx_app.base.BaseViewModel
import com.androchef.mvrx_app.injection.assisted.AssistedViewModelFactory
import com.androchef.mvrx_app.injection.assisted.MovieListAssistedModule
import com.androchef.mvrx_app.injection.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CacheModule::class,
        DomainModule::class,
        RemoteModule::class,
        UIModule::class,
        MovieListAssistedModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun viewModelFactories(): Map<Class<out BaseViewModel<*>>, AssistedViewModelFactory<*, *>>

}