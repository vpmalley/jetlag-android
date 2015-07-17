package fr.jetlag.blogwithj;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ArticleDetailActivityTest
    extends ActivityInstrumentationTestCase2<ArticleDetailActivity> {

  private ArticleDetailActivity mActivity;

  public ArticleDetailActivityTest() {
    super(ArticleDetailActivity.class);
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();

    // Injecting the Instrumentation instance is required
    // for your test to run with AndroidJUnitRunner.
    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    Intent intent = new Intent();
    intent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, "hello");
    setActivityIntent(intent);
    mActivity = getActivity();
  }

  @Test
  public void testParagraphsAreShown() throws UiObjectNotFoundException {
    RecyclerView articleView = (RecyclerView) mActivity.findViewById(R.id.article_paragraphs);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), articleView);
  }

  @Test
  public void testTextParagraphIsShown() throws UiObjectNotFoundException {
    RecyclerView articleView = (RecyclerView) mActivity.findViewById(R.id.article_paragraphs);

    TextView titleView = (TextView) articleView.getChildAt(0).findViewById(R.id.title);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), titleView);
    Assert.assertEquals("how are you?", titleView.getText());

    TextView contentView = (TextView) articleView.getChildAt(0).findViewById(R.id.text_content);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), contentView);
    Assert.assertEquals("I am good", contentView.getText());
  }

  @Test
  public void testPicParagraphIsShown() throws UiObjectNotFoundException {
    RecyclerView articleView = (RecyclerView) mActivity.findViewById(R.id.article_paragraphs);

    TextView titleView = (TextView) articleView.getChildAt(2).findViewById(R.id.title);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), titleView);
    Assert.assertEquals("Show what you see", titleView.getText());

    RecyclerView galleryView = (RecyclerView) articleView.getChildAt(2).findViewById(R.id.media_gallery);
    ImageView pic = (ImageView) galleryView.getChildAt(1);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), pic);
  }

  @Test
  public void testFABIsShown() throws UiObjectNotFoundException {
    FloatingActionButton fab = (FloatingActionButton) mActivity.findViewById(R.id.fab);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), fab);
  }

  @Test
  public void testClickFABOpensActionToolbar() throws UiObjectNotFoundException {
    LinearLayout toolbar = (LinearLayout) mActivity.findViewById(R.id.actions_toolbar);;
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), toolbar);
    assertEquals(View.INVISIBLE, toolbar.getVisibility());
    Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());
    assertEquals(View.VISIBLE, toolbar.getVisibility());

    RecyclerView articleView = (RecyclerView) mActivity.findViewById(R.id.article_paragraphs);
    ViewAsserts.assertBottomAligned(articleView, toolbar);
  }

  @Test
  public void testClickNewText() throws UiObjectNotFoundException {
    ImageButton newTextButton = (ImageButton) mActivity.findViewById(R.id.action_new_text);
    ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), newTextButton);
    Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());
    assertEquals(View.VISIBLE, newTextButton.getVisibility());

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(EditionActivity_.class.getName(), null, false);
    Espresso.onView(ViewMatchers.withId(R.id.action_new_text)).perform(ViewActions.click());
    EditionActivity_ activity = (EditionActivity_) monitor.waitForActivityWithTimeout(10000);
    assertNotNull(activity);
    assertEquals(1, monitor.getHits());
    assertEquals(EditionActivity_.class, activity.getClass());
  }

  @After
  public void tearDown() throws Exception {
    super.tearDown();
  }
}
