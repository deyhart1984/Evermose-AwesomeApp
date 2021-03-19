package com.evermos.awesome.ui.catalog.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import com.evermos.awesome.ui.base.BaseActivity
import com.evermos.awesome.utils.ImageUtils
import kotlinx.android.synthetic.main.catalog_detail_activity.*


class CatalogDetailActivity : BaseActivity(), CatalogDetailView {

    val mPresenter: CatalogDetailPresenter<CatalogDetailView> = CatalogDetailPresenter()
    var mData = CatalogModel.Data()

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, CatalogDetailActivity::class.java)
        }

        lateinit var instance: CatalogDetailActivity

        fun getInstace(): CatalogDetailActivity {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_detail_activity)
        mPresenter.onAttach(this)

        setUp()
    }

    override fun setUp() {
        setToolbar(toolbar)
        showBackButton(true)

        instance = this
        mData = intent.extras.get("data") as CatalogModel.Data
        showDetail()
    }

    private fun showDetail(){
        Glide.with(this).load(mData.src!!.large2x)
            .apply(ImageUtils.RequestOption())
            .into(image_view)

        tv_title_toolbar.text = mData.photographer
        tv_title.text = mData.photographer
        tv_description.text = mData.photographer_url
    }

}