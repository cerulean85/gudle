package net.techpda.gudle

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.Gson

import com.squareup.picasso.Picasso
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.verticalLayout

class MovieFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {

        return UI {

            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this.ctx)
            rv.addItemDecoration(DividerItemDecoration(this.ctx, LinearLayoutManager(this.ctx).orientation))
            rv.adapter = AlbumAdapter(this.ctx, JCModel.dummySet!!.list, JCModel.imageSet!!.list)

        }.view

        // Creates the view controlled by the fragment
//        val view = inflater.inflate(R.layout.fragment_movie, container, false)
//        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
//        val ratingTextView = view.findViewById<TextView>(R.id.ratingTextView)
//        val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)
//        val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)
//
//        // Retrieve and display the movie data from the Bundle
//        val args = arguments
//        titleTextView.text = args?.getString(MovieHelper.KEY_TITLE)
//        ratingTextView.text = String.format("%d/10", args?.getInt(MovieHelper.KEY_RATING))
//        overviewTextView.text = args?.getString(MovieHelper.KEY_OVERVIEW)
//
//        // Download the image and display it using Picasso
//        Picasso.get()
//                .load(args?.getString(MovieHelper.KEY_POSTER_URI))
//                .placeholder(R.drawable.p1)
//                .error(R.drawable.ic_launcher_background)
//                .into(posterImageView)

//        return view
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(movie: Movie): MovieFragment {

            // Store the movie data in a Bundle object
            val args = Bundle()
            args.putString(MovieHelper.KEY_TITLE, movie.title)
            args.putInt(MovieHelper.KEY_RATING, movie.year)
            args.putString(MovieHelper.KEY_POSTER_URI, movie.image)
            args.putString(MovieHelper.KEY_OVERVIEW, movie.title)

            // Create a new MovieFragment and set the Bundle as the arguments
            // to be retrieved and displayed when the view is created
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
