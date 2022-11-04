package com.example.webviewdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mapsglwebviewlib.MapsGLWebview
import com.example.mapsglwebviewlib.interop.JSBuilder
import com.example.webviewdemo.composable.ComposeLayerDialog
import com.example.webviewdemo.composable.ComposeSnackbar
import com.example.webviewdemo.model.Legend
import com.example.webviewdemo.model.MapLayer

/*
 * Description: Android MapsGL Webview Demo - host javascript implementation
 */
class MainActivity : ComponentActivity() {
    private var msg: String? = null
    private var mapLayers = MapLayer.listAll()
    private var jsBuilder: JSBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMain()
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun ComposeMain() {
        val openMenu = remember { mutableStateOf(false) }
        val openDialog = remember { mutableStateOf(false) }
        val openSnackbar = remember { mutableStateOf(false) }
        val openAnimateControl = remember { mutableStateOf(false) }
        val animateTime = remember { mutableStateOf("00:00:00") }
        val animateState = remember { mutableStateOf<TimelineState>(TimelineState.Stop) }
        val openLegends = remember { mutableStateOf<List<Legend>>(emptyList()) }

        val showMenu: (open: Boolean) -> Unit = {
            openMenu.value = it
        }

        val showDialog: (open: Boolean) -> Unit = {
            openDialog.value = it
        }

        val showSnackbar: (open: Boolean, msg: String?) -> Unit = { isOpen, str ->
            openSnackbar.value = isOpen
            msg = if (isOpen) {
                str
            } else {
                ""
            }
        }

        val showAnimationControl: (open: Boolean) -> Unit = {
            openAnimateControl.value = it
        }

        val updateTime: (time: String) -> Unit = {
            animateTime.value = it
        }

        val updateAnimateState: (state: TimelineState) -> Unit = {
            animateState.value = it
        }

        val showLegend: (jsonString: String) -> Unit = { base64 ->
            openLegends.value = Legend.decode(base64)
        }

        Scaffold(
            topBar = {
                ComposeTopBar(
                    openMenu.value,
                    showMenu,
                    showDialog,
                    showAnimationControl,
                    jsBuilder
                )
            },

            content = {
                ComposeWebView(
                    showLegend, showSnackbar, updateTime
                )
                if (openDialog.value) {
                    ComposeLayerDialog(
                        mapLayers,
                        showDialog,
                        jsBuilder
                    )
                }
                if (openSnackbar.value) {
                    ComposeSnackbar(showSnackbar, msg, R.drawable.ic_info)
                }

                if (openAnimateControl.value) {
                    ComposeAnimationControl(
                        animateTime,
                        updateAnimateState,
                        animateState,
                        jsBuilder
                    )
                }
                ComposeLegend(openLegends)
            },
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    fun ComposeWebView(
        decodeLegend: (jsonString: String) -> Unit,
        showSnackbar: (open: Boolean, msg: String?) -> Unit,
        updateTime: (time: String) -> Unit
    ) {
        val getJSBuilder: (builder: JSBuilder) -> Unit = {
            jsBuilder = it
        }

        AndroidView(factory = {
            MapsGLWebview(it, decodeLegend, showSnackbar, updateTime, getJSBuilder)

        }, update = {
            it.load()
        })
    }
}

sealed class TimelineState {
    object Play : TimelineState()
    object Stop : TimelineState()
}

