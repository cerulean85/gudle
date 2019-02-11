package net.techpda.gudle

import android.app.ProgressDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.Window
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
import com.nshmura.recyclertablayout.RecyclerTabLayout
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
//import kotlinx.coroutines.experimental.*
import net.techpda.gudle.R.id.async
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager
import org.json.JSONArray
import org.json.JSONObject
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    //https://github.com/nshmura/RecyclerTabLayout

//    private lateinit var pagerAdapter: CategoryPagerAdapter
//    private lateinit var pagerAdapter2: CategoryPagerAdapter
//    private lateinit var recyclerTabLayout: RecyclerTabLayout

    private var mBound: Boolean = false


//    private fun setViewPagerAdapter()
//    {
//        viewPager.adapter = pagerAdapter
//        recyclerTabLayout.setUpWithViewPager(viewPager)
//    }

    override fun onStart() {
        super.onStart()

        if(App.useDiagnois) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   // 마시멜로우 이상일 경우
                if (!Settings.canDrawOverlays(this)) {              // 체크
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:$packageName"))
                    this.startActivityForResult(intent, 1)
                } else {


                    bindService(Intent(this, DiagnosisService::class.java), mServiceConn, Context.BIND_AUTO_CREATE)
//                val intent = Intent(applicationContext, MyService::class.java)
//
//                startService(Intent(applicationContext, MyService::class.java))
                }
            } else {
                bindService(Intent(this, DiagnosisService::class.java), mServiceConn, Context.BIND_AUTO_CREATE)
            }
        }

    }
    override fun onStop() {
        super.onStop()

        if(mBound) {
            unbindService(mServiceConn)
            mBound = false
        }


    }

    var mServiceConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder: DiagnosisService.DiagnosisServiceBinder = service as DiagnosisService.DiagnosisServiceBinder
            App.mService = binder.service
            mBound = true
        }
    }

    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var context: Context? = null
    var tabHome: TabHome? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        App.displayWidth = displayMetrics.widthPixels
        App.heightHomeCourse = (App.displayWidth * (460.0f/1242.0f)).toInt()

        context = applicationContext

//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
//                 .permitAll()
//                 .detectAll()
//                 .penaltyDeath()
//                 .penaltyDeathOnNetwork()
//                 .penaltyDialog()
//                 .penaltyDropBox()
//                 .penaltyFlashScreen()
//                 .penaltyLog()
//                 .build())
//        } else {
//            var policy: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
//            policy.detectAll().penaltyDeath().penaltyDropBox().penaltyLog()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) policy.penaltyDeathOnCleartextNetwork()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) policy.penaltyDeathOnFileUriExposure()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) policy.permitNonSdkApiUsage()
//            StrictMode.setVmPolicy(policy.build())
//        }



        App.pref.test = "test"
//        App.binder.getSystemInfo()


        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                print("onPageScrollStateChanged")
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                print("onPageScrolled")
            }
            override fun onPageSelected(position: Int) {
                print("onPageSelected")
//                if(position == 0) {
//                    setupViewPager(viewPager!!)
//                    tabLayout!!.setupWithViewPager(viewPager!!)
//
//                    tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
//                    tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
//                    tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
//                    tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
//                }

            }

        })

        tabLayout = findViewById(R.id.tabs) as TabLayout


        App.binder.getSystemInfo({

            setupViewPager(viewPager!!)
            tabLayout!!.setupWithViewPager(viewPager!!)
            tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
            tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
            tabLayout!!.getTabAt(2)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)
            tabLayout!!.getTabAt(3)!!.setIcon(R.drawable.ic_mtrl_chip_checked_black)

        })

//        actionBar.setDisplayShowHomeEnabled(true)
//        actionBar.setIcon(R.drawable.ic_icon_search)


//        tabLayout!!.setupWithViewPager(viewPager)

//        recyclerTabLayout = findViewById(R.id.recyclerTabLayout)
//
//        var list: ArrayList<Movie> = MovieHelper.getMovies()
//        pagerAdapter = CategoryPagerAdapter(supportFragmentManager, list)
//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//                print("onPageScrollStateChanged")
//            }
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                print("onPageScrolled")
//            }
//            override fun onPageSelected(position: Int) {
//                print("onPageSelected")
//            }
//
//        })
//
//        marketingPager.adapter = HomeBannerListAdapter(this, JCModel.marketSet!!.list)
//        marketingPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//
//            override fun onPageScrollStateChanged(state: Int) {
//                print("onPageScrollStateChanged")
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                print("onPageScrolled")
//            }
//            override fun onPageSelected(position: Int) {
//                print("onPageSelected")
//            }
//        })



//        setContentView(R.layout.activity_main)
    //        webView.loadUrl("http://220.68.94.74")
//        loadWebView("http://220.68.94.74")
//        loadWebView("http://220.68.94.74/2.mp4")
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(applicationContext, supportFragmentManager)

        tabHome = TabHome()
        adapter.addFragment(tabHome!!, "x")
        adapter.addFragment(TabFavorite(), "y")
        adapter.addFragment(TabPoint(), "z")
        adapter.addFragment(TabMore(), "w")


        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(context: Context?, manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {

            return ""

            var sb: SpannableStringBuilder? = SpannableStringBuilder(" ")
            var span: ImageSpan? =  ImageSpan(App.util.getDrawable(context!!, R.drawable.ic_icon_search))

            var d: Drawable = App.util.getDrawable(context!!, R.drawable.ic_mtrl_chip_checked_black)
            d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
            span = ImageSpan(d, ImageSpan.ALIGN_BASELINE)
            sb!!.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            return sb
            return mFragmentTitleList[position]
        }
    }



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

//        var mHandler: MessageHandler = MessageHandler(this)   //핸들러 사용법


//

////        recyclerTabLayout.setIndicatorColor(R.color.colorPrimary)
////        recyclerTabLayout.setBackgroundColor(R.color.colorPrimaryDark)







//        JCModel.loadImageArr(recyclerTabLayout, viewPager)
//        JCModel.loadDummyArr(recyclerTabLayout, viewPager)
//        recyclerTabLayout.setIndicatorColor(R.color.colorPrimary)
//        recyclerTabLayout.setBackgroundColor(R.color.colorPrimaryDark)
//        recyclerTabLayout.setUpWithViewPager(viewPager)





//        tabLayout.setupWithViewPager(viewPager)


//        val tabLayout = findViewById<View>(R.id.tablayout) as TabLayout
//        tablayout.setupWithViewPager(viewPager)

        /*
        var imageSet: ImageArray? = null
        FuelManager.instance.basePath = "http://favorite.cafe24app.com";

        try {
            Fuel.get("getImageAll").responseJson { request, response, result ->
                var gson = Gson()
                val json: String  = result.get().content;
                imageSet = gson.fromJson(json, ImageArray::class.java)
            }
        } catch (e: Exception) {

        } finally {

        }

        verticalLayout {

            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this@MainActivity).orientation))

            try {
                Fuel.get("getDummyAll").responseJson { request, response, result ->
                    var gson = Gson()
                    val json: String  = result.get().content;
                    val albumArr: AlbumArray = gson.fromJson(json, AlbumArray::class.java)

                    rv.adapter = HomeContentsListAdapter(context, albumArr.list, imageSet!!.list)
                }
            } catch (e: Exception) {
            } finally {
            }
        }
        */









/* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA



        /*

        var pages:ArrayList<Model> = arrayListOf()
        pages.add(Model(R.string.one, R.layout.layout_one))
        pages.add(Model(R.string.two, R.layout.layout_two))
        pages.add(Model(R.string.three, R.layout.layout_three))

//        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
//        viewPager.adapter = HomeBannerListAdapter(this, pages)

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
            vp.adapter = HomeBannerListAdapter(this@MainActivity, pages)

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












//    override fun onBackPressed() {
//        if(webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            JCDialog(this@MainActivity).exit()
//        }
//    }





fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}





/* 스태틱으로 만들기 */
//    companion object {

//        var imageSet: ImageArray? = null

//        fun baz() {
//            // Do something
//        }
//    }


//class MessageHandler(ref: MainActivity): WeakReferenceHandler<MainActivity>(ref) {
//    override fun handleMessage(mReference: MainActivity?, msg: Message) {
//        if(mReference==null) return
//        when(msg.what) {
//            0 -> {
//                mReference.recyclerTabLayout.setUpWithViewPager(mReference.viewPager)
//
//
//            }
//
//        }
//    }
//}
//
//fun m() = runBlocking {
//    var job = launch {
//        try {
//
//            withTimeout(3000L) {
//                println("@@@ㅎㅎ 시작되었어요.")
//            }
//        } catch (e: TimeoutCancellationException) {
//            println("@@@ㅎㅎ 타임아웃!.")
//        }
//    }
//    job.join()
//    println("@@@ㅎㅎ 작업이 정상적으로 진행되었다면 여기가 호출 되겠죠!")
//}
//
//
//
//suspend fun test() {
//    delay(500L)
//    println("@@@ㅎㅎ 테스트 중!")
//}

