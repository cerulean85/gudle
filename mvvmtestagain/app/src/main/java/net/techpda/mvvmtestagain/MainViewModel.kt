package net.techpda.mvvmtestagain

import android.databinding.ObservableField

class MainViewModel {
    var repoModel: RepoModel = RepoModel()
    val text = ObservableField("old data")
    val isLoading = ObservableField(false)

    val onDataReadyCallback = object : OnDataReadyCallback {
        override fun onDataReady(data: String) {
            isLoading.set(false)
            text.set(data)
        }
    }

    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }
}