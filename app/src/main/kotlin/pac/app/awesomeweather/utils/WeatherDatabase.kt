package pac.app.awesomeweather.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.AsyncTask
import android.support.v4.app.Fragment
import org.jetbrains.anko.async
import org.jetbrains.anko.db.*
import org.jetbrains.anko.uiThread
import pac.app.awesomeweather.utils.yandex_parser.models.*
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

val DATABASE_NAME = "weather.db"
val DATABASE_VERSION = 1

public class WeatherDatabase(var context: Context): ManagedSQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun saveYandexForecast(forecastItem: ForecastItem) {
        use {
            insert(ForecastTable.NAME,
                    ForecastTable.Column.TYPE to forecastItem.type,
                    ForecastTable.Column.TEMPERATURE_FROM to forecastItem.temperatureFrom,
                    ForecastTable.Column.TEMPERATURE_TO to forecastItem.temperatureTo,
                    ForecastTable.Column.TEMPERATURE to forecastItem.temperature,
                    ForecastTable.Column.IMAGE to forecastItem.image,
                    ForecastTable.Column.WEATHER_TYPE to forecastItem.weatherType,
                    ForecastTable.Column.DATE to forecastItem.date.getTime())
        }
    }

    fun getCurrentYandexForecast(finish: (result: ForecastItem?) -> Unit) {
        context.async {
            var forecastItem: ForecastItem? = null

            use {
                // Get current time
                val currentDate = Calendar.getInstance()
                // Get current part of day
                val partDay = getPartDay(currentDate.get(Calendar.HOUR_OF_DAY))
                // Clear time
                currentDate.set(Calendar.HOUR_OF_DAY, 0)
                currentDate.set(Calendar.MINUTE, 0)
                currentDate.set(Calendar.SECOND, 0)
                currentDate.set(Calendar.MILLISECOND, 0)
                val timeInMillis = currentDate.getTimeInMillis()

                select(ForecastTable.NAME)
                        .where("${ForecastTable.Column.DATE} = {timeInMillis} AND ${ForecastTable.Column.TYPE} = {partDay}", "timeInMillis" to timeInMillis, "partDay" to partDay)
                        .exec {
                            forecastItem = parseOpt(ForecastItem.ForecastItemParser)
                        }
            }

            uiThread {
                finish(forecastItem)
            }
        }
    }

    fun getDaysYandexForecast(finish: (result: List<Date>) -> Unit) {
        context.async {
            var listDates = ArrayList<Date>()

            use {
                select(ForecastTable.NAME)
                        .distinct().column(ForecastTable.Column.DATE)
                        .orderBy(ForecastTable.Column.DATE)
                        .exec {
                            listDates.addAll(parseList(rowParser<Long, Date> { Date(it) }))
                        }
            }

            uiThread {
                finish(listDates)
            }
        }
    }

    fun getForecastByDay(dateInMs: Long, finish: (result: List<ForecastItem>) -> Unit) {
        context.async {
            var listForecasts = ArrayList<ForecastItem>()

            use {
                select(ForecastTable.NAME)
                        .where("${ForecastTable.Column.DATE} = {dateInMs}", "dateInMs" to dateInMs)
                        .exec {
                            listForecasts.addAll(parseList(ForecastItem.ForecastItemParser))
                        }
            }

            uiThread {
                finish(listForecasts)
            }
        }
    }

    private fun getPartDay(hour: Int): Int {
        when (hour) {
            in 0..3 -> return DAY_TYPE_NIGHT
            in 4..9 -> return DAY_TYPE_MORNING
            in 10..15 -> return DAY_TYPE_DAY
            else -> return DAY_TYPE_EVENING
        }
    }

    public fun clearYandexWeather() {
        use {
            transaction {
                delete(ForecastTable.NAME)
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.transaction {
            createTable(ForecastTable.NAME, false,
                        ForecastTable.Column.ID to INTEGER + PRIMARY_KEY,
                        ForecastTable.Column.TYPE to INTEGER,
                        ForecastTable.Column.TEMPERATURE_FROM to INTEGER,
                        ForecastTable.Column.TEMPERATURE_TO to INTEGER,
                        ForecastTable.Column.TEMPERATURE to INTEGER,
                        ForecastTable.Column.IMAGE to TEXT,
                        ForecastTable.Column.WEATHER_TYPE to TEXT,
                        ForecastTable.Column.DATE to INTEGER)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.transaction {
            dropTable(ForecastTable.NAME)
        }

        onCreate(db)
    }

    companion object {
        var instance: WeatherDatabase? = null

        synchronized fun getInstance(context: Context): WeatherDatabase {
            if (instance == null) {
                instance = WeatherDatabase(context.getApplicationContext())
            }
            return instance!!
        }
    }

}

val Context.database: WeatherDatabase
    get() = WeatherDatabase.getInstance(getApplicationContext())

val Fragment.database: WeatherDatabase
    get() = WeatherDatabase.getInstance(getActivity().getApplicationContext())

object ForecastTable {
    val NAME = "yandex_weather_forecast"
    object Column {
        val ID = "id"
        val TYPE = "type"
        val TEMPERATURE_FROM = "temperature_from"
        val TEMPERATURE_TO = "temperature_to"
        val TEMPERATURE = "temperature"
        val IMAGE = "image"
        val WEATHER_TYPE = "weather_type"
        val DATE = "date"
    }
}
