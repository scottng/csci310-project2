package csci310.ng.scott.usclassifieds;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class CreateActivityTest {

    //espresso rule which tells which activity to start
    @Rule
    public final ActivityTestRule<CreateAccountActivity> activityRule =
            new ActivityTestRule<>(CreateAccountActivity.class);


    @Test
    public void userCanEnterFullName(){
        onView(withId(R.id.edit_text_full_name)).perform(typeText("Yolanda Buenaventura"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_full_name)).check(matches(withText("Yolanda Buenaventura")));
    }

    @Test
    public void userCanEnterPassword(){
        onView(withId(R.id.edit_text_password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).check(matches(withText("password")));
    }

    @Test
    public void userCanConfirmPassword(){
        onView(withId(R.id.edit_text_confirm_password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_confirm_password)).check(matches(withText("password")));
    }

    @Test
    public void userCanEnterBio(){
        onView(withId(R.id.edit_text_bio)).perform(typeText("description"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_bio)).check(matches(withText("description")));
    }

}