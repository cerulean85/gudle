package techpda.net.cliplearning.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextWatcher
import com.google.gson.annotations.SerializedName
import techpda.net.cliplearning.BR
import android.text.Editable

class MainModel(name: String, var owner: String?, var stars: Int?, var issued: Boolean = false) : BaseModel() {


    @get:Bindable
    @field:SerializedName("name")
    var name: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    fun getNameWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                name = s.toString()
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        }
    }

}

class Main: BaseModel() {
    @get:Bindable @field:SerializedName("banner_count") var countBanner: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.countBanner) }

    @get:Bindable @field:SerializedName("total_course_count") var countCourse: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.countCourse) }

    @get:Bindable @field:SerializedName("data_list1") var collectionCourse: ArrayList<Course>? = arrayListOf()
        set(value) { field = value; notifyPropertyChanged(BR.collectionCourse) }

    @get:Bindable @field:SerializedName("data_list2") var collectionBanner: ArrayList<Course>? = arrayListOf()
        set(value) { field = value; notifyPropertyChanged(BR.collectionBanner) }
}

class Course: BaseModel() {
    @get:Bindable @field:SerializedName("course_no") var no: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.no) }

    @get:Bindable @field:SerializedName("course_content_no") var noContent: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.noContent) }

    @get:Bindable @field:SerializedName("service_title") var title: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.title) }

    @get:Bindable @field:SerializedName("category_title") var nameCategory: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.nameCategory) }

    @get:Bindable @field:SerializedName("course_image_thumbnail_url") var urlThumb: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.urlThumb) }

    @get:Bindable @field:SerializedName("view_count") var countView: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.countView) }

    @get:Bindable @field:SerializedName("course_info_image_url") var urlImageInfo: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.urlImageInfo) }

    @get:Bindable @field:SerializedName("course_image_url") var urlImage: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.urlImage) }

    @get:Bindable @field:SerializedName("study_goal") var studyGoal: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.studyGoal) }

    @get:Bindable @field:SerializedName("study_target") var studyTarget: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.studyTarget) }

    @get:Bindable @field:SerializedName("study_ref") var studyRef: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.studyRef) }

    @get:Bindable @field:SerializedName("study_complete") var studyComplete: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.studyComplete) }

    @get:Bindable @field:SerializedName("study_ncs") var studyNcs: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.studyNcs) }

    @get:Bindable @field:SerializedName("teacher_info") var teacherInfo: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.teacherInfo) }

    @get:Bindable @field:SerializedName("short_description") var shortDescription: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.shortDescription) }

    @get:Bindable @field:SerializedName("course_introduce") var courseIntroduce: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.courseIntroduce) }

    @get:Bindable @field:SerializedName("delivery_flag") var flagDelivery: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.flagDelivery) }
}

class Banner: BaseModel() {
    @get:Bindable @field:SerializedName("board_article_no") var no: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.no) }

    @get:Bindable @field:SerializedName("board_no") var noBoard: Int = 0
        set(value) { field = value; notifyPropertyChanged(BR.noBoard) }

    @get:Bindable @field:SerializedName("title") var title: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.title) }

    @get:Bindable @field:SerializedName("thumbnail_url") var urlThumb: String = ""
        set(value) { field = value; notifyPropertyChanged(BR.urlThumb) }
}
