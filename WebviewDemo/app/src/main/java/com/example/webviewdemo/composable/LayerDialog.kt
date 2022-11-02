package com.example.webviewdemo.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.webviewdemo.interop.JSBuilder
import com.example.webviewdemo.model.MapLayer

/*
 * Description: Composable UI Dialog box to select MapsGL layers
 */
@Composable
fun ComposeLayerDialog(
    mapLayers: List<MapLayer>,
    showDialog: (open: Boolean) -> Unit,
    jsBuilder: JSBuilder? = null
) {
    Dialog(
        onDismissRequest = { showDialog(false) },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp, vertical = 0.dp
            ), modifier = Modifier.width(800.dp)
        ) {
            items(
                items = mapLayers,
                itemContent = {
                    ComposeLayersSelection(it, showDialog, jsBuilder)
                }
            )
        }
    }
}

@Composable
fun ComposeLayersSelection(
    mapLayer: MapLayer,
    showDialog: (open: Boolean) -> Unit,
    jsBuilder: JSBuilder? = null
) {
    var onClickLayerCard: (mapLayer: MapLayer) -> Unit = {
        if (it.isEnabled) {
            jsBuilder?.removeWeatherLayer?.let { it1 -> it1(mapLayer.layer.code) }
            mapLayer.isEnabled = false
        } else {
            jsBuilder?.addWeatherLayer?.let { it1 -> it1(mapLayer.layer.code, "") }
            mapLayer.isEnabled = true
        }
        showDialog(false)
    }

    Card(
        Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                onClickLayerCard(mapLayer)
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            mapLayer.apply {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Checkbox(
                        checked = isEnabled,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.White,
                            checkedColor = Color.Green
                        ),
                        onCheckedChange = {
                            onClickLayerCard(mapLayer)
                        }, enabled = true
                    )
                    Text(
                        text = mapLayer.layer.print, fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(15.dp),
                        textAlign = TextAlign.Left
                    )
                }
                Divider(color = Color(0xFF808080), thickness = 1.dp)
            }
        }
    }
}