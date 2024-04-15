package com.dailies.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.DayOfWeek

@Dao
interface DailiesDao {
    @Insert
    fun insertSingleDaily(dailies: Dailies)

    @Insert
    fun insertMultipleDaily(dailyList: List<Dailies>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDaily(dailies: Dailies)

    @Delete
    fun deleteDaily(dailies: Dailies)

    @Query("DELETE FROM dailies")
    fun deleteAll()

    @Query("SELECT * FROM dailies")
    fun getAllDailies(): LiveData<List<Dailies>>

    @Query("SELECT * FROM dailies WHERE day = :day")
    fun getDailiesbyDay(
        day: DayOfWeek
    ): LiveData<List<Dailies>>
}