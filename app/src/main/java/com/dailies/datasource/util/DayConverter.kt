

package com.dailies.datasource.util

import androidx.room.TypeConverter
import java.time.DayOfWeek

object DayConverter {

    /**
     *  Used to convert day of week enum to string
     *
     */
    @TypeConverter
    @JvmStatic
    fun toString(day:DayOfWeek)
    = day.toString()


    /**
     * Used to convert string to day of week enum
     *
     */
    @TypeConverter
    @JvmStatic
    fun toDay(day:String)
    = DayOfWeek.valueOf(day)
}