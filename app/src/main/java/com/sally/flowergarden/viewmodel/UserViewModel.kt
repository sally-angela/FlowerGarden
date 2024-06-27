package com.sally.flowergarden.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sally.flowergarden.model.User
import com.sally.flowergarden.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    val userLD = MutableLiveData<User>()
//    val statusLD = MutableLiveData<String>()
//    val statusEditLD = MutableLiveData<String>()

//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().login(username, password))
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://flowergarden123.000webhostapp.com/user?username=$username&password=$password"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<User>() { }.type
//                val result = Gson().fromJson<User>(it, sType)
//                userLD.value = result as User?
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun signUp(list: List<User>) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

//    fun signUp(username: String, email: String, firstName: String, lastName: String, password: String, images: String) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://flowergarden123.000webhostapp.com/newuser?username=$username&email=$email&firstName=$firstName" +
//                  "&lastName=$lastName&password=$password&images=$images"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<String>() { }.type
//                val result = Gson().fromJson<String>(it, sType)
//                statusLD.value = result
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

    fun fetch(userId: Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(userId))
        }
    }

    fun editProfile(firstName: String, lastName: String, password: String, id: Int) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().updateUser(firstName, lastName, password, id)
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://flowergarden123.000webhostapp.com/editprofile?firstName=$firstName&lastName=$lastName" +
//                  "&password=$password&id=$id"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<String>() { }.type
//                val result = Gson().fromJson<String>(it, sType)
//                statusEditLD.value = result
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}