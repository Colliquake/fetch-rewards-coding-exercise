package com.example.fetchapplication.test

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fetchapplication.GetDataFragment
import com.example.fetchapplication.MainActivity
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetDataPageTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun ButtonHasGetDataText(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val getDataScenario = launchFragmentInContainer<GetDataFragment>()
        
        getDataScenario.onFragment{fragment ->
            navController.setGraph(com.example.fetchapplication.R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        
        onView(ViewMatchers.withId(com.example.fetchapplication.R.id.get_data_button)).check(matches(withText(containsString("Get Data"))))
    }
    
    
    @Test
    fun ButtonClickNavigatesToDisplayDataPage(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        
        val getDataScenario = launchFragmentInContainer<GetDataFragment>()
        
        getDataScenario.onFragment{ fragment ->
            navController.setGraph(com.example.fetchapplication.R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        
        onView(ViewMatchers.withId(com.example.fetchapplication.R.id.get_data_button)).perform(
            ViewActions.click())
        assert(navController.currentDestination?.id == com.example.fetchapplication.R.id.displayDataFragment)
    }
}