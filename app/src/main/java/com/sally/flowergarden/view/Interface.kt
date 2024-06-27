package com.sally.flowergarden.view

import android.view.View

interface LoginClickListener {
    fun onLoginClick(v: View)
}

interface SignUpClickListener {
    fun onSignUpClick(v: View)
}

interface FlowerReadClickListener{
    fun onFlowerReadClick(v: View)
}

interface EditClickListener{
    fun onEditClick(v: View)
}

interface LogOutClickListener{
    fun onLogOutClick(v: View)
}