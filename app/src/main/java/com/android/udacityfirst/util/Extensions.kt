package com.android.udacityfirst.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

fun Context.showToast(text: String){
    Toast.makeText(this, "$text", Toast.LENGTH_LONG).show()
}


fun View?.removeSelf() {
    this ?: return
    val parentView = parent as? ViewGroup ?: return
    parentView.removeView(this)
}

fun Fragment.setNavigateAction(action: Int){
    findNavController().navigate(action)
}