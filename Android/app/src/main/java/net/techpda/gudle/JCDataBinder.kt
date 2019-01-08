package net.techpda.gudle

import android.graphics.Color
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.Gson

object JCDataBinder {

    private var model: JCModel = JCModel
    private var gson: Gson = Gson()

    init {
        FuelManager.instance.basePath = "http://favorite.cafe24app.com";
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

                val json: String  = result.get().content;
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

}