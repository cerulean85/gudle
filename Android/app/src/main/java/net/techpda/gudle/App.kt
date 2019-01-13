package net.techpda.gudle

import android.app.Application

class App : Application() {

    companion object {
        lateinit var pref : SharedPref
        lateinit var binder: Binder
        lateinit var mService: DiagnosisService

        val DEVELOPING: Boolean = true
        fun log(message: String) {
            if(DEVELOPING)
                println(message)
        }

        val useDiagnois: Boolean = false
        var displayWidth: Int = 0
        var heightHomeCourse: Int = 0
    }

    override fun onCreate() {
        pref= SharedPref(applicationContext)
        binder = Binder()
        super.onCreate()
    }



}