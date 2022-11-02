package com.example.webviewdemo.model

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.gson.JsonElement
import com.google.gson.JsonParser

/*
 * Description: MapsGL Layer legend
 *              Retrieve from javascript, render in native
 */
data class Legend(
    val index: Int,
    val key: String? = null,
    val label: String? = null,
    val bmp: ImageBitmap? = null
) {
    companion object {
        fun decode(jsonString: String): List<Legend> {
            val jsonObject = JsonParser.parseString(jsonString)
            val jsonArray = jsonObject.asJsonArray
            val size = jsonArray.size()
            kotlin.runCatching {
                val list = arrayListOf<Legend>()
                for (i in 0 until size) {
                    val json: JsonElement = jsonArray.get(i)
                    val jsonObj = json.asJsonObject
                    jsonObj.apply {
                        val key = get("key").asString
                        val label = get("label").asString
                        val base64String = get("data").asString
                        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
                        val bmp: ImageBitmap =
                            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                                .asImageBitmap()

                        list.add(Legend(i, key, label, bmp))
                    }
                }
                return list.toList()
            }.onFailure {
                Log.d("Legend", it.toString())
            }
            return emptyList()
        }
    }

    fun isNotEmpty(): Boolean {
        return bmp != null
    }
}