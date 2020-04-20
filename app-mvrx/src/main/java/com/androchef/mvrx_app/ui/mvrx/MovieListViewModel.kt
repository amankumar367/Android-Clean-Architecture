package com.androchef.mvrx_app.ui.mvrx

import com.airbnb.mvrx.*
import com.androchef.domain.interactor.movielist.BookmarkMovieUseCase
import com.androchef.domain.interactor.movielist.GetMovieListUseCase
import com.androchef.domain.interactor.movielist.UnBookmarkMovieUseCase
import com.androchef.domain.models.movies.Movie
import com.androchef.mvrx_app.base.BaseViewModel
import com.androchef.mvrx_app.injection.assisted.AssistedViewModelFactory
import com.androchef.mvrx_app.injection.assisted.DaggerMvRxViewModelFactory
import com.androchef.presentation.movielist.mapper.MovieMapper
import com.androchef.presentation.movielist.models.MovieView
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.lang.Error

class MovieListViewModel @AssistedInject constructor(
    @Assisted state: MovieListState,
    private val movieMapper: MovieMapper,
    private val getMovieListUseCase: GetMovieListUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
    private val unBookmarkMovieUseCase: UnBookmarkMovieUseCase
) : BaseViewModel<MovieListState>(state) {

    fun fetchMovies() {
        setState { MovieListState(Loading()) }
        val singleObserver = object : DisposableSingleObserver<List<Movie>>() {
            override fun onSuccess(t: List<Movie>) {
                setState {
                    copy(listOfMovies = Success(t.map { movieMapper.mapToView(it) }))
                }
            }

            override fun onError(e: Throwable) {
                setState {
                    copy(listOfMovies = Fail(e))
                }
            }
        }
        getMovieListUseCase.execute(singleObserver)
    }

    fun onBookmarkStatusChanged(movieView: MovieView) {
        val singleObserver = object : DisposableCompletableObserver() {

            override fun onComplete() {
                setState {
                    copy(onBookmarkChange = true)
                }
            }

            override fun onError(e: Throwable) {
                setState {
                    copy(onBookmarkChange = false)
                }
            }
        }
        
        if (movieView.isBookMarked)
            bookmarkMovieUseCase.execute(singleObserver, movieView.id)
        else
            unBookmarkMovieUseCase.execute(singleObserver, movieView.id)
    }


    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<MovieListViewModel, MovieListState> {
        override fun create(state: MovieListState): MovieListViewModel
    }

    companion object :
        DaggerMvRxViewModelFactory<MovieListViewModel, MovieListState>(MovieListViewModel::class.java)
}