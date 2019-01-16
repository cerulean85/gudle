package net.techpda.gudle

import android.graphics.Color

object MovieHelper {

    val KEY_NAME_CATEGORY = "전체"
    val KEY_NO_CATEGORY = "no_category"
    val KEY_TITLE = "title"
    val KEY_RATING = "rating"
    val KEY_POSTER_URI = "posterUri"
    val KEY_OVERVIEW = "overview"

    fun getMovies(): ArrayList<Movie> {
//        var hyoUrl: String = "http://heraldk.com/wp-content/uploads/2018/04/20180407000033_0.jpg"
        var hyoUrl: String = "http://favorite.cafe24app.com/img/img0"
        var list: ArrayList<Movie> = arrayListOf()

        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"16.jpg", Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"17.jpg", Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl+"18.jpg", Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl+"19.jpg", Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl+"20.jpg", Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl+"21.jpg", Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl+"22.jpg", Color.GREEN))
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"22.jpg", Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"23.jpg", Color.LTGRAY))

        return list
    }

}