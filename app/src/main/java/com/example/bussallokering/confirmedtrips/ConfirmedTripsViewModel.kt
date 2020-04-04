package com.example.bussallokering.confirmedtrips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ConfirmedTripsViewModel : ViewModel() {

  private val isLoadingMutableLiveData = MutableLiveData<Boolean>(false)
  val isLoading : LiveData<Boolean>
    get() = isLoadingMutableLiveData

  private val confirmedTripsMutableLiveData = MutableLiveData<List<ConfirmedTrip>>()
  val confirmedTrips : LiveData<List<ConfirmedTrip>>
    get() = confirmedTripsMutableLiveData

  fun loadConfirmedTrips() {
    GlobalScope.launch {
      loadMockConfirmedTrips() // TODO: replace with the real deal
    }
  }

  private suspend fun loadMockConfirmedTrips() {
    isLoadingMutableLiveData.postValue(true)
    delay(1000)
    val confirmedTrips = listOf<ConfirmedTrip>(
      ConfirmedTrip("Nyckelby", "Brommaplan", LocalDateTime.of(2020,5,6, 13, 12, 0)),
      ConfirmedTrip("Brommaplan", "Nyckelby", LocalDateTime.of(2020,5,6, 19, 30, 0)),
      ConfirmedTrip("Nyckelby", "Brommaplan", LocalDateTime.of(2020,5,7, 10, 0, 0))
    )
    confirmedTripsMutableLiveData.postValue(confirmedTrips)

    isLoadingMutableLiveData.postValue(false)
  }


}

