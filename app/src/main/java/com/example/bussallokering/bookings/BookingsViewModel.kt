package com.example.bussallokering.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class BookingsViewModel : ViewModel() {

  private val isLoadingMutableLiveData = MutableLiveData<Boolean>(false)
  val isLoading : LiveData<Boolean>
    get() = isLoadingMutableLiveData

  private val bookingsMutableLiveData = MutableLiveData<List<Booking>>()
  val bookings : LiveData<List<Booking>>
    get() = bookingsMutableLiveData

  fun loadBookings() {
    GlobalScope.launch {
      loadMockBookings() // TODO: replace with the real deal
    }
  }

  private suspend fun loadMockBookings() {
    isLoadingMutableLiveData.postValue(true)
    delay(1000)
    val bookings = listOf<Booking>(
      Booking("Nyckelby", "Brommaplan", LocalDate.of(2020,5,10), LocalTime.of(12, 0),LocalTime.of(14, 0)),
      Booking("Brommaplan", "Nyckelby", LocalDate.of(2020,5,10), LocalTime.of(19, 0),LocalTime.of(20, 0)),
      Booking("Nyckelby", "Brommaplan", LocalDate.of(2020,5,11), LocalTime.of(10, 0),LocalTime.of(13, 0))
      )
    bookingsMutableLiveData.postValue(bookings)

    isLoadingMutableLiveData.postValue(false)
  }


}

