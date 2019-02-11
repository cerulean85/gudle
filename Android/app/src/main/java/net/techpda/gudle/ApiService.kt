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

    @FormUrlEncoded
    @POST("getCourseDetail")
    fun getCourseDetail(@Field("course_no") noCourse: String): Observable<Course>

    @FormUrlEncoded
    @POST("getClipList")
    fun getClipList(@Field("course_no") noCourse: String): Observable<ClipSet>

    @FormUrlEncoded
    @POST("getClipDetail")
    fun getClipDetail(@Field("lesson_subitem_no") noContent: String, @Field("course_no") noCourse: String): Observable<Clip>

    @FormUrlEncoded
    @POST("getClipDetail")
    fun getClipRepleList(@Field("lesson_subitem_no") noContent: String, @Field("filter") filter: String, @Field("now_page") nowPage:String): Observable<CommentSet>


}