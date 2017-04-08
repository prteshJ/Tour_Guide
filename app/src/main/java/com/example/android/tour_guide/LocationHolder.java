package com.example.android.tour_guide;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PriteshJ on 4/7/17.
 * <p>
 * Used Holder pattern for performance efficiency reasons
 */
public class LocationHolder extends RecyclerView.ViewHolder {
    private ImageView mLocationImage;
    private TextView mLocationName;
    private TextView mAttractionName;

    public LocationHolder(View placeView) {
        super(placeView);
        mLocationImage = (ImageView) placeView.findViewById(R.id.location_image);
        mLocationName = (TextView) placeView.findViewById(R.id.location_title);
        mAttractionName = (TextView) placeView.findViewById(R.id.attraction_name);
    }

    public void bindLocation(Location place) {
//        mLocationImage.setImageResource(place.getmLocationImageResourceId()); Was creating out of memory exceptions for HD images
        mLocationImage.setImageBitmap(place.getmBitmap());
        mLocationName.setText(place.getmLocationName());
        mAttractionName.setText(place.getmAttractionName());
    }

}
