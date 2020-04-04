package com.example.bussallokering.plan

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bussallokering.bookingconfirmation.BookingConfirmationActivity
import com.example.bussallokering.R
import com.example.bussallokering.bookings.BookingsActivity
import com.example.bussallokering.confirmedtrips.ConfirmedTripsActivity
import com.example.bussallokering.databinding.ActivityPlanBinding
import java.time.LocalDate
import java.time.LocalTime


class PlanActivity : AppCompatActivity() {

  val viewModel: PlanViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    title = "Planera din resa"

    val binding : ActivityPlanBinding = ActivityPlanBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonDate.setOnClickListener {
      showDatePicker()
    }

    binding.buttonTimeFrom.setOnClickListener {
      showFromTimePicker()
    }

    binding.buttonTimeTo.setOnClickListener {
      showToTimePicker()
    }

    binding.buttonSubmit.setOnClickListener {
      val intent = Intent(this, BookingConfirmationActivity::class.java)
      startActivity(intent)
    }

    binding.viewModel = viewModel
    binding.lifecycleOwner = this

  }

  private fun showDatePicker() {
    val displayDate : LocalDate = viewModel.selectedDate.value ?: LocalDate.now()
    var datePickerDialog = DatePickerDialog(
      this,
      OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        viewModel.setSelectedDate(LocalDate.of(year, monthOfYear, dayOfMonth))
      }, displayDate.year, displayDate.monthValue, displayDate.dayOfMonth
    )
    datePickerDialog.show()
  }

  private fun showFromTimePicker() {
    val displayFromTime : LocalTime = viewModel.selectedFromTime.value ?: LocalTime.now()
    var datePickerDialog = TimePickerDialog(
      this,
      TimePickerDialog.OnTimeSetListener{ view, hour, minute ->
        viewModel.setSelectedFromTime(LocalTime.of(hour, minute))
      }, displayFromTime.hour, displayFromTime.minute, true
    )
    datePickerDialog.show()
  }

  private fun showToTimePicker() {
    val displayFromTime : LocalTime = viewModel.selectedToTime.value ?: LocalTime.now()
    var datePickerDialog = TimePickerDialog(
      this,
      TimePickerDialog.OnTimeSetListener{ view, hour, minute ->
        viewModel.setSelectedToTime(LocalTime.of(hour, minute))
      }, displayFromTime.hour, displayFromTime.minute, true
    )
    datePickerDialog.show()
  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.topmenu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle item selection
    return when (item.itemId) {
      R.id.booking_requests -> {
        val intent = Intent(this, BookingsActivity::class.java)
        startActivity(intent)
        true
      }
      R.id.confirmed_trips -> {
        val intent = Intent(this, ConfirmedTripsActivity::class.java)
        startActivity(intent)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}
