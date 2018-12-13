package net.techpda.gudle

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Display
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.github.kittinunf.fuel.core.FuelManager

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    public var imageArr: ImageArray? = null
    fun getImageArr() : ImageArray? { return imageArr }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        FuelManager.instance.basePath = "http://favorite.cafe24app.com";
//
//        try {
//            Fuel.get("getDummyAll").responseJson { request, response, result ->
//                var gson = Gson()
//                val json: String  = result.get().content;
//                val albumArr: AlbumArray = gson.fromJson(json, AlbumArray::class.java)
//
//                var str: String = "";
//                str += albumArr.list[0].title
//                str += albumArr.list[1].title
//                str += albumArr.list[2].title
//                str += albumArr.list[3].title
//                txView!!.text = str
//            }
//        } catch (e: Exception) {
//            txView!!.text = e.message
//        } finally {
//
//        }
//
//        try {
//            Fuel.get("getImageAll").responseJson { request, response, result ->
//                var gson = Gson()
//                val json: String  = result.get().content;
//                val imageArr: ImageArray = gson.fromJson(json, ImageArray::class.java)
//
//                var str: String = FuelManager.instance.basePath +  "/img/" + imageArr.list[0].name;
//                Picasso.get().load(str).into(imgView)
//
//            }
//        } catch (e: Exception) {
//
//        } finally {
//
//        }
//        var rv = recyclerView { };

        FuelManager.instance.basePath = "http://favorite.cafe24app.com";

        try {
            Fuel.get("getImageAll").responseJson { request, response, result ->
                var gson = Gson()
                val json: String  = result.get().content;
                imageArr = gson.fromJson(json, ImageArray::class.java)

    //                var str: String = FuelManager.instance.basePath +  "/img/" + imageArr.list[0].name;
//                Picasso.get().load(str).into(imgView)

            }
        } catch (e: Exception) {

        } finally {

        }

        verticalLayout {

//            var vp = viewPager {
//                layoutParams = LinearLayout.LayoutParams(matchParent, dip(300))
//            }
//            vp.adapter = CustomPagerAdapter(this@MainActivity, pages)
//
//            scrollView {
//
//
//
//            }

            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this@MainActivity).orientation))

            try {
                Fuel.get("getDummyAll").responseJson { request, response, result ->
                    var gson = Gson()
                    val json: String  = result.get().content;
                    val albumArr: AlbumArray = gson.fromJson(json, AlbumArray::class.java)

                    rv.adapter = MovieAdapter(context, albumArr.list)

//                var str: String = "";
//                str += albumArr.list[0].title
//                str += albumArr.list[1].title
//                str += albumArr.list[2].title
//                str += albumArr.list[3].title
//                txView!!.text = str
                }
            } catch (e: Exception) {
//            txView!!.text = e.message
            } finally {

            }




        }









/* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA



        /*

        var pages:ArrayList<Model> = arrayListOf()
        pages.add(Model(R.string.one, R.layout.layout_one))
        pages.add(Model(R.string.two, R.layout.layout_two))
        pages.add(Model(R.string.three, R.layout.layout_three))

//        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
//        viewPager.adapter = CustomPagerAdapter(this, pages)

        // ListView Test

        var hyoUrl: String = "http://heraldk.com/wp-content/uploads/2018/04/20180407000033_0.jpg"

//        var d: Drawable = resources.getDrawable(R.drawable.p1)  drawable image call

        var list: ArrayList<Movie> = arrayListOf()
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))
        list.add(Movie("Forrest Gump Sherlock Holmes The Shkddab1 Redemption",2009, hyoUrl, Color.CYAN))
        list.add(Movie("The Shawshank Redemption",1994, hyoUrl, Color.LTGRAY))
        list.add(Movie("Forrest Gump",1994, hyoUrl, Color.GREEN))
        list.add(Movie("Titanic",1997, hyoUrl, Color.DKGRAY))
        list.add(Movie("Taxi",1998, hyoUrl, Color.MAGENTA))
        list.add(Movie("Inception",1994, hyoUrl, Color.WHITE))
        list.add(Movie("The Imitation Game",2014, hyoUrl, Color.GREEN))

        verticalLayout {

            var vp = viewPager {
                layoutParams = LinearLayout.LayoutParams(matchParent, dip(300))
            }
            vp.adapter = CustomPagerAdapter(this@MainActivity, pages)

            scrollView {



            }

            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this@MainActivity).orientation))
            rv.adapter = MovieAdapter(context, list)
        }



AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA */

*/


//        val queue = Volley.newRequestQueue(this)
//        val url: String = "http://favorite.cafe24app.com/getDummyAll"
//
//        val stringReq = StringRequest(Request.Method.GET, url,
//                Response.Listener<String> { response ->
//                    var strResp = response.toString()
//                    val jsonObj: JSONObject = JSONObject(strResp)
//                    val jsonArray: JSONArray = jsonObj.getJSONArray("list")
//                    var title: String = ""
//                    var list: ArrayList<Movie> = arrayListOf()
//                    for(i in 0 until jsonArray.length()) {
//                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
//
//
////                        list.add(Movie(jsonInner.get("title").toString(),2009, hyoUrl, Color.CYAN))
//
//
////                         title +=
//
//                    }
//
////                    txView!!.text = jsonObj.toString()
////                    val jsonObj: JSONObject = JSONObject(str)
//
//                },
//                Response.ErrorListener { txView!!.text = "That didn't work" }
//                                            /* !!는 강제로 느낌표가 아님을 선언 */
//        )
//
//
//        queue.add(stringReq)






        //WebView Test
//        loadWebView("https://m.post.naver.com/viewer/postView.nhn?volumeNo=16838844&memberNo=25041664&vType=VERTICAL")







    }




//    override fun onBackPressed() {
//        if(webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            JCDialog(this@MainActivity).exit()
//        }
//    }
//
//    fun loadWebView(url: String)
//    {
//        // WebView settings
//        JCWebViewSettings(webView).setDefault()
//
//        webView.loadUrl(url)
//        webView.fitsSystemWindows = true
//
//        //  if SDK version is greater of 19 then activate hardware acceleration
//        //  otherwise activate software acceleration
//        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
//
//        //풀모드일 때는 이쪽으로 빠짐
//        webView.webChromeClient = object: JCFullscreenableChromeClient(this) {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                print("progress: $newProgress")
//            }
//        }
//
//        //일반모드일 때.
//        webView.webViewClient = object: WebViewClient() {
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//
//
//                toast("Page loading.")
//
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                toast("Page Finished.")
//            }
//        }
//    }





}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}




