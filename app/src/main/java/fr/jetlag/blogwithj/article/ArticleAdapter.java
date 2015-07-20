package fr.jetlag.blogwithj.article;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.jetlag.blogwithj.ArticleDetailFragment;
import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.display.block.GalleryBlockDisplay;
import fr.jetlag.blogwithj.display.block.TextBlockDisplay;

/**
 * Created by vince on 04/06/15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

  private final Article article;

  private final ArticleDetailFragment fragment;

  public ArticleAdapter(Article article, ArticleDetailFragment fragment) {
    super();
    this.article = article;
    this.fragment = fragment;
  }

  @Override
  public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
    int resource;
    View v;
    ViewHolder vh;
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
  public void onBindViewHolder(ViewHolder viewHolder, final int position) {
    final Paragraph paragraph = article.getParagraphs().get(position);
    viewHolder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        fragment.editParagraph(position);
      }
    });
    paragraph.getBlockDisplay().onBindViewHolder(viewHolder);
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

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
      super(itemView);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
      itemView.setOnClickListener(onClickListener);
    }
  }
}
