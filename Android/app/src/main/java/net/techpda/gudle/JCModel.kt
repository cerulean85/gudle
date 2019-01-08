package net.techpda.gudle

import android.graphics.Color
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.Gson

//enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
//    RED(R.string.one, R.layout.layout_one),
//    BLUE(R.string.two, R.layout.layout_two),
//    GREEN(R.string.three, R.layout.layout_three)
//}


object JCModel {
    var imageSet: ImageArray? = ImageArray()
    var dummySet: AlbumArray? = AlbumArray()
    var marketSet: MovieArray? = MovieArray()
}

data class MovieArray(val list: ArrayList<Movie> = arrayListOf())
data class Movie(var title: String, var year: Int, var image: String, var color: Int, val layoutResId: Int = R.layout.layout_one)
data class Model(val titleResId: Int, val layoutResId: Int)

data class Md(val a:String, val b:String, val c:String)
//data class Person(val list:List<Album>)


data class  AlbumArray ( val list: ArrayList<Album> = arrayListOf() )
data class Album(
        val id: Int, val parent: Int,
        val title: String, val contents: String,
        val type: Int, val order: Int,
        val date: String, val img   : Int,
        val bgColor: Int, val category: Int,
        val commentCount: Int, val viewCount: Int,
        val likeCount: Int, val myComment: Int, val myLike: Int, val layoutResId: Int = R.layout.layout_one)

data class ImageArray( val list: ArrayList<Image> = arrayListOf())
data class Image( val id: Int, val width: Int, val height: Int, val name: String)