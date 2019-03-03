package techpda.net.cliplearning.networks

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import techpda.net.cliplearning.views.MainActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetManager @Inject constructor(var applicationContext: Context){

    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }

    fun gogo() {
        applicationContext.startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}