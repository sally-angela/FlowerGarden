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
    val statusLD = MutableLiveData<String>()
    val statusEditLD = MutableLiveData<String>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://flowergarden123.000webhostapp.com/user?username=$username&password=$password"

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

    fun signUp(username: String, email: String, firstName: String, lastName: String, password: String, images: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://flowergarden123.000webhostapp.com/newuser?username=$username&email=$email&firstName=$firstName" +
                  "&lastName=$lastName&password=$password&images=$images"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<String>() { }.type
                val result = Gson().fromJson<String>(it, sType)
                statusLD.value = result
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun editProfile(firstName: String, lastName: String, password: String, id: Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://flowergarden123.000webhostapp.com/editprofile?firstName=$firstName&lastName=$lastName" +
                  "&password=$password&id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<String>() { }.type
                val result = Gson().fromJson<String>(it, sType)
                statusEditLD.value = result
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