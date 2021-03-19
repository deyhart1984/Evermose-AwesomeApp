package com.evermos.awesome.data.network.model.appversion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Version {
    class Response {
        @SerializedName("rc")
        @Expose
        var rc: String? = null

        @SerializedName("message")
        @Expose
        var message: String? = null

        @SerializedName("data")
        @Expose
        var data: Data? = null
    }

    class Data {

        @SerializedName("version_code")
        @Expose
        var versionCode: Int = 0

        @SerializedName("version_name")
        @Expose
        var versionName: String? = null

        @SerializedName("type")
        @Expose
        var type: Int = 0

        @SerializedName("file_name")
        @Expose
        var fileName: String? = null

        @SerializedName("message")
        @Expose
        var message: String? = null
    }
}
