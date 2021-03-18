package com.example.growby.utils

import android.content.Context
import com.example.growby.R
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: String?, context: Context?): String {
    val locale = Locale("id", "ID")
    Locale.setDefault(locale)

//    val dateTemp = date!!.substring(0, 5) + "-" + date!!.substring(5, date.length)
    val dateTemp = date
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val dateParsed = dateFormat.parse(dateTemp)
    val dayOfWeek = dateParsed.day

    return SimpleDateFormat("dd MMM yyyy").format(dateFormat.parse(dateTemp) as Date)
}


