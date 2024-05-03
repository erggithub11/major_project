package com.dailies.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.DayOfWeek

/**
 * The dao class holds the middle way of data management and are used in the repository.
 */
@Dao
interface DailiesDao {

    /**
     * Insert allows new data to be inserted into the class.
     */
    @Insert
    fun insertSingleDaily(dailies: Dailies)

    @Insert
    fun insertMultipleDaily(dailyList: List<Dailies>)

    /**
     * Update allows options to change data values without removing them.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDaily(dailies: Dailies)

    /**
     * Delete selects the chosen dailies to be removed.
     */
    @Delete
    fun deleteDaily(dailies: Dailies)

    @Query("DELETE FROM dailies")
    fun deleteAll()

    /**
     * getAllDailies retrieves all dailies from the database.
     */
    @Query("SELECT * FROM dailies")
    fun getAllDailies(): LiveData<List<Dailies>>


    /**
     * The queries below are used to filter the database option out.
     */
    @Query("SELECT * FROM dailies WHERE day = :day")
    fun getDailiesbyDay(
        day: DayOfWeek
    ): LiveData<List<Dailies>>

    @Query("SELECT * FROM dailies WHERE notify =:notify")
    fun getDailiesbyNotify(
        notify : Boolean
    ): LiveData<List<Dailies>>
}