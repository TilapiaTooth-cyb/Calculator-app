package com.example.calculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class CalculatorActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val displayId = R.id.txt_display
    private val historyId = R.id.history_container

    @Test
    fun basicAddition_calculatesAndDisplaysCorrectly() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())
        onView(withId(R.id.btn_equal)).perform(click())

        onView(withId(displayId)).check(matches(withText("15.0")))
    }

    @Test
    fun chainedOperation_updatesDisplayAndHistory() {
        onView(withId(R.id.btn_5)).perform(click())
        onView(withId(R.id.btn_mul)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_equal)).perform(click())

        onView(withId(R.id.btn_sub)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())
        onView(withId(R.id.btn_equal)).perform(click())

        onView(withId(displayId)).check(matches(withText("7.0")))

        onView(allOf(withText(containsString("10.0 - 3.0 = 7.0")), isDescendantOfA(withId(historyId)))).check(matches(isDisplayed()))
    }

    @Test
    fun squareRoot_calculatesAndUpdatesInput() {
        onView(withId(R.id.btn_9)).perform(click())
        onView(withId(R.id.btn_sqrt)).perform(click())

        onView(withId(displayId)).check(matches(withText("3.0")))

        onView(allOf(withText(containsString("√9.0 = 3.0")), isDescendantOfA(withId(historyId)))).check(matches(isDisplayed()))
    }

    @Test
    fun toggleSign_changesNumberSign() {
        onView(withId(R.id.btn_5)).perform(click())

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(displayId)).check(matches(withText("-5")))

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(displayId)).check(matches(withText("5")))
    }

    @Test
    fun backspace_removesLastDigit() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())

        onView(withId(displayId)).check(matches(withText("123")))

        onView(withId(R.id.btn_back)).perform(click())

        onView(withId(displayId)).check(matches(withText("12")))
    }

    @Test
    fun clearButton_resetsAllState() {
        onView(withId(R.id.btn_5)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_5)).perform(click())
        onView(withId(R.id.btn_equal)).perform(click())

        onView(withId(R.id.btn_clear)).perform(click())

        onView(withId(displayId)).check(matches(withText("")))

        onView(withId(historyId)).check(matches(hasChildCount(0)))
    }

    @Test
    fun divisionByZero_displaysInfinity() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_div)).perform(click())
        onView(withId(R.id.btn_0)).perform(click())
        onView(withId(R.id.btn_equal)).perform(click())

        onView(withId(displayId)).check(matches(withText(containsString("Infinity"))))
    }
}
