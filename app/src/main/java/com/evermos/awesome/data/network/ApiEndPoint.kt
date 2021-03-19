package com.evermos.awesome.data.network

import com.evermos.awesome.utils.ConstantsUtils

object ApiEndPoint {
    //Version
    const val ENDPOINT_APP_VERSION = ConstantsUtils.BASE_URL + "appversion"
    const val ENDPOINT_APP_DOWNLOAD = ConstantsUtils.BASE_URL + "/asset/apk"

    //Catalog
    const val ENDPOINT_CATALOG = ConstantsUtils.BASE_URL
    //Project
    const val ENDPOINT_PROJECT = ConstantsUtils.BASE_URL + "projectdata"
}
