package com.example.bussallokering.confirmedtrips

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bussallokering.databinding.ActivityConfirmedTripsBinding

class ConfirmedTripsActivity : AppCompatActivity() {

  val viewModel: ConfirmedTripsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    title = "Dina bekräftade resor"

    val binding = ActivityConfirmedTripsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    viewModel.confirmedTrips.observe(this,  Observer { confirmedTrips ->
      val adapter = ConfirmedTripsAdapter(confirmedTrips)
      binding.recyclerView.adapter = adapter
      binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    })

    viewModel.loadConfirmedTrips(this)

  }


}
