package com.example.webviewdemo

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.webviewdemo.interop.JSBuilder

/*
 * Description: Application Toolbar with buttons: menu, animation(play), dialog(layers)
 */
@Composable
fun ComposeTopBar(
    isMenuOpened: Boolean,
    showMenu: (open: Boolean) -> Unit,
    showDialog: (open: Boolean) -> Unit,
    showAnimationControl: (open: Boolean) -> Unit,
    jsBuilder: JSBuilder? = null
) {
    TopAppBar(title = {
        Text("MapsGL Webview", color = Color.White)
    },
        backgroundColor = Color(0xff0f9d58),
        actions = {
            IconButton(onClick = {
                showAnimationControl(true)
            }) {
                Icon(
                    painterResource(R.drawable.ic_animation),
                    "animate",
                    modifier = Modifier.size(35.dp)
                )
            }

            Text(text = "    ")

            IconButton(onClick = { showDialog(true) }) {
                Icon(
                    painterResource(R.drawable.ic_layers),
                    "layers",
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = { showMenu(true) }) {
                Icon(
                    painterResource(R.drawable.ic_more_vert),
                    "menu",
                    modifier = Modifier.size(35.dp)
                )
            }

            DropdownMenu(
                expanded = isMenuOpened,
                onDismissRequest = { showMenu(false) }
            ) {

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getCenter()
                }) {
                    Text("get Center")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.setCenter(30.0, -74.5)
                }) {
                    Text("set Center")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getZoom()
                }) {
                    Text("get Zoom")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.setZoom(3)
                }) {
                    Text("set Zoom")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getBounds()
                }) {
                    Text("get Bounds")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getBearing()
                }) {
                    Text("get Bearing")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getPitch()
                }) {
                    Text("get Pitch")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getFov()
                }) {
                    Text("get Fov")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getLegend()
                }) {
                    Text("get Legend")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getStartDate()
                }) {
                    Text("get StartDate")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getEndDate()
                }) {
                    Text("get EndDate")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.getCurrentDate()
                }) {
                    Text("get CurrentDate")
                }

                DropdownMenuItem(onClick = {
                    showMenu(false)
                    jsBuilder?.query(47.194686564788356, -115.76953124999976);

                }) {
                    Text("query")
                }
            }
        }
    )
}