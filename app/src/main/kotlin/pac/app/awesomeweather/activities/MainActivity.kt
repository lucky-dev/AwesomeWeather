package pac.app.awesomeweather.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import pac.app.awesomeweather.R
import pac.app.awesomeweather.YANDEX_WEATHER
import pac.app.awesomeweather.fragments.CurrentWeatherFragment
import pac.app.awesomeweather.fragments.ListWeatherFragment
import pac.app.awesomeweather.utils.WeatherService
import pac.app.awesomeweather.utils.isNeedToUpdate

public class MainActivity : AppCompatActivity(), ListWeatherFragment.ListWeatherFragmentListener {

    private var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var lvListResources: ListView? = null
    private var currentWeatherFragment: CurrentWeatherFragment? = null
    private var optionsMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resourcesWeather = getResources().getStringArray(R.array.resources_weather)

        getSupportActionBar().setDisplayHomeAsUpEnabled(false)
        getSupportActionBar().setHomeButtonEnabled(false)
        getSupportActionBar().setTitle(resourcesWeather.get(0))

        drawerLayout = find<DrawerLayout>(R.id.drawer_layout)
        drawerToggle = object: ActionBarDrawerToggle(this,
                                                    drawerLayout,
                                                    R.string.drawer_open,
                                                    R.string.drawer_close) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }
        }

        drawerLayout?.setDrawerListener(drawerToggle)

        lvListResources = find<ListView>(R.id.lv_list_resources)

        lvListResources?.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, resourcesWeather))
        lvListResources?.setOnItemClickListener { adapterView, view, i, l -> drawerLayout?.closeDrawer(lvListResources) }

        currentWeatherFragment = getSupportFragmentManager().findFragmentById(R.id.current_weather_fragment) as? CurrentWeatherFragment
    }

    override fun onStart() {
        super<AppCompatActivity>.onStart()

        val intentFilter = IntentFilter()
        intentFilter.addAction(WeatherService.ACTION_START_UPDATING)
        intentFilter.addAction(WeatherService.ACTION_STOP_UPDATING)
        LocalBroadcastManager.getInstance(this).registerReceiver(apiResponseReceiver, intentFilter)

        if ((isNeedToUpdate(this)) && (!WeatherService.isRunning(this))) {
            WeatherService.downloadNews(this, YANDEX_WEATHER)
        }
    }

    override fun onStop() {
        super<AppCompatActivity>.onStop()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(apiResponseReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)

        optionsMenu = menu

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val drawerOpen = drawerLayout?.isDrawerOpen(lvListResources) ?: false
        menu.findItem(R.id.action_refresh).setVisible(!drawerOpen)

        setRefreshMenuItem(WeatherService.isRunning(this))

        return super<AppCompatActivity>.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle?.onOptionsItemSelected(item) == true) {
            return true
        }

        if (item!!.getItemId() == R.id.action_refresh) {
            if (!WeatherService.isRunning(this)) {
                WeatherService.downloadNews(this, YANDEX_WEATHER)
            }
        }

        return super<AppCompatActivity>.onOptionsItemSelected(item)
    }

    private fun setRefreshMenuItem(refreshing: Boolean) {
        val refreshItem = optionsMenu?.findItem(R.id.action_refresh)

        if (refreshItem != null) {
            if (refreshing) {
                MenuItemCompat.setActionView(refreshItem, R.layout.indeterminate_progress)
            } else {
                MenuItemCompat.setActionView(refreshItem, null)
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onPostCreate(savedInstanceState);

        drawerToggle?.syncState();
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super<AppCompatActivity>.onConfigurationChanged(newConfig)

        drawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun clickOnDate(date: Long) {
        startActivity<DetailsWeatherActivity>(DetailsWeatherActivity.DATE_WEATHER to date)
    }

    private val apiResponseReceiver = object: BroadcastReceiver() {
        public override fun onReceive(context: Context, intent: Intent) {
            if (intent.getAction() == WeatherService.ACTION_START_UPDATING) {
                setRefreshMenuItem(true)
            } else if (intent.getAction() == WeatherService.ACTION_STOP_UPDATING) {
                setRefreshMenuItem(false)
            }
        }
    }
}
