package com.androchef.mvrx_app.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androchef.mvrx_app.R
import com.androchef.mvrx_app.ui.movielist.MovieListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            MovieListActivity.start(
                this
            )
            finish()
        },2000)
    }
}
