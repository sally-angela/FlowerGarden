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
import com.sally.flowergarden.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FlowerViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {

    val flowersLD = MutableLiveData<List<Flower>>()
    val flowerLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        flowerLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())
            flowersLD.postValue(db.flowerDao().selectAllFlower())
            loadingLD.postValue(false)
        }
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://flowergarden123.000webhostapp.com/flower/flower.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<List<Flower>>() { }.type
//                val result = Gson().fromJson<List<Flower>>(it, sType)
//                flowersLD.value = result as ArrayList<Flower>?
//                loadingLD.value = false
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//                flowerLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}