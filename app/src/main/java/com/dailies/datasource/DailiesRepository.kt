package com.dailies.datasource

import android.app.Application
import com.dailies.model.Dailies
import java.time.DayOfWeek

/**
 * Repository allows access to the dao and are primarily used everywhere else in the program.
 */
class DailiesRepository(application: Application) {
    private val dailiesDao = DailiesDatabase.getDatabase(application)!!.dailiesDao()


    /**
     * Crud options produced for database management
     */
    fun insert(dailies: Dailies){
        dailiesDao.insertSingleDaily(dailies)
    }

    fun delete(dailies: Dailies){
        dailiesDao.deleteDaily(dailies)
    }

    fun insertMultipleDailies(dailies: List<Dailies>){
        dailiesDao.insertMultipleDaily(dailies)
    }

    /**
     * Fucntion to retrieve all Dailies from the dao
     */
    fun getAllDaily() = dailiesDao.getAllDailies()


    /**
     * These functions sends a query to the DAO to retrieve filtered value from the Dao
     */
    fun getDailiesByDay(
        day: DayOfWeek
    ) = dailiesDao.getDailiesbyDay(day)

    fun getDailiesByNotify(
        on: Boolean
    ) = dailiesDao.getDailiesbyNotify(on)


    /**
     * update Dailes sends an update request that updates the selected daily with the new value.
     */
    fun updateDailies(
        dailies: Dailies
    ) = dailiesDao.updateDaily(dailies)



}