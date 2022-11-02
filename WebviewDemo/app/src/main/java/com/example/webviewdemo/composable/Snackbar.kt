package com.example.webviewdemo.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/*
 * Description: Snackbar displays information (response from javascript - menu selection)
 */
@Composable
fun ComposeSnackbar(
    showSnackbar: (open: Boolean, msg: String?) -> Unit,
    msg: String?,
    resourceId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 100.dp, 0.dp, 0.dp)
            .heightIn(max = 120.dp)
    ) {
        Snackbar(action = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("snack_error"),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = {
                    showSnackbar(false, null)
                }) {
                    Icon(
                        painterResource(id = resourceId),
                        contentDescription = "snack",
                        tint = Color.White,
                    )
                }
            }
            Text(
                text = msg ?: "", color = Color.White, modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )

        }, modifier = Modifier.padding(10.dp)) {
            Text(text = msg ?: "", color = Color.White)
        }
    }
}
