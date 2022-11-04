package com.example.webviewdemo

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
    viewModel: MainViewModel,
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
                    viewModel.openSnackbar.value = false
                }) {
                    Icon(
                        painterResource(id = R.drawable.ic_info),
                        contentDescription = "snack",
                        tint = Color.White,
                    )
                }
            }
            Text(
                text = viewModel.msg.value ?: "", color = Color.White, modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )

        }, modifier = Modifier.padding(10.dp)) {
            Text(text = viewModel.msg.value ?: "", color = Color.White)
        }
    }
}
