package com.android.udacityfirst

import android.app.Application
import android.content.Context
import com.android.udacityfirst.util.MySharedPref
import com.android.udacityfirst.util.MySharedPref.Companion.SHARED_PREF_NAME

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPref.sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}