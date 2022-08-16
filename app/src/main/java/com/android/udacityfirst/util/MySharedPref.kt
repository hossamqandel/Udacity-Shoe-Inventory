package com.android.udacityfirst.util

import android.content.SharedPreferences

class MySharedPref {

    companion object {
        var sharedPref: SharedPreferences? = null
        val SHARED_PREF_NAME = "user info"
        private val USER_EMAIL = "user email"
        private val USER_PASSWORD = "user password"

        var shEmail: String?
            get() {
                return sharedPref?.getString(USER_EMAIL, "")
            }
            set(value) {
                sharedPref?.edit()?.putString(USER_EMAIL, value)?.apply()
            }

        var shPassword: String?
            get() {
                return sharedPref?.getString(USER_PASSWORD, "")
            }
            set(value) {
                sharedPref?.edit()?.putString(USER_PASSWORD, value)?.apply()
            }

    }
}