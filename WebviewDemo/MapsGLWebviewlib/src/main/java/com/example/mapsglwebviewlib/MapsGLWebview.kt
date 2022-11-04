package com.example.mapsglwebviewlib

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mapsglwebviewlib.interop.JSBuilder
import com.example.mapsglwebviewlib.interop.WebAppInterface

class MapsGLWebview(
    context: Context,
    decodeLegend: (jsonString: String) -> Unit,
    showSnackbar: (open: Boolean, msg: String?) -> Unit,
    updateTime: (time: String) -> Unit,
    getJSBuilder: (builder: JSBuilder) -> Unit
) : WebView(context) {

    companion object {
        val mUrl = "file:///android_asset/mapview_android.html"
    }

    private val jsBuilder: JSBuilder = JSBuilder(context, this)

    init {
        getJSBuilder(jsBuilder)

        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        webViewClient = WebViewClient()
        settings.javaScriptEnabled = true
        addJavascriptInterface(
            WebAppInterface(decodeLegend, showSnackbar, updateTime, jsBuilder),
            "Android"
        )
        load()
    }

    fun load() {
        loadUrl(mUrl)
    }
}