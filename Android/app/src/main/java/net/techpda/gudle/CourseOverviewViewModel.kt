package net.techpda.gudle

import android.content.Intent
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v4.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_overview.*
import net.techpda.gudle.activities.CourseOverviewActivity
import net.techpda.gudle.databinding.ActivityCourseOverviewBinding


class CourseOverviewViewModel(val binding: ActivityCourseOverviewBinding): BaseViewModel {

    var title: ObservableField<String> = ObservableField() //JCModel.detailCourse.title
    var shortDescription: ObservableField<String> = ObservableField() //JCModel.detailCourse.shortDescription

    var imageView: ScaledImageView? = null

//    var items: ObservableArrayList<CourseListItemViewModel> = ObservableArrayList()

    override fun onCreate() {

        App.binder.getCourseDetail(App.currentCourseNo().toString(),
        {
            title.set(JCModel.detailCourse.title)
            shortDescription.set(JCModel.detailCourse.shortDescription)

            JCModel.detailCourse.urlImage.let{
                val url = JCModel.detailCourse.urlImage
                if(!url.isNullOrEmpty()) {
                    Picasso.get()
                            .load(url)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background)
                            //.transform(BlurTransformation(context, 25))
                            .into(imageView)
                }
            }
        })

        App.binder.getClipList(App.currentCourseNo().toString(),
                {
                    val articles = JCModel.clipSet.collectionClip
                    val adapter = AdapterClipSet(articles!!)
                    binding.listClip.adapter = adapter
                })
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }

//    fun addItem() {
//        items.add(CourseListItemViewModel("zzz"))
//    }
}