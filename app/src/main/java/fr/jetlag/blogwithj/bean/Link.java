package fr.jetlag.blogwithj.bean;

import fr.jetlag.blogwithj.display.block.BlockDisplay;
import fr.jetlag.blogwithj.display.block.TextBlockDisplay;
import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.article.Paragraph;

/**
 * Created by vince on 04/06/15.
 */
public class Link implements Content {

  private Caption caption;

  private String url;

  public Link(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public BlockDisplay getBlockDisplay(Paragraph paragraph) {
    return new TextBlockDisplay(paragraph, url);
  }

  @Override
  public int getType() {
    return Type.LINK.ordinal();
  }

  @Override
  public String toString() {
    return url + " of type " + Type.LINK;
  }
}
