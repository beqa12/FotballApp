package com.example.footballapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeHelper {

    companion object{
        fun getMatchDate(time: Long): String {
            val sdf = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            val formatted = sdf.format(Date(time))
            return formatted
        }
    }
}