package com.example.bussallokering.bookingconfirmation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bussallokering.R

class BookingConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Bekräftelse"

        setContentView(R.layout.activity_booking_confirmation)
    }

}