package com.evermos.awesome.ui.catalog

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import com.evermos.awesome.data.network.model.catalog.DataResponse
import com.evermos.awesome.ui.base.BaseFragment
import com.evermos.awesome.ui.catalog.adapter.CatalogAdapter
import com.evermos.awesome.ui.catalog.adapter.ILoadMore
import kotlinx.android.synthetic.main.catalog_fragment.*
import java.util.*
import kotlin.collections.ArrayList


class CatalogFragmentList : BaseFragment(), CatalogFragmentView, ILoadMore {

    val mPresenter: CatalogFragmentPresenter<CatalogFragmentView> = CatalogFragmentPresenter()

    var PAGE = 1

    companion object {
        lateinit var instance: CatalogFragmentList

        fun newInstance(): CatalogFragmentList {
            val args = Bundle()
            instance = CatalogFragmentList()
            instance.arguments = args
            return instance
        }
    }

    var items: MutableList<CatalogModel.Data?> = ArrayList()
    var adapter : CatalogAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        mPresenter.onAttach(this)
        instance = this
        return inflater.inflate(R.layout.catalog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUp(view)
    }

    override fun setUp(view: View) {

        items.add(null)

        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        adapter = CatalogAdapter(
            recycler_view,
            requireActivity(),
            items, false
        )

        //adapter!!.notifyItemInserted(items.size - 1)

        recycler_view.adapter = adapter

        mPresenter.getCatalog(PAGE)
    }

    override fun onLoadMore() {
        items.add(null)
        //adapter!!.notifyItemInserted(items.size - 1)
        mPresenter.getCatalog(PAGE)
    }

    override fun success(data: DataResponse) {
        if(PAGE == 1){
            adapter!!.setLoadMore(this)

            adapter!!.setOnClickListener(object : CatalogAdapter.OnClickListener{
                override fun onDetail(data: CatalogModel.Data) {
                    showMessage(data.photographer!!)
                }
            })
        }

        Handler().postDelayed({
            items.removeAt(items.size - 1) // Remove null until
            adapter!!.notifyItemRemoved(items.size)

            items.addAll(data.photos!!)
            adapter!!.notifyDataSetChanged()
            adapter!!.setLoaded()
        }, 1000)

        PAGE += 1
    }

    override fun failed(msg: String) {
        //Tampilkan page error (berupa animasi)
        showMessage(msg)

        items.removeAt(items.size - 1) // Remove null until
        adapter!!.notifyItemRemoved(items.size)
    }


}
