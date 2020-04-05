package com.example.bussallokering.bookings

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Booking(val origin: String, val destination: String, val timeSpanStart : LocalDateTime, val timeSpanEnd : LocalDateTime)