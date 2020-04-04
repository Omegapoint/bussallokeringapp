package com.example.bussallokering.bookings

import java.time.LocalDate
import java.time.LocalTime

data class Booking(val origin: String, val destination: String, val date : LocalDate, val startTime : LocalTime, val endTime : LocalTime)