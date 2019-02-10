package net.techpda.gudle

import com.google.gson.annotations.SerializedName

//enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
//    RED(R.string.one, R.layout.layout_one),
//    BLUE(R.string.two, R.layout.layout_two),
//    GREEN(R.string.three, R.layout.layout_three)
//}


object JCModel {
    var imageSet: ImageArray? = ImageArray()
    var dummySet: AlbumArray? = AlbumArray()
//    var marketSet: MovieArray? = MovieArray()
//    var dataHome: DataHome = DataHome()
    var systemInfo: SystemInfo = SystemInfo()
    var main: Main = Main()

    var course2: ArrayList<Album> = arrayListOf()

    var clipCurrent: Clip = Clip()
    var clipSet: ArrayList<Clip> = arrayListOf()
    var attendanceSet: ArrayList<Attendance> = arrayListOf()

    var mapCourse:MutableMap<String, ArrayList<Album>> = mutableMapOf()

    var collectionCoruseClassifiedByCategory: MutableMap<String, ArrayList<Course>> = mutableMapOf()

    var detailCourse: Detail = Detail()

    var commentSet: ArrayList<Comment> = arrayListOf()
}

data class MovieArray(val list: ArrayList<Movie> = arrayListOf())
data class Movie(var title: String, var year: Int, var image: String, var color: Int, val layoutResId: Int = R.layout.layout_one)
data class Model(val titleResId: Int, val layoutResId: Int)

data class Md(val a:String, val b:String, val c:String)
//data class Person(val list:List<Album>)


//data class SystemInfo(
//        var category: ArrayList<Category> = arrayListOf(),
//        var versionApp: String? = "",
//        var versionMinOS: String? = "",
//        var urlEvent: String? = "",
//
//        var eventNoBard: Int? = 0,
//        var eventNoBoardArticle: Int? = 0,
//        var eventTitle: String? = "",
//        var eventUrlImage: String? = "",
//        var eventNoPopup: Int? = 0
//)

open class Base
{
    @field:SerializedName("is_success") val isSuccess: Boolean = false
    @field:SerializedName("is_open")val isOpen: Boolean = false
}

class SystemInfo: Base() {


    @field:SerializedName("version")
    val version: String = ""
    @field:SerializedName("ios_version")
    val versionIOS: String = ""
    @field:SerializedName("android_version")
    val versionAndroid: String = ""
    @field:SerializedName("event_popup_url")
    val urlEventPopup: String = ""
    @field:SerializedName("android_min_version")
    val versionMinAndroid: String = ""
    @field:SerializedName("ios_min_version")
    val versionMinIOS: String = ""
    @field:SerializedName("close_message")
    val messageClose: String = ""
    @field:SerializedName("data_list")var collectionCategory: ArrayList<Category>? = arrayListOf()

}

class Category(
        @field:SerializedName("category_no") val noCategory: Int,
        @field:SerializedName("title") val title: String
)

//model.dataHome.countBanner = element.asJsonObject.get("banner_count").asInt
//model.dataHome.countCourse = element.asJsonObject.get("total_course_count").asInt
//model.dataHome.isNewAlarm = element.asJsonObject.get("exists_new_alarm").asInt
//
//val d1 = element.asJsonObject.get("data_list1").asJsonArray
//d1.forEach {
//
//    var urlThumbCourseImage = it.asJsonObject.get("course_image_thumbnail_url")
//    model.dataHome.course.add(Album(
//            noCourse = it.asJsonObject.get("course_no").asInt,
//            noContent = it.asJsonObject.get("course_content_no").asInt,
//            title = it.asJsonObject.get("service_title").asString,
//            nameCategory = it.asJsonObject.get("category_title").asString,
//            urlThumb = if(urlThumbCourseImage.isJsonNull) "" else urlThumbCourseImage.asString,
//            countView = it.asJsonObject.get("view_count").asInt)) }
//
//val d2 = element.asJsonObject.get("data_list2").asJsonArray
//d2.forEach {
//    model.dataHome.banner.add(Article(
//            no = it.asJsonObject.get("board_article_no").asInt,
//            noBoard = it.asJsonObject.get("board_no").asInt,
//            title = it.asJsonObject.get("title").asString,
//            urlThumb = it.asJsonObject.get("thumbnail_url").asString))}

class Main: Base() {
    @field:SerializedName("banner_count")
    val countBanner: Int = 0
    @field:SerializedName("total_course_count")
    val countCourse: Int = 0
    @field:SerializedName("exists_new_alarm")
    val isNewAlarm: Int = 0

    @field:SerializedName("data_list1") val collectionCourse: ArrayList<Course>? = null
    @field:SerializedName("data_list2") val collectionBanner: ArrayList<Banner>? = null
}

class Course(
        @field:SerializedName("course_no") val no: Int,
        @field:SerializedName("course_content_no") val noContent: Int,
        @field:SerializedName("service_title") val title: String,
        @field:SerializedName("category_title") val nameCategory: String,
        @field:SerializedName("course_image_thumbnail_url") val urlThumb: String,
        @field:SerializedName("view_count") val countView: Int
)

class Banner(
        @field:SerializedName("board_article_no") val no: Int,
        @field:SerializedName("board_no") val noBoard: Int,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("thumbnail_url") val urlThumb: String
)

//data class DataHome(
//        var banner: ArrayList<Article> = arrayListOf(),
//        var course: ArrayList<Album> =  arrayListOf(),
//
//        var countBanner: Int? = 0,
//        var countCourse: Int? = 0,
//        var isNewAlarm: Int? = 0
//)

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
       var introduce: String = "",
       var flagDelivery: Int = 0
)

data class Clip(
        var noCourse: Int = 0,
        var noContent: Int = 0,
        var title: String = "",
        var titleContent: String = "",
        var timeFinished: Int = 0,
        var order: Int = 0,
        var urlImage01: String = "",
        var urlImage02: String = "",
        var countLike: Int = 0,
        var countView: Int = 0,

        var urlLink: String = "",
        var countReple: Int = 0,
        var favorite: Int = 0,
        var vertical: Int = 0,

        var quiz: Quiz = Quiz()
)

data class Quiz(
        var no: Int = 0,
        var type: Int = 0,
        var text: String = "",
        var urlFile: String = "",
        var score: Int = 0,
        var level: Int = 0,
        var description: String = "",
        var exampleSet: ArrayList<Example> = arrayListOf()
)

data class Example(
        var no: Int = 0,
        var type: Int = 0,
        var order: Int = 0,
        var text: String = "",
        var isAnswer: Int = 0
)

data class Attendance(
        var noContent: Int = 0,
        var codeAttendance: Int = 0
)

data class File(
        var name: String = "",
        var url: String = "",
        var thumbnail: String = ""
)

data class Comment(
        var noBoardArticle: Int = 0,
        var noBoard: Int = 0,
        var nickname: String = "",
        var contents: String = "",
        var countView: Int = 0,
        var countLike: Int = 0,
        var countReple: Int = 0,
        var countReport: Int = 0,
        var dateUpdate: String = "",
        var good: Int = 0,
        var noUser: Int = 0,
        var like: Int = 0,
        var urlImage01: String = "",
        var urlImage02: String = "",
        var title: String = "",

        var file:File? = null
)