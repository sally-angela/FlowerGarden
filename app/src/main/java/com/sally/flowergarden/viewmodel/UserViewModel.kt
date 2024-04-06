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
import com.sally.flowergarden.model.User

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/user?username=$username&password=$password"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(it, sType)
                userLD.value = result as User?
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