package fr.jetlag.blogwithj.article;

import java.io.Serializable;

import fr.jetlag.blogwithj.display.block.BlockDisplay;

/**
 * Created by vince on 04/06/15.
 */
public interface Content extends Serializable {

  BlockDisplay getBlockDisplay(Paragraph paragraph);

  enum Type {
    TEXT,
    MEDIA,
    MAP,
    LINK
  }

  int getType();

}
