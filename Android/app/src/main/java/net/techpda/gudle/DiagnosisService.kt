package net.techpda.gudle;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat
import android.os.IBinder;
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import org.jetbrains.anko.windowManager

class DiagnosisService : Service() {

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    val wm: WindowManager = windowManager


    override fun onCreate() {
        super.onCreate()

        var inflate: LayoutInflater? = LayoutInflater.from(applicationContext)


        val params = WindowManager.LayoutParams(
                /*ViewGroup.LayoutParams.MATCH_PARENT*/300,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.LEFT or Gravity.TOP;
        var mView = inflate!!.inflate(R.layout.diagnosis_layout, null)

        wm.addView(mView, params)


    }
}
