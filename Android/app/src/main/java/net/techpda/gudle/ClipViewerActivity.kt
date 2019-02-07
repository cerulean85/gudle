package net.techpda.gudle

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_clip_viewer.*

class ClipViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip_viewer)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        btnBack.setOnClickListener {

            onBackPressed()

        }

        loadWebView(JCModel.clipCurrent.urlLink)
    }


        fun loadWebView(url: String)
    {
        // WebView settings
        JCWebViewSettings(webView).setDefault()

        webView.loadUrl(url)
        webView.fitsSystemWindows = true

        //  if SDK version is greater of 19 then activate hardware acceleration
        //  otherwise activate software acceleration
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        //풀모드일 때는 이쪽으로 빠짐
        webView.webChromeClient = object: JCFullscreenableChromeClient(this) {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                print("progress: $newProgress")
            }
        }

        //일반모드일 때.
        webView.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {


                toast("Page loading.")

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                toast("Page Finished.")
            }
        }
    }
}
