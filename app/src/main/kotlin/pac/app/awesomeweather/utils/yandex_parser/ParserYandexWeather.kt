package pac.app.awesomeweather.utils.yandex_parser

import android.net.ParseException
import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import pac.app.awesomeweather.utils.yandex_parser.models.*
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class ParserYandexWeather() {
    private val parser: XmlPullParser

    init {
        parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
    }

    fun parse(xml: InputStream?): List<ForecastItem> {
        parser.setInput(xml, null)
        parser.nextTag()
        return readForecast(parser)
    }

    private fun readForecast(parser: XmlPullParser): List<ForecastItem> {
        val result = ArrayList<ForecastItem>()

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                val name = parser.getName()

                if (name == "day") {
                    readDay(parser, result)
                } else {
                    skip(parser)
                }
            }
        }

        return result
    }

    private fun readDay(parser: XmlPullParser, listForecastItems: ArrayList<ForecastItem>) {
        var date: Date = Date()

        try {
            val strDate: String? = parser.getAttributeValue(null, "date")
            if (strDate != null) {
                date = SimpleDateFormat("yyyy-MM-dd").parse(strDate)
            }
        } catch (e: ParseException) {
        }

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                val name = parser.getName()

                if (name == "day_part") {
                    val type: String? = parser.getAttributeValue(null, "type")

                    when (type) {
                        "morning" -> parsePartDay(parser, listForecastItems, date, DAY_TYPE_MORNING)
                        "day" -> parsePartDay(parser, listForecastItems, date, DAY_TYPE_DAY)
                        "evening" -> parsePartDay(parser, listForecastItems, date, DAY_TYPE_EVENING)
                        "night" -> parsePartDay(parser, listForecastItems, date, DAY_TYPE_NIGHT)
                        else -> skip(parser)
                    }
                } else {
                    skip(parser)
                }
            }
        }
    }

    private fun parsePartDay(parser: XmlPullParser, listForecastItems: ArrayList<ForecastItem>, date: Date, dayType: Int) {
        val item = ForecastItem()

        item.date = date
        item.type = dayType

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                val name = parser.getName()

                when (name) {
                    "temperature_from" -> item.temperatureFrom = readInt(parser)
                    "temperature_to" -> item.temperatureTo = readInt(parser)
                    "temperature" -> item.temperature = readInt(parser)
                    "image-v3" -> item.image = "http://yandex.st/weather/1.2.61/i/icons/48x48/${readText(parser)}.png"
                    "weather_type" -> item.weatherType = readText(parser)
                    else -> skip(parser)
                }
            }
        }

        listForecastItems.add(item)
    }

    private fun skip(parser: XmlPullParser) {
        var countTags = 1
        while (countTags != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> countTags--
                XmlPullParser.START_TAG -> countTags++
            }
        }
    }

    private fun readText(parser: XmlPullParser): String {
        var result = ""

        try {
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText()
                parser.nextTag()
            }
        } catch (e: IOException) {
        } catch (e: XmlPullParserException) {
        }

        return result
    }

    private fun readInt(parser: XmlPullParser): Int {
        var result = Int.MIN_VALUE

        try {
            result = readText(parser).toInt()
        } catch (e: NumberFormatException) {
        }

        return result
    }

}
