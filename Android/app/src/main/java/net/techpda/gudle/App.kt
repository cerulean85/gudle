package net.techpda.gudle

import android.app.Application
import io.reactivex.disposables.CompositeDisposable

class App : Application() {

    companion object {
        lateinit var pref : SharedPref
        lateinit var binder: Binder
        lateinit var mService: DiagnosisService

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

        var mCurrentCourseNo: Int = 0
        fun setCurrentCourseNo(no: Int)
        {
            mCurrentCourseNo = no
        }

        fun currentCourseNo(): Int {
            val tmp: Int = mCurrentCourseNo
            mCurrentCourseNo = 0
            return tmp
        }


    }



    override fun onCreate() {
        pref= SharedPref(applicationContext)
        binder = Binder()
        super.onCreate()
    }




}