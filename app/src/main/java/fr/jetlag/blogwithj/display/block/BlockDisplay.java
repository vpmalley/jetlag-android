package fr.jetlag.blogwithj.display.block;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by vince on 04/06/15.
 */
public interface BlockDisplay {

  void onBindViewHolder(RecyclerView.ViewHolder holder);
}
