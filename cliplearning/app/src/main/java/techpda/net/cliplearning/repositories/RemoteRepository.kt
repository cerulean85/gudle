package techpda.net.cliplearning.repositories

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import techpda.net.cliplearning.Status
import techpda.net.cliplearning.networks.provideAPIService
import techpda.net.cliplearning.models.Main
import techpda.net.cliplearning.models.MainModel
import techpda.net.cliplearning.repositories.Repository.Companion.main
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class RemoteRepository {

    val disposable by lazy { CompositeDisposable() }
    val apiService by lazy { provideAPIService() }

    fun getRepository() : Observable<ArrayList<MainModel>> {
        var arr = ArrayList<MainModel>()
        arr.add(MainModel("First from remote", "Owner 1", 100, false))
        arr.add(MainModel("Second from remote", "Owner 2", 30, true))
        arr.add(MainModel("Third from remote", "Owner 3", 430, false))

        return Observable.just(arr).delay(2, TimeUnit.SECONDS)
    }

    var noCurrentPage: Int = 0
    fun getMain(nowPage: String, noCategory: String,
                onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {
        noCurrentPage = when (nowPage) {
            Status.CALL_PAGE_FIRST.value -> 1       //첫번째 페이지 호출
            Status.CALL_PAGE_NEXT_AUTO.value -> noCurrentPage+1 //다음 페이지 자동 호출
            else -> nowPage.toInt() //입력한 파라미터에 대한 페이지 호출
        }

        disposable.add(

            apiService.getMain("1", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }

                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        if(noCurrentPage==1) main = it
                        else {
                            it.collectionCourse!!.forEach { e-> main.collectionCourse!!.add(e) }
                            main.countCourse = it.countCourse
                        }
                        onSucess()
                    } else {
                        onFail()
                    }


                },

                {
                    onFail()
                }))
    }


}