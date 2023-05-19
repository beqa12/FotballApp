package com.example.footballapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeHelper {

    companion object{
        fun getMatchDate(time: Long, pattern: String): String {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val formatted = sdf.format(Date(time))
            return formatted
        }
    }
}