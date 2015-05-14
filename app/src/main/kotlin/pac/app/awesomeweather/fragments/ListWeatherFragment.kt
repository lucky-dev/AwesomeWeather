package pac.app.awesomeweather.fragments

import android.app.Activity
import android.os.Bundle
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.app.ListFragment
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

import pac.app.awesomeweather.R
import pac.app.awesomeweather.activities.DetailsWeatherActivity
import pac.app.awesomeweather.utils.WeatherDatabase
import pac.app.awesomeweather.utils.WeatherService
import pac.app.awesomeweather.utils.isLoadingData
import pac.app.awesomeweather.utils.isNeedToUpdate
import java.text.SimpleDateFormat
import java.util.Date

public class ListWeatherFragment : ListFragment() {

    private var db: WeatherDatabase? = null
    private var arrayAdapter: ArrayAdapter<DateModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setEmptyText(getString(R.string.no_data))
        setListShownNoAnimation(true)

        arrayAdapter = ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1)
        getListView().setAdapter(arrayAdapter)

        db = WeatherDatabase.getInstance(getActivity().getApplicationContext())
    }

    override fun onStart() {
        super.onStart()

        val intentFilter = IntentFilter()
        intentFilter.addAction(WeatherService.ACTION_START_UPDATING)
        intentFilter.addAction(WeatherService.ACTION_STOP_UPDATING)
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(apiResponseReceiver, intentFilter)

        if (isLoadingData(getActivity())) {
            setListShownNoAnimation(false)
        } else {
            db?.getDaysYandexForecast { result ->
                arrayAdapter?.clear()

                result.forEach {
                    arrayAdapter?.add(DateModel(it))
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(apiResponseReceiver)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        val dateModel = arrayAdapter?.getItem(position)

        val intent = Intent(getActivity(), javaClass<DetailsWeatherActivity>())
        intent.putExtra(DetailsWeatherActivity.DATE_WEATHER, dateModel!!.dateInMs)
        getActivity().startActivity(intent)
    }

    private val apiResponseReceiver = object: BroadcastReceiver() {
        public override fun onReceive(context: Context, intent: Intent) {
            val response = intent.getIntExtra(WeatherService.RESPONSE_PARAM_CODE, WeatherService.RESPONSE_VALUE_CODE_ERROR)

            if (intent.getAction() == WeatherService.ACTION_START_UPDATING) {
                setListShownNoAnimation(false)
            } else if (intent.getAction() == WeatherService.ACTION_STOP_UPDATING) {
                setListShownNoAnimation(true)

                arrayAdapter?.clear()

                if (response == WeatherService.RESPONSE_VALUE_CODE_OK) {
                    db?.getDaysYandexForecast { result ->
                        result.forEach {
                            arrayAdapter?.add(DateModel(it))
                        }
                    }
                }
            }
        }
    }

    class DateModel(date: Date) {
        var dateInMs: Long
        private var strDate: String

        init {
            strDate = SimpleDateFormat("yyyy-MM-dd").format(date)
            dateInMs = date.getTime()
        }

        override fun toString(): String {
            return strDate
        }
    }

}
