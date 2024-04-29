package com.dailies.model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dailies.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import com.dailies.datasource.DailiesRepository
import com.dailies.ui.navigation.Screen
import java.util.Locale

class DailiesViewModel (application: Application) : AndroidViewModel(application){
    private val repository: DailiesRepository = DailiesRepository(application)

    var dailyList: LiveData<List<Dailies>> = repository.getAllDaily()
        private set

    var mondayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.MONDAY)
        private set

    var tuesdayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.TUESDAY)
        private set

    var wednesdayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.WEDNESDAY)
        private set

    var thursdayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.THURSDAY)
        private set

    var fridayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.FRIDAY)
        private set

    var saturdayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.SATURDAY)
        private set

    var sundayList: LiveData<List<Dailies>> = repository.getDailiesByDay(DayOfWeek.SUNDAY)
        private set

    var notifyList: LiveData<List<Dailies>> = repository.getDailiesByNotify(true)
        private set

    var currentDaily by mutableStateOf<Dailies?>(null)
        private set


    fun loadAllDailies():LiveData<List<Dailies>>{
        return repository.getAllDaily()
    }

    fun insertDailies(newDailies:Dailies){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(newDailies)
        }
    }

    fun removeDailies(deleteDailies: Dailies){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(deleteDailies)
        }
    }

    private val anyDay = application.resources.getStringArray(R.array.day_array)[0]
    private val anyName = application.resources.getStringArray(R.array.day_array)[0]





    fun updateDailiesSearch(value: DailiesSearch){
        getDailies(value)
    }

    var dailiesSearch: DailiesSearch by mutableStateOf(
        DailiesSearch(
            day = anyDay
        )
    )
        private set


    private fun getDailies(newDailiesSearch: DailiesSearch){
        var changed = false
        if (newDailiesSearch.day != dailiesSearch.day){
            changed = true
        }

        if (changed){
            if (newDailiesSearch.day == anyDay){
                dailyList = repository.getDailiesByDay(DayOfWeek.valueOf(newDailiesSearch.day.uppercase(Locale.ROOT)))
            }
        }
    }

    companion object {
        lateinit var currentDaily: Dailies
    }

    fun updateDaily(dailies:Dailies){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateDailies(dailies)
        }

    }


}