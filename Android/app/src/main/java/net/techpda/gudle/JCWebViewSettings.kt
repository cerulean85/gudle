package net.techpda.gudle

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*

class JCWebViewSettings(val webView: WebView)  {


    public fun setDefault()
    {
        val settings = webView.settings

        //자바스크립트 사용
        settings.javaScriptEnabled = true


        //캐시 사용 여부 설정
        settings.setAppCacheEnabled(true)

        //캐시가 사용되는 방식을 재정의
        //LOAD_CACHE_ELSE_NETWORK 기간이 만료돼 캐시를 사용할 수 없을 경우 네트워크를 사용합니다.
        //LOAD_CACHE_ONLY 네트워크를 사용하지 않고 캐시를 불러옵니다.
        //LOAD_DEFAULT 기본적인 모드로 캐시를 사용하고 만료된 경우 네트워크를 사용해 로드합니다.
        //LOAD_NORMAL 기본적인 모드로 캐시를 사용합니다.
        //LOAD_NO_CACHE 캐시모드를 사용하지 않고 네트워크를 통해서만 호출합니다.
        settings.cacheMode = WebSettings.LOAD_DEFAULT

        //캐시파일의 경로 설정
        //settings.setAppCachePath(cacheDir.path)

        //화면의 줌 컨트롤과 제스처를 사용하여 화면 확대 여부 설정
        settings.setSupportZoom(true)

        //내장 줌 컨트롤 사용 여부
        settings.builtInZoomControls = true

        //내장 줌 메커니즘을 사용할 때 웹뷰가 화면에 줌 컨트롤을 표시할지 여부 설정
        settings.displayZoomControls = true



        //기본적인 텍스트 줌
        settings.textZoom = 100

        //네트워크의 이미지의 리소스를 로드하지 않음
        settings.blockNetworkImage = false


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true
        }

        //settings.pluginState = WebSettings.PluginState.ON

        //웹뷰가 wide viewport를 사용하도록 설정하는 속성으로, html콘텐츠가 웹뷰에 맞게 나타나도록 함
        settings.useWideViewPort = true

        settings.loadWithOverviewMode = true

        //팝업창 띄울 때 해상 속성 추가해야 window.open()이 제대로 작동
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        // 로컬 스토리지 사용 여부 설정하는 속성으로, 팝업창 등을 '하루동안 보지 않기'기능 사용에 필요
        settings.domStorageEnabled = true

        // 여러 개의 윈도우를 사용할 수 있도록 설정
        settings.setSupportMultipleWindows(true)

        //웹뷰가 앱에 등록되어 있는 이미지 리소스 자동으로 로드하도록 설정
        settings.loadsImagesAutomatically = true

        //웹뷰 내에서 콘텐트 URL 접근 허용 여부 결정
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)


        settings.allowUniversalAccessFromFileURLs = true

        //웹뷰 내에서 파일 액세스 활성화 여부
        settings.allowFileAccess = true

        //웹에서 해당 속성 통해 앱에서 띄운 웹뷰로 인지할 수 있도록 함
//        settings.userAgentString = ""
    }


}