package techpda.net.cliplearning.repositories

import android.content.Context
import io.reactivex.Observable
import techpda.net.cliplearning.models.Main
import techpda.net.cliplearning.networks.NetManager
import techpda.net.cliplearning.models.MainModel

import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.CommonDataSource

@Singleton
class Repository @Inject constructor (var netManager: NetManager, var localDataSource: LocalRepository, var remoteDataSource: RemoteRepository) {

//    val localDataSource = LocalRepository()
//    val remoteDataSource = RemoteRepository()

//    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
//        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data:") }, 2000)
//    }

    companion object {
        lateinit var main: Main
    }


    fun getRepository(): Observable<ArrayList<MainModel>> {

        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getRepository()
            }
        }

        return localDataSource.getRepository()
    }

    fun getContext(): Context {
        return netManager.applicationContext
    }


}