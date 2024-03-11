package com.dailies.datasource

import android.app.Application
import java.time.LocalDateTime
import com.dailies.model.Dailies



class DailiesRepository(application: Application) {
    private val dailiesDao = DailiesDatabase.getDatabase(application)!!.dailiesDao()

    fun insert(dailies: Dailies){
        dailiesDao.insertSingleDaily(dailies)
    }

    fun insertMultipleDailies(dailies: List<Dailies>){
        dailiesDao.insertMultipleDaily(dailies)
    }

    fun getAllDaily() = dailiesDao.getAllDailies()
}