package com.sally.flowergarden.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.flowergarden.databinding.FragmentProfileBinding
import com.sally.flowergarden.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var sharedFile = "com.sally.flowergarden"
        shared = this.requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        sharedEditor = shared.edit()

        var firstName = shared.getString(LoginActivity.FIRSTNAME, "")
        var lastName = shared.getString(LoginActivity.LASTNAME, "")
        var imageURL = shared.getString(LoginActivity.IMAGE, "")

        binding.txtFirstName.editText?.setText(firstName)
        binding.txtLastName.editText?.setText(lastName)
        val builder = Picasso.Builder(binding.root.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(imageURL).into(binding.imgProfile)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnEditProfile.setOnClickListener {
            var id = shared.getInt(LoginActivity.ID, -1)
            var password = shared.getString(LoginActivity.PASSWORD, "")

            var firstName = binding.txtFirstName.editText?.text.toString()
            var lastName = binding.txtLastName.editText?.text.toString()
            var oldPass = binding.txtOldPass.editText?.text.toString()
            var newPass = binding.txtNewPass.editText?.text.toString()
            var reNewPass = binding.txtReNewPass.editText?.text.toString()

            if(firstName != "" && lastName != "" && oldPass != "" && newPass != "" && reNewPass != "") {
                if(newPass != reNewPass) {
                    Toast.makeText(requireContext(), "New Password and Confirm New Password not match", Toast.LENGTH_SHORT).show()
                }
                else if(oldPass == password) {
                    viewModel.editProfile(firstName, lastName, newPass, id)
                }
                else {
                    Toast.makeText(requireContext(), "Wrong Old Password", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(requireContext(), "All data cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            sharedEditor.putInt(LoginActivity.ID, -1)
            sharedEditor.putString(LoginActivity.USERNAME, "")
            sharedEditor.putString(LoginActivity.EMAIL, "")
            sharedEditor.putString(LoginActivity.FIRSTNAME, "")
            sharedEditor.putString(LoginActivity.LASTNAME, "")
            sharedEditor.putString(LoginActivity.PASSWORD, "")
            sharedEditor.putString(LoginActivity.IMAGE, "")
            sharedEditor.apply()

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.statusEditLD.observe(viewLifecycleOwner, Observer {
            var status = viewModel.statusEditLD.value.toString()
            if(status == "OK") {
                Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()

                sharedEditor.putString(LoginActivity.FIRSTNAME, binding.txtFirstName.editText?.text.toString())
                sharedEditor.putString(LoginActivity.LASTNAME, binding.txtLastName.editText?.text.toString())
                sharedEditor.putString(LoginActivity.PASSWORD, binding.txtNewPass.editText?.text.toString())
                sharedEditor.apply()
            }
            else {
                Toast.makeText(requireContext(), "Failed to Update Profile", Toast.LENGTH_SHORT).show()
            }
        })
    }
}