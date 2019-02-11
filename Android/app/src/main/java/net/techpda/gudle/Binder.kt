package net.techpda.gudle

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import com.google.gson.*
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlin.system.exitProcess

class Binder {

    private val prefixAddr: String = "https://mobile01.e-koreatech.ac.kr/"

    private var model: JCModel = JCModel
    private var gson: Gson = Gson()

    constructor()

    init {
        FuelManager.instance.basePath = "http://favorite.cafe24app.com";

//        FuelManager.instance.basePath = "https://mobile01.e-koreatech.ac.kr/";
    }

//    fun getImageAll() {
//        try {
//            Fuel.get("getImageAll").responseJson { request, response, result ->
//                println("thread test00")
//                var gson = Gson()
//                val json: String = result.get().content
//                JCModel.imageSet = gson.fromJson(json, ImageArray::class.java)
//
//            }
//        } catch (e: Exception) {
//            println(e.toString())
//        } finally {
//
//        }
//    }
//
//    fun getDummyAll(body: () -> Unit) {
//        try {
//            Fuel.get("getDummyAll").responseJson { request, response, result ->
//
//                val json: String = result.get().content
//                model.dummySet = gson.fromJson(json, AlbumArray::class.java)
//
//                body()
//            }
//        } catch (e: Exception) {
//        } finally {
//        }
//    }

//    fun getMarketSet() {
//            var hyoUrl: String = "http://favorite.cafe24app.com/img/img0"
//
//            JCModel.marketSet!!.list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"16.jpg", Color.CYAN))
//            JCModel.marketSet!!.list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"17.jpg", Color.LTGRAY))
//            JCModel.marketSet!!.list.add(Movie("Forrest Gump",1994, hyoUrl+"17.jpg", Color.GREEN))
//            JCModel.marketSet!!.list.add(Movie("Titanic",1997, hyoUrl+"18.jpg", Color.DKGRAY))
//            JCModel.marketSet!!.list.add(Movie("Taxi",1998, hyoUrl+"18.jpg", Color.MAGENTA))
//            JCModel.marketSet!!.list.add(Movie("Inception",1994, hyoUrl+"19.jpg", Color.WHITE))
//            JCModel.marketSet!!.list.add(Movie("The Imitation Game",2014, hyoUrl+"20.jpg", Color.GREEN))
//            JCModel.marketSet!!.list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"21.jpg", Color.CYAN))
//            JCModel.marketSet!!.list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"22.jpg", Color.LTGRAY))
//    }

//    fun getSystemInfo(body:()->Unit = {}) {
//
////        FuelManager.instance.basePath = "https://mobile01.e-koreatech.ac.kr/"
//
//        val addr = prefixAddr + "getSystemInfo"
//
//        var r: String = "Never...."
//        try {
//            addr.httpPost().header("Content-Type" to "application/x-www-form-urlencoded")
//            .responseJson{ request, response, result ->
//                log(result.get().content)
//
//                val json:String = result.get().content
//                var element = JsonParser().parse(json)
//
//                model.systemInfo.urlEvent = element.asJsonObject.get("event_popup_url")?.asString
//                model.systemInfo.versionMinOS = element.asJsonObject.get("android_min_version")?.asString
//                model.systemInfo.eventNoBard = element.asJsonObject.get("board_no")?.asInt
//
////                model.systemInfo.eventNoBard = element.asJsonObject.get("board_no").isJsonNull asInt
//                model.systemInfo.eventNoBoardArticle = element.asJsonObject.get("board_article_no")?.asInt
//                model.systemInfo.eventTitle = element.asJsonObject.get("popup_title")?.asString
//                model.systemInfo.eventNoPopup = element.asJsonObject.get("popup_no")?.asInt
//                model.systemInfo.eventUrlImage = element.asJsonObject.get("image_url")?.asString
//
//                model.systemInfo.category.add(Category(0, "전체"))
//                val cate = element.asJsonObject.get("data_list").asJsonArray
//                cate.forEach {
//                    model.systemInfo.category.add(Category(
//                            noCategory = it.asJsonObject.get("category_no").asInt,
//                            title = it.asJsonObject.get("title").asString))}
//            }
//
//            body()
//
//        } catch (e: Exception) {
//        } finally {
//
//
//        }

//        cs.print(pref.getString("TEST"))

//        try {
//            Fuel.get("getDummyAll").responseJson { request, response, result ->
//
//                val json: String  = result.get().content;
//                model.dummySet = gson.fromJson(json, AlbumArray::class.java)
//
//                body()
//            }
//        } catch (e: Exception) {
//        } finally {
//        }
//    }

    fun getSystemInfo(
            onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {
        App.disposable.add(App.apiService.getSystemInfo()

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.systemInfo = it
                        JCModel.systemInfo.collectionCategory!!.add(0, Category(0, "전체"))
                        onSucess()
                    } else {
                        onFail(); exitProcess(0)
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                }

        )
    }

    fun getMain(nowPage: String, noCategory: String,
                onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {
        App.disposable.add(App.apiService.getMain(nowPage, noCategory)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.main = it
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                }

        )
    }

//    fun getMain(page: String, category: String, body:()->Unit = {}) {
//
//        val addr = prefixAddr + "getMain"
//
//        val list: List<Pair<String, String>>? = listOf(Pair("now_page", page), Pair("category_no", category))
//
//        try {
//            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
//                    .responseJson{ request, response, result ->
//
//                        val json:String = result.get().content
//                        var element = JsonParser().parse(json)
//
//                        model.dataHome.countBanner = element.asJsonObject.get("banner_count").asInt
//                        model.dataHome.countCourse = element.asJsonObject.get("total_course_count").asInt
//                        model.dataHome.isNewAlarm = element.asJsonObject.get("exists_new_alarm").asInt
//
//                        val d1 = element.asJsonObject.get("data_list1").asJsonArray
//                        d1.forEach {
//
//                            var urlThumbCourseImage = it.asJsonObject.get("course_image_thumbnail_url")
//                            model.dataHome.course.add(Album(
//                                    noCourse = it.asJsonObject.get("course_no").asInt,
//                                    noContent = it.asJsonObject.get("course_content_no").asInt,
//                                    title = it.asJsonObject.get("service_title").asString,
//                                    nameCategory = it.asJsonObject.get("category_title").asString,
//                                    urlThumb = if(urlThumbCourseImage.isJsonNull) "" else urlThumbCourseImage.asString,
//                                    countView = it.asJsonObject.get("view_count").asInt)) }
//
//                        val d2 = element.asJsonObject.get("data_list2").asJsonArray
//                        d2.forEach {
//                            model.dataHome.banner.add(Article(
//                                    no = it.asJsonObject.get("board_article_no").asInt,
//                                    noBoard = it.asJsonObject.get("board_no").asInt,
//                                    title = it.asJsonObject.get("title").asString,
//                                    urlThumb = it.asJsonObject.get("thumbnail_url").asString))}
//
//
//
//                        body()
//                    }
//        } catch(e: Exception) {
//
//        } finally {
//
//        }
//    }

    fun getCourseCollectionClassfiedByCategory(noCategory: String,
                                               onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {
        App.disposable.add(App.apiService.getMain("1", noCategory)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.collectionCoruseClassifiedByCategory[noCategory] = it.collectionCourse?.let { it } ?: arrayListOf()
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                }

        )
    }

    fun getCourseDetail(noCourse: String,
        onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {

        App.disposable.add(App.apiService.getCourseDetail(noCourse)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.detailCourse = it
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                }
        )
    }

    fun getClipList(noCourse: String,
        onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {
        App.disposable.add(App.apiService.getClipList(noCourse)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.clipSet = it
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                })
    }

    fun getClipRepleList(noContent: String, filter: String, nowPage: String,
        onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {})
    {

        App.disposable.add(App.apiService.getClipRepleList(noContent, filter, nowPage)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.commentSet = it
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                })

//        val addr = prefixAddr + "getClipRepleList"
//
//        val list: List<Pair<String, String>>? = listOf(Pair("lesson_subitem_no", noContent),
//                Pair("filter_type", filter), Pair("now_page", nowPage))
//
//        try {
//            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
//                    .responseJson { request, response, result ->
//
//                        val json: String = result.get().content
//                        var element = JsonParser().parse(json)
//
//                        var list: ArrayList<Comment> = arrayListOf()
//
//                        element.asJsonObject.get("data_list")?.let {
//                            checkValidAndGet_Array(element.asJsonObject, "data_list")?.let {
//
//                                it?.forEach { e ->
//
//                                    val o = e.asJsonObject
//                                    list.add(Comment(
//                                            noBoardArticle = checkValidAndGet_Int(o, "board_article_no"),
//                                            noBoard = checkValidAndGet_Int(o, "board_no"),
//                                            nickname = checkValidAndGet(o, "nickname"),
//                                            contents = checkValidAndGet(o, "contents"),
//                                            countLike = checkValidAndGet_Int(o, "like_count"),
//                                            countView = checkValidAndGet_Int(o, "view_count"),
//                                            countReple = checkValidAndGet_Int(o, "reple_count"),
//                                            countReport = checkValidAndGet_Int(o, "report_count"),
//                                            dateUpdate = checkValidAndGet(o, "update_date"),
//                                            good = checkValidAndGet_Int(o, "is_good"),
//                                            noUser = checkValidAndGet_Int(o, "user_no"),
//                                            like = checkValidAndGet_Int(o, "user_likes"),
//                                            urlImage01 = checkValidAndGet(o, "profile_image"),
//                                            urlImage02 = checkValidAndGet(o, "profile_thumbnail_url"),
//                                            title = checkValidAndGet(o, "clip_name"),
//                                            file = File(name = checkValidAndGet(o, "unit_attach_file_name"),
//                                                    url = checkValidAndGet(o, "unit_attach_image_url"),
//                                                    thumbnail = checkValidAndGet(o, "unit_attach_thumbnail_url"))))
//                                }
//
//                                model.commentSet = list
//                            }
//                        }
//
//                        body()
//                    }
//        } catch (e: Exception) {
//
//        } finally {
//
//        }
    }

    fun getClipDetail(noContent: String, noCourse: String,
                      onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {})
    {

        var clip: Clip? = null
        model.clipSet.collectionClip!!.forEach {

            if(it.noContent.toString() == noContent && it.noCourse.toString() == noCourse) {
                clip = it
                return@forEach
            }
        }
        if(clip == null)
            return

        App.disposable.add(App.apiService.getClipDetail(noContent, noCourse)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        JCModel.clipCurrent = it
                        onSucess()
                    } else {
                        onFail()
                    }

                }) {
                    Log.e("Fail", "${it.message}")
                })


//        val addr = prefixAddr + "getClipDetail"
//
//        val list: List<Pair<String, String>>? = listOf(Pair("lesson_subitem_no", noContent), Pair("course_no", noCourse))
//
//        try {
//            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
//                .responseJson{ request, response, result ->
//
//                    val json:String = result.get().content
//                    var element = JsonParser().parse(json)
//
//                    model.clipCurrent.urlLink = checkValidAndGet(element.asJsonObject, "url")
//                    model.clipCurrent.countReple = checkValidAndGet_Int(element.asJsonObject, "reple_count")
//                    model.clipCurrent.favorite =  checkValidAndGet_Int(element.asJsonObject, "is_clip_like")
//                    model.clipCurrent.vertical = checkValidAndGet_Int(element.asJsonObject, "is_vertical_video")
//
//                    checkValidAndGet_Array(element.asJsonObject, "data_list")?.let {
//
//                        if(it.size() > 0) {
//
//                            for (i in 0..(it.size()-1)) {
//
//                                if(i == 0) {
//                                    model.clipCurrent.quiz.no = checkValidAndGet_Int(it[i].asJsonObject, "quiz_no")
//                                    model.clipCurrent.quiz.type = checkValidAndGet_Int(it[i].asJsonObject, "quiz_type")
//                                    model.clipCurrent.quiz.text = checkValidAndGet(it[i].asJsonObject, "quiz_text")
//                                    model.clipCurrent.quiz.urlFile = checkValidAndGet(it[i].asJsonObject, "quiz_text_file_url")
//                                    model.clipCurrent.quiz.score = checkValidAndGet_Int(it[i].asJsonObject, "quiz_score")
//                                    model.clipCurrent.quiz.level = checkValidAndGet_Int(it[i].asJsonObject, "difficulty")
//                                    model.clipCurrent.quiz.description = checkValidAndGet(it[i].asJsonObject, "description")
//                                }
//
//                                val noExample: Int = checkValidAndGet_Int(it[i].asJsonObject, "example_no")
//                                var noCorrect: Int = checkValidAndGet_Int(it[i].asJsonObject, "correct_example_no")
//
//                                model.clipCurrent.quiz.exampleSet.add(Example(
//                                        no = noExample,
//                                        order = checkValidAndGet_Int(it[i].asJsonObject, "display_order"),
//                                        type = checkValidAndGet_Int(it[i].asJsonObject, "example_type"),
//                                        text = checkValidAndGet(it[i].asJsonObject, "examples"),
//                                        isAnswer = if(noExample == noCorrect) 1 else 0
//                                ))
//                            }
//                        }
//                    }
//                }
//
//            body()
//
//        } catch(e: Exception) {
//
//        } finally {
//
//        }

    }

    private fun log(message: String) { App.log("[From Binder]  $message") }

    private fun checkValidAndGet(obj: JsonObject, name: String): String = if(obj.get(name).isJsonNull) "" else obj.get(name).asString
    private fun checkValidAndGet_Int(obj: JsonObject, name: String): Int = if(obj.get(name).isJsonNull) 0 else obj.get(name).asInt
    private fun checkValidAndGet_Array(obj: JsonObject, name: String): JsonArray? = if(obj.get(name).isJsonNull) null else obj.get(name).asJsonArray

}
