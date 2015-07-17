package fr.jetlag.blogwithj.display.block;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.media.Gallery;

/**
 * Created by vince on 05/06/15.
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

  private final Gallery content;

  public MediaAdapter(Gallery content) {
    this.content = content;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ImageView v = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_picture, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    content.getMedias().get(position).loadInto(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return content.getMedias().size();
  }

  /**
   * Holds a View for each picture in the Gallery
   */
  public static class ViewHolder extends RecyclerView.ViewHolder {

    final ImageView imageView;

    public ViewHolder(ImageView itemView) {
      super(itemView);
      imageView = itemView;
    }
  }
}
