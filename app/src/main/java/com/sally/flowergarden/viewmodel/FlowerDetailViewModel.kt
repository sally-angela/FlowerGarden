package com.sally.flowergarden.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sally.flowergarden.model.Flower

class FlowerDetailViewModel(application: Application): AndroidViewModel(application) {
    val flowerLD = MutableLiveData<Flower>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(flowerId: Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://flowergarden123.000webhostapp.com/flower/flower.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Flower>>() { }.type
                val result = Gson().fromJson<List<Flower>>(it, sType)
                val selectedFlower = result.find { it.id == flowerId }
                selectedFlower?.let {
                    flowerLD.value = it as Flower?
                }
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}