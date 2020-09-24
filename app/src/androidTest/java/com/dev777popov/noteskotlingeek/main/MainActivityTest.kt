package com.dev777popov.noteskotlingeek.main

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.main.MainActivity
import com.dev777popov.noteskotlingeek.ui.main.MainViewModel
import com.dev777popov.noteskotlingeek.ui.main.MainViewState
import com.dev777popov.noteskotlingeek.ui.main.NotesRVAdapter
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin


class MainActivityTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java, true, false)

    private val model: MainViewModel = mockk(relaxed = true)
    private val viewStateLiveData = MutableLiveData<MainViewState>()

    private val testNotes = listOf(
        Note("1", "title1", "text1"),
        Note("2", "title2", "text2"),
        Note("3", "title3", "text3")
    )

    @Before
    fun setup(){
        loadKoinModules(
            listOf(module {
                viewModel(override = true) { model }
            })
        )

        every { model.getViewState() } returns viewStateLiveData
        every { model.onCleared() } just runs
        activityRule.launchActivity(null)
        viewStateLiveData.postValue(MainViewState(notes = testNotes))
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun check_data_is_displayed() {
        onView(withId(R.id.rv_notes)).perform(scrollToPosition<NotesRVAdapter.MyViewHolder>(1))
        onView(withText(testNotes[0].text)).check(matches(isDisplayed()))
    }
}