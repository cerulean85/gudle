package techpda.net.cliplearning.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import techpda.net.cliplearning.extensions.plusAssign
import techpda.net.cliplearning.models.MainModel
import techpda.net.cliplearning.repositories.Repository
import javax.inject.Inject


class OverviewViewModel @Inject constructor(var repo: Repository): BaseViewModel(repo) {
    private val compositeDisposable = CompositeDisposable()

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)
    var repository = MutableLiveData<ArrayList<MainModel>>()

    fun loadRepository() {
        isLoading.set(true)
        compositeDisposable += repo
            .getRepository()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ArrayList<MainModel>>() {

                override fun onError(e: Throwable) {
                    //if some error happens in our data layer our app will not crash, we will
                    // get error here
                }

                override fun onNext(data: ArrayList<MainModel>) {
                    repository.value = data
                }

                override fun onComplete() {
                    isLoading.set(false)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}