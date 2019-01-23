package net.techpda.gudle

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_course_overview.*

class CourseOverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_overview)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val noCourse = intent.extras.getInt("course_no")
        Toast.makeText(this, "get: $noCourse", Toast.LENGTH_SHORT).show()

        back.setOnClickListener {


            onBackPressed()

        }
    }

}
