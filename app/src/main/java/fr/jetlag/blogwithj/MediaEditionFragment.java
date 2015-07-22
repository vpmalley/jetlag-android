package fr.jetlag.blogwithj;

import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.media.Gallery;

/**
 * Created by vince on 16/07/15.
 */
@EFragment(R.layout.fragment_media_paragraph_edition)
public class MediaEditionFragment extends EditionFragment {

  @FragmentArg(EditionActivity.ARG_CONTENT)
  Gallery mediaContent;

  @ViewById
  ImageView mainpic;

  @ViewById
  EditText maincaption;

  @AfterViews
  public void displayGallery(){
    if (!mediaContent.getMedias().isEmpty()) {
      mediaContent.getMedias().get(0).loadInto(mainpic);
    } else {
      Picasso.with(getActivity()).load(R.drawable.ic_insert_photo_white_48dp).into(mainpic);
    }
  }

  @Override
  Content saveViewToContent() {

    return mediaContent;
  }
}
