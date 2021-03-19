package com.evermos.awesome.utils

import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat

import java.io.File

object FileHelperUtils {
    private val DIR_APK = "apk"

    val isExternalStorageAvailable: Boolean
        get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    fun getDirApkPath(context: Context): String {
        val apkDir = File(getRootDirPath(context), DIR_APK)
        if (!apkDir.exists()) apkDir.mkdir()
        return apkDir.absolutePath
    }

    fun getRootDirPath(context: Context): String {
        if (isExternalStorageAvailable) {
            val file = ContextCompat.getExternalFilesDirs(context.applicationContext, null)[0]
            return file.absolutePath
        } else {
            return context.applicationContext.filesDir.absolutePath
        }
    }

}
