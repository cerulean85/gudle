package net.techpda.gudle

import android.app.Application
import io.reactivex.disposables.CompositeDisposable

class App : Application() {

    companion object {
        lateinit var pref : SharedPref
        lateinit var binder: Binder
        lateinit var mService: DiagnosisService
        lateinit var util: Util

        val apiService by lazy { provideApiService() }
        val disposable = CompositeDisposable()

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
        util = Util()
        super.onCreate()
    }



}