package fr.jetlag.blogwithj.article;

import java.io.Serializable;
import java.util.Date;

import fr.jetlag.blogwithj.display.block.BlockDisplay;
import fr.jetlag.blogwithj.map.Place;
import fr.jetlag.blogwithj.social.SocialStats;

/**
 * Created by vince on 04/06/15.
 */
public class Paragraph implements Serializable {

  private String question;

  private Content blockContent;

  private Content hublotContent;

  private Place location;

  private Date travelDate;

  private String weatherDescription;

  private SocialStats socialStats;
  private Content content;

  public Paragraph(String question, Content blockContent) {
    this.question = question;
    this.blockContent = blockContent;
  }

  public String getQuestion() {
    return question;
  }

  public Content getBlockContent() {
    return blockContent;
  }

  public BlockDisplay getBlockDisplay() {
    return blockContent.getBlockDisplay(this);
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public void setContent(Content content) {
    this.content = content;
  }
}
