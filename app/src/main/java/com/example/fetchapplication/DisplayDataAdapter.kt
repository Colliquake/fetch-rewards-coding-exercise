package com.example.fetchapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class DisplayDataAdapter(val ascendingSortedGroupedList: Map<Int, List<Triple<Int, Int, String>>>) : RecyclerView.Adapter<DisplayDataAdapter.DisplayDataViewHolder>() {
    
    //true = items expanded (showing all items), false = items collapsed
    var details1 = false
    var details2 = false
    var details3 = false
    var details4 = false
    
    //items associated with each category/group
    val list1 = ascendingSortedGroupedList.filterKeys { it in 1 until 2 }.flatMap { it.value }
    val list2 = ascendingSortedGroupedList.filterKeys { it in 2 until 3 }.flatMap { it.value }
    val list3 = ascendingSortedGroupedList.filterKeys { it in 3 until 4 }.flatMap { it.value }
    val list4 = ascendingSortedGroupedList.filterKeys { it in 4 until 5 }.flatMap { it.value }
    
    //    var combinedList = ascendingSortedGroupedList.flatMap{it.value}
    var combinedList = mutableListOf(
        Triple(1, 1, "one"),
        Triple(2, 2, "two"),
        Triple(3, 3, "three"),
        Triple(4, 4, "four")
    )
    
    class DisplayDataViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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
        if (combinedList[position].third == "one") {
            holder.display_data_button.text = "List Id 1"
            holder.display_data_button.isEnabled = true
            holder.display_data_button.setOnClickListener {
                when (details1) {
                    true -> {
                        val startIndex = 1
                        val endIndex = list1.size + 1
                        val count = endIndex - startIndex
                        combinedList.subList(startIndex, endIndex).clear();
                        notifyItemRangeRemoved(startIndex, count)
                        details1 = !details1
                    }
                    
                    else -> {
                        combinedList.addAll(1, list1)
                        notifyItemRangeInserted(1, list1.size)
                        details1 = !details1
                    }
                }
            }
            holder.display_data_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")))
            holder.display_data_button.setTextColor(Color.parseColor("#FFFFFF"))
        } else if (combinedList[position].third == "two") {
            holder.display_data_button.text = "List Id 2"
            holder.display_data_button.isEnabled = true
            holder.display_data_button.setOnClickListener {
                when (details2) {
                    true -> {
                        val startIndex = if(details1) list1.size + 2 else 2
                        val endIndex = if(details1) list1.size + list2.size + 2 else list2.size + 2
                        val count = endIndex - startIndex
                        combinedList.subList(startIndex, endIndex).clear();
                        notifyItemRangeRemoved(startIndex, count)
                        details2 = !details2
                    }
                    
                    else -> {
                        if(!details1) combinedList.addAll(2, list2) else combinedList.addAll(list1.size + 2, list2)
                        if(!details1) notifyItemRangeInserted(2, list2.size) else notifyItemRangeInserted(list1.size + 2, list2.size)
                        details2 = !details2
                    }
                }
            }
            holder.display_data_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")))
            holder.display_data_button.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else if(combinedList[position].third == "three"){
            holder.display_data_button.text = "List Id 3"
            holder.display_data_button.isEnabled = true
            holder.display_data_button.setOnClickListener {
                when(details3){
                    true -> {
                        val startIndex = if(details4) combinedList.size - list4.size - list3.size - 1 else combinedList.size - list3.size - 1
                        val endIndex = if(details4) combinedList.size - list4.size - 1 else combinedList.size - 1
                        val count = endIndex - startIndex
                        combinedList.subList(startIndex, endIndex).clear()
                        notifyItemRangeRemoved(startIndex, count)
                        details3 = !details3
                    }
                    else -> {
//                        if(!details4) combinedList.addAll(combinedList.size - 1, list3) else combinedList.addAll(combinedList.size - list4.size - 1, list3)
//                        if(!details4) notifyItemRangeInserted(combinedList.size - 1, list3.size) else notifyItemRangeInserted(combinedList.size - list4.size, list3.size)
                        var index = 3
                        if(details1) index += list1.size
                        if(details2) index += list2.size
                        combinedList.addAll(index, list3)
                        notifyItemRangeInserted(index, list3.size)
                        details3 = !details3
                    }
                }
            }
            holder.display_data_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")))
            holder.display_data_button.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else if(combinedList[position].third == "four"){
            holder.display_data_button.text = "List Id 4"
            holder.display_data_button.isEnabled = true
            holder.display_data_button.setOnClickListener {
                when(details4){
                    true -> {
                        val startIndex = combinedList.size - list4.size
                        val endIndex = combinedList.size
                        val count = endIndex - startIndex
                        combinedList.subList(startIndex, endIndex).clear()
                        notifyItemRangeRemoved(startIndex, count)
                        details4 = !details4
                    }
                    else -> {
                        var index = 4
                        if(details1) index += list1.size
                        if(details2) index += list2.size
                        if(details3) index += list3.size
                        combinedList.addAll(index, list4)
                        notifyItemRangeInserted(index, list4.size)
                        details4 = !details4
                    }
                }
            }
            holder.display_data_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")))
            holder.display_data_button.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else {
            val item =
                "List Id: " + combinedList[position].second + "      " + "Name: " + combinedList[position].third + "      " + "Id: " + combinedList[position].first
            holder.display_data_button.text = item
            holder.display_data_button.isEnabled = false
            holder.display_data_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBF00")))
            holder.display_data_button.setTextColor(Color.parseColor("#000000"))
        }
    }
}