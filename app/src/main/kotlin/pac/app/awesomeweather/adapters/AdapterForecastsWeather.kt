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
import pac.app.awesomeweather.R
import pac.app.awesomeweather.adapters.wrappers.ForecastWrapper
import java.util.ArrayList

class AdapterForecastsWeather(var context: Context): BaseAdapter() {

    private val data: ArrayList<ForecastWrapper>
    private val layoutInflater: LayoutInflater?

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater

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

            rootViewItem = layoutInflater?.inflate(R.layout.forecast_weather_item, null)

            holder?.ivWeatherIcon = rootViewItem?.findViewById(R.id.iv_weather_icon) as? ImageView
            holder?.tvTemperature = rootViewItem?.findViewById(R.id.tv_temperature) as? TextView
            holder?.tvWeatherType = rootViewItem?.findViewById(R.id.tv_weather_type) as? TextView
            holder?.tvPartDay = rootViewItem?.findViewById(R.id.tv_part_day) as? TextView

            convertView?.setTag(holder)
        } else {
            holder = convertView.getTag() as? ViewHolder
        }

        holder?.tvPartDay?.setText(item.partDay)

        if (item.image.isNotEmpty()) {
            Picasso.with(context)
                    .load(item.image)
                    .fit()
                    .into(holder?.ivWeatherIcon)
        }

        holder?.tvTemperature?.setText(item.temperature)
        holder?.tvWeatherType?.setText(item.weatherType)

        return rootViewItem
    }

    data class ViewHolder(var ivWeatherIcon: ImageView? = null,
                          var tvTemperature: TextView? = null,
                          var tvWeatherType: TextView? = null,
                          var tvPartDay: TextView? = null)

}
