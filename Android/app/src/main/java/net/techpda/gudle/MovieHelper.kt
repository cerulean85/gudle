package net.techpda.gudle

import android.graphics.Color

object MovieHelper {

    val KEY_TITLE = "title"
    val KEY_RATING = "rating"
    val KEY_POSTER_URI = "posterUri"
    val KEY_OVERVIEW = "overview"

    fun getMovies(): ArrayList<Movie> {
        var hyoUrl: String = "http://heraldk.com/wp-content/uploads/2018/04/20180407000033_0.jpg"
        var list: ArrayList<Movie> = arrayListOf()
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))

        return list
    }

}