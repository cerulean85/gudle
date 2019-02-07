package net.techpda.gudle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_course_overview.*
import org.jetbrains.anko.startActivity

class CourseOverviewActivity : AppCompatActivity() {

    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = applicationContext


        setContentView(R.layout.activity_course_overview)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        JCModel.detailCourse.urlImage02.let {

            val url = JCModel.detailCourse.urlImage02
            if(!url.isNullOrEmpty()) {
                Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background)
                        //.transform(BlurTransformation(context, 25))
                        .into(ivContentThumb)
            }
        }

        tvContentTittle.text = JCModel.detailCourse.title
        tvContentDesc.text = JCModel.detailCourse.description


        listClip.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(this@CourseOverviewActivity).orientation))

        listClip.adapter = AdapterClipSet(context!!, JCModel.clipSet)


//        btnContents.setOnClickListener {
//
//            App.binder.getClipList(JCModel.detailCourse.noCourse.toString()) {
//
//                startActivity(Intent(context, ClipSetActivity::class.java), null)
//
//            }
//        }


        btnBack.setOnClickListener {

            onBackPressed()

        }
    }
}
