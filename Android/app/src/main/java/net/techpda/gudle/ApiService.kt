package net.techpda.gudle

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    //    @FormUrlEncoded  //Form-encoded method must contain at least one @Field
    @POST("getSystemInfo")
    fun getSystemInfo(): Observable<SystemInfo>

    @FormUrlEncoded
    @POST("getMain")
    fun getMain(@Field("now_page") nowPage: String, @Field("category_no") noCategory: String): Observable<Main>

}