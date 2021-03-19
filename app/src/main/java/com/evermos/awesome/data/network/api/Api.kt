package com.evermos.awesome.data.network.api

import com.evermos.awesome.data.network.model.catalog.DataResponse
import com.evermos.awesome.utils.ConstantsUtils
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers("Authorization: " + ConstantsUtils.PEXELS_API_KEY)
    @GET("search")
    fun getPexels(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<DataResponse>
}