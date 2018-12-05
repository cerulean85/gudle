package net.techpda.gudle

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        var list: ArrayList<Movie> = arrayListOf()
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, resources.getDrawable(R.drawable.p1), Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, resources.getDrawable(R.drawable.p1), Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, resources.getDrawable(R.drawable.p1), Color.GREEN))
        list.add(Movie("Titanic",1997, resources.getDrawable(R.drawable.p1), Color.DKGRAY))
        list.add(Movie("Taxi",1998, resources.getDrawable(R.drawable.p1), Color.MAGENTA))
        list.add(Movie("Inception",1994, resources.getDrawable(R.drawable.p1), Color.WHITE))
        list.add(Movie("The Imitation Game",2014, resources.getDrawable(R.drawable.p1), Color.GREEN))

        verticalLayout {
            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this@MainActivity).orientation))
            rv.adapter = MovieAdapter(list)
        }
    }
}

data class Movie(var title: String, var year: Int, var image: Drawable, var color: Int)
