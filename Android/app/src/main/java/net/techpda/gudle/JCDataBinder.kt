package net.techpda.gudle

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

}