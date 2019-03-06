package techpda.net.cliplearning.networks

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//@Module
//@Suppress("unused")
//object NetworkModule {
//    /**
//     * Provides the Post service implementation.
//     * @param retrofit the Retrofit object used to instantiate the service
//     * @return the Post service implementation.
//     */
//    @Provides
//    @Reusable
//    @JvmStatic
//    fun provideAPIService(retrofit: Retrofit): APIService {
//        return retrofit.create(APIService::class.java)
//    }
//
//    /**
//     * Provides the Retrofit object.
//     * @return the Retrofit object
//     */
//    @Provides
//    @Reusable
//    @JvmStatic
//    fun provideRetrofitInterface(): Retrofit {
////        return Retrofit.Builder()
////            .baseUrl("https://mobile01.e-koreatech.ac.kr")
////            .client(provideOkHttpClient(providedLoggingInterceptor()))
////            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .build()
//    }
//}


@Module
class NetworkModule {

    private val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): APIService {
        return retrofit.create<APIService>(APIService::class.java!!)
    }
}
