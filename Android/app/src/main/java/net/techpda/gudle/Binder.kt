package net.techpda.gudle

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlin.system.exitProcess

class Binder {

    private var model: JCModel = JCModel



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


    var noCurrentPage: Int = 0
    fun getMain(nowPage: String, noCategory: String,
                onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {

        noCurrentPage = when (nowPage) {
            Status.CALL_PAGE_FIRST.value -> 1       //첫번째 페이지 호출
            Status.CALL_PAGE_NEXT_AUTO.value -> noCurrentPage+1 //다음 페이지 자동 호출
            else -> nowPage.toInt() //입력한 파라미터에 대한 페이지 호출
        }

        App.disposable.add(App.apiService.getMain(noCurrentPage.toString(), noCategory)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {
                        if(noCurrentPage==1) JCModel.main = it
                        else {
                            it.collectionCourse!!.forEach { e-> JCModel.main.collectionCourse!!.add(e) }
                            JCModel.main.countCourse = it.countCourse
                        }
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

    var noCategoryCurrentNo: Int = 0
    fun getCourseCollectionClassfiedByCategory(nowPage: String, noCategory: String,
                                               onSucess: () -> Unit = {}, onFail: () -> Unit = {}, onSubscribe: () -> Unit = {}, onTerminate: () -> Unit = {}) {

        noCategoryCurrentNo = when (nowPage) {
            Status.CALL_PAGE_FIRST.value -> 1       //첫번째 페이지 호출
            Status.CALL_PAGE_NEXT_AUTO.value -> noCategoryCurrentNo+1 //다음 페이지 자동 호출
            else -> nowPage.toInt() //입력한 파라미터에 대한 페이지 호출
        }

        App.disposable.add(App.apiService.getMain(noCategoryCurrentNo.toString(), noCategory)

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe() }
                .doOnTerminate { onTerminate() }
                .subscribe({ it ->

                    if (it.isOpen && it.isSuccess) {

                        if(noCategoryCurrentNo==1)
                            JCModel.collectionCoruseClassifiedByCategory[noCategory] = it.collectionCourse?.let { it } ?: arrayListOf()
                        else {
                            it.collectionCourse!!.forEach { e-> JCModel.collectionCoruseClassifiedByCategory[noCategory]!!.add(e) }
                        }

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
    }
}
