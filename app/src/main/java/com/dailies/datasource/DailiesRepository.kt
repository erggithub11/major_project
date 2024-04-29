package com.dailies.datasource

import android.app.Application
import java.time.LocalDateTime
import com.dailies.model.Dailies
import java.time.DayOfWeek


class DailiesRepository(application: Application) {
    private val dailiesDao = DailiesDatabase.getDatabase(application)!!.dailiesDao()

    fun insert(dailies: Dailies){
        dailiesDao.insertSingleDaily(dailies)
    }

    fun delete(dailies: Dailies){
        dailiesDao.deleteDaily(dailies)
    }

    fun insertMultipleDailies(dailies: List<Dailies>){
        dailiesDao.insertMultipleDaily(dailies)
    }

    fun getAllDaily() = dailiesDao.getAllDailies()

    fun getDailiesByDay(
        day: DayOfWeek
    ) = dailiesDao.getDailiesbyDay(day)

    fun getDailiesByNotify(
        on: Boolean
    ) = dailiesDao.getDailiesbyNotify(on)

    fun updateDailies(
        dailies: Dailies
    ) = dailiesDao.updateDaily(dailies)



}