package net.techpda.gudle

import android.app.Application

class App : Application() {

    companion object {
        lateinit var pref : SharedPref
        lateinit var binder: Binder

        val DEVELOPING: Boolean = true
        fun log(message: String) {
            if(DEVELOPING)
                println(message)
        }
    }

    override fun onCreate() {
        pref= SharedPref(applicationContext)
        binder = Binder()
        super.onCreate()
    }



}