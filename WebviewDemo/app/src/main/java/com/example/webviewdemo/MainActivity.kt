package com.example.webviewdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.webviewdemo.composable.ComposeMain

/*
 * Description: Android MapsGL Webview Demo - host javascript implementation
 */
class MainActivity : ComponentActivity() {

    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {
            ComposeMain(viewModel)
        }
    }
}