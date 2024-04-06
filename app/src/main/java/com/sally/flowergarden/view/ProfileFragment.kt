package com.sally.flowergarden.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sally.flowergarden.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener{
            var sharedFile= "com.sally.flowergarden"
            var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

            var editor: SharedPreferences.Editor = shared.edit()
            editor.putInt(LoginActivity.ID, -1)
            editor.putString(LoginActivity.USERNAME, "")
            editor.putString(LoginActivity.EMAIL, "")
            editor.putString(LoginActivity.FIRSTNAME, "")
            editor.putString(LoginActivity.LASTNAME, "")
            editor.putString(LoginActivity.PASSWORD, "")
            editor.putString(LoginActivity.IMAGE, "")
            editor.apply()

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }
    }
}