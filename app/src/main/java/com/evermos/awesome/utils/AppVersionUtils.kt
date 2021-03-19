package com.evermos.awesome.utils

import android.content.Context
import com.evermos.awesome.BuildConfig

object AppVersionUtils {
    fun getCode(context: Context): Int {
        return BuildConfig.VERSION_CODE
    }

    fun getName(context: Context): String {
        return BuildConfig.VERSION_NAME
    }
}
