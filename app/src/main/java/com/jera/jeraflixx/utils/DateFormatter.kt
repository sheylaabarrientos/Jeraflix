package com.jera.jeraflixx.utils

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun String.dateFormatter(): String {
    if (this == "") {
        return "Sem data registrada"
    }
    val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    } else {
        return this
    }
    val parsedDate = LocalDate.parse(this)
    return parsedDate.format(formatter).toString()
}



