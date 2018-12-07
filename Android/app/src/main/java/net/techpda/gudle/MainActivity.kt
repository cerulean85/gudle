package net.techpda.gudle

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Display
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_one.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load the image with Picasso
//        Picasso.get().load("http://heraldk.com/wp-content/uploads/2018/04/20180407000033_0.jpg").into(imageView)

//        var list:ArrayList<Model> = arrayListOf()
//        list.add(Model(R.string.one, R.layout.layout_one))
//        list.add(Model(R.string.two, R.layout.layout_two))
//        list.add(Model(R.string.three, R.layout.layout_three))
//
//        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
//        viewPager.adapter = CustomPagerAdapter(this, list)

        // ListView Test
        /*
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

        verticalLayout {

            var rv = recyclerView { }
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this@MainActivity).orientation))
            rv.adapter = MovieAdapter(context, list)
        }
        */

        //WebView Test
        val settings = webView.settings

        settings.javaScriptEnabled = true

        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true

        settings.textZoom = 125

        settings.blockNetworkImage = false

        settings.loadsImagesAutomatically = true

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true
        }

        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true

        // WebView settings
        webView.fitsSystemWindows = true


        /*
            if SDK version is greater of 19 then activate hardware acceleration
            otherwise activate software acceleration
        */
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        webView.webChromeClient = FullscreenableChromeClient(this)
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
//
//        webView.webChromeClient = object: WebChromeClient() {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                print("progress: $newProgress")
//            }
//        }


        webView.loadUrl("https://m.post.naver.com/viewer/postView.nhn?volumeNo=16838844&memberNo=25041664&vType=VERTICAL")

    }

    private fun showAppExitDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Please confirm")
        builder.setMessage("No back history found, want to exit the app?")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes", { _, _ ->
            super@MainActivity.onBackPressed()
        })

        builder.setNegativeButton("No", { _, _ ->
            toast("thank you.")
        })

        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()
            toast("Going to back history")
        } else {
            showAppExitDialog()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        var d: Display = windowManager.defaultDisplay
        var orientation = Configuration.ORIENTATION_UNDEFINED
//        if(d.)
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

data class Movie(var title: String, var year: Int, var image: String, var color: Int)
data class Model(val titleResId: Int, val layoutResId: Int)
