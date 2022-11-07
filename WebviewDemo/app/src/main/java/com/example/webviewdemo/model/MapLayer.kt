package com.example.webviewdemo.model

/*
 * Description: MapsGL layers for selection / overlay rendering
 */
data class MapLayer(val layer: LayerEnum, var isEnabled: Boolean = false) {

    companion object {
        fun listAll(): List<MapLayer> = mutableListOf<MapLayer>(
            MapLayer(LayerEnum.TEMP),
            MapLayer(LayerEnum.WIND_SPEED),
            MapLayer(LayerEnum.WIND_PARTICLE),
            MapLayer(LayerEnum.WIND_BARBS),
            MapLayer(LayerEnum.ALERT),
            MapLayer(LayerEnum.ALERT_OUTLINE),
            MapLayer(LayerEnum.DEW_POINTS),
            MapLayer(LayerEnum.PRESSURE),
            MapLayer(LayerEnum.PRESSURE_CONTOUR),
            MapLayer(LayerEnum.FIRES_OB_HEAT),
        ).toList()
    }
}

enum class LayerEnum(
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