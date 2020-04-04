package com.example.bussallokering.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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