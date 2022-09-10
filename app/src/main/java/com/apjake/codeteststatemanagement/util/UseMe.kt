package com.apjake.codeteststatemanagement.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object UseMe {
    fun nowTimestamp(): Long{
        return System.currentTimeMillis()
    }

    fun toDateString(date: Long?): String{
        if(date == null) return ""
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(date))
    }

}