package com.androchef.mvrx_app.injection.assisted

import com.androchef.mvrx_app.injection.ViewModelKey
import com.androchef.mvrx_app.ui.mvrx.MovieListViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AssistedInject_MovieListAssistedModule::class])
@AssistedModule
abstract class MovieListAssistedModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun helloViewModelFactory(factory: MovieListViewModel.Factory): AssistedViewModelFactory<*, *>
}