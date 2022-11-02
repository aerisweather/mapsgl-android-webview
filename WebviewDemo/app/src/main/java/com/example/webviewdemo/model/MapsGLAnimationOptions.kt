package com.example.webviewdemo.model

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/*
 * Description: MapsGL Animation Options - configure map (initialization)
 */
data class MapsGLAnimationOptions(
    val start: ZonedDateTime? = null,
    val end: ZonedDateTime? = null,
    val duration: Double = 4.0,
    val delay: Int = 0,
    val endDelay: Double = 1.0,
    val autoplay: Boolean = false,
    val repeat: Boolean = true,
    val enabled: Boolean = true,
    val pauseWhileLoading: Boolean = false
) {
    override fun toString(): String {
        return "{\"start\":\"${format(start ?: ZonedDateTime.now().minusDays(1))}\"," +
                "\"end\":\"${format(end ?: ZonedDateTime.now())}\"," +
                "\"duration\":$duration," +
                "\"delay\":$delay," +
                "\"endDelay\":$endDelay," +
                "\"autoplay\":${format(autoplay)}," +
                "\"repeat\":${format(repeat)}," +
                "\"enabled\":${format(enabled)}," +
                "\"pauseWhileLoading\":${format(pauseWhileLoading)}}"
    }

    private fun format(date: ZonedDateTime): String {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
        return date.format(firstApiFormat)
    }

    private fun format(unit: Boolean): Int {
        return if (unit) {
            1
        } else {
            0
        }
    }
}