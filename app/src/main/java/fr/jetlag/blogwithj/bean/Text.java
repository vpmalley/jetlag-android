package fr.jetlag.blogwithj.bean;

import fr.jetlag.blogwithj.display.block.BlockDisplay;
import fr.jetlag.blogwithj.display.block.TextBlockDisplay;
import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.article.Paragraph;

/**
 * Created by vince on 04/06/15.
 */
public class Text implements Content {

  public Text(String text) {
    this.text = text;
  }

  private String text;

  private User author;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

  @Override
  public BlockDisplay getBlockDisplay(Paragraph paragraph) {
    return new TextBlockDisplay(paragraph, text);
  }

  @Override
  public int getType() {
    return Type.TEXT.ordinal();
  }
}
