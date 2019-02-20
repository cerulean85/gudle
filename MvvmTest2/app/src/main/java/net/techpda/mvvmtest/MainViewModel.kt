package net.techpda.mvvmtest

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MainViewModel: ViewModel() {
    var repoModel: RepoModel = RepoModel()

    var text = ObservableField("Old Data")

    var isLoading = ObservableField(false)

    var repositories = ArrayList<Repository>()

    fun refresh() {
        isLoading.set(true)
        repoModel.refreshData(object: OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }

    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories = data
            }
        })
    }
}