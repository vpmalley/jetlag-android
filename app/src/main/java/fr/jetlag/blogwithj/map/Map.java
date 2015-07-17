package fr.jetlag.blogwithj.map;

import java.util.List;

import fr.jetlag.blogwithj.display.block.BlockDisplay;
import fr.jetlag.blogwithj.display.block.TextBlockDisplay;
import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.article.Paragraph;
import fr.jetlag.blogwithj.bean.Caption;
import fr.jetlag.blogwithj.social.SocialStats;

/**
 * Created by vince on 04/06/15.
 */
public class Map implements Content {

  private Caption caption;

  private List<Marker> markers;

  private SocialStats socialStats;

  @Override
  public BlockDisplay getBlockDisplay(Paragraph paragraph) {
    return new TextBlockDisplay(paragraph, caption.toString());
  }

  @Override
  public int getType() {
    return Type.MAP.ordinal();
  }

}
