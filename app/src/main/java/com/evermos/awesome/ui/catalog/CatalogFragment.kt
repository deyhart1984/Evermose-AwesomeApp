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
import com.evermos.awesome.ui.main.MainActivity
import kotlinx.android.synthetic.main.catalog_fragment.*
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList


class CatalogFragment : BaseFragment(), CatalogFragmentView, ILoadMore {

    val mPresenter: CatalogFragmentPresenter<CatalogFragmentView> = CatalogFragmentPresenter()

    var PAGE = 0

    companion object {
        lateinit var instance: CatalogFragment

        fun newInstance(): CatalogFragment {
            val args = Bundle()
            instance = CatalogFragment()
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
        showGridView()
        mPresenter.getCatalog(PAGE)
    }

    override fun onLoadMore() {
        //showMessage("LOAD MORE ACTION")
        items.add(null)
        //adapter!!.notifyItemInserted(items.size - 1)
        mPresenter.getCatalog(PAGE)

    }

    fun showGridView(){
        PAGE = 1
        recycler_view.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = CatalogAdapter(
            recycler_view,
            requireActivity(),
            items, true
        )
        recycler_view.adapter = adapter
        recycler_view.scrollToPosition(0)
        MainActivity.instance.VIEW_TYPE = false
    }

    fun showListView(){
        PAGE = 1
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        adapter = CatalogAdapter(
            recycler_view,
            requireActivity(),
            items, false
        )
        recycler_view.adapter = adapter
        recycler_view.scrollToPosition(0)
        MainActivity.instance.VIEW_TYPE = false
    }

    override fun success(data: DataResponse) {

        if(PAGE == 1){
            adapter!!.setLoadMore(this)

            adapter!!.setOnClickListener(object : CatalogAdapter.OnClickListener{
                override fun onDetail(data: CatalogModel.Data) {
                    (activity as MainActivity).openCatalogDetail(data)
                }
            })
        }

        Handler().postDelayed({
            items.removeAt(items.size - 1) // Remove null until
            adapter!!.notifyItemRemoved(items.size)

            if(PAGE == 1 && items.size > 0){
                items = ArrayList()
                items.addAll(items)
            }else{
                items.addAll(data.photos!!)
            }
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