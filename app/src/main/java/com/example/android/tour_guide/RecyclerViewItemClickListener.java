package com.example.android.tour_guide;

import android.view.View;

/**
 * Created by PriteshJ on 4/7/17.
 * <p>
 * Custom interface that needs to implemented by
 * Recycler View to implement list view on click
 * behavior
 */
public interface RecyclerViewItemClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}