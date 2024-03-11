package com.dailies.model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import com.dailies.datasource.DailiesRepository
import com.dailies.ui.navigation.Screen

class DailiesViewModel (application: Application) : AndroidViewModel(application){
    private val repository: DailiesRepository = DailiesRepository(application)

    var dailyList: LiveData<List<Dailies>> = repository.getAllDaily()
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
}