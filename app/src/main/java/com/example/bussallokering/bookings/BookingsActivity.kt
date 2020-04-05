package com.example.bussallokering.bookings

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bussallokering.databinding.ActivityBookingsBinding

class BookingsActivity : AppCompatActivity() {

  val viewModel: BookingsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    title = "Dina bokade resor"

    val binding = ActivityBookingsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    viewModel.bookings.observe(this,  Observer { confirmedTrips ->
      val adapter = BookingsAdapter(confirmedTrips)
      binding.recyclerView.adapter = adapter
      binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    })

    viewModel.loadBookings(this)

  }


}
