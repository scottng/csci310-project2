package csci310.ng.scott.usclassifieds;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<LandingActivity> mActivityTestRule = new ActivityTestRule<>(LandingActivity.class);

    @Test
    public void profileSignOutTest() throws InterruptedException {
        onView(withId(R.id.button_directto_login)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.edit_text_email)).perform(typeText("tc1@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.navigation_profile)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.buttonSignOut)).perform(click());

    }

    @Test
    public void profileCheckItemsTest() throws InterruptedException {
        onView(withId(R.id.button_directto_login)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.edit_text_email)).perform(typeText("tc1@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.navigation_profile)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.search_user)).perform(click());
        Thread.sleep(1000);
    }



}
