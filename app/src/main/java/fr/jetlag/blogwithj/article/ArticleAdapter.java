package fr.jetlag.blogwithj.article;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.display.block.GalleryBlockDisplay;
import fr.jetlag.blogwithj.display.block.TextBlockDisplay;

/**
 * Created by vince on 04/06/15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final Article article;

  public ArticleAdapter(Article article) {
    super();
    this.article = article;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    int resource;
    View v;
    RecyclerView.ViewHolder vh;
    // Creates a view depending on the content of the paragraph
    switch (Content.Type.values()[viewType]) {
      case LINK : resource = R.layout.card_link;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        vh = new TextBlockDisplay.ViewHolder(v);
        break;
      case MAP : resource = R.layout.card_map;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        vh = new TextBlockDisplay.ViewHolder(v);
        break;
      case MEDIA : resource = R.layout.card_media;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        vh = new GalleryBlockDisplay.ViewHolder(v, viewGroup.getContext());
        break;
      case TEXT :
      default: resource = R.layout.card_text;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        vh = new TextBlockDisplay.ViewHolder(v);
        break;
    }
    return vh;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    article.getParagraphs().get(position).getBlockDisplay().onBindViewHolder(viewHolder);
  }

  @Override
  public int getItemCount() {
    return article.getParagraphs().size();
  }

  /**
   * Determines the View that will be displayed for each of the Paragraphs
   * @param position the index of the Paragraph in the Article
   * @return the type of View to display this paragraph
   */
  @Override
  public int getItemViewType(int position) {
    return article.getParagraphs().get(position).getBlockContent().getType();
  }
}
