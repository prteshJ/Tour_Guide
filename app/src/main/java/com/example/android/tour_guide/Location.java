package com.example.android.tour_guide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by PriteshJ on 4/7/17.
 */
public class Location {
    private int mLocationImageResourceId;
    private String mAttractionName;
    private Resources mRes;
    private String mLocationName;

    public Location() {
    }

    /**
     * @param res                      is or accessing application resources {@link Resources}
     * @param mLocationImageResourceId is tourist attraction image
     * @param mLocationName            is location where attraction is present
     * @param mAttractionName          is tourist attraction name
     */
    public Location(Resources res, int mLocationImageResourceId, String mLocationName, String mAttractionName) {
        this.mRes = res;
        this.mLocationImageResourceId = mLocationImageResourceId;
        this.mLocationName = mLocationName;
        this.mAttractionName = mAttractionName;
    }

    public String getmLocationName() {
        return mLocationName;
    }

    public String getmAttractionName() {
        return mAttractionName;
    }

    /**
     * @return Bitmap which is scaled version of HD image for performance efficiency purpose
     */
    public Bitmap getmBitmap() {
        Bitmap thumbnail = BitmapFactory.decodeResource(mRes, mLocationImageResourceId);
        thumbnail = Bitmap.createScaledBitmap(thumbnail, 150, 85, true);
        return thumbnail;
    }

    /**
     * @return string containing all details about Location object
     */
    @Override
    public String toString() {
        return "Location{" +
                "mLocationImageResourceId=" + mLocationImageResourceId +
                ", mAttractionName='" + mAttractionName + '\'' +
                ", mRes=" + mRes +
                ", mLocationName='" + mLocationName + '\'' +
                '}';
    }
}

