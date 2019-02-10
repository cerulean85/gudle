package net.techpda.gudle

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://mobile01.e-koreatech.ac.kr")
        .client(provideOkHttpClient(providedLoggingInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)


private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    val b = OkHttpClient.Builder()
    b.addInterceptor(interceptor)
    return b.build()
}

private fun providedLoggingInterceptor(): HttpLoggingInterceptor {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return interceptor

}