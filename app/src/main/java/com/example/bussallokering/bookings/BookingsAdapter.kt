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
    holder.view.findViewById<TextView>(R.id.text_view_origin).text = confirmedTrips[position].origin
    holder.view.findViewById<TextView>(R.id.text_view_destination).text = confirmedTrips[position].destination
    holder.view.findViewById<TextView>(R.id.text_view_date).text = confirmedTrips[position].date.format(DateTimeFormatter.ofPattern("dd MMM"))
    holder.view.findViewById<TextView>(R.id.text_view_timespan).text =
      confirmedTrips[position].startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + "-" + confirmedTrips[position].endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
  }

  override fun getItemCount() = confirmedTrips.size
}

