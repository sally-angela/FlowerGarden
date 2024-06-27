package com.sally.flowergarden.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.ActivityLoginBinding
import com.sally.flowergarden.model.User
import com.sally.flowergarden.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity(), LoginClickListener, SignUpClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    companion object {
        val ID = "ID"
        val USERNAME = "USERNAME"
        val EMAIL = "EMAIL"
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

        binding.user = User("", "", "", "", "", "")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.loginlistener = this
        binding.signuplistener = this

//        binding.btnLogin.setOnClickListener {
//            var username = binding.txtUsername.editText?.text.toString()
//            var password = binding.txtPassword.editText?.text.toString()
//
//            if(username != "" && password != "") {
//                viewModel.login(username, password)
//            }
//            else {
//                Toast.makeText(this, "Username dan Password cannot be empty", Toast.LENGTH_SHORT).show()
//            }
//        }

//        binding.btnCreateAcc.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.userLD.observe(this, Observer {
            binding.user = it
            if(binding.user!!.email != "") {
                sharedEditor.putInt(ID, binding.user!!.id ?: -1)
                sharedEditor.putString(USERNAME, binding.user!!.username)
                sharedEditor.putString(EMAIL, binding.user!!.email)
                sharedEditor.putString(FIRSTNAME, binding.user!!.firstName)
                sharedEditor.putString(LASTNAME, binding.user!!.lastName)
                sharedEditor.putString(PASSWORD, binding.user!!.password)
                sharedEditor.putString(IMAGE, binding.user!!.images)
                sharedEditor.apply()

                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onLoginClick(v: View) {
        val user = binding.user!!
        if(user.username.toString() != "" && user.password.toString() != "") {
            viewModel.login(user.username.toString(), user.password.toString())
        }
        else {
            Toast.makeText(this, "Username dan Password cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSignUpClick(v: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}