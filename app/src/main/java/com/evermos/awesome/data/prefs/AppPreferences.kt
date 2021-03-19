package com.evermos.awesome.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.evermos.awesome.utils.ConstantsUtils
import com.evermos.awesome.BaseApplication.Companion.mContext

class AppPreferences {
    companion object {
        var mPrefs: SharedPreferences = mContext.getSharedPreferences(ConstantsUtils.PREF_NAME, Context.MODE_PRIVATE)

        fun isLogin(): Boolean {
            return mPrefs.getBoolean(ConstantsUtils.PREF_LOGIN_STATUS, false)
        }

        fun setLogin(login: Boolean) {
            mPrefs.edit().putBoolean(ConstantsUtils.PREF_LOGIN_STATUS, login).apply()
        }
    }
}
