package com.example.bussallokering.confirmedtrips

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.bussallokering.bookings.Booking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConfirmedTripsViewModel : ViewModel() {

  private val isLoadingMutableLiveData = MutableLiveData<Boolean>(false)
  val isLoading : LiveData<Boolean>
    get() = isLoadingMutableLiveData

  private val confirmedTripsMutableLiveData = MutableLiveData<List<ConfirmedTrip>>()
  val confirmedTrips : LiveData<List<ConfirmedTrip>>
    get() = confirmedTripsMutableLiveData

  fun loadConfirmedTrips(context : Context) {
    GlobalScope.launch {
      loadRealConfirmedTrips(context)
    }
  }

  private suspend fun loadMockConfirmedTrips(context : Context) {
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


  private suspend fun loadRealConfirmedTrips(context : Context) {
    isLoadingMutableLiveData.postValue(true)

    val url = "http://192.168.1.105:5000/tripbookings?user=5e88ebc5063cda49945e782d"

    val jsonObjectRequest = JsonArrayRequest(
      Request.Method.GET, url, null,
      Response.Listener { response ->
        Log.d("appen", response.toString())

        val confirmedTrips = IntRange(0, response.length() - 1).map { i ->
          ConfirmedTrip(response.getJSONObject(i).getString("startPosition"),
            response.getJSONObject(i).getString("endPosition"),
            LocalDateTime.parse(response.getJSONObject(i).getString("departureTime"), DateTimeFormatter.ISO_DATE_TIME))
        }
        confirmedTripsMutableLiveData.postValue(confirmedTrips)
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

