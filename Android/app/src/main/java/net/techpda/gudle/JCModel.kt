package net.techpda.gudle

//enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
//    RED(R.string.one, R.layout.layout_one),
//    BLUE(R.string.two, R.layout.layout_two),
//    GREEN(R.string.three, R.layout.layout_three)
//}

data class Movie(var title: String, var year: Int, var image: String, var color: Int)
data class Model(val titleResId: Int, val layoutResId: Int)