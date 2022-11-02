package com.example.webviewdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.webviewdemo.model.Legend

/*
 * Description: Draw Layer legend at top of screen (if available from javascript)
 */
@Composable
fun ComposeLegend(openLegends: MutableState<List<Legend>>) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val hash = HashSet<String>()
            for (legend in openLegends.value) {
                if (legend.isNotEmpty()) {
                    legend.key?.let {
                        if (!hash.contains(it)) {
                            ComposeLayerLegend(legend)
                            hash.add(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeLayerLegend(legend: Legend) {
    Row() {
        Column(
            modifier = Modifier.widthIn(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = legend.label ?: "none")
            if (legend.bmp != null) {
                Image(
                    bitmap = legend.bmp,
                    contentDescription = "legend",
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painterResource(R.drawable.ic_cancel),
                    contentDescription = "legend",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}