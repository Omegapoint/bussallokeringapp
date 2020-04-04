package com.example.bussallokering.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime

class PlanViewModel : ViewModel() {
    private val selectedDateMutableLiveData = MutableLiveData<LocalDate>()
    val selectedDate : LiveData<LocalDate>
        get() = selectedDateMutableLiveData

    private val selectedFromTimeMutableLiveData = MutableLiveData<LocalTime>()
    val selectedFromTime : LiveData<LocalTime>
        get() = selectedFromTimeMutableLiveData

    private val selectedToTimeMutableLiveData = MutableLiveData<LocalTime>()
    val selectedToTime : LiveData<LocalTime>
        get() = selectedToTimeMutableLiveData


    private val isSubmittableMediatorLiveData = MediatorLiveData<Boolean>()
    val isSubmittable : LiveData<Boolean>
      get() = isSubmittableMediatorLiveData

    init {
        isSubmittableMediatorLiveData.addSource(selectedDate, Observer {
            isSubmittableMediatorLiveData.postValue(validateSubmit())
        })
        isSubmittableMediatorLiveData.addSource(selectedFromTime, Observer {
            isSubmittableMediatorLiveData.postValue(validateSubmit())
        })
        isSubmittableMediatorLiveData.addSource(selectedToTime, Observer {
            isSubmittableMediatorLiveData.postValue(validateSubmit())
        })
    }

    private fun validateSubmit() : Boolean {
        return selectedDate.value != null && selectedFromTime.value != null && selectedToTime.value != null
    }

    fun setSelectedDate(localDate: LocalDate) {
        selectedDateMutableLiveData.postValue(localDate)
    }

    fun setSelectedFromTime(localTime: LocalTime) {
        selectedFromTimeMutableLiveData.postValue(localTime)
    }

    fun setSelectedToTime(localTime: LocalTime) {
        selectedToTimeMutableLiveData.postValue(localTime)
    }

}