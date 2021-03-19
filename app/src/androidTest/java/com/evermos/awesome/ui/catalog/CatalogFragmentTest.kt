package com.evermos.awesome.ui.catalog

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import com.evermos.awesome.ui.catalog.adapter.CatalogAdapter
import com.evermos.awesome.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogFragmentTest {
    val mPresenter: CatalogFragmentPresenter<CatalogFragmentView> = CatalogFragmentPresenter()
    var items: MutableList<CatalogModel.Data?> = ArrayList()
    var adapter : CatalogAdapter? = null

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        activityTestRule.activity
            .supportFragmentManager.beginTransaction()
    }

    @Test
    fun setUp() {
        items.add(null)
    }

    @Test
    fun listView() {
        onView(withId(R.id.recycler_view))
    }
}