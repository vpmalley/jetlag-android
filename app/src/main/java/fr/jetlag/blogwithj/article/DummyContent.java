package fr.jetlag.blogwithj.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.jetlag.blogwithj.bean.Text;
import fr.jetlag.blogwithj.media.Gallery;
import fr.jetlag.blogwithj.media.Media;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static List<Article> ITEMS = new ArrayList<>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static Map<String, Article> ITEM_MAP = new HashMap<>();

  static {
    Article helloArticle = new Article("hello", new Text("hello world"));
    helloArticle.addParagraph(new Paragraph("how are you?", new Text("I am good")));
    helloArticle.addParagraph(new Paragraph("where are you?", new Text("Do you really want an answer?\n\nYou should not ask")));
    Gallery gallery = new Gallery();
    gallery.addMedia(new Media("http://media.radiofrance-podcast.net/podcast09/RF_OMM_0000009303_ITE.jpg"));
    gallery.addMedia(new Media("http://ottawa.ca/include/doors/supremecourt.jpg"));
    gallery.addMedia(new Media("http://ottawa.ca/include/doors/AdvancedResearchComplexuOttawa.jpg"));
    helloArticle.addParagraph(new Paragraph("Show what you see", gallery));
    addItem(helloArticle);
    addItem(new Article("ola", new Text("Ola mundo")));
    addItem(new Article("c'est l'histoire d'un mec", new Text("Il était une fois")));
  }

  private static void addItem(Article item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.getTitle(), item);
  }
}
