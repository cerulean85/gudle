package net.techpda.mvvmtestagain2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Movie
import android.databinding.ObservableArrayList
import android.databinding.DataBindingUtil





class MainActivity : AppCompatActivity() {

    private var movieList: ObservableArrayList<Movie>? = null
    private var mAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        mAdapter = MoviesAdapter()
        movieList = ObservableArrayList<Any>()
        binding.recyclerView.setAdapter(mAdapter)
        binding.setMovieList(movieList)

        prepareMovieData()

    }
}
