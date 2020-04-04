package com.example.bussallokering

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bussallokering.databinding.ActivityMainBinding
import com.example.bussallokering.plan.PlanViewModel
import java.time.LocalDate
import java.time.LocalTime


class MainActivity : AppCompatActivity() {

  val viewModel: PlanViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
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


}
