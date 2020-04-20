package com.androchef.mvrx_app.ui.movielist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androchef.mvrx_app.R

class MovieListActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MovieListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        loadMovieListFragment()
    }

    private fun loadMovieListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,MovieListFragment())
            .commit()
    }

}
