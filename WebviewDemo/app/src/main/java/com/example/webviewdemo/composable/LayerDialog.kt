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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.webviewdemo.MainViewModel
import com.example.webviewdemo.R
import com.example.webviewdemo.TimelineState
import com.example.webviewdemo.model.MapLayer

/*
 * Description: Composable UI Dialog box to select MapsGL layers
 */
@Composable
fun ComposeLayerDialog(viewModel: MainViewModel) {
    Dialog(
        onDismissRequest = { viewModel.openDialog.value = false },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp, vertical = 0.dp
            ), modifier = Modifier.width(800.dp)
        ) {
            items(
                items = viewModel.mapLayers,
                itemContent = {
                    ComposeLayersSelection(it, viewModel)
                }
            )
        }
    }
}

@Composable
fun ComposeLayersSelection(
    mapLayer: MapLayer,
    viewModel: MainViewModel
) {
    var onClickLayerCard: (mapLayer: MapLayer) -> Unit = {
        if (it.isEnabled) {
            viewModel.jsBuilder?.removeWeatherLayer?.let { it1 -> it1(mapLayer.layer.code) }
            mapLayer.isEnabled = false
        } else {
            viewModel.jsBuilder?.addWeatherLayer?.let { it1 -> it1(mapLayer.layer.code, "") }
            mapLayer.isEnabled = true
        }
        viewModel.openDialog.value = false

        /*
         * If no layer selected...
         *  1. hide Animation control
         *  2. if animation is running, stop it.
         */
        viewModel.apply {
            if (!hasSelectedLayer()) {
                openAnimateControl.value = false
                if (animateState.value is TimelineState.Play) {
                    jsBuilder?.stop()
                }
            }
        }
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
                        .background(colorResource(id = R.color.brand_variant))
                ) {
                    Checkbox(
                        checked = isEnabled,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.White,
                            checkedColor = colorResource(id = R.color.brand_primary)
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
                Divider(color = colorResource(id = R.color.brand_variant), thickness = 1.dp)
            }
        }
    }
}