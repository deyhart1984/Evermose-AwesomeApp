package com.evermos.awesome.ui.catalog

import com.evermos.awesome.ui.base.MvpPresenter

interface CatalogFragmentPresenterView<V: CatalogFragmentView> : MvpPresenter<V>{
    fun getCatalog(page: Int)
}