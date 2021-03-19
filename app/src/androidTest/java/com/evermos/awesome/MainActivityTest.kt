package com.evermos.awesome

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.evermos.awesome.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun actionMenuToolBar(){
        onView(withId(R.id.action_grid)).perform(ViewActions.click()).check(matches(isDisplayed()))
        onView(withId(R.id.action_list)).perform(ViewActions.click()).check(matches(isDisplayed()))
    }

    @Test
    fun actionMenuBottomBar(){
        onView(withId(R.id.page_1)).perform(ViewActions.click())
        onView(withId(R.id.page_2)).perform(ViewActions.click())
        onView(withId(R.id.page_3)).perform(ViewActions.click())
        onView(withId(R.id.page_4)).perform(ViewActions.click())
        onView(withId(R.id.page_5)).perform(ViewActions.click())
    }
}