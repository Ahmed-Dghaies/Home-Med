package com.example.home_med.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.home_med.R

class medicationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var medicationName: TextView
    var medicationType: TextView
    var medicationQty: TextView

    init {
        medicationName = view.findViewById(R.id.rv_medicationName)
        medicationType = view.findViewById(R.id.rv_medicationType)
        medicationQty = view.findViewById(R.id.rv_medicationQty)
    }
}