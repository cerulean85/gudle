package techpda.net.cliplearning.repositories

import io.reactivex.Observable
import techpda.net.cliplearning.models.MainModel
import java.util.concurrent.TimeUnit

class LocalRepository {




    fun getRepository(): Observable<ArrayList<MainModel>> {
        var arr = ArrayList<MainModel>()
        arr.add(MainModel("First From Local", "Owner 1", 100, false))
        arr.add(MainModel("Second From Local", "Owner 2", 30, true))
        arr.add(MainModel("Third From Local", "Owner 3", 430, false))

        return Observable.just(arr).delay(2, TimeUnit.SECONDS)

    }
}