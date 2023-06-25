package com.example.plugins

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.isValidEmail():Boolean{
    return true
    //this.matches("[A-Za-Z_0-9]\.[A-Za-Z_0-9]@[a-z]{3,20}\.[a-z]{2,7}")

}

fun Instant.parseDateToString():String{
    var str=this.toString().filter { it.isDigit() }
    val year=str.substring(0,4)
    str=str.drop(4)
    val months = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    val month = months[str.substring(0,2).toInt()-1]
    str=str.drop(2)
    var day=str.substring(0,2)
    if (day[0].equals('0')) day=day.drop(1)
    str=str.drop(2)
    val time="${str.substring(0,2)}:${str.substring(2,4)}"
    return "${time}, ${day} $month $year"
}

fun Instant.parseDateForInput():String{
    var str=this.toString().filter { it.isDigit() }
    val year=str.substring(0,4)
    str=str.drop(4)
    val month =str.substring(0,2)
    str=str.drop(2)
    var day=str.substring(0,2)
    str=str.drop(2)
    return "$year-$month-${day}"
}

fun Instant.parseDateForDateTimeInput():String{
    var str=this.toString().filter { it.isDigit() }
    val year=str.substring(0,4)
    str=str.drop(4)
    val month =str.substring(0,2)
    str=str.drop(2)
    var day=str.substring(0,2)
    str=str.drop(2)
    val time="${str.substring(0,2)}:${str.substring(2,4)}"
    return "$year-$month-${day}T${time}"
}

fun String.toInstant():Instant{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val result = LocalDate.parse(this,formatter).atStartOfDay()
    return result.toInstant(ZoneOffset.UTC)
}

fun String.toInstantDateTime():Instant?{
    if(this.isNullOrEmpty()) return null
    val str=this.replace("T"," ")
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val result = LocalDate.parse(str,formatter).atStartOfDay()
    return result.toInstant(ZoneOffset.UTC)
}