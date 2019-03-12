package techpda.net.cliplearning.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.text.TextWatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import techpda.net.cliplearning.extensions.TextWatcherAdapter
import techpda.net.cliplearning.extensions.plusAssign
import techpda.net.cliplearning.models.Main
import techpda.net.cliplearning.models.MainModel
import techpda.net.cliplearning.networks.APIService
import techpda.net.cliplearning.networks.NetworkModule
//import techpda.net.cliplearning.networks.provideAPIService
import techpda.net.cliplearning.repositories.Repository
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(var repo: Repository): BaseViewModel(repo) {

    @Inject lateinit var apiService: APIService //by lazy { provideAPIService() }

    private lateinit var subscription: Disposable

    lateinit var main: ObservableField<Main>

//    init {

//    }

    private fun getMain() {
        subscription = apiService.getMain("1", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

            }
            .doOnTerminate {

            }

            .subscribe({

                main.set(it)

            },

            {

            })
    }

    private val compositeDisposable = CompositeDisposable()

    var text = ObservableField("old data")

    val textWatcher: TextWatcher = TextWatcherAdapter(text)


//    val textWatcher: TextWatcher = TextWatcherAda
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
        if(!subscription.isDisposed) {
            subscription.dispose()
        }
//        if(!compositeDisposable.isDisposed) {
//            compositeDisposable.dispose()
//        }
    }
}