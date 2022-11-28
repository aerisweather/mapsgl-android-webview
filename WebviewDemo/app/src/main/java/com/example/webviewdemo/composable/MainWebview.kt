package com.example.webviewdemo.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.mapsglwebviewlib.MapsGLWebview
import com.example.mapsglwebviewlib.interop.JSBuilder
import com.example.webviewdemo.*

/*
 * Description: Main screent, toolbar, webview
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposeMain(viewModel: MainViewModel) {
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
            ComposeSecretDialog(viewModel)
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
            viewModel.showProgress,
            getJSBuilder
        )

    }, update = {
        it.load()
    })
}


@Composable
fun ComposeSecretDialog(viewModel: MainViewModel) {
    /*
     * Display annoying dialog if aeris-id and aeris-secret not implemented
     */

    val id = stringResource(id = com.example.mapsglwebviewlib.R.string.aerisapi_client_id)
    val secret = stringResource(id = com.example.mapsglwebviewlib.R.string.aerisapi_client_secret)
    if (id.lowercase().contains("your") || secret.lowercase().contains("your")) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Card() {
                Column() {
                    Row(modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
                        Icon(
                            painterResource(
                                R.drawable.ic_warning
                            ),
                            "animate",
                            modifier = Modifier.size(35.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.permission),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
                        Text(text = stringResource(id = R.string.permission_desc))
                    }
                }
            }
        }
    }
}