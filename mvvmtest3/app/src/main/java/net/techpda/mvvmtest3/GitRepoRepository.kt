package net.techpda.mvvmtest3

import android.content.Context
import android.os.Handler
import io.reactivex.Observable
import javax.inject.Inject

class GitRepoRepository @Inject constructor (var netManager: NetManager) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

//    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
//        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data:") }, 2000)
//    }


    fun getRepositories(): Observable<ArrayList<Repository>> {

        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getRepositories()
            }
        }

        return localDataSource.getRepositories()
    }
}

//interface OnDataReadyCallback {
//    fun onDataReady(data: String)
//}

//interface OnRepositoryReadyCallback {
//    fun onDataReady(data: ArrayList<Repository>)
//}