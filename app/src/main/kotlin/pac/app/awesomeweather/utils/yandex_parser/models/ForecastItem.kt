package pac.app.awesomeweather.utils.yandex_parser.models

import org.jetbrains.anko.db.MapRowParser
import pac.app.awesomeweather.utils.ForecastTable
import java.util.Date

val DAY_TYPE_NIGHT = 1
val DAY_TYPE_MORNING = 2
val DAY_TYPE_DAY = 3
val DAY_TYPE_EVENING = 4

data class ForecastItem(var id: Long = 0,
                        var type: Int = 0,
                        var temperatureFrom: Int = Int.MIN_VALUE,
                        var temperatureTo: Int = Int.MIN_VALUE,
                        var temperature: Int = Int.MIN_VALUE,
                        var image: String = "",
                        var weatherType: String = "",
                        var date: Date = Date()) {
    val strTemperature: String
        get() {
            var tmpr = Int.MIN_VALUE
            if ((temperatureFrom != Int.MIN_VALUE) && (temperatureTo != Int.MIN_VALUE)) {
                tmpr = (temperatureFrom + temperatureTo) / 2
            } else if (temperature != Int.MIN_VALUE) {
                tmpr = temperature
            }

            if (tmpr != Int.MIN_VALUE) {
                return if (tmpr > 0) "+$tmpr" else "-$tmpr"
            }

            return ""
        }

    companion object ForecastItemParser: MapRowParser<ForecastItem> {
        override fun parseRow(columns: Map<String, Any>): ForecastItem {
            return ForecastItem(columns[ForecastTable.Column.ID] as? Long ?: 0,
                                (columns[ForecastTable.Column.TYPE] as? Long)?.toInt() ?: 0,
                                (columns[ForecastTable.Column.TEMPERATURE_FROM] as? Long)?.toInt() ?: Int.MIN_VALUE,
                                (columns[ForecastTable.Column.TEMPERATURE_TO] as? Long)?.toInt() ?: Int.MIN_VALUE,
                                (columns[ForecastTable.Column.TEMPERATURE] as? Long)?.toInt() ?: Int.MIN_VALUE,
                                columns[ForecastTable.Column.IMAGE] as? String ?: "",
                                columns[ForecastTable.Column.WEATHER_TYPE] as? String ?: "",
                                Date(columns[ForecastTable.Column.DATE] as? Long ?: 0))
        }
    }
}
