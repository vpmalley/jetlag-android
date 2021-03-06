package fr.jetlag.blogwithj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.article.Paragraph;
import fr.jetlag.blogwithj.bean.Text;
import fr.jetlag.blogwithj.media.Gallery;

/**
 * Created by vince on 16/07/15.
 */
@EActivity(R.layout.activity_paragraph_edition)
public class EditionActivity extends AppCompatActivity {

  /**
   * The Paragraph to be edited, if there is one
   */
  public static final String ARG_PARAGRAPH = "paragraph";

  /**
   * The content to be edited in the editionFragment
   */
  public static final String ARG_CONTENT = "content";

  /**
   * The index of the edited paragraph in the article
   */
  public static final String ARG_INDEX = "index";

  @Extra(ARG_PARAGRAPH)
  Paragraph paragraph;

  @Extra(ARG_INDEX)
  int paragraphIndex;

  //EditText title; // TODO figure the action bar lifecycle to know when to find it

  EditionFragment editionFragment;

  Paragraph getParagraph() {
    return paragraph;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getIntent().hasExtra(ARG_PARAGRAPH)) {
      paragraph = (Paragraph) getIntent().getSerializableExtra(ARG_PARAGRAPH);
    } else {
      paragraph = new Paragraph("", new Text(""));
    }
    if (getIntent().hasExtra(ARG_INDEX)) {
      paragraphIndex = getIntent().getIntExtra(ARG_INDEX, -1);
    }

    if (savedInstanceState == null) {
      if (paragraph.getBlockContent() instanceof Text) {
        editionFragment = new TextEditionFragment_();
      } else if (paragraph.getBlockContent() instanceof Gallery) {
        editionFragment = new MediaEditionFragment_();
      } else {
        editionFragment = new TextEditionFragment_();
      }

      Bundle arguments = new Bundle();
      arguments.putSerializable(ARG_CONTENT, paragraph.getBlockContent());
      editionFragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.paragraph_container, editionFragment)
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.edition, menu);
    EditText titleField = (EditText) menu.findItem(R.id.action_title).getActionView();
    titleField.setHint(getResources().getString(R.string.paragraph_title_hint));
    if (!paragraph.getQuestion().isEmpty()) {
      titleField.setText(paragraph.getQuestion());
    }
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.action_validate:
        saveAndFinish();
        return true;
      default: return false;
    }
  }

  void saveAndFinish() {
    saveViewToParagraph();
    Content c = editionFragment.saveViewToContent();
    paragraph.setContent(c);
    Intent intent = new Intent();
    intent.putExtra(ARG_PARAGRAPH, paragraph);
    intent.putExtra(ARG_INDEX, paragraphIndex);
    setResult(RESULT_OK, intent);
    finish();
  }

  private void saveViewToParagraph() {
    EditText title = (EditText) findViewById(R.id.action_title);
    String newTitle = title.getText().toString();
    if (!newTitle.isEmpty()) {
      paragraph.setQuestion(newTitle);
    }
  }
}
