package net.techpda.myapplication

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject
constructor(private val repoRepository: RepositoryProvider): ViewModel(){

    var disposable: CompositeDisposable? = null

    val repos = MutableLiveData<List<Repository>>()
    val repoLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = repoLoadError

    init {
        disposable = CompositeDisposable()
        fetchRepos()
    }

    internal fun getRepos(): LiveData<List<Repository>> {
        return repos
    }

    internal fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun fetchRepos() {
        loading.value = true
        disposable!!.add(repoRepository.repositories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<Repository>>() {
                    override fun onSuccess(value: List<Repository>) {
                        repoLoadError.value = false
                        repos.value = value
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.value = true
                        loading.value = false
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }

}