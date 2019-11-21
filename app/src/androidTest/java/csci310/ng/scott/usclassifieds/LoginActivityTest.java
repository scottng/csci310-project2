package csci310.ng.scott.usclassifieds;

import android.app.Activity;

import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private Activity getActivityInstance(){
        final Activity[] currentActivity = {null};

        getInstrumentation().runOnMainSync(new Runnable(){
            public void run(){
                Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> it = resumedActivity.iterator();
                currentActivity[0] = it.next();
            }
        });

        return currentActivity[0];
    }

    @Rule
    public final ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void userCanEnterEmail() {
        onView(withId(R.id.edit_text_email)).perform(typeText("myemail@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_email)).check(matches(withText("myemail@usc.edu")));
    }

    @Test
    public void userCanEnterPassword() {
        onView(withId(R.id.edit_text_password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).check(matches(withText("password")));
    }


    @Test
    public void userCanLogin() {
        LoginActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_email)).perform(typeText("ta@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).perform(typeText("password"), closeSoftKeyboard());

        onView(withId(R.id.button_login)).perform(click());

        Activity activity1 = getActivityInstance();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Login Successful")).
                inRoot(withDecorView(IsNot.not(is(activity1.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void userCannotLoginAfterInvalidInfo() {
        LoginActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_email)).perform(typeText("asdf@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password)).perform(typeText("invalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Login failed.")).
                inRoot(withDecorView(IsNot.not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}
