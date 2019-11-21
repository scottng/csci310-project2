package csci310.ng.scott.usclassifieds;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SellActivityTest {

    //espresso rule which tells which activity to start
    @Rule
    public final ActivityTestRule<SellActivity> activityRule =
            new ActivityTestRule<>(SellActivity.class);


    @Test
    public void userCanEnterTitle(){
        onView(withId(R.id.edit_text_title)).perform(typeText("Test Title"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_title)).check(matches(withText("Test Title")));
    }

    @Test
    public void userCanEnterDescription(){
        onView(withId(R.id.edit_text_description)).perform(typeText("Test Description"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_description)).check(matches(withText("Test Description")));
    }

    @Test
    public void userCanEnterPrice(){
        onView(withId(R.id.edit_text_price)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_price)).check(matches(withText("100")));
    }

    @Test
    public void userCanChangeSellCategory(){
        onView(withId(R.id.radio_textbooks))
                .perform(click());

        onView(withId(R.id.radio_electronics))
                .perform(click());

        onView(withId(R.id.radio_transportation))
                .perform(click());

        onView(withId(R.id.radio_clothes))
                .perform(click());

        onView(withId(R.id.radio_miscellaneous))
                .perform(click());

        onView(withId(R.id.radio_miscellaneous))
                .check(matches(isChecked()));

        onView(withId(R.id.radio_clothes))
                .check(matches(not(isChecked())));

        onView(withId(R.id.radio_transportation))
                .check(matches(not(isChecked())));

        onView(withId(R.id.radio_electronics))
                .check(matches(not(isChecked())));

        onView(withId(R.id.radio_textbooks))
                .check(matches(not(isChecked())));
    }

    @Test
    public void noTitleToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.title_blank_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void TooLongTitleToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_title)).perform(typeText("1234567891123456789"), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.title_length_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void noPriceToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_title)).perform(typeText("123456789112345678"), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.price_blank_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void TooLongPriceToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_title)).perform(typeText("123456789112345678"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_price)).perform(typeText("1234567891"), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.item_too_expensive_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void noDescriptionToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_title)).perform(typeText("123456789112345678"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_price)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.description_empty_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void TooLongDescriptionToastCheck(){
        SellActivity activity = activityRule.getActivity();
        String hundredNOne = "";
        for (int i=0; i<101; i++){
            hundredNOne+="0";
        }
        onView(withId(R.id.edit_text_title)).perform(typeText("123456789112345678"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_price)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_description)).perform(typeText(hundredNOne), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.description_length_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void noPictureToastCheck(){
        SellActivity activity = activityRule.getActivity();
        onView(withId(R.id.edit_text_title)).perform(typeText("123456789112345678"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_price)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_description)).perform(typeText("Hello from Espresso!"), closeSoftKeyboard());
        onView(withId(R.id.button_post_item)).perform(click());
        onView(withText(R.string.picture_empty_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}