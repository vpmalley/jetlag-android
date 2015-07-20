package fr.jetlag.blogwithj;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EFragment;

import fr.jetlag.blogwithj.article.Article;
import fr.jetlag.blogwithj.article.ArticleAdapter;
import fr.jetlag.blogwithj.article.DummyContent;
import fr.jetlag.blogwithj.article.Paragraph;

/**
 * A editionFragment representing a single Article detail screen.
 * This editionFragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
@EFragment
public class ArticleDetailFragment extends Fragment {
  /**
   * The editionFragment argument representing the item ID that this editionFragment
   * represents.
   */
  public static final String ARG_ITEM_ID = "item_id";

  /**
   * The request code used when creating a paragraph
   */
  public static int REQ_CREATION = 101;

  /**
   * The request code used when editing a paragraph
   */
  public static int REQ_EDITION = 102;

  /**
   * The dummy contentView this editionFragment is presenting.
   */
  private Article article;

  FloatingActionButton fab;

  RecyclerView paragraphList;

  LinearLayout actionsToolbar;

  ImageButton newTextButton;

  /**
   * Mandatory empty constructor for the editionFragment manager to instantiate the
   * editionFragment (e.g. upon screen orientation changes).
   */
  public ArticleDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments().containsKey(ARG_ITEM_ID)) {
      article = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

    paragraphList = (RecyclerView) rootView.findViewById(R.id.article_paragraphs);
    fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
    actionsToolbar = (LinearLayout) rootView.findViewById(R.id.actions_toolbar);
    newTextButton = (ImageButton) rootView.findViewById(R.id.action_new_text);

    populateList();
    setFabBehaviour();
    setNewTextAction();
    return rootView;
  }

  public void populateList() {
    paragraphList.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    paragraphList.setLayoutManager(layoutManager);
    paragraphList.setAdapter(new ArticleAdapter(article, this));
  }

  private void setFabBehaviour() {
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          int atCx = (actionsToolbar.getLeft() + actionsToolbar.getRight()) / 2;
          int atCy = (actionsToolbar.getTop() + actionsToolbar.getBottom()) / 2;
          int finalRadius = Math.max(actionsToolbar.getWidth(), actionsToolbar.getHeight());
          Animator atAnim =
              ViewAnimationUtils.createCircularReveal(actionsToolbar, atCx, atCy, 0, finalRadius);
          actionsToolbar.setVisibility(View.VISIBLE);
          atAnim.start();

          int fCx = (fab.getLeft() + fab.getRight()) / 2;
          int fCy = (fab.getTop() + fab.getBottom()) / 2;
          int initialRadius = fab.getWidth();
          Animator fAnim =
              ViewAnimationUtils.createCircularReveal(fab, fCx, fCy, initialRadius, 0);
          fAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              super.onAnimationEnd(animation);
              fab.setVisibility(View.INVISIBLE);
            }
          });
          fAnim.start();
        } else {
          actionsToolbar.setVisibility(View.VISIBLE);
          fab.setVisibility(View.INVISIBLE);
        }
      }
    });
  }

  /**
   * Binding the creation of a text paragraph with its button
   */
  private void setNewTextAction() {
    newTextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        createParagraph();

        // to revert previous UI changes
        actionsToolbar.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
      }
    });
  }

  public void createParagraph() {
    EditionActivity_.intent(ArticleDetailFragment.this).startForResult(REQ_CREATION);
  }

  public void editParagraph(int paragraphIndex) {
    EditionActivity_.intent(ArticleDetailFragment.this)
        .paragraph(article.getParagraphs().get(paragraphIndex))
        .paragraphIndex(paragraphIndex)
        .startForResult(REQ_EDITION);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d("onActivityResult", "fragment " + requestCode);
    super.onActivityResult(requestCode, resultCode, data);
    if ((REQ_CREATION == requestCode) && (data != null) && data.hasExtra(EditionActivity.ARG_PARAGRAPH)
        && (Activity.RESULT_OK == resultCode)) {
      Paragraph p = (Paragraph) data.getSerializableExtra(EditionActivity.ARG_PARAGRAPH);
      article.addParagraph(p);
      paragraphList.getAdapter().notifyDataSetChanged();
    } else if ((REQ_EDITION == requestCode) && (data != null) && data.hasExtra(EditionActivity.ARG_PARAGRAPH)
        && data.hasExtra(EditionActivity.ARG_INDEX) && (Activity.RESULT_OK == resultCode)) {
      Paragraph p = (Paragraph) data.getSerializableExtra(EditionActivity.ARG_PARAGRAPH);
      int paragraphIndex = data.getIntExtra(EditionActivity.ARG_INDEX, -1);
      if (-1 != paragraphIndex) {
        article.getParagraphs().set(paragraphIndex, p);
      } else {
        article.addParagraph(p);
      }
      paragraphList.getAdapter().notifyDataSetChanged();
    }
  }

  public Article getArticle() {
    return article;
  }
}
