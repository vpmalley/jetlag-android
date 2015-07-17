package fr.jetlag.blogwithj.display.block;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.jetlag.blogwithj.R;
import fr.jetlag.blogwithj.article.Paragraph;

/**
 * Created by vince on 04/06/15.
 */
public class TextBlockDisplay implements BlockDisplay {

  private String title;

  private String content;

  public TextBlockDisplay(Paragraph p, String content) {
    this.title = p.getQuestion();
    this.content = content;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder) {
    if (holder instanceof TextBlockDisplay.ViewHolder) {
      ((ViewHolder) holder).titleView.setText(this.title);
      ((ViewHolder) holder).contentView.setText(this.content);
    }
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    final TextView titleView;

    final TextView contentView;

    public ViewHolder(View cardView) {
      super(cardView);
      titleView = (TextView) cardView.findViewById(R.id.title);
      contentView = (TextView) cardView.findViewById(R.id.text_content);
    }
  }
}
