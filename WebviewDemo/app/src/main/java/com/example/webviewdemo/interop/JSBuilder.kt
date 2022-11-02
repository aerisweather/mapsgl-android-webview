package com.example.webviewdemo.interop

import android.content.Context
import android.webkit.WebView
import com.example.webviewdemo.R
import com.example.webviewdemo.model.MapsGLAnimationOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
 * Description: supported javascript functions to call
 *              Kotlin -> Javascript interOp
 *
 * NOTE:        See index.html for javascript functions
 */
class JSBuilder(
    private val context: Context,
    private val webView: WebView?,
) {
    fun echo() = requestJS("echo('abc')")

    /*
     * Core
     */
    fun getCenter() = requestJS("getCenter()")
    fun setCenter(lat: Double, lon: Double) = requestJS("setCenter(\"$lat\", \"$lon\")")
    fun getZoom() = requestJS("getZoom()")
    fun setZoom(zoom: Int) = requestJS("setZoom(\"$zoom\")")
    fun getBounds() = requestJS("getBounds()")
    fun getBearing() = requestJS("getBearing()")
    fun getPitch() = requestJS("getPitch()")
    fun getFov() = requestJS("getFov()")
    fun getLegend() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            requestJS("getLegend()")
        }
    }

    /*
     * Configuration
     */
    fun configureMap() {
        val id = context.resources.getString(R.string.aerisapi_client_id)
        val secret = context.resources.getString(R.string.aerisapi_client_secret)
        /*
         * TODO Implement Options
         */
        val options = MapsGLAnimationOptions().toString()
        requestJS("configureMap(\"$id\", \"$secret\", '$options')")
    }

    val initMap: () -> Unit = {
        CoroutineScope(Dispatchers.Main).launch {
            configureMap()
        }
    }

    /*
     * Layers
     */
    val addWeatherLayer: (name: String, options: String) -> Unit = { n, o ->
        requestJS("addWeatherLayer(\"$n\", \"$o\")")
    }

    val removeWeatherLayer: (name: String) -> Unit = { n ->
        requestJS("removeWeatherLayer(\"$n\")")
    }

    val hasWeatherLayer: (name: String) -> Unit = { n ->
        requestJS("hasWeatherLayer(\"$n\")")
    }

    fun query(lat: Double, lon: Double) {
        requestJS("query(\"$lat\", \"$lon\")")
    }

    /*
     * Timeline
     */
    fun getStartDate() = requestJS("getStartDate()")
    fun getEndDate() = requestJS("getEndDate()")
    fun getCurrentDate() = requestJS("getCurrentDate()")

    /*
     * Animation
     */
    fun play() = requestJS("play()")
    fun stop() = requestJS("stop()")
    fun restart() = requestJS("restart()")
    fun resume() = requestJS("resume()")

    private fun requestJS(str: String) {
        webView?.evaluateJavascript("javascript:$str;", null)
    }
}