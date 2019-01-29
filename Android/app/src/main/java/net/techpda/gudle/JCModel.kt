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
//    var marketSet: MovieArray? = MovieArray()
    var dataHome: DataHome = DataHome()
    var systemInfo: SystemInfo = SystemInfo()

    var course2: ArrayList<Album> = arrayListOf()

    var mapCourse:MutableMap<String, ArrayList<Album>> = mutableMapOf()

    var detailCourse: Detail = Detail()
}

data class MovieArray(val list: ArrayList<Movie> = arrayListOf())
data class Movie(var title: String, var year: Int, var image: String, var color: Int, val layoutResId: Int = R.layout.layout_one)
data class Model(val titleResId: Int, val layoutResId: Int)

data class Md(val a:String, val b:String, val c:String)
//data class Person(val list:List<Album>)


data class SystemInfo(
        var category: ArrayList<Category> = arrayListOf(),
        var versionApp: String? = "",
        var versionMinOS: String? = "",
        var urlEvent: String? = "",

        var eventNoBard: Int? = 0,
        var eventNoBoardArticle: Int? = 0,
        var eventTitle: String? = "",
        var eventUrlImage: String? = "",
        var eventNoPopup: Int? = 0
)

data class DataHome(
        var banner: ArrayList<Article> = arrayListOf(),
        var course: ArrayList<Album> =  arrayListOf(),

        var countBanner: Int? = 0,
        var countCourse: Int? = 0,
        var isNewAlarm: Int? = 0
)

data class  AlbumArray ( val list: ArrayList<Album> = arrayListOf() )
data class Album(
        var id: Int? = 0,
        var parent: Int? = 0,
        var title: String? = "",
        var contents: String? = "",
        var type: Int? = 0,
        var order: Int? = 0,
        var date: String? = "",
        var img   : Int? = 0,
        var bgColor: Int? = 0,
        var category: Int? = 0,
        var commentCount: Int? = 0,
        var viewCount: Int? = 0,
        var likeCount: Int? = 0,
        var myComment: Int? = 0,
        var myLike: Int? = 0,
        var layoutResId: Int? = R.layout.layout_one,


        var noCourse: Int? = 0,
        var noContent: Int? = 0,

        var dateStart: String? = "",
        var dateEnd: String? = "",
        var nameCategory: String? = "",
        var urlThumb: String? = "",
        var countView: Int? = 0,

        var visible: Boolean = true

)

data class Category(
        var noCategory: Int? = 0,
        var title: String? = ""
)

data class ImageArray( val list: ArrayList<Image> = arrayListOf())
data class Image( val id: Int, val width: Int, val height: Int, val name: String)

data class Article(
    var no: Int? = 0,
    var noBoard: Int? = 0,
    var title: String? = "",
    var urlFile: String? = "",
    var urlThumb: String? = ""
)

data class Detail(
       var noCourse: Int = 0,
       var title: String? = "",
       var noCourseContent: Int = 0,
       var studyGoal: String = "",
       var studyTarget: String = "",
       var studyRef: String = "",
       var studyComplete: String = "",
       var teacherInfo: String = "",
       var studyNcs: String = "",
       var urlImage01: String = "",
       var urlImage02: String = "",
       var urlImage03: String = "",
       var countView: Int = 0,
       var description: String = "",
       var introduce: String = ""
)