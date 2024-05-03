package com.dailies.datasource.util

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

/**
 * Legacy converter class that is left unused.
 */
object LocalDateTimeConverter {
    @TypeConverter
    @JvmStatic
    fun toLocalDate(timestamp: Long): LocalDateTime =
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp),
            TimeZone.getDefault().toZoneId()
        )

    @TypeConverter
    @JvmStatic
    fun toTimestamp(localDateTime: LocalDateTime): Long =
        localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    
}