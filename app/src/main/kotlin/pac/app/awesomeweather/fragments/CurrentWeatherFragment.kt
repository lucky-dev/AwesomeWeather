package pac.app.awesomeweather.fragments

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import pac.app.awesomeweather.R
import pac.app.awesomeweather.utils.WeatherService
import pac.app.awesomeweather.utils.WeatherDatabase
import pac.app.awesomeweather.utils.isLoadingData
import pac.app.awesomeweather.utils.isNeedToUpdate

public class CurrentWeatherFragment : Fragment() {

    private val TAG = "CurrentWeatherFragment"

    private var llCurrentWeather: LinearLayout? = null
    private var ivWeatherIcon: ImageView? = null
    private var tvTemperature: TextView? = null
    private var tvWeatherType: TextView? = null
    private var rlLoadingData: RelativeLayout? = null
    private var tvNoData: TextView? = null
    private var db: WeatherDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val flRoot = inflater.inflate(R.layout.fragment_current_weather, container, false)

        llCurrentWeather = flRoot.findViewById(R.id.ll_current_weather) as? LinearLayout
        ivWeatherIcon = flRoot.findViewById(R.id.iv_weather_icon) as? ImageView
        tvTemperature = flRoot.findViewById(R.id.tv_temperature) as? TextView
        tvWeatherType = flRoot.findViewById(R.id.tv_weather_type) as? TextView
        rlLoadingData = flRoot.findViewById(R.id.rl_loading_data) as? RelativeLayout
        tvNoData = flRoot.findViewById(R.id.tv_no_data) as? TextView

        return flRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showAnimationLoadingData(false)

        db = WeatherDatabase.getInstance(getActivity().getApplicationContext())
    }

    override fun onStart() {
        super.onStart()

        val intentFilter = IntentFilter()
        intentFilter.addAction(WeatherService.ACTION_START_UPDATING)
        intentFilter.addAction(WeatherService.ACTION_STOP_UPDATING)
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(apiResponseReceiver, intentFilter)

        if (isLoadingData(getActivity())) {
            showAnimationLoadingData(true)
        } else {
            updateWeather()
        }
    }

    override fun onStop() {
        super.onStop()

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(apiResponseReceiver)
    }

    private fun updateWeather() {
        db?.getCurrentYandexForecast { item ->
            Log.d(TAG, "Current weather: $item")

            if (item.image.isNotEmpty()) {
                Picasso.with(getActivity())
                        .load(item.image)
                        .fit()
                        .into(ivWeatherIcon)
            }

            tvTemperature?.setText(item.strTemperature)
            tvWeatherType?.setText(item.weatherType)
        }
    }

    private fun showAnimationLoadingData(show: Boolean) {
        llCurrentWeather?.setVisibility(if (show) View.GONE else View.VISIBLE)
        rlLoadingData?.setVisibility(if (show) View.VISIBLE else View.GONE)
        tvNoData?.setVisibility(View.GONE)
    }

    private fun showLabelNoData(show: Boolean) {
        llCurrentWeather?.setVisibility(View.GONE)
        rlLoadingData?.setVisibility(View.GONE)
        tvNoData?.setVisibility(if (show) View.VISIBLE else View.GONE)
    }

    private val apiResponseReceiver = object: BroadcastReceiver() {
        public override fun onReceive(context: Context, intent: Intent) {
            val response = intent.getIntExtra(WeatherService.RESPONSE_PARAM_CODE, WeatherService.RESPONSE_VALUE_CODE_ERROR)

            if (intent.getAction() == WeatherService.ACTION_START_UPDATING) {
                showAnimationLoadingData(true)
            } else if (intent.getAction() == WeatherService.ACTION_STOP_UPDATING) {
                if (response == WeatherService.RESPONSE_VALUE_CODE_OK) {
                    showAnimationLoadingData(false)

                    updateWeather()
                } else {
                    showLabelNoData(true)

                    Toast.makeText(getActivity(), getString(R.string.error_message), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
