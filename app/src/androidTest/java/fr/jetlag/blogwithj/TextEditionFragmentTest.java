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

import fr.jetlag.blogwithj.article.Paragraph;
import fr.jetlag.blogwithj.bean.Text;

/**
 * Created by vince on 16/07/15.
 */
public class TextEditionFragmentTest extends ActivityInstrumentationTestCase2<EditionActivity_> {

  private EditionActivity_ mActivity;

  public TextEditionFragmentTest() {
    super(EditionActivity_.class);
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();

    setActivityInitialTouchMode(true);
    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    Intent intent = new Intent();
    intent.putExtra(EditionActivity.ARG_PARAGRAPH, new Paragraph("hello world", new Text("hello all the world")));
    setActivityIntent(intent);
    mActivity = getActivity();
  }

  @Test
  public void testExistingTitleIsShown() throws UiObjectNotFoundException {
    TextView title = (TextView) mActivity.findViewById(R.id.action_title);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), title);
    assertEquals("hello world", title.getText().toString());
  }

  @Test
  public void testClickValidateParagraphIsFilledWithTitle() {
    Espresso.onView(ViewMatchers.withId(R.id.action_title)).perform(ViewActions.clearText());
    Espresso.onView(ViewMatchers.withId(R.id.action_title)).perform(ViewActions.typeText("my new para"));
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    assertEquals("my new para", mActivity.getParagraph().getQuestion());
  }

  @Test
  public void testExistingContentIsShown() throws UiObjectNotFoundException {
    EditText content = (EditText) mActivity.findViewById(R.id.content);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), content);
    assertEquals("hello all the world", content.getText().toString());
  }

  @Test
  public void testContentCanBeAdded() throws UiObjectNotFoundException {
    EditText content = (EditText) mActivity.findViewById(R.id.content);
    Espresso.onView(ViewMatchers.withId(R.id.content)).perform(ViewActions.typeText(" from everywhere"));
    assertEquals("hello all the world from everywhere", content.getText().toString());
  }

  @Test
  public void testClickValidateParagraphIsFilledWithContent() {
    Espresso.onView(ViewMatchers.withId(R.id.content)).perform(ViewActions.typeText(" from everywhere"));
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    assertEquals("hello all the world from everywhere", ((Text)mActivity.getParagraph().getBlockContent()).getText());
  }

  @Test
  public void testClickValidateAndEndActivity() throws UiObjectNotFoundException {
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ArticleDetailActivity.class.getName(), null, false);
    Espresso.onView(ViewMatchers.withId(R.id.action_validate)).perform(ViewActions.click());
    assertTrue(mActivity.isFinishing());
  }

}
