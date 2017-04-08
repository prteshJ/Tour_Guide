package com.example.android.tour_guide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by PriteshJ on 4/7/17.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {
    private ArrayList<Location> mLocationList = new ArrayList<>();
    private Context mContext;

    /**
     * @param mLocationList contains all location data
     * @param context       is containing fragment data {@link Context}
     */
    public LocationAdapter(ArrayList<Location> mLocationList, Context context) {
        this.mLocationList = mLocationList;
        this.mContext = context;
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationHolder holder, int position) {
        Location place = mLocationList.get(position);
        holder.bindLocation(place);
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }
    
    // Added below methods to prevent recycler view from duplcating items
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}


