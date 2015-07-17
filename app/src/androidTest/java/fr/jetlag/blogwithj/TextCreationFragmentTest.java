package fr.jetlag.blogwithj;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import fr.jetlag.blogwithj.bean.Text;

/**
 * Created by vince on 16/07/15.
 */
public class TextCreationFragmentTest extends ActivityInstrumentationTestCase2<EditionActivity_> {

  private EditionActivity_ mActivity;

  public TextCreationFragmentTest() {
    super(EditionActivity_.class);
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();

    setActivityInitialTouchMode(true);
    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    Intent intent = new Intent();
    setActivityIntent(intent);
    mActivity = getActivity();
  }

  @Test
  public void testTitleIsShown() {
    TextView title = (TextView) mActivity.findViewById(R.id.action_title);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), title);
  }

  @Test
  public void testClickValidateParagraphIsFilledWithTitle() {
    Espresso.onView(ViewMatchers.withId(R.id.action_title)).perform(ViewActions.typeText("my new para"));
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    assertEquals("my new para", mActivity.getParagraph().getQuestion());
  }

  @Test
  public void testContentIsShown() throws UiObjectNotFoundException {
    EditText content = (EditText) mActivity.findViewById(R.id.content);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), content);
  }

  @Test
  public void testContentCanBeAdded() throws UiObjectNotFoundException {
    EditText content = (EditText) mActivity.findViewById(R.id.content);
    Espresso.onView(ViewMatchers.withId(R.id.content)).perform(ViewActions.typeText("hello world"));
    assertEquals("hello world", content.getText().toString());
  }

  @Test
  public void testClickValidateParagraphIsFilledWithContent() {
    Espresso.onView(ViewMatchers.withId(R.id.content)).perform(ViewActions.typeText("hello world"));
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    assertEquals("hello world", ((Text)mActivity.getParagraph().getBlockContent()).getText());
  }

  @Test
  public void testClickValidateAndEndActivity() throws UiObjectNotFoundException {

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ArticleDetailActivity.class.getName(), null, false);
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    ArticleDetailActivity activity = (ArticleDetailActivity) monitor.waitForActivityWithTimeout(10000);
    assertNotNull(activity);
    assertEquals(1, monitor.getHits());
    assertEquals(ArticleDetailActivity.class, activity.getClass());
  }

}
