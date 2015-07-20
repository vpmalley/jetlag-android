package fr.jetlag.blogwithj.display.block;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.article.ArticleAdapter;
import fr.jetlag.blogwithj.media.Gallery;

/**
 * Created by vince on 05/06/15.
 */
public class GalleryBlockDisplay implements BlockDisplay {

  private String title;

  private Gallery content;

  public GalleryBlockDisplay(String title, Gallery content) {
    this.title = title;
    this.content = content;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder) {
    if (holder instanceof ViewHolder) {
      ((ViewHolder) holder).titleView.setText(title);
      ((ViewHolder) holder).galleryView.setAdapter(new MediaAdapter(content));
    }
  }

  public static class ViewHolder extends ArticleAdapter.ViewHolder {

    final TextView titleView;

    final RecyclerView galleryView;

    public ViewHolder(View cardView, Context context) {
      super(cardView);
      titleView = (TextView) cardView.findViewById(R.id.title);
      galleryView = (RecyclerView) cardView.findViewById(R.id.media_gallery);
      galleryView.setHasFixedSize(true);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
      galleryView.setLayoutManager(layoutManager);
    }
  }
}
