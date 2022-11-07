package com.example.webviewdemo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mapsglwebviewlib.interop.JSBuilder
import com.example.webviewdemo.model.Legend
import com.example.webviewdemo.model.MapLayer

/*
 * Description: ViewModel
 */
class MainViewModel : ViewModel() {

    var mapLayers = MapLayer.listAll()
    var jsBuilder: JSBuilder? = null

    val openMenu = mutableStateOf(false)
    val openDialog = mutableStateOf(false)
    val openSnackbar = mutableStateOf(false)
    val openProgress = mutableStateOf(false)
    val openAnimateControl = mutableStateOf(false)
    val animateTime = mutableStateOf("00:00:00")
    val animateState = mutableStateOf<TimelineState>(TimelineState.Stop)
    val openLegends = mutableStateOf<List<Legend>>(emptyList())
    val msg = mutableStateOf<String>("")

    val showSnackbar: (open: Boolean, msg: String?) -> Unit = { isOpen, str ->
        openSnackbar.value = isOpen
        msg.value =
            str ?: ""
    }

    val updateTime: (time: String) -> Unit = {
        animateTime.value = it
    }

    val showLegend: (jsonString: String) -> Unit = { base64 ->
        openLegends.value = Legend.decode(base64)
    }

    val showProgress: (show: Boolean) -> Unit = {
        openProgress.value = it
    }
}

sealed class TimelineState {
    object Play : TimelineState()
    object Stop : TimelineState()
}