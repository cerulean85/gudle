package net.techpda.gudle.activities

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.ScrollView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_course_overview.*
import net.techpda.gudle.databinding.ActivityCourseOverviewBinding
import org.jetbrains.anko.contentView
//import sun.util.locale.provider.LocaleProviderAdapter.getAdapter
import android.support.v7.widget.RecyclerView
import net.techpda.gudle.*


class CourseOverviewActivity : AppCompatActivity() {

    var context: Context? = null

    val model: CourseOverviewViewModel = CourseOverviewViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCourseOverviewBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_course_overview)
        model.imageView = ivContentThumb
        binding.model = model


        model.onCreate()


        context = applicationContext


//        setContentView(R.layout.activity_course_overview)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


//
//        tvContentTittle.text = JCModel.detailCourse.title
//        tvContentDesc.text = JCModel.detailCourse.shortDescription
//
//
        listClip.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(this@CourseOverviewActivity).orientation))
//        listClip.adapter = AdapterClipSet(context!!, JCModel.clipSet.collectionClip!!)
//
//
        btnQuestions.setOnClickListener {


            nestedScrollView.fullScroll(ScrollView.FOCUS_UP)
//
//            App.binder.getClipList(JCModel.detailCourse.noCourse.toString()) {
//
//                startActivity(Intent(context, ClipSetActivity::class.java), null)
//
//            }
        }
//
        btnBack.setOnClickListener {
            onBackPressed()
        }

        contentView!!.post {
            nestedScrollView.fullScroll(ScrollView.FOCUS_UP)
        }
    }

    @BindingAdapter("app:items")
    fun setItemList(recyclerView: RecyclerView, items: ArrayList<CourseListItemViewModel>) {
        val adapter: AdapterClipSet

        if (recyclerView.adapter == null) {
            adapter = AdapterClipSet()
            recyclerView.adapter = adapter
        } else {
            adapter = recyclerView.adapter as AdapterClipSet
        }

        adapter.add(items)
    }
    override fun onResume() {
        super.onResume()
        model.onResume()
    }

    override fun onPause() {
        super.onPause()
        model.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.onDestroy()
    }
}
