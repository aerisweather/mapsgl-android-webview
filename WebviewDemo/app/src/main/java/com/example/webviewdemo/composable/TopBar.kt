package com.example.webviewdemo

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.webviewdemo.ui.theme.BrandPrimary

/*
 * Description: Application Toolbar with buttons: menu, animation(play), dialog(layers)
 */
@Composable
fun ComposeTopBar(viewModel: MainViewModel) {
    TopAppBar(title = {
        Text("MapsGL Webview", color = Color.White)
    },
        backgroundColor = colorResource(id = R.color.brand_primary),
        actions = {
            IconButton(onClick = {
                viewModel.apply {
                    if (hasSelectedLayer()) {
                        openAnimateControl.value = true
                    }
                }
            }) {
                Icon(
                    painterResource(R.drawable.ic_animation),
                    "animate",
                    modifier = Modifier.size(35.dp)
                )
            }
            Text(text = "    ")

            IconButton(onClick = { viewModel.openDialog.value = true }) {
                Icon(
                    painterResource(R.drawable.ic_layers),
                    "layers",
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = { viewModel.openMenu.value = true }) {
                Icon(
                    painterResource(R.drawable.ic_more_vert),
                    "menu",
                    modifier = Modifier.size(35.dp)
                )
            }

            DropdownMenu(
                expanded = viewModel.openMenu.value,
                onDismissRequest = { viewModel.openMenu.value = false }
            ) {

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getCenter()
                }) {
                    Text("get Center")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.setCenter(30.0, -74.5)
                }) {
                    Text("set Center")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getZoom()
                }) {
                    Text("get Zoom")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.setZoom(3)
                }) {
                    Text("set Zoom")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getBounds()
                }) {
                    Text("get Bounds")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getBearing()
                }) {
                    Text("get Bearing")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getPitch()
                }) {
                    Text("get Pitch")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getFov()
                }) {
                    Text("get Fov")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getStartDate()
                }) {
                    Text("get StartDate")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getEndDate()
                }) {
                    Text("get EndDate")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.getCurrentDate()
                }) {
                    Text("get CurrentDate")
                }

                DropdownMenuItem(onClick = {
                    viewModel.openMenu.value = false
                    viewModel.jsBuilder?.query(47.194686564788356, -115.76953124999976);

                }) {
                    Text("query")
                }
            }
        }
    )
}