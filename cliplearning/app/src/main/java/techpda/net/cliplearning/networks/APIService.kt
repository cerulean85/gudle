package techpda.net.cliplearning.networks

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import techpda.net.cliplearning.models.Course
import techpda.net.cliplearning.models.Main

interface APIService {

//    @POST("getSystemInfo")
//    fun getSystemInfo(): Observable<SystemInfo>

    @FormUrlEncoded
    @POST("getMain")
    fun getMain(@Field("now_page") nowPage: String, @Field("category_no") noCategory: String): Observable<Main>

    @FormUrlEncoded
    @POST("getCourseDetail")
    fun getCourseDetail(@Field("course_no") noCourse: String): Observable<Course>

//    @FormUrlEncoded
//    @POST("getClipList")
//    fun getClipList(@Field("course_no") noCourse: String): Observable<ClipSet>
//
//    @FormUrlEncoded
//    @POST("getClipDetail")
//    fun getClipDetail(@Field("lesson_subitem_no") noContent: String, @Field("course_no") noCourse: String): Observable<Clip>
//
//    @FormUrlEncoded
//    @POST("getClipDetail")
//    fun getClipRepleList(@Field("lesson_subitem_no") noContent: String, @Field("filter") filter: String, @Field("now_page") nowPage:String): Observable<CommentSet>

}


//fun provideAPIService(): APIService = Retrofit.Builder()
//    .baseUrl("https://mobile01.e-koreatech.ac.kr")
//    .client(provideOkHttpClient(providedLoggingInterceptor()))
//    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//    .create(APIService::class.java)
//
//
//private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
//    val b = OkHttpClient.Builder()
//    b.addInterceptor(interceptor)
//    return b.build()
//}
//
//private fun providedLoggingInterceptor(): HttpLoggingInterceptor {
//
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//    return interceptor
//}