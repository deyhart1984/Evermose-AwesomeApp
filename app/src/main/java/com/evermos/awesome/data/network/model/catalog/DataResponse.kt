package com.evermos.awesome.data.network.model.catalog

data class DataResponse(
    val page: Int? = null,
    val per_page: Int? = null,
    val photos: ArrayList<CatalogModel.Data?>? = null,
    val total_results: Int? = null
)