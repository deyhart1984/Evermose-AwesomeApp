package com.evermos.awesome.data.network.model.catalog

import java.io.Serializable

class CatalogModel {

    class Data (
        var id: Int = 0,
        var photographer: String? = null,
        var photographer_url: String? = null,
        var src: SRC? = null
    ): Serializable

    data class SRC (
        var original: String? = null,
        var large2x: String? = null,
        var large: String? = null,
        var medium: String? = null,
        var small: String? = null,
        var portrait: String? = null,
        var landscape: String? = null,
        var tiny: String? = null
    ): Serializable
}
