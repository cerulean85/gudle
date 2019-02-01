package net.techpda.gudle

import android.graphics.Color
import android.os.Handler
import android.os.Message
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import com.google.gson.*

class Binder {

    private val prefixAddr: String = "https://mobile01.e-koreatech.ac.kr/"

    private var model: JCModel = JCModel
    private var gson: Gson = Gson()

    init {
        FuelManager.instance.basePath = "http://favorite.cafe24app.com";

//        FuelManager.instance.basePath = "https://mobile01.e-koreatech.ac.kr/";
    }

    fun getImageAll() {
        try {
            Fuel.get("getImageAll").responseJson { request, response, result ->
                println("thread test00")
                var gson = Gson()
                val json: String  = result.get().content
                JCModel.imageSet = gson.fromJson(json, ImageArray::class.java)

            }
        } catch (e: Exception) {
            println(e.toString())
        } finally {

        }
    }

    fun getDummyAll(body:()->Unit) {
        try {
            Fuel.get("getDummyAll").responseJson { request, response, result ->

                val json: String  = result.get().content
                model.dummySet = gson.fromJson(json, AlbumArray::class.java)

                body()
            }
        } catch (e: Exception) {
        } finally {
        }
    }

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

    fun getSystemInfo(body:()->Unit = {}) {

//        FuelManager.instance.basePath = "https://mobile01.e-koreatech.ac.kr/"

        val addr = prefixAddr + "getSystemInfo"

        var r: String = "Never...."
        try {
            addr.httpPost().header("Content-Type" to "application/x-www-form-urlencoded")
            .responseJson{ request, response, result ->
                log(result.get().content)

                val json:String = result.get().content
                var element = JsonParser().parse(json)

                model.systemInfo.urlEvent = element.asJsonObject.get("event_popup_url")?.asString
                model.systemInfo.versionMinOS = element.asJsonObject.get("android_min_version")?.asString
                model.systemInfo.eventNoBard = element.asJsonObject.get("board_no")?.asInt

//                model.systemInfo.eventNoBard = element.asJsonObject.get("board_no").isJsonNull asInt
                model.systemInfo.eventNoBoardArticle = element.asJsonObject.get("board_article_no")?.asInt
                model.systemInfo.eventTitle = element.asJsonObject.get("popup_title")?.asString
                model.systemInfo.eventNoPopup = element.asJsonObject.get("popup_no")?.asInt
                model.systemInfo.eventUrlImage = element.asJsonObject.get("image_url")?.asString

                model.systemInfo.category.add(Category(0, "전체"))
                val cate = element.asJsonObject.get("data_list").asJsonArray
                cate.forEach {
                    model.systemInfo.category.add(Category(
                            noCategory = it.asJsonObject.get("category_no").asInt,
                            title = it.asJsonObject.get("title").asString))}
            }

            body()

        } catch (e: Exception) {
        } finally {


        }

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
    }

    fun getMain(page: String, category: String, body:()->Unit = {}) {

        val addr = prefixAddr + "getMain"

        val list: List<Pair<String, String>>? = listOf(Pair("now_page", page), Pair("category_no", category))

        try {
            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseJson{ request, response, result ->

                        val json:String = result.get().content
                        var element = JsonParser().parse(json)

                        model.dataHome.countBanner = element.asJsonObject.get("banner_count").asInt
                        model.dataHome.countCourse = element.asJsonObject.get("total_course_count").asInt
                        model.dataHome.isNewAlarm = element.asJsonObject.get("exists_new_alarm").asInt

                        val d1 = element.asJsonObject.get("data_list1").asJsonArray
                        d1.forEach {

                            var urlThumbCourseImage = it.asJsonObject.get("course_image_thumbnail_url")
                            model.dataHome.course.add(Album(
                                    noCourse = it.asJsonObject.get("course_no").asInt,
                                    noContent = it.asJsonObject.get("course_content_no").asInt,
                                    title = it.asJsonObject.get("service_title").asString,
                                    nameCategory = it.asJsonObject.get("category_title").asString,
                                    urlThumb = if(urlThumbCourseImage.isJsonNull) "" else urlThumbCourseImage.asString,
                                    countView = it.asJsonObject.get("view_count").asInt)) }

                        val d2 = element.asJsonObject.get("data_list2").asJsonArray
                        d2.forEach {
                            model.dataHome.banner.add(Article(
                                    no = it.asJsonObject.get("board_article_no").asInt,
                                    noBoard = it.asJsonObject.get("board_no").asInt,
                                    title = it.asJsonObject.get("title").asString,
                                    urlThumb = it.asJsonObject.get("thumbnail_url").asString))}



                        body()
                    }
        } catch(e: Exception) {

        } finally {

        }
    }

    fun getCourseByCategory(category: String, body:()->Unit = {}) {
        val addr = prefixAddr + "getMain"

        val list: List<Pair<String, String>>? = listOf(Pair("now_page", "1"), Pair("category_no", category))

        try {
            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseJson{ request, response, result ->

                        val json:String = result.get().content
                        var element = JsonParser().parse(json)

                        var list: ArrayList<Album> = arrayListOf()

                        val d1 = element.asJsonObject.get("data_list1").asJsonArray
                        d1.forEach {
                            list.add(Album(
                                    noCourse = it.asJsonObject.get("course_no").asInt,
                                    noContent = it.asJsonObject.get("course_content_no").asInt,
                                    title = it.asJsonObject.get("service_title").asString,
                                    nameCategory = it.asJsonObject.get("category_title").asString,
                                    urlThumb = it.asJsonObject.get("course_image_thumbnail_url").asString,
                                    countView = it.asJsonObject.get("view_count").asInt)) }

                        model.mapCourse[category] = list

                        body()
                    }
        } catch(e: Exception) {

        } finally {

        }
    }

    fun getCourseDetail(noCourse: String, body:()->Unit = {}) {
        val addr = prefixAddr + "getCourseDetail"

        val list: List<Pair<String, String>>? = listOf(Pair("course_no", noCourse))

        try {
            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseJson{ request, response, result ->

                        val json:String = result.get().content
                        var element = JsonParser().parse(json)

                        model.detailCourse.noCourse = element.asJsonObject.get("course_no").asInt
                        model.detailCourse.title = element.asJsonObject.get("service_title").asString
                        model.detailCourse.noCourseContent = element.asJsonObject.get("course_content_no").asInt

                        var obj = element.asJsonObject
                        model.detailCourse.studyGoal = checkValidAndGet( obj,"study_goal")
                        model.detailCourse.studyTarget = checkValidAndGet( obj,"study_target")
                        model.detailCourse.studyRef = checkValidAndGet( obj,"study_ref")
                        model.detailCourse.studyComplete = checkValidAndGet( obj,"study_complete")
                        model.detailCourse.teacherInfo = checkValidAndGet( obj,"teacher_info")
                        model.detailCourse.studyNcs = checkValidAndGet( obj,"study_ncs")
                        model.detailCourse.urlImage01 = checkValidAndGet( obj,"course_info_image_url")
                        model.detailCourse.urlImage02 = checkValidAndGet( obj,"course_image_url")
                        model.detailCourse.urlImage03 = checkValidAndGet( obj,"course_image_thumbnail_url")
                        model.detailCourse.countView = element.asJsonObject.get("view_count").asInt
                        model.detailCourse.description  = checkValidAndGet( obj,"short_description")
                        model.detailCourse.introduce  = checkValidAndGet( obj,"course_introduce")

                        body()
                    }
        } catch(e: Exception) {

        } finally {

        }
    }

    fun getClipList(noCourse: String, body:()->Unit = {}) {
        val addr = prefixAddr + "getClipList"

        val list: List<Pair<String, String>>? = listOf(Pair("no_course", noCourse))

        try {
            addr.httpPost(list).header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseJson{ request, response, result ->

                        val json:String = result.get().content
                        var element = JsonParser().parse(json)

                        var list: ArrayList<Clip> = arrayListOf()

                        model.detailCourse.flagDelivery = checkValidAndGet_Int(element.asJsonObject,"delivery_flag")

                        val d1 = element.asJsonObject.get("data_list1").asJsonArray
                        d1.forEach {
                            list.add(Clip(
                                noCourse = it.asJsonObject.get("course_no").asInt,
                                noContent = it.asJsonObject.get("lesson_subitem_no").asInt,
                                title = it.asJsonObject.get("title").asString,
                                titleContent = it.asJsonObject.get("lesson_title").asString,
                                timeFinished = it.asJsonObject.get("required_learning_time_in_secondes").asInt,
                                order = it.asJsonObject.get("display_order").asInt,
                                urlImage01 = it.asJsonObject.get("image_url").asString,
                                urlImage02 = it.asJsonObject.get("thumbnail_url").asString,
                                countLike = it.asJsonObject.get("like_count").asInt,
                                countView = it.asJsonObject.get("view_count").asInt))}

                        model.clipSet = list

                        var list2: ArrayList<Attendance> = arrayListOf()

                        val d2 = element.asJsonObject.get("data_list2").asJsonArray
                        d2.forEach {
                            list2.add(Attendance(
                                noContent = it.asJsonObject.get("lesson_subitem_no").asInt,
                                codeAttendance = it.asJsonObject.get("attendance_code_code").asInt))}

                        model.attendanceSet = list2

                        body()
                    }
        } catch(e: Exception) {

        } finally {

        }
    }

    private fun log(message: String) { App.log("[From Binder]  $message") }

    private  fun checkValidAndGet(obj: JsonObject, name: String): String = if(obj.get(name).isJsonNull) "" else obj.get(name).asString
    private  fun checkValidAndGet_Int(obj: JsonObject, name: String): Int = if(obj.get(name).isJsonNull) 0 else obj.get(name).asInt


}
