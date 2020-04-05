package com.example.bussallokering.bookings

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bussallokering.confirmedtrips.ConfirmedTrip
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BookingsViewModel : ViewModel() {

  private val isLoadingMutableLiveData = MutableLiveData<Boolean>(false)
  val isLoading : LiveData<Boolean>
    get() = isLoadingMutableLiveData

  private val bookingsMutableLiveData = MutableLiveData<List<Booking>>()
  val bookings : LiveData<List<Booking>>
    get() = bookingsMutableLiveData

  fun loadBookings(context : Context) {
    GlobalScope.launch {
      loadRealBookings(context)
    }
  }

  private suspend fun loadMockBookings(context : Context) {
    isLoadingMutableLiveData.postValue(true)
    delay(1000)
    val bookings = listOf<Booking>(
      Booking("Nyckelby", "Brommaplan", LocalDateTime.of(2020,5,10, 12, 0), LocalDateTime.of(2020,5,10, 14, 0)),
      Booking("Brommaplan", "Nyckelby", LocalDateTime.of(2020,5,10, 19, 0), LocalDateTime.of(2020,5,10, 20, 0)),
      Booking("Nyckelby", "Brommaplan", LocalDateTime.of(2020,5,11, 10, 0), LocalDateTime.of(2020,5,11, 13, 0))
      )
    bookingsMutableLiveData.postValue(bookings)

    isLoadingMutableLiveData.postValue(false)
  }


  private suspend fun loadRealBookings(context : Context) {
    isLoadingMutableLiveData.postValue(true)

    val url = "http://192.168.1.105:5000/triprequests?user=5e88ebc5063cda49945e782d"

    val jsonObjectRequest = JsonArrayRequest(
      Request.Method.GET, url, null,
      Response.Listener { response ->
        Log.d("appen", response.toString())

        val bookings = IntRange(0, response.length() - 1).map { i ->
          Booking(response.getJSONObject(i).getString("startPosition"),
            response.getJSONObject(i).getString("endPosition"),
            LocalDateTime.parse(response.getJSONObject(i).getString("timeSpanStart"), DateTimeFormatter.ISO_DATE_TIME),
            LocalDateTime.parse(response.getJSONObject(i).getString("timeSpanStart"), DateTimeFormatter.ISO_DATE_TIME)) // TODO: change to time span end
        }
        bookingsMutableLiveData.postValue(bookings)
        isLoadingMutableLiveData.postValue(false)
      },
      Response.ErrorListener { error ->
        Log.e("appen", error.toString())
        isLoadingMutableLiveData.postValue(false)
      }
    )
    val queue = Volley.newRequestQueue(context)
    queue.add(jsonObjectRequest)
  }



}

