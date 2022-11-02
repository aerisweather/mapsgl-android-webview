package com.example.webviewdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.webviewdemo.interop.JSBuilder

@Composable
fun ComposeAnimationControl(
    time: MutableState<String>,
    updateAnimateState: (state: TimelineState) -> Unit,
    animateState: MutableState<TimelineState>,
    jsBuilder: JSBuilder? = null
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.background(color = Color.White)) {
            Row() {

                IconButton(onClick = {
                    val state = if (animateState.value is TimelineState.Stop) {
                        TimelineState.Play
                    } else {
                        TimelineState.Stop
                    }
                    updateAnimateState(state)
                    if (state is TimelineState.Play) {
                        jsBuilder?.play()
                    } else {
                        jsBuilder?.stop()
                    }
                }) {
                    Icon(
                        painterResource(
                            if (animateState.value is TimelineState.Stop) {
                                R.drawable.ic_play
                            } else {
                                R.drawable.ic_stop
                            }
                        ),
                        "animate",
                        modifier = Modifier.size(35.dp)
                    )
                }
                Box(
                    modifier = Modifier.width(500.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = time.value.replace("(", "\n("))
                }
            }
        }
    }
}