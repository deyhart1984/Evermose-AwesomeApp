package com.evermos.awesome

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.evermos.awesome.data.prefs.AppPreferences
import com.evermos.awesome.utils.ConstantsUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import java.lang.String.format
import java.util.concurrent.TimeUnit

class BaseApplication: MultiDexApplication() {

    companion object {
        lateinit var mContext: Context
        lateinit var mInstance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = this

        if (!BuildConfig.DEBUG) {
            //Fabric.with(this, new Crashlytics());
        }

        AppPreferences

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(original.method(),
                    original.body())
                builder.addHeader("Authorization"
                    , ConstantsUtils.PEXELS_API_KEY)
                chain.proceed(builder.build())
            }
            //build()

//        val okHttpClient = OkHttpClient.Builder()
//        okHttpClient.connectTimeout(600, TimeUnit.SECONDS)
//        okHttpClient.readTimeout(600, TimeUnit.SECONDS)
//        okHttpClient.addInterceptor { chain ->
//            val credential: String = Credentials.basic(ConstantsUtils.BASIC_AUTH_USERNAME, ConstantsUtils.BASIC_AUTH_PASSWORD)
//
//            val request = chain.request().newBuilder()
//                .addHeader("Authorization", ConstantsUtils.PEXELS_API_KEY)
//                .build()
//            chain.proceed(request)
//        }
        AndroidNetworking.initialize(applicationContext, okHttpClient.build())
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }
    }

    fun getInstance(): BaseApplication {
        return mInstance
    }

    fun getContext(): Context {
        return mContext
    }
}