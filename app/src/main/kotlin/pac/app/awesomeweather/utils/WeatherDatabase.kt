package pac.app.awesomeweather.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.AsyncTask
import pac.app.awesomeweather.utils.yandex_parser.models.*
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

val DATABASE_NAME = "weather.db"
val DATABASE_VERSION = 1

public class WeatherDatabase(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun saveYandexForecast(forecastItem: ForecastItem) {
        val values = ContentValues()
        values.put(ForecastTable.Column.TYPE, forecastItem.type)
        values.put(ForecastTable.Column.TEMPERATURE_FROM, forecastItem.temperatureFrom)
        values.put(ForecastTable.Column.TEMPERATURE_TO, forecastItem.temperatureTo)
        values.put(ForecastTable.Column.TEMPERATURE, forecastItem.temperature)
        values.put(ForecastTable.Column.IMAGE, forecastItem.image)
        values.put(ForecastTable.Column.WEATHER_TYPE, forecastItem.weatherType)
        values.put(ForecastTable.Column.DATE, forecastItem.date.getTime())

        getWritableDatabase().insert(ForecastTable.NAME, null, values)
    }

    fun getCurrentYandexForecast(finish: (result: ForecastItem) -> Unit) {
        object : AsyncTask<Void, Void, ForecastItem>() {
            override fun doInBackground(vararg params: Void?): ForecastItem {
                var forecastItem = ForecastItem()

                var cursor: Cursor? = null
                try {
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

                    cursor = getReadableDatabase().rawQuery("SELECT * FROM ${ForecastTable.NAME} WHERE ${ForecastTable.Column.DATE} = $timeInMillis AND ${ForecastTable.Column.TYPE} = $partDay", null)

                    if (cursor?.moveToFirst() ?: false) {
                        do {
                            forecastItem.id = cursor?.getLong(cursor?.getColumnIndex(ForecastTable.Column.ID) ?: -1) ?: 0
                            forecastItem.type = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TYPE) ?: -1) ?: 0
                            forecastItem.temperatureFrom = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE_FROM) ?: -1) ?: 0
                            forecastItem.temperatureTo = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE_TO) ?: -1) ?: 0
                            forecastItem.temperature = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE) ?: -1) ?: 0
                            forecastItem.image = cursor?.getString(cursor?.getColumnIndex(ForecastTable.Column.IMAGE) ?: -1) ?: ""
                            forecastItem.weatherType = cursor?.getString(cursor?.getColumnIndex(ForecastTable.Column.WEATHER_TYPE) ?: -1) ?: ""
                            forecastItem.date =  Date(cursor?.getLong(cursor?.getColumnIndex(ForecastTable.Column.DATE) ?: -1) ?: 0)
                        } while (cursor?.moveToNext() ?: false)
                    }
                } finally {
                    cursor?.close()
                }

                return forecastItem
            }

            override fun onPostExecute(result: ForecastItem) {
                finish(result)
            }
        }.execute()
    }

    fun getDaysYandexForecast(finish: (result: List<Date>) -> Unit) {
        object : AsyncTask<Void, Void, List<Date>>() {
            override fun doInBackground(vararg params: Void?): List<Date> {
                var listDates = ArrayList<Date>()

                var cursor: Cursor? = null
                try {
                    cursor = getReadableDatabase().rawQuery("SELECT DISTINCT ${ForecastTable.Column.DATE} FROM ${ForecastTable.NAME} ORDER BY ${ForecastTable.Column.DATE} ASC", null)

                    if (cursor?.moveToFirst() ?: false) {
                        do {
                            listDates.add(Date(cursor?.getLong(cursor?.getColumnIndex(ForecastTable.Column.DATE) ?: -1) ?: 0))
                        } while (cursor?.moveToNext() ?: false)
                    }
                } finally {
                    cursor?.close()
                }

                return listDates
            }

            override fun onPostExecute(result: List<Date>) {
                finish(result)
            }
        }.execute()
    }

    fun getForecastByDay(dateInMs: Long, finish: (result: List<ForecastItem>) -> Unit) {
        object : AsyncTask<Void, Void, List<ForecastItem>>() {
            override fun doInBackground(vararg params: Void?): List<ForecastItem> {
                var listForecasts = ArrayList<ForecastItem>()

                var cursor: Cursor? = null
                try {
                    cursor = getReadableDatabase().rawQuery("SELECT * FROM ${ForecastTable.NAME} WHERE ${ForecastTable.Column.DATE} = ${dateInMs}", null)

                    if (cursor?.moveToFirst() ?: false) {
                        do {
                            val forecastItem = ForecastItem()
                            forecastItem.id = cursor?.getLong(cursor?.getColumnIndex(ForecastTable.Column.ID) ?: -1) ?: 0
                            forecastItem.type = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TYPE) ?: -1) ?: 0
                            forecastItem.temperatureFrom = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE_FROM) ?: -1) ?: 0
                            forecastItem.temperatureTo = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE_TO) ?: -1) ?: 0
                            forecastItem.temperature = cursor?.getInt(cursor?.getColumnIndex(ForecastTable.Column.TEMPERATURE) ?: -1) ?: 0
                            forecastItem.image = cursor?.getString(cursor?.getColumnIndex(ForecastTable.Column.IMAGE) ?: -1) ?: ""
                            forecastItem.weatherType = cursor?.getString(cursor?.getColumnIndex(ForecastTable.Column.WEATHER_TYPE) ?: -1) ?: ""
                            forecastItem.date =  Date(cursor?.getLong(cursor?.getColumnIndex(ForecastTable.Column.DATE) ?: -1) ?: 0)
                            listForecasts.add(forecastItem)
                        } while (cursor?.moveToNext() ?: false)
                    }
                } finally {
                    cursor?.close()
                }

                return listForecasts
            }

            override fun onPostExecute(result: List<ForecastItem>) {
                finish(result)
            }
        }.execute()
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
        try {
            getWritableDatabase().beginTransaction()
            getWritableDatabase().execSQL("DELETE FROM ${ForecastTable.NAME}")
            getWritableDatabase().setTransactionSuccessful()
        } finally {
            getWritableDatabase().endTransaction()
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.beginTransaction()
            db.execSQL("CREATE TABLE ${ForecastTable.NAME} (${ForecastTable.Column.ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${ForecastTable.Column.TYPE} INTEGER, ${ForecastTable.Column.TEMPERATURE_FROM} INTEGER, ${ForecastTable.Column.TEMPERATURE_TO} INTEGER, ${ForecastTable.Column.TEMPERATURE} INTEGER, ${ForecastTable.Column.IMAGE} TEXT, ${ForecastTable.Column.WEATHER_TYPE} TEXT, ${ForecastTable.Column.DATE} INTEGER);")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.beginTransaction()
            db.execSQL("DROP TABLE IF EXISTS ${ForecastTable.NAME}")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        onCreate(db)
    }

    companion object {
        var instance: WeatherDatabase? = null

        synchronized fun getInstance(context: Context): WeatherDatabase? {
            if (instance == null) {
                instance = WeatherDatabase(context.getApplicationContext())
            }
            return instance
        }
    }

}

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
