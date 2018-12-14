package net.techpda.gudle

//enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
//    RED(R.string.one, R.layout.layout_one),
//    BLUE(R.string.two, R.layout.layout_two),
//    GREEN(R.string.three, R.layout.layout_three)
//}

data class Movie(var title: String, var year: Int, var image: String, var color: Int)
data class Model(val titleResId: Int, val layoutResId: Int)
data class Md(val a:String, val b:String, val c:String)
//data class Person(val list:List<Album>)


data class  AlbumArray ( val list: ArrayList<Album> = arrayListOf() )
data class Album(
        val id: Int, val parent: Int,
        val title: String, val contents: String,
        val type: Int, val order: Int,
        val date: String, val img: Int,
        val bgColor: Int, val category: Int,
        val commentCount: Int, val viewCount: Int,
        val likeCount: Int, val myComment: Int, val myLike: Int)

data class ImageArray( val list: ArrayList<Image> = arrayListOf())
data class Image( val id: Int, val width: Int, val height: Int, val name: String)
