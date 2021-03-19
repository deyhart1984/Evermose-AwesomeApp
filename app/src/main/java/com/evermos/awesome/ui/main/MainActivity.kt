package com.evermos.awesome.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import com.evermos.awesome.ui.base.BaseActivity
import com.evermos.awesome.ui.catalog.CatalogFragment
import com.evermos.awesome.ui.catalog.CatalogFragmentList
import com.evermos.awesome.ui.catalog.detail.CatalogDetailActivity
import kotlinx.android.synthetic.main.main_activity.*


@Suppress("DEPRECATION", "DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")
class MainActivity : BaseActivity(), MainView {

    private var mPresenter : MainPresenter<MainView> = MainPresenter()

    var VIEW_TYPE = false

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        lateinit var instance: MainActivity
        fun getInstace(): MainActivity {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mPresenter.onAttach(this)

        setUp()
    }

    override fun setUp() {
        setToolbar(toolbar)
        //showBackButton(true)

        instance = this

        bottomBarMenu()
        openCatalogGrid()
    }

    private fun bottomBarMenu(){

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.page_1 -> {

                    true
                }
                R.id.page_2 -> {

                    true
                }
                R.id.page_3 -> {

                    true
                }
                R.id.page_4 -> {

                    true
                }
                R.id.page_5 -> {

                    true
                }
                else -> false
            }
        }
    }

    fun openCatalogGrid(){
        tv_title_toolbar.text = "Awesome App"
        val fragment = CatalogFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)

        if (!supportFragmentManager.isStateSaved) {
            transaction.commit()
        } else {
            transaction.commitAllowingStateLoss()
        }
    }

    fun openCatalogList(){
        tv_title_toolbar.text = "Awesome App"
        val fragment = CatalogFragmentList()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)

        if (!supportFragmentManager.isStateSaved) {
            transaction.commit()
        } else {
            transaction.commitAllowingStateLoss()
        }
    }

    fun openCatalogDetail(data: CatalogModel.Data){
        val bundle = Bundle()
        intent = CatalogDetailActivity.getStartIntent(this)
        bundle.putSerializable("data", data)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun setBottomMenuSelected(id: Int){
        val item: MenuItem = bottomNavigationView.menu.findItem(id)
        item.isChecked = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_grid -> {
                val fragmentManager = supportFragmentManager
                val currentFragment = fragmentManager.findFragmentById(R.id.frame_container)
                if ( (currentFragment is CatalogFragment) || (currentFragment is CatalogFragmentList)) {
                    VIEW_TYPE = true
//                    openCatalogGrid()
                    CatalogFragment.instance.showGridView()
                }
                return true
            }
            R.id.action_list -> {
                val fragmentManager = supportFragmentManager
                val currentFragment = fragmentManager.findFragmentById(R.id.frame_container)
                if ( (currentFragment is CatalogFragment) || (currentFragment is CatalogFragmentList) ) {
                    VIEW_TYPE = true
//                    openCatalogList()
                    CatalogFragment.instance.showListView()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
