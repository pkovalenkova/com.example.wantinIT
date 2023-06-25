package com.example.plugins

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Primitive

/*object DataParser{
    fun parseDateToString(datetime:Instant):String{
        var str=datetime.toString().filter { it.isDigit() }
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
}*/

class LocalDateTimeSerializer:KSerializer<Instant>{
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTimeDescriptor",PrimitiveKind.STRING);

    /*override fun deserialize(decoder: Decoder): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        //val timeStamp = LocalDateTime.parse("05.06.2023 10:22:11",formatter)
        return LocalDateTime.parse(decoder.decodeString(),formatter)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.toString())
    }*/

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString((LocalDateTime.ofInstant(value, ZoneOffset.UTC).toString()))
    }

    override fun deserialize(decoder: Decoder): Instant {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val result = LocalDateTime.parse(decoder.decodeString(),formatter)
        return result.toInstant(ZoneOffset.UTC)
    }
}
