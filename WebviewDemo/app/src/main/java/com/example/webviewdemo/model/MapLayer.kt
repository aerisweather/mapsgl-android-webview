package com.example.webviewdemo.model

/*
 * Description: MapsGL layers for selection / overlay rendering
 */
data class MapLayer(val layer: Layers, var isEnabled: Boolean = false) {

    companion object {
        fun listAll(): List<MapLayer> = mutableListOf<MapLayer>(
            MapLayer(Layers.TEMP),
            MapLayer(Layers.WIND_SPEED),
            MapLayer(Layers.WIND_PARTICLE),
            MapLayer(Layers.WIND_BARBS),
            MapLayer(Layers.ALERT),
            MapLayer(Layers.ALERT_OUTLINE),
            MapLayer(Layers.DEW_POINTS),
            MapLayer(Layers.PRESSURE),
            MapLayer(Layers.PRESSURE_CONTOUR),
            MapLayer(Layers.FIRES_OB_HEAT),
        ).toList()
    }
}

enum class Layers(
    val code: String,           // javascript controller input to set layer
    val print: String           // Dialog UI for user
) {
    TEMP("temperatures", "Temperature"),
    WIND_SPEED("wind-speeds", "Wind-speed"),
    WIND_PARTICLE("wind-particles", "Wind-particles"),
    WIND_BARBS("wind-barbs", "Wind-barbs"),
    ALERT("alerts", "Alerts"),
    ALERT_OUTLINE("alerts-outline", "Alerts-Outline"),
    DEW_POINTS("dew-points", "Dew-points"),
    PRESSURE("pressure-msl", "Pressure"),
    PRESSURE_CONTOUR("pressure-msl-contour", "Pressure contour"),
    FIRES_OB_HEAT("fires-obs-heat", "Fires heat"),
}