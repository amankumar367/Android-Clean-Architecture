package com.androchef.mvrx_app.ui.mvrx

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.androchef.domain.models.movies.Movie

data class MovieListState(
    var listOfMovies: Async<List<Movie>> = Uninitialized
) : MvRxState