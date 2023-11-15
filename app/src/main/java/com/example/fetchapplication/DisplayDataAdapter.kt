package com.example.fetchapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class DisplayDataAdapter(val ascendingSortedGroupedList: Map<Int, List<Triple<Int, Int, String>>>) : RecyclerView.Adapter<DisplayDataAdapter.DisplayDataViewHolder>() {
    
    val combinedList = ascendingSortedGroupedList.flatMap{it.value}
    
    class DisplayDataViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val display_data_button = view.findViewById<Button>(R.id.data_item)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayDataViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return DisplayDataViewHolder(layout)
    }
    
    override fun getItemCount(): Int {
        return combinedList.size
    }
    
    override fun onBindViewHolder(holder: DisplayDataViewHolder, position: Int) {
        val item = "Name: " + combinedList[position].third + "      " + "Id: " + combinedList[position].first + "      " + "List Id: " + combinedList[position].second
        holder.display_data_button.text = item
    }
}