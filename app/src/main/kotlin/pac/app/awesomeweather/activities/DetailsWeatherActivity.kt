package pac.app.awesomeweather.activities

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.widget.FrameLayout
import pac.app.awesomeweather.R
import pac.app.awesomeweather.fragments.DetailsCurrentWeather
import java.text.SimpleDateFormat
import java.util.Date

public class DetailsWeatherActivity : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_weather)

        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        getSupportActionBar().setHomeButtonEnabled(true)

        var formattedDate = ""

        if (savedInstanceState == null) {
            var date: Long = -1
            if (getIntent() != null) {
                date = getIntent().getLongExtra(DATE_WEATHER, -1)
            }

            if (date != -1L) {
                formattedDate = SimpleDateFormat("yyyy-MM-dd").format(Date(date))

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_root, DetailsCurrentWeather.newInstance(date))
                        .commit()
            }
        }

        getSupportActionBar().setTitle(formattedDate)
    }

    companion object {
        val DATE_WEATHER = "date_weather"
    }

}
