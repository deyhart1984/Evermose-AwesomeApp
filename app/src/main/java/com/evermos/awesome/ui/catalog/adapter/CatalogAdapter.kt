package com.evermos.awesome.ui.catalog.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import com.evermos.awesome.ui.main.MainActivity
import com.evermos.awesome.utils.ImageUtils
import kotlinx.android.synthetic.main.loadmore_progress.view.*

internal class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var progressBar = itemView.progressBar
}

internal class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
    var imageView = itemView.findViewById<ImageView>(R.id.image_view)
    var mLayout = itemView.findViewById<LinearLayout>(R.id.item_layout)
}

class CatalogAdapter(recyclerView: RecyclerView, private var activity: Activity, var items: MutableList<CatalogModel.Data?>, var isGrid: Boolean):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_ITEMTYPE = 0
    val VIEW_LOADINGTYPE = 1

    internal var loadMore: ILoadMore? = null
    internal var isLoading: Boolean = false
    internal var visibleThreshold = 1
    internal var lastVisibleItem = 0
    internal var totalItemCount = 0

    internal var mOnClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
    }

    interface OnClickListener {
        fun onDetail(data: CatalogModel.Data)
    }

    init{
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        if(isGrid){
            val layoutManager = recyclerView.layoutManager as GridLayoutManager

            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                //Each item occupies 1 span by default.
                override fun getSpanSize(p0: Int): Int
                {
                    return when (getItemViewType(p0))
                    {
                        //returns total no of spans, to occupy full sapce for progressbar
                        VIEW_LOADINGTYPE -> layoutManager.spanCount
                        VIEW_ITEMTYPE -> 1
                        else -> -1
                    }
                }
            }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            //Press Ctrl + O
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if(!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    if(loadMore != null)
                        loadMore!!.onLoadMore()
                    isLoading = true
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            VIEW_ITEMTYPE -> {
                var view = LayoutInflater.from(activity).inflate(R.layout.catalog_list_item, parent, false)
                if(isGrid){
                    view = LayoutInflater.from(activity).inflate(R.layout.catalog_grid_item, parent, false)
                }
                ItemViewHolder(view)
            }
            VIEW_LOADINGTYPE -> {
                val view = LayoutInflater.from(activity).inflate(R.layout.loadmore_progress, parent, false)
                LoadingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Different View type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder) {
            val item = items[position]
            holder.tvDescription.text = item!!.photographer + "\n" + item!!.photographer_url

            Glide.with(activity).load(item.src!!.medium)
                .apply(ImageUtils.RequestOption())
                .into(holder.imageView)

            holder.mLayout.setOnClickListener {
                if (mOnClickListener == null) return@setOnClickListener
                mOnClickListener!!.onDetail(item)
            }
        }
        else if(holder is LoadingViewHolder){
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(items[position] == null) {
            VIEW_LOADINGTYPE
        } else {
            VIEW_ITEMTYPE
        }
    }

    fun setLoaded(){
        isLoading = false
    }

    fun setLoadMore(iLoadMore: ILoadMore){
        this.loadMore = iLoadMore
    }
}