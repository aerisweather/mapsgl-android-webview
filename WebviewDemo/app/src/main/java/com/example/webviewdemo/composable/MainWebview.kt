package com.example.webviewdemo.composable

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mapsglwebviewlib.MapsGLWebview
import com.example.mapsglwebviewlib.interop.JSBuilder
import com.example.webviewdemo.*

/*
 * Description: Main screent, toolbar, webview
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposeMain(viewModel: MainViewModel) {
    var msg: String? = null

    Scaffold(
        topBar = {
            ComposeTopBar(viewModel)
        },

        content = {
            ComposeWebView(viewModel)
            if (viewModel.openDialog.value) {
                ComposeLayerDialog(viewModel)
            }
            if (viewModel.openSnackbar.value) {
                ComposeSnackbar(viewModel)
            }

            if (viewModel.openAnimateControl.value) {
                ComposeAnimationControl(viewModel)
            }
            ComposeLegend(viewModel.openLegends)
        },
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ComposeWebView(viewModel: MainViewModel) {
    val getJSBuilder: (builder: JSBuilder) -> Unit = {
        viewModel.jsBuilder = it
    }

    AndroidView(factory = {
        MapsGLWebview(
            it,
            viewModel.showLegend,
            viewModel.showSnackbar,
            viewModel.updateTime,
            getJSBuilder
        )

    }, update = {
        it.load()
    })
}