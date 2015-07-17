package fr.jetlag.blogwithj.media;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.bean.Caption;
import fr.jetlag.blogwithj.bean.Link;
import fr.jetlag.blogwithj.bean.User;
import fr.jetlag.blogwithj.map.Place;

/**
 * Created by vince on 04/06/15.
 */
public class Media {

  private Link link;

  private Caption caption;

  private Place location;

  private User author;

  public Media(String url) {
    this.link = new Link(url);
  }

  public void loadInto(ImageView imageView) {
    Picasso picasso = Picasso.with(imageView.getContext());
    picasso.load(link.getUrl())
        .placeholder(R.drawable.abc_tab_indicator_material)
        .error(R.drawable.abc_tab_indicator_material)
        .resize(0, 280).into(imageView);
  }

  @Override
  public String toString() {
    return link.toString();
  }
}
