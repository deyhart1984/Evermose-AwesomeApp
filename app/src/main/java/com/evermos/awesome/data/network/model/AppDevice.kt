package com.evermos.awesome.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.evermos.awesome.BuildConfig

class AppDevice {
    @SerializedName("app_vcode")
    @Expose
    var appVersionCode: Int? = BuildConfig.VERSION_CODE

    @SerializedName("app_vname")
    @Expose
    var appVersionName: String? = BuildConfig.VERSION_NAME

    @SerializedName("device_id")
    @Expose
    var deviceId: String? = null
}