package pac.app.awesomeweather.fragments

import android.app.Activity
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import pac.app.awesomeweather.R
import pac.app.awesomeweather.adapters.AdapterForecastsWeather
import pac.app.awesomeweather.adapters.wrappers.ForecastWrapper
import pac.app.awesomeweather.utils.WeatherDatabase
import pac.app.awesomeweather.utils.WeatherService
import pac.app.awesomeweather.utils.database
import pac.app.awesomeweather.utils.yandex_parser.models.DAY_TYPE_DAY
import pac.app.awesomeweather.utils.yandex_parser.models.DAY_TYPE_EVENING
import pac.app.awesomeweather.utils.yandex_parser.models.DAY_TYPE_MORNING
import pac.app.awesomeweather.utils.yandex_parser.models.DAY_TYPE_NIGHT

public class DetailsCurrentWeather : ListFragment() {

    private val TAG = "DetailsCurrentWeather"

    private var arrayAdapter: AdapterForecastsWeather? = null
    private var dateWeather: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dateWeather = getArguments().getLong(DATE_WEATHER)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setEmptyText(getString(R.string.no_data))
        setListShownNoAnimation(true)

        getListView().setSelector(android.R.color.transparent)
        arrayAdapter = AdapterForecastsWeather(getActivity())
        getListView().setAdapter(arrayAdapter)
    }

    override fun onStart() {
        super.onStart()

        database.getForecastByDay(dateWeather!!) { result ->
            arrayAdapter?.clear()

            result.forEach { item ->
                val forecastItemWrapper = ForecastWrapper()

                forecastItemWrapper.partDay = when (item.type) {
                    DAY_TYPE_MORNING -> getString(R.string.morning)
                    DAY_TYPE_DAY -> getString(R.string.day)
                    DAY_TYPE_EVENING -> getString(R.string.evening)
                    DAY_TYPE_NIGHT -> getString(R.string.night)
                    else -> ""
                }

                forecastItemWrapper.temperature = item.strTemperature
                forecastItemWrapper.image = item.image
                forecastItemWrapper.weatherType = item.weatherType

                arrayAdapter?.addItem(forecastItemWrapper)
            }

            arrayAdapter?.notifyDataSetChanged()
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        val DATE_WEATHER = "date_weather"

        fun newInstance(timeInMs: Long): DetailsCurrentWeather {
            val detailsCurrentWeather = DetailsCurrentWeather()
            val bundle = Bundle()
            bundle.putLong(DATE_WEATHER, timeInMs)
            detailsCurrentWeather.setArguments(bundle)
            return detailsCurrentWeather
        }
    }

}
