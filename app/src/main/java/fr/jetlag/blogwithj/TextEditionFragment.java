package fr.jetlag.blogwithj;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.bean.Text;

/**
 * Created by vince on 16/07/15.
 */
@EFragment(R.layout.fragment_text_paragraph_edition)
public class TextEditionFragment extends EditionFragment {

  @FragmentArg(EditionActivity.ARG_CONTENT)
  Text textContent;

  @ViewById
  EditText content;

  @AfterViews
  public void displayParagraph(){
    content.setText(textContent.getText());
  }

  @Override
  Content saveViewToContent() {
    textContent.setText(content.getText().toString());
    return textContent;
  }
}
