package com.example.bussallokering.confirmedtrips

import java.time.LocalDateTime

data class ConfirmedTrip(val origin: String, val destination: String, val localDateTime: LocalDateTime)