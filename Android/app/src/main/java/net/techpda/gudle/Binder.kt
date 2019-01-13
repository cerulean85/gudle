package net.techpda.gudle

import android.graphics.Color
import android.os.Handler
import android.os.Message
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class Binder {

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

    fun getMarketSet() {
            var hyoUrl: String = "http://favorite.cafe24app.com/img/img0"

            JCModel.marketSet!!.list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"16.jpg", Color.CYAN))
            JCModel.marketSet!!.list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"17.jpg", Color.LTGRAY))
            JCModel.marketSet!!.list.add(Movie("Forrest Gump",1994, hyoUrl+"17.jpg", Color.GREEN))
            JCModel.marketSet!!.list.add(Movie("Titanic",1997, hyoUrl+"18.jpg", Color.DKGRAY))
            JCModel.marketSet!!.list.add(Movie("Taxi",1998, hyoUrl+"18.jpg", Color.MAGENTA))
            JCModel.marketSet!!.list.add(Movie("Inception",1994, hyoUrl+"19.jpg", Color.WHITE))
            JCModel.marketSet!!.list.add(Movie("The Imitation Game",2014, hyoUrl+"20.jpg", Color.GREEN))
            JCModel.marketSet!!.list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl+"21.jpg", Color.CYAN))
            JCModel.marketSet!!.list.add(Movie("The Shawshank Redemption",1994, hyoUrl+"22.jpg", Color.LTGRAY))
    }

    fun getSystemInfo() {

//        FuelManager.instance.basePath = "https://mobile01.e-koreatech.ac.kr/"

        val addr = "http://mobile01.e-koreatech.ac.kr/getSystemInfo"

        var r: String = "Never...."
        try {
            addr.httpPost().header("Content-Type" to "application/x-www-form-urlencoded")
            .responseJson{ request, response, result ->
                log(result.get().content)

                val json: String  = result.get().content

            }

//            Fuel.post("getSystemInfo").responseJson { request, response, result ->
//
//                val json: String  = result.get().content
//                log.print(json)
////                model.dummySet = gson.fromJson(json, AlbumArray::class.java)
//
//            }
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

    fun getMain(page: String, category: String) {

        val addr = "http://mobile01.e-koreatech.ac.kr/getMain"

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
                            model.dataHome.course.add(Album(
                                    noCourse = it.asJsonObject.get("course_no").asInt,
                                    noContent = it.asJsonObject.get("course_content_no").asInt,
                                    title = it.asJsonObject.get("service_title").asString,
                                    nameCategory = it.asJsonObject.get("category_title").asString,
                                    urlThumb = it.asJsonObject.get("course_image_thumbnail_url").asString,
                                    countView = it.asJsonObject.get("view_count").asInt)) }

                        val d2 = element.asJsonObject.get("data_list2").asJsonArray
                        d2.forEach {
                            model.dataHome.banner.add(Article(
                                    no = it.asJsonObject.get("board_article_no").asInt,
                                    noBoard = it.asJsonObject.get("board_no").asInt,
                                    title = it.asJsonObject.get("title").asString,
                                    urlThumb = it.asJsonObject.get("thumbnail_url").asString))}

//                        model.dataHome = gson.fromJson(json, DataHome::class.java)
                        log(json)
                    }
        } catch(e: Exception) {

        } finally {

        }

    }

    private fun log(message: String) { App.log("[From Binder]  $message") }


}
