package net.techpda.gudle

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Binder
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.*
import android.widget.SeekBar
import kotlinx.android.synthetic.main.diagnosis_layout.view.*
import org.jetbrains.anko.windowManager
import java.text.SimpleDateFormat
import java.util.*

class DiagnosisService : Service() {

    var mView: View? = null

    var mBinder: IBinder = DiagnosisServiceBinder()


    fun getInstance(): DiagnosisService {
        return this
    }

    inner class DiagnosisServiceBinder : Binder() {
        internal
        val service: DiagnosisService
            get() = this@DiagnosisService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()

        var inflate: LayoutInflater? = LayoutInflater.from(applicationContext)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var pOpen = WindowManager.LayoutParams(
                displayMetrics.widthPixels,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT)
        pOpen.gravity = Gravity.RIGHT or Gravity.TOP
        mView = inflate!!.inflate(R.layout.diagnosis_layout, null)
        windowManager.addView(mView, pOpen)

        mView!!.openBtn.setOnClickListener {
            windowManager.removeView(mView)
            windowManager.addView(mView, pOpen)
        }

        mView!!.closeBtn.setOnClickListener {

            windowManager.removeView(mView)
            var pClose = WindowManager.LayoutParams(
                    mView!!.openBtn.width,
                    mView!!.openBtn.height,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT)
            pClose.gravity = Gravity.CENTER
            windowManager.addView(mView, pClose)
        }

        mView!!.removeBtn.setOnClickListener {
            mView!!.contentTV.text = ""
        }

        mView!!.adjustBtn.setOnClickListener {
            if(mView!!.widCT.visibility == View.GONE)
                mView!!.widCT.visibility = View.VISIBLE
            else mView!!.widCT.visibility = View.GONE

            if(mView!!.heiCT.visibility == View.GONE)
                mView!!.heiCT.visibility = View.VISIBLE
            else mView!!.heiCT.visibility = View.GONE
        }


        mView!!.seekBarOpa.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                val opa: Double = i / 100.00
                val rounded: Long = Math.round((opa * 255).toDouble())
                var hex = Integer.toHexString(rounded.toInt()).toUpperCase()
//
                if (hex.length == 1)
                    hex = "0$hex"
//
                val color: String = "#" + String.format("%s", hex) + "A890FE"
                mView!!.setBackgroundColor(Color.parseColor(color))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        mView!!.seekBarWid.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                val max: Int = displayMetrics.widthPixels
                var width: Int = max

                if(i==1) width = (width * 0.5).toInt()
                else if(i==0) width = mView!!.openBtn.width

                var pOpen = WindowManager.LayoutParams(
                        width,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                        PixelFormat.TRANSLUCENT)
                pOpen.gravity = Gravity.RIGHT or Gravity.TOP
                windowManager.removeView(mView)
                windowManager.addView(mView, pOpen)

                toBottom()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        mView!!.seekBarHei.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                val max: Int = displayMetrics.heightPixels
                var height: Int = max

                if(i==1) height = (height * 0.5).toInt()
                else if(i==0) height = mView!!.openBtn.height

                var pOpen = WindowManager.LayoutParams(
                        mView!!.width,
                        height,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                        PixelFormat.TRANSLUCENT)
                pOpen.gravity = Gravity.RIGHT or Gravity.TOP
                windowManager.removeView(mView)
                windowManager.addView(mView, pOpen)

                toBottom()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    var lineCount: Int = 0
    fun addLog(message: String) {

        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val currentDate: String = sdf.format(Date())

        lineCount += 1
        mView!!.contentTV.text = mView!!.contentTV.text.toString() + "[$lineCount] " + currentDate + ": " + message + "\n"
        toBottom()


    }

    fun toBottom() {
        mView!!.scrollView.post { mView!!.scrollView.fullScroll(View.FOCUS_DOWN) }
    }
}