package com.evermos.awesome.ui.catalog.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evermos.awesome.R
import com.evermos.awesome.data.network.model.catalog.CatalogModel
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogDetailActivityTest {

    var mData = CatalogModel.Data()

    @Test
    fun textView() {
        onView(withId(R.id.tv_title))
        onView(withId(R.id.tv_description))
    }

    @Test
    fun imageView(){
        onView(withId(R.id.image_view))
    }

}