package pac.app.awesomeweather.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import pac.app.awesomeweather.R
import pac.app.awesomeweather.YANDEX_WEATHER
import pac.app.awesomeweather.fragments.CurrentWeatherFragment
import pac.app.awesomeweather.utils.WeatherService
import pac.app.awesomeweather.utils.isLoadingData
import pac.app.awesomeweather.utils.isNeedToUpdate

public class MainActivity : ActionBarActivity() {

    private var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var lvListResources: ListView? = null
    private var currentWeatherFragment: CurrentWeatherFragment? = null
    private var optionsMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resourcesWeather = getResources().getStringArray(R.array.resources_weather)

        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        getSupportActionBar().setHomeButtonEnabled(true)
        getSupportActionBar().setTitle(resourcesWeather.get(0))

        drawerLayout = findViewById(R.id.drawer_layout) as? DrawerLayout
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

        lvListResources = findViewById(R.id.lv_list_resources) as? ListView

        lvListResources?.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, resourcesWeather))
        lvListResources?.setOnItemClickListener { (adapterView, view, i, l) -> drawerLayout?.closeDrawer(lvListResources) }

        currentWeatherFragment = getSupportFragmentManager().findFragmentById(R.id.current_weather_fragment) as? CurrentWeatherFragment
    }

    override fun onStart() {
        super.onStart()

        val intentFilter = IntentFilter()
        intentFilter.addAction(WeatherService.ACTION_START_UPDATING)
        intentFilter.addAction(WeatherService.ACTION_STOP_UPDATING)
        LocalBroadcastManager.getInstance(this).registerReceiver(apiResponseReceiver, intentFilter)

        if ((isNeedToUpdate(this)) && (!isLoadingData(this))) {
            WeatherService.downloadNews(this, YANDEX_WEATHER)
        }
    }

    override fun onStop() {
        super.onStop()

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

        setRefreshMenuItem(isLoadingData(this))

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle?.onOptionsItemSelected(item) == true) {
            return true
        }

        if (item!!.getItemId() == R.id.action_refresh) {
            if (!isLoadingData(this)) {
                WeatherService.downloadNews(this, YANDEX_WEATHER)
            }
        }

        return super.onOptionsItemSelected(item)
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
        super.onPostCreate(savedInstanceState);

        drawerToggle?.syncState();
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig);

        drawerToggle?.onConfigurationChanged(newConfig);
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
