package com.example.student.teamproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserDataValidationTest {
    private static final String testPassword = "foobar";
    private static final Context appContext = InstrumentationRegistry.getTargetContext();
    private static final String incorrectDataStr =
            appContext.getString(R.string.incorrect_login_data);
    private static final String correctDataStr =
            appContext.getString(R.string.correct_login_data);

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void goToSignUpFragment() {
        FragmentActivity activity = (FragmentActivity) mActivityRule.getActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = new SignUpFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Test
    public void userValidation_IncorrectEmail_Toast() {

        String[] emailArray = {
                "user@example,com", "user_at_foo.org", "user.name@example.", "      ", "\t\t \t\t ",
                "foo@bar_baz.com", "foo@bar+baz.com", "foo@bar..com"
        };

        //        ToastMatcher toastMatcher = new ToastMatcher();

//        onView(withId(R.id.nav_signing_in))
//                .perform(click());

        for (String anEmailArray : emailArray) {
            insertTestEmail(anEmailArray);

            onView(withText(incorrectDataStr))
                    .inRoot(new ToastMatcher())
                    .check(matches(withText(incorrectDataStr)));
        }
    }

    @Test
    public void userValidation_CorrectEmail_Toast() {
        String[] emailArray = {
                "user@example.com", "USER@foo.com", "A_US-ER@foo.bar.org", "first.last@foo.jp",
                "alice+bob@baz.cn", "ulumulu.395+andrzej@poczta.onet.pl"
        };

        //        ToastMatcher toastMatcher = new ToastMatcher();

//        onView(withId(R.id.nav_signing_in))
//                .perform(click());

        for (String anEmailArray : emailArray) {
            insertTestEmail(anEmailArray);

            onView(withText(correctDataStr))
                    .inRoot(new ToastMatcher())
                    .check(matches(withText(correctDataStr)));
        }
    }

//    @Test
//    public void userValidation_IsEmailLowerCase_Toast() {
//        String mixedCaseEmail = "Foo@ExAMPle.CoM";
//
//        assertThat(mixedCaseEmail.equals(mixedCaseEmail.toLowerCase()), is(true));
//    }

    private void insertTestEmail(String email) {
        onView(withId(R.id.sign_up_email_input))
                .perform(clearText());
        onView(withId(R.id.sign_up_email_input))
                .perform(typeText(email));

        onView(withId(R.id.sign_up_password_input))
                .perform(clearText());
        onView(withId(R.id.sign_up_password_input))
                .perform(typeText(testPassword));

        onView(withId(R.id.sign_up_password_rep_input))
                .perform(clearText());
        onView(withId(R.id.sign_up_password_rep_input))
                .perform(typeText(testPassword));

        onView(withId(R.id.sign_up_email_text))
                .perform(click());

        onView(withId(R.id.sign_up_button))
                .perform(click());
    }
}
