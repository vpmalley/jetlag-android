package fr.jetlag.blogwithj.media;

import java.util.ArrayList;
import java.util.List;

import fr.jetlag.blogwithj.display.block.BlockDisplay;
import fr.jetlag.blogwithj.display.block.GalleryBlockDisplay;
import fr.jetlag.blogwithj.article.Content;
import fr.jetlag.blogwithj.article.Paragraph;
import fr.jetlag.blogwithj.social.SocialStats;

/**
 * Created by vince on 04/06/15.
 */
public class Gallery implements Content {

  private Content description;

  private List<Media> medias;

  private SocialStats socialStats;

  public Gallery() {
    this.medias = new ArrayList<>();
  }

  public void addMedia(Media m) {
    medias.add(m);
  }

  public List<Media> getMedias() {
    return medias;
  }

  @Override
  public BlockDisplay getBlockDisplay(Paragraph paragraph) {
    return new GalleryBlockDisplay(paragraph.getQuestion(), this);
  }

  @Override
  public int getType() {
    return Type.MEDIA.ordinal();
  }

}
