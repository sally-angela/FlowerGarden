package com.sally.flowergarden.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.ActivitySignUpBinding
import com.sally.flowergarden.model.User
import com.sally.flowergarden.viewmodel.UserViewModel

class SignUpActivity : AppCompatActivity(), SignUpClickListener, LoginClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user = User("","", "", "", "", "")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.signuplistener = this
        binding.loginlistener = this

//        binding.btnSignUp.setOnClickListener {
//            var username = binding.txtNewUsername.editText?.text.toString()
//            var email = binding.txtEmail.editText?.text.toString()
//            var firstName = binding.txtNewFirstName.editText?.text.toString()
//            var lastName = binding.txtNewLastName.editText?.text.toString()
//            var password = binding.txtPass.editText?.text.toString()
//            var rePassword = binding.txtConfirmPass.editText?.text.toString()
//            var images = binding.txtImage.editText?.text.toString()
//
//            if(password != rePassword) {
//                Toast.makeText(this, "Password and Confirm Password not match", Toast.LENGTH_SHORT).show()
//            }
//            else if(username != "" && email != "" && firstName != "" && lastName != "" && password != "" && images != "") {
//                viewModel.signUp(username, email, firstName, lastName, password, images)
//            }
//            else {
//                Toast.makeText(this, "All data cannot be empty", Toast.LENGTH_SHORT).show()
//            }
//        }

//        binding.btnLoginAcc.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            this.finish()
//        }

//        observeViewModel()
    }

//    fun observeViewModel(){
//        viewModel.statusLD.observe(this, Observer {
//            var status = viewModel.statusLD.value.toString()
//            if(status == "OK") {
//                Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
//
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                this.finish()
//            }
//            else {
//                Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    override fun onLoginClick(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onSignUpClick(v: View) {
        var rePassword = binding.txtConfirmPass.text.toString()
        
        if(binding.user!!.password != rePassword) {
            Toast.makeText(this, "Password and Confirm Password not match", Toast.LENGTH_SHORT).show()
        }
        else if(binding.user!!.username != "" && binding.user!!.email != "" && binding.user!!.firstName != "" &&
            binding.user!!.lastName != "" && binding.user!!.password != "" && binding.user!!.images != "") {

            val userSignUp = listOf(binding.user!!)
            viewModel.signUp(userSignUp)

            Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        else {
            Toast.makeText(this, "All data cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}