package com.terabyte.core.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateHelper {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getMinDateMills(): Long {
        val minDate = Calendar.getInstance().apply {
            set(2010, Calendar.JANUARY, 1)
        }
        return minDate.timeInMillis
    }

    fun getMaxDateMills(): Long {
        return Calendar.getInstance().timeInMillis - 86400L * 1000L
    }

    fun dateToString(date: Calendar): String {
        return dateFormat.format(date)
    }


}