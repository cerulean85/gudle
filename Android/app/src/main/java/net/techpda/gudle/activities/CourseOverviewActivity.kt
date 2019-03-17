package net.techpda.gudle.activities

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_course_overview.*
import org.jetbrains.anko.contentView
import net.techpda.gudle.*
import net.techpda.gudle.databinding.ActivityCourseOverviewBinding


class CourseOverviewActivity : AppCompatActivity() {

    var context: Context? = null

    lateinit var model: CourseOverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCourseOverviewBinding>(this, R.layout.activity_course_overview)
        model = CourseOverviewViewModel(binding)
        binding.model = model

        model.imageView = ivContentThumb
        model.onCreate()

        context = applicationContext

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        listClip.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(this@CourseOverviewActivity).orientation))

        btnQuestions.setOnClickListener {
//                startActivity(Intent(context, ClipSetActivity::class.java), null)
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        contentView!!.post {
            nestedScrollView.fullScroll(ScrollView.FOCUS_UP)
        }
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
