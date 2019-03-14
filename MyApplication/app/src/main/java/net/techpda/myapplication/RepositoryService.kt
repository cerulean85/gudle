package net.techpda.myapplication

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RepositoryService {

    @get:GET("orgs/Google/repos")
    val repositories: Single<List<Repository>>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String, @Path("name") name: String): Single<Repository>

//    companion object Factory{
//        fun create(): RepositoryService {
//
//            val retrofit = retrofit2.Retrofit.Builder()
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl("https://api.github.com/")
//                    .build()
//
//            return retrofit.create(RepositoryService::class.java)
//
//        }
//
//    }
}