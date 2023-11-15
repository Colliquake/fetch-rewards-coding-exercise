package com.example.fetchapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fetchapplication.databinding.FragmentGetDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class GetDataFragment : Fragment(R.layout.fragment_get_data) {
    
    companion object {
        var data: String? = "empty"
        
        init {
            fetchData()
        }
        
        private fun fetchData() = GlobalScope.launch(Dispatchers.IO){
            val URL: String = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
            val itemFetch = OkHttpClient()
            val request = Request.Builder()
                .url(URL)
                .build()
            itemFetch.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                
                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (response.isSuccessful) {
                            val body = response.body?.string()
                            data = body ?: "empty"
                        }
                    }
                }
            })
        }
    }
    
    private var _binding: FragmentGetDataBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.getDataButton.setOnClickListener {
            val action = GetDataFragmentDirections.actionGetDataFragmentToDisplayDataFragment()
            view.findNavController().navigate(action)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}