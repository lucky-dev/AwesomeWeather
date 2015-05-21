package pac.app.awesomeweather.utils

import android.content.Context
import android.util.TimeUtils
import pac.app.awesomeweather.PREFS_NAME
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun setUpdatedTime(context: Context) {
    val settings = context.getSharedPreferences(PREFS_NAME, 0)
    val editor = settings.edit()
    editor.putLong("weather_updated_at", System.currentTimeMillis())
    editor.commit()
}

fun isNeedToUpdate(context: Context): Boolean {
    val settings = context.getSharedPreferences(PREFS_NAME, 0)

    val updatedAt = settings.getLong("weather_updated_at", 0)
    val diffTime = System.currentTimeMillis() - updatedAt

    val halfHour = 1800000 // 30 min

    return diffTime > halfHour
}
