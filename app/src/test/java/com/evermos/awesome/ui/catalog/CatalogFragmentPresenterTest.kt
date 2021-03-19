package com.evermos.awesome.ui.catalog

import com.evermos.awesome.data.network.api.RetrofitClient
import com.evermos.awesome.data.network.model.catalog.DataResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.*
import retrofit2.Call
import retrofit2.Response

internal class CatalogFragmentPresenterTest {

    @Mock
    lateinit var catalogView: CatalogFragmentView

    @Mock
    lateinit var catalogPresenterView: CatalogFragmentPresenterView<CatalogFragmentView>

    lateinit var catalogPresenter: CatalogFragmentPresenter<CatalogFragmentView>

    @BeforeEach
    fun testSetUp(){
        initMocks(this)
        catalogPresenter = CatalogFragmentPresenter()
    }

    @Test
    fun testGetCatalog(){
        val page = catalogPresenter.getCatalog(1)
        val call = RetrofitClient.instance.getPexels("people", 1)
        call.enqueue(object : retrofit2.Callback<DataResponse>{
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                t.message?.let { verify(catalogView).failed(it) }
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                //assertEquals(200, response.code(), "Response Code 200")
                Mockito.`when`(assertEquals(200, response.code())).thenReturn(verify(catalogView).success(DataResponse()))
            }
        })
    }
}