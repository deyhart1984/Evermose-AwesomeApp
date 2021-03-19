package com.evermos.awesome.ui.catalog

import android.util.Log
import com.evermos.awesome.data.network.api.RetrofitClient
import com.evermos.awesome.data.network.model.catalog.DataResponse
import com.evermos.awesome.ui.base.BasePresenter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogFragmentPresenter<V: CatalogFragmentView>: BasePresenter<V>(), CatalogFragmentPresenterView<V> {

    override fun getCatalog(page: Int) {
        //getMvpView().showLoading()

        val call = RetrofitClient.instance.getPexels("people", page )
        call.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                //getMvpView().hideLoading()

                Log.i("RESPONSE", response.toString())

                if (response.code() == 200) {
                    Log.i("RESPONSE_page", response.body()!!.page.toString())
                    response.body()?.let { getMvpView().success(it) }
                } else {
                    val errorBody = response.errorBody()!!.string()
                    val error = JSONObject(errorBody).getString("error")
                    getMvpView().failed(error)
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                //getMvpView().hideLoading()
                t.printStackTrace()
                t.message?.let { getMvpView().failed(it) }
            }
        })
    }
}