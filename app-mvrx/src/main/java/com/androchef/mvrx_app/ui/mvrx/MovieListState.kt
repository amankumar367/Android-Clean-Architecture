package com.androchef.mvrx_app.ui.mvrx

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.androchef.domain.models.movies.Movie
import com.androchef.presentation.movielist.models.MovieView

data class MovieListState(
    val listOfMovies: Async<List<MovieView>> = Uninitialized,
    val onBookmarkChange : Boolean = false
) : MvRxState