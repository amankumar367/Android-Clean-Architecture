package com.androchef.mvrx_app.injection.modules

import com.androchef.data.repository.MoviesRemote
import com.androchef.mvrx_app.BuildConfig
import com.androchef.remote.MoviesRemoteImp
import com.androchef.remote.services.MovieServiceFactory
import com.androchef.remote.services.MoviesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideMovieRemote(moviesRemoteImp: MoviesRemoteImp): MoviesRemote {
        return moviesRemoteImp
    }

    @Provides
    @Singleton
    fun provideMovieService(): MoviesService {
        return MovieServiceFactory.create(
            BuildConfig.DEBUG,
            BuildConfig.BASE_URL,
            BuildConfig.TMDB_API_KEY
        )
    }
}