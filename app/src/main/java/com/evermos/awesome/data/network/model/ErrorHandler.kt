package com.evermos.awesome.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorHandler {
    @SerializedName("error")
    @Expose
    var error: String? = null
}