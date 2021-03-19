package com.evermos.awesome.data.network.api

import com.evermos.awesome.utils.ConstantsUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                        //.addHeader("Authorization", AUTH)
                        .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    val instance: Api by lazy{
        /*val gson = GsonBuilder()
            .setLenient()
            .create()*/

        val retrofit = Retrofit.Builder()
                .baseUrl(ConstantsUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        retrofit.create(Api::class.java)
    }
}
