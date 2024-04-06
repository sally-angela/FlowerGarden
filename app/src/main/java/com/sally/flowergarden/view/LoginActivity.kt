package com.sally.flowergarden.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.ActivityLoginBinding
import com.sally.flowergarden.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    companion object {
        val ID = "ID"
        val USERNAME = "USERNAME"
        val FIRSTNAME = "FIRSTNAME"
        val LASTNAME = "LASTNAME"
        val PASSWORD = "PASSWORD"
        val IMAGE = "IMAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedFile = "com.sally.flowergarden"
        shared = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        sharedEditor = shared.edit()

        var checkLogin = shared.getInt("ID", -1)
        if(checkLogin != -1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.btnLogin.setOnClickListener {
            var username = binding.txtUsername.editText?.text.toString()
            var password = binding.txtPassword.editText?.text.toString()

            if(username != "" && password != "") {
                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.fetch(username, password)

                observeViewModel()
            }
            else {
                Toast.makeText(this, "Username dan Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCreateAcc.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun observeViewModel(){
        viewModel.userLD.observe(this, Observer {
            sharedEditor.putInt(ID, viewModel.userLD.value?.id ?: -1)
            sharedEditor.putString(USERNAME, viewModel.userLD.value?.username)
            sharedEditor.putString(FIRSTNAME, viewModel.userLD.value?.firstName)
            sharedEditor.putString(LASTNAME, viewModel.userLD.value?.lastName)
            sharedEditor.putString(PASSWORD, viewModel.userLD.value?.password)
            sharedEditor.putString(IMAGE, viewModel.userLD.value?.images)
            sharedEditor.apply()

            var checkLogin = shared.getInt("ID", -1)
            if(checkLogin != -1) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}