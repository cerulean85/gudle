package net.techpda.gudle.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clip_set.*
import net.techpda.gudle.R

class ClipSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip_set)

        btnBack.setOnClickListener {

            onBackPressed()

        }
    }
}
