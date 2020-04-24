package com.androchef.mvrx_app.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.androchef.mvrx_app.R
import com.androchef.mvrx_app.extension.gone
import com.androchef.mvrx_app.extension.toast
import com.androchef.mvrx_app.extension.visible
import com.androchef.mvrx_app.ui.movielist.adapter.MovieListAdapter
import com.androchef.mvrx_app.ui.mvrx.MovieListViewModel
import com.androchef.presentation.movielist.models.MovieView
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import kotlinx.android.synthetic.main.layout_error_state.view.*
import javax.inject.Inject

class MovieListFragment : BaseMvRxFragment(), MovieListAdapter.OnBookmarkClickedListener {

    @Inject
    lateinit var movieListViewModelFactory: MovieListViewModel.Factory

    private val movieListViewModel: MovieListViewModel by fragmentViewModel()

    private var movieListAdapter: MovieListAdapter? = null

    private lateinit var mView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_movie_list, container, false)
        initRecyclerView()
        onClicks()
        return mView.rootView
    }

    private fun initRecyclerView() {
        movieListAdapter = MovieListAdapter()
        movieListAdapter?.setBookMarkChangeListener(this)
        mView.movieListRecyclerView.layoutManager = LinearLayoutManager(context)
        mView.movieListRecyclerView.adapter = movieListAdapter
    }


    private fun onClicks() {
        mView.layoutError.btnRetryButton.setOnClickListener {
            fetchMovies()
        }

        mView.rootRecyclerView.setOnRefreshListener {
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        movieListViewModel.fetchMovies()
    }

    override fun invalidate() {
        withState(movieListViewModel) {
            when (it.listOfMovies) {
                Uninitialized -> fetchMovies()
                is Loading -> loadingState()
                is Success -> successState(it.listOfMovies)
                is Fail -> showErrorLayout(
                    it.listOfMovies.error.localizedMessage ?: "Some Error Came"
                )
            }

            when {
                it.onBookmarkChange -> {
                    toast("bookmark updated")
                    fetchMovies()
                }
            }
        }
    }

    private fun successState(listOfMovies: Success<List<MovieView>>) {
        hideLoading()
        movieListAdapter?.refreshList(listOfMovies.invoke())
        mView.layoutError.gone()
        mView.movieListRecyclerView.visible()
    }

    private fun loadingState() {
        mView.rootRecyclerView.isRefreshing = true
        mView.movieListRecyclerView.gone()
        mView.layoutError.gone()
    }

    private fun hideLoading() {
        mView.rootRecyclerView.isRefreshing = false
    }

    private fun showErrorLayout(errorMessage: String) {
        hideLoading()
        mView.movieListRecyclerView.gone()
        mView.layoutError.tvErrorMessage.text = errorMessage
        mView.layoutError.visible()
    }

    override fun onBookmarkChanged(movieView: MovieView, isBookMarked: Boolean) {
        movieListViewModel.onBookmarkStatusChanged(movieView, isBookMarked)
    }
}
