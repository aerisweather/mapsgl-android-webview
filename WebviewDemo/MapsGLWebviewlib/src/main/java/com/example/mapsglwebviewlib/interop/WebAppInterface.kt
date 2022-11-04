package com.example.mapsglwebviewlib.interop

import android.webkit.JavascriptInterface

/*
 * Description: Javascript events
 *              Javascript -> Kotlin events interOp
 */
class WebAppInterface(
    private val decodeLegend: (jsonString: String) -> Unit,
    private val showSnackbar: (open: Boolean, msg: String?) -> Unit,
    private val updateTime: (time: String) -> Unit,
    private val jsBuilder: JSBuilder? = null
) {
    val TAG = "WebAppInterface"

    /*
     * Debugging - Echo(Log) and Error
     */
    @JavascriptInterface
    fun onError(err: String) {
        /* All html / javascript error comes here */
        android.util.Log.d(TAG, "onError:$err")
        showSnackbar(true, err)
    }

    @JavascriptInterface
    fun echo(msg: String) {
        android.util.Log.d(TAG, "echo:$msg")
        // use this to debug
        // showSnackbar(true, msg)
    }

    /*
     * Configuration
     */
    @JavascriptInterface
    fun onConfigureMap() {
        android.util.Log.d(TAG, "onConfigureMap")
    }

    @JavascriptInterface
    fun onWindowReady() {
        jsBuilder?.initMap?.let { it() }
        android.util.Log.d(TAG, "onWindowReady")
    }

    @JavascriptInterface
    fun onCtrlReady(timelineStart: String, timelineEnd: String) {
        /* controller is ready */
        android.util.Log.d(
            TAG,
            "onCtrlReady timelineStart:$timelineStart timelineEnd:$timelineEnd"
        )
    }

    @JavascriptInterface
    fun onCtrlLoadStart() {
        android.util.Log.d(TAG, "onCtrlLoadStart")
    }

    @JavascriptInterface
    fun onCtrlLoadComplete() {
        android.util.Log.d(TAG, "onCtrlLoadComplete")
    }

    /*
     * Core
     */
    @JavascriptInterface
    fun getCenter(lat: Float, lon: Float) {
        /* response */
        showSnackbar(true, "center lat:$lat lon:$lon")
        android.util.Log.d(TAG, "getCenter lat:$lat lon:$lon")
    }

    @JavascriptInterface
    fun getZoom(zoom: Int) {
        /* response (1,2,3...) */
        showSnackbar(true, "zoom:$zoom")
        android.util.Log.d(TAG, "getZoom zoom:$zoom")
    }

    @JavascriptInterface
    fun getBounds(east: Float, west: Float, north: Float, south: Float) {
        showSnackbar(true, "bounds east:$east west:$west \n north:$north south:$south")
        android.util.Log.d(TAG, "getBounds east:$east west:$west north:$north south:$south")
    }

    @JavascriptInterface
    fun getBearing(value: Int) {
        showSnackbar(true, "bearing:$value")
        android.util.Log.d(TAG, "getBearing bearing:$value")
    }

    @JavascriptInterface
    fun getPitch(value: Int) {
        showSnackbar(true, "pitch:$value")
        android.util.Log.d(TAG, "getPitch pitch:$value")
    }

    @JavascriptInterface
    fun getFov(value: Float) {
        showSnackbar(true, "fov:$value")
        android.util.Log.d(TAG, "getFov fov:$value")
    }

    @JavascriptInterface
    fun onMapClick(x: Int, y: Int, height: Int, width: Int, lat: Float, lon: Float) {
        /* return x,y points */
        android.util.Log.d(
            TAG,
            "onMapClick x:$x y:$y height:$height width:$width lat:$lat lon:$lon"
        )
    }

    @JavascriptInterface
    fun onCtrlMapClick(feature: String) {
        android.util.Log.d(TAG, "onCtrlMapClick feature:$feature")
    }

    @JavascriptInterface
    fun onCtrlZoom() {
        android.util.Log.d(TAG, "onCtrlZoom")
    }

    @JavascriptInterface
    fun onCtrlMove() {
        android.util.Log.d(TAG, "onCtrlMove")
    }

    /*
     * Layers
     */
    @JavascriptInterface
    fun getLegend(jsonString: String) {
        android.util.Log.d(TAG, "getLegend jsonString:$jsonString")
        decodeLegend(jsonString)
    }

    @JavascriptInterface
    fun onCtrlAddSource() {
        android.util.Log.d(TAG, "onCtrlAddSource")
    }

    @JavascriptInterface
    fun onCtrlAddLayer() {
        android.util.Log.d(TAG, "onCtrlAddLayer")
        jsBuilder?.getLegend()
    }

    @JavascriptInterface
    fun onCtrlRemoveSource() {
        android.util.Log.d(TAG, "onCtrlRemoveSource")
    }

    @JavascriptInterface
    fun onCtrlRemoveLayer() {
        android.util.Log.d(TAG, "onCtrlRemoveLayer")
        jsBuilder?.getLegend()
    }

    @JavascriptInterface
    fun onCtrlHasLayer(hasLayer: Boolean) {
        android.util.Log.d(TAG, "onCtrlHasLayer:$hasLayer")
    }

    @JavascriptInterface
    fun onQueryResult(data: String) {
        android.util.Log.d(TAG, "query:$data")
        showSnackbar(true, "query:\n${data.replace(":", "\n:")}")
    }

    /*
    *timeline
    */
    @JavascriptInterface
    fun getStartDate(time: String) {
        android.util.Log.d(TAG, "getStartDate:$time")
        showSnackbar(true, "getStartDate:\n${time.replace("(", "\n(")}")
    }

    @JavascriptInterface
    fun getEndDate(time: String) {
        android.util.Log.d(TAG, "getEndDate:$time")
        showSnackbar(true, "getEndDate:\n${time.replace("(", "\n(")}")
    }

    @JavascriptInterface
    fun getCurrentDate(time: String) {
        android.util.Log.d(TAG, "getCurrentDate:$time")
        showSnackbar(true, "getCurrentDate:\n${time.replace("(", "\n(")}")
    }

    /*
     * animation
     */
    @JavascriptInterface
    fun onAnimationStart() {
        android.util.Log.d(TAG, "onAnimationStart")
    }

    @JavascriptInterface
    fun onAnimationStop() {
        android.util.Log.d(TAG, "onAnimationStop")
    }

    @JavascriptInterface
    fun onAnimationPause() {
        android.util.Log.d(TAG, "onAnimationPause")
    }

    @JavascriptInterface
    fun onAnimationResume() {
        android.util.Log.d(TAG, "onAnimationResume")
    }

    @JavascriptInterface
    fun onAnimationAdvance(position: String, date: String) {
        android.util.Log.d(TAG, "onAnimationAdvance position:$position date:$date")
        updateTime(date)
    }
}