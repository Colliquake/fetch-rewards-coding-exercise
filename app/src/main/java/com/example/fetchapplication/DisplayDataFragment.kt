package com.example.fetchapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapplication.databinding.FragmentDisplayDataBinding
import org.json.JSONArray
import org.json.JSONObject

class DisplayDataFragment : Fragment() {
    
    private var _binding: FragmentDisplayDataBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var recyclerView: RecyclerView
    
    private var items = JSONArray(GetDataFragment.data)
    
    val itemsList = mutableListOf<Triple<Int, Int, String>>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        for(i in 0 until items.length()){
            val item: JSONObject = items.getJSONObject(i)
            val name = item.optString("name")
            if(name != "null" && name != ""){
                itemsList.add(Triple(item.getInt("id"), item.getInt("listId"), name))
            }
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisplayDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortedGroupedList = itemsList.groupBy{it.second}.toSortedMap()
        val ascendingSortedGroupedList = sortedGroupedList.mapValues{it.value.sortedBy{it.first}}
        
        recyclerView = binding.displayDataRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DisplayDataAdapter(ascendingSortedGroupedList)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}