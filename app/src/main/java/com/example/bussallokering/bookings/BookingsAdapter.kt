package com.example.bussallokering.bookings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bussallokering.R
import java.time.format.DateTimeFormatter

class BookingsAdapter(private val confirmedTrips: List<Booking>) :
  RecyclerView.Adapter<BookingsAdapter.ViewHolder>() {

  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

  // Create new views (invoked by the layout manager)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsAdapter.ViewHolder {
    // create a new view
    val textView = LayoutInflater.from(parent.context).inflate(R.layout.booking_item, parent, false)
    // set the view's size, margins, paddings and layout parameters
    return ViewHolder(textView)
  }

  // Replace the contents of a view (invoked by the layout manager)
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.view.findViewById<TextView>(R.id.text_view_origin).text = "Från: " + confirmedTrips[position].origin
    holder.view.findViewById<TextView>(R.id.text_view_destination).text = "Till: " + confirmedTrips[position].destination
    holder.view.findViewById<TextView>(R.id.text_view_time_span_from).text = "Först tänkbara avgångstid: " + confirmedTrips[position].timeSpanStart.format(DateTimeFormatter.ofPattern("dd MMM HH:mm"))
    holder.view.findViewById<TextView>(R.id.text_view_time_span_to).text = "Sista tänkbara avgångstid: " + confirmedTrips[position].timeSpanEnd.format(DateTimeFormatter.ofPattern("dd MMM HH:mm"))
  }

  override fun getItemCount() = confirmedTrips.size
}

