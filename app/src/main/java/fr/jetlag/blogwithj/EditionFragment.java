package fr.jetlag.blogwithj;

import android.support.v4.app.Fragment;

import fr.jetlag.blogwithj.article.Content;

/**
 * Created by vince on 17/07/15.
 *
 * Enriching the base Fragment with some methods to be implemented
 */
public abstract class EditionFragment extends Fragment {

  abstract Content saveViewToContent();

}
