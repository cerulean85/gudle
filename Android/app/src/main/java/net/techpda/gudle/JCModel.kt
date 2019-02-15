package net.techpda.gudle

import com.google.gson.annotations.SerializedName
import kotlin.properties.Delegates

//enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
//    RED(R.string.one, R.layout.layout_one),
//    BLUE(R.string.two, R.layout.layout_two),
//    GREEN(R.string.three, R.layout.layout_three)
//}

object JCModel {
    var systemInfo: SystemInfo = SystemInfo()
    var main: Main = Main()
    var detailCourse: Course = Course()
    var clipCurrent: Clip = Clip()
    var clipSet: ClipSet = ClipSet()
    var collectionCoruseClassifiedByCategory: MutableMap<String, ArrayList<Course>> = mutableMapOf()
    var commentSet: CommentSet = CommentSet()

    var scrolledHome: Boolean by Delegates.observable(false) { _, oldValue, newValue ->
        onScrolledHomeChanged?.invoke(oldValue, newValue)
    }

    var onScrolledHomeChanged: ((Boolean, Boolean) -> Unit)? = null
}

open class Base
{
    @field:SerializedName("is_success") val isSuccess: Boolean = false
    @field:SerializedName("is_open")val isOpen: Boolean = false
}

class SystemInfo: Base() {
    @field:SerializedName("version") val version: String = ""
    @field:SerializedName("ios_version") val versionIOS: String = ""
    @field:SerializedName("android_version") val versionAndroid: String = ""
    @field:SerializedName("event_popup_url") val urlEventPopup: String = ""
    @field:SerializedName("android_min_version") val versionMinAndroid: String = ""
    @field:SerializedName("ios_min_version") val versionMinIOS: String = ""
    @field:SerializedName("close_message") val messageClose: String = ""
    @field:SerializedName("data_list")var collectionCategory: ArrayList<Category>? = arrayListOf()
}

class Category(
        @field:SerializedName("category_no") val noCategory: Int,
        @field:SerializedName("title") val title: String
)

class Main: Base() {
    @field:SerializedName("banner_count") var countBanner: Int = 0
    @field:SerializedName("total_course_count") var countCourse: Int = 0
    @field:SerializedName("exists_new_alarm") var isNewAlarm: Int = 0
    @field:SerializedName("data_list1") var collectionCourse: ArrayList<Course>? = arrayListOf()
    @field:SerializedName("data_list2") var collectionBanner: ArrayList<Banner>? = arrayListOf()
}

class Course: Base() {
    @field:SerializedName("course_no") var no: Int = 0
    @field:SerializedName("course_content_no") var noContent: Int = 0
    @field:SerializedName("service_title") var title: String = ""
    @field:SerializedName("category_title") var nameCategory: String = ""
    @field:SerializedName("course_image_thumbnail_url") var urlThumb: String = ""
    @field:SerializedName("view_count") var countView: Int = 0
    @field:SerializedName("course_info_image_url") var urlImageInfo: String = ""
    @field:SerializedName("course_image_url") var urlImage: String = ""
    @field:SerializedName("study_goal") var studyGoal: String = ""
    @field:SerializedName("study_target") var studyTarget: String = ""
    @field:SerializedName("study_ref") var studyRef: String = ""
    @field:SerializedName("study_complete") var studyComplete: String = ""
    @field:SerializedName("study_ncs") var studyNcs: String = ""
    @field:SerializedName("teacher_info") var teacherInfo: String = ""
    @field:SerializedName("short_description") var shortDescription: String = ""
    @field:SerializedName("course_introduce") var courseIntroduce: String = ""
    @field:SerializedName("delivery_flag") var flagDelivery: String = ""
}


class Banner(
    @field:SerializedName("board_article_no") val no: Int,
    @field:SerializedName("board_no") val noBoard: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("thumbnail_url") val urlThumb: String
)

class ClipSet: Base() {
    @field:SerializedName("delivery_flag") var flagDelivery: Int = 0
    @field:SerializedName("data_list1") var collectionClip: ArrayList<Clip>? = arrayListOf()
    @field:SerializedName("data_list2") var collectionAttendance: ArrayList<Attendance>? = arrayListOf()
}

class Clip: Base() {
    @field:SerializedName("course_no") var noCourse: Int = 0
    @field:SerializedName("lesson_subitem_no") var noContent: Int = 0
    @field:SerializedName("title") var title: String = ""
    @field:SerializedName("lesson_title") var titleContent: String = ""
    @field:SerializedName("required_learning_time_in_secondes") var timeFinished: Int = 0
    @field:SerializedName("display_order") var order: Int = 0
    @field:SerializedName("image_url") var urlImage01: String = ""
    @field:SerializedName("thumbnail_url") var urlImage02: String = ""
    @field:SerializedName("like_count") var countLike: Int = 0
    @field:SerializedName("view_count") var countView: Int = 0
    @field:SerializedName("url") var urlLink: String = ""
    @field:SerializedName("reple_count") var countReple: Int = 0
    @field:SerializedName("is_clip_like") var favorite: Int = 0
    @field:SerializedName("is_vertical_video") var vertical: Int = 0
    @field:SerializedName("data_list") var quizSet: ArrayList<QuizAndExample> = arrayListOf()
}

data class QuizAndExample(
    @field:SerializedName("quiz_no") var noQuiz: Int = 0,
    @field:SerializedName("quiz_type") var quizType: Int = 0,
    @field:SerializedName("quiz_text") var quizText: String = "",
    @field:SerializedName("quiz_text_file_url") var quizUrlFile: String = "",
    @field:SerializedName("quiz_score") var quizScore: Int = 0,
    @field:SerializedName("difficulty") var quizLevel: Int = 0,
    @field:SerializedName("description") var quizDescription: String = "",
    @field:SerializedName("example_no") var noExample: Int = 0,
    @field:SerializedName("correct_example_no") var noExampleEcorrect: Int = 0,
    @field:SerializedName("display_order") var exampleType: Int = 0,
    @field:SerializedName("example_type") var exampleOrder: Int = 0,
    @field:SerializedName("examples") var exampleText: String = ""
)

data class Attendance(
    @field:SerializedName("lesson_subitem_no") var noContent: Int = 0,
    @field:SerializedName("attendance_code_code") var codeAttendance: Int = 0
)

class CommentSet: Base() {
    @field:SerializedName("data_list") var itemSet: ArrayList<Comment> = arrayListOf()
}

data class Comment(
        @field:SerializedName("board_article_no") var noBoardArticle: Int = 0,
        @field:SerializedName("board_no") var noBoard: Int = 0,
        @field:SerializedName("nickname") var nickname: String = "",
        @field:SerializedName("contents") var contents: String = "",
        @field:SerializedName("like_count") var countView: Int = 0,
        @field:SerializedName("view_count") var countLike: Int = 0,
        @field:SerializedName("reple_count") var countReple: Int = 0,
        @field:SerializedName("report_count") var countReport: Int = 0,
        @field:SerializedName("update_date") var dateUpdate: String = "",
        @field:SerializedName("is_good") var good: Int = 0,
        @field:SerializedName("user_no") var noUser: Int = 0,
        @field:SerializedName("user_likes") var like: Int = 0,
        @field:SerializedName("profile_image") var urlImage01: String = "",
        @field:SerializedName("profile_thumbnail_url") var urlImage02: String = "",
        @field:SerializedName("clip_name") var title: String = "",
        @field:SerializedName("unit_attach_file_name") var file: String = "",
        @field:SerializedName("unit_attach_image_url") var url: String = "",
        @field:SerializedName("unit_attach_thumbnail_url") var thumbnail: String = ""
)