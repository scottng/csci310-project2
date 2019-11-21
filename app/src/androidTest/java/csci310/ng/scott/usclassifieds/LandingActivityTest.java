package csci310.ng.scott.usclassifieds;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LandingActivityTest {

    //espresso rule which tells which activity to start
    @Rule
    public final ActivityTestRule<LandingActivity> activityRule =
            new ActivityTestRule<>(LandingActivity.class);


    @Test
    public void directToCreateAccount(){
        onView(withId(R.id.button_create_account)).perform(click());
    }

    @Test
    public void directToLogin(){
        onView(withId(R.id.button_directto_login)).perform(click());
    }

}