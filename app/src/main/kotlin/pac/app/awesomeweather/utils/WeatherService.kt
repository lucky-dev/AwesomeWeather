package pac.app.awesomeweather.utils

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import pac.app.awesomeweather.URL_YANDEX_WEATHER
import pac.app.awesomeweather.YANDEX_WEATHER
import pac.app.awesomeweather.utils.yandex_parser.ParserYandexWeather
import pac.app.awesomeweather.utils.yandex_parser.models.ForecastItem
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

public class WeatherService : IntentService("WeatherService") {

    private val TAG = "WeatherService"

    private var db: WeatherDatabase? = null

    override fun onCreate() {
        super.onCreate()
        db = WeatherDatabase.getInstance(this)
    }

    override fun onHandleIntent(intent: Intent?) {
        val weatherResource = intent?.getIntExtra(REQUEST_PARAM_WEATHER_RESOURCE, 0)

        when (weatherResource) {
            YANDEX_WEATHER -> handleYandexWeather()
        }
    }

    private fun handleYandexWeather() {
        setLoadingData(this, true)

        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(ACTION_START_UPDATING))

        val intent = Intent(ACTION_STOP_UPDATING)

        try {
            downloadXmlData(URL_YANDEX_WEATHER) { statusCode, xmlData ->
                if (statusCode == 200) {
                    val coreParser = ParserYandexWeather()
                    val listForecasts = coreParser.parse(xmlData)

                    if (listForecasts.isNotEmpty()) {
                        db?.clearYandexWeather()
                        listForecasts.forEach { db?.saveYandexForecast(it) }
                    }

                    intent.putExtra(RESPONSE_PARAM_CODE, RESPONSE_VALUE_CODE_OK)

                    setUpdatedTime(this)
                } else {
                    intent.putExtra(RESPONSE_PARAM_CODE, RESPONSE_VALUE_CODE_ERROR)
                }
            }
        } catch (e: Exception) {
            intent.putExtra(RESPONSE_PARAM_CODE, RESPONSE_VALUE_CODE_ERROR)
        } finally {
            setLoadingData(this, false)

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    private fun downloadXmlData(urlWeatherRes: String, callback: (Int, InputStream?) -> Unit) {
        val url = URL(urlWeatherRes)
        val connection = url.openConnection() as? HttpURLConnection
        connection?.setReadTimeout(10000)
        connection?.setConnectTimeout(15000)
        connection?.setRequestMethod("GET")
        connection?.setDoInput(true)
        connection?.connect()

        callback(connection?.getResponseCode() ?: -1, connection?.getInputStream())
    }

    companion object {
        val REQUEST_PARAM_WEATHER_RESOURCE = "weather_resource"
        val RESPONSE_PARAM_CODE = "response_code"
        // Values
        val RESPONSE_VALUE_CODE_OK = 0
        val RESPONSE_VALUE_CODE_ERROR = -1
        // Actions
        val ACTION_START_UPDATING = "pac.app.awesomeweather.ACTION_START_UPDATING"
        val ACTION_STOP_UPDATING = "pac.app.awesomeweather.ACTION_STOP_UPDATING"

        fun downloadNews(context: Context, weatherResource: Int) {
            val intent = Intent(context, javaClass<WeatherService>())
            intent.putExtra(REQUEST_PARAM_WEATHER_RESOURCE, weatherResource)
            context.startService(intent)
        }
    }

}
