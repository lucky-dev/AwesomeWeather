package pac.app.awesomeweather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.text
import pac.app.awesomeweather.R
import pac.app.awesomeweather.adapters.wrappers.ForecastWrapper
import java.util.ArrayList

class AdapterForecastsWeather(var context: Context): BaseAdapter() {

    private val data: ArrayList<ForecastWrapper>

    init {
        data = ArrayList<ForecastWrapper>()
    }

    fun addItem(item: ForecastWrapper) = data.add(item)

    override fun getCount() = data.size()

    override fun getItem(position: Int) = data.get(position)

    override fun getItemId(position: Int) = position.toLong()

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = data.get(position)

        var holder: ViewHolder? = null
        var rootViewItem: View? = null
        if (convertView == null) {
            holder = ViewHolder()

            rootViewItem = context.layoutInflater.inflate(R.layout.forecast_weather_item, null)

            holder?.ivWeatherIcon = rootViewItem?.find<ImageView>(R.id.iv_weather_icon)
            holder?.tvTemperature = rootViewItem?.find<TextView>(R.id.tv_temperature)
            holder?.tvWeatherType = rootViewItem?.find<TextView>(R.id.tv_weather_type)
            holder?.tvPartDay = rootViewItem?.find<TextView>(R.id.tv_part_day)

            convertView?.setTag(holder)
        } else {
            holder = convertView.getTag() as? ViewHolder
        }

        holder?.tvPartDay?.text = item.partDay

        if (item.image.isNotEmpty()) {
            Picasso.with(context)
                    .load(item.image)
                    .fit()
                    .into(holder?.ivWeatherIcon)
        }

        holder?.tvTemperature?.text = item.temperature
        holder?.tvWeatherType?.text = item.weatherType

        return rootViewItem
    }

    data class ViewHolder(var ivWeatherIcon: ImageView? = null,
                          var tvTemperature: TextView? = null,
                          var tvWeatherType: TextView? = null,
                          var tvPartDay: TextView? = null)

}
