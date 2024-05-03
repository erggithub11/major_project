

package com.dailies.datasource.util

import androidx.room.TypeConverter
import java.time.DayOfWeek

object DayConverter {

    /**
     *  Used to convert DayOfWeek into strings via to.String() function
     *
     */
    @TypeConverter
    @JvmStatic
    fun toString(day:DayOfWeek)
    = day.toString()


    /**
     * Used to convert strings to DayoOfWeek enum values.
     *
     */
    @TypeConverter
    @JvmStatic
    fun toDay(day:String)
    = DayOfWeek.valueOf(day)
}