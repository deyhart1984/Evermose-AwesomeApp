package com.evermos.awesome.ui.catalog

import com.evermos.awesome.data.network.model.catalog.DataResponse
import com.evermos.awesome.ui.base.MvpView

interface CatalogFragmentView: MvpView {
    fun success(data: DataResponse)
    fun failed(msg: String)
}