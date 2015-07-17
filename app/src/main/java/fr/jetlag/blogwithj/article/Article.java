package fr.jetlag.blogwithj.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.jetlag.blogwithj.social.SocialStats;

/**
 * Created by vince on 04/06/15.
 */
public class Article {

  public Article(String title, Content description) {
    this.title = title;
    this.description = description;
  }

  private String title;

  private Content description;

  private List<Paragraph> paragraphs = new ArrayList<>();

  private SocialStats socialStats;

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return description.toString();
  }

  @Override
  public String toString() {
    return getTitle();
  }

  public void addParagraph(Paragraph p) {
    paragraphs.add(p);
  }

  public List<Paragraph> getParagraphs() {
    return paragraphs;
  }
}
