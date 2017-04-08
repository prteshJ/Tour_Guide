package com.example.android.tour_guide;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionsFragment extends Fragment {
    ArrayList<Location> attractions = new ArrayList<>(); // Contains all attractions

    public AttractionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the layout file which contains recycler view so that we can use it
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        RecyclerView placesList = (RecyclerView) rootView.findViewById(R.id.placesList);

        // Populate list of attractions around Chicago
        attractions.add(new Location(getResources(), R.drawable.garfield_park_conservatory, getString(R.string.east_garfield_park_location), getString(R.string.attraction_1)));
        attractions.add(new Location(getResources(), R.drawable.the_606, getString(R.string.humboldt_park_location), getString(R.string.attraction_2)));
        attractions.add(new Location(getResources(), R.drawable.wrigley_field_baseball_and_softball, getString(R.string.wrigleyville_location), getString(R.string.attraction_3)));
        attractions.add(new Location(getResources(), R.drawable.the_second_city_comedy_club, getString(R.string.lincoln_park_location), getString(R.string.attraction_4)));

        // Pass all attractions data and context to adapter
        LocationAdapter placesAdapter = new LocationAdapter(attractions, getContext());

        /**
         * Sets layout manager for recycler view
         *
         * Sets adapter for recycler view which is bound to a custom view holder
         *
         * Sets up a custom on touch listener to detect clicks on recycler view since this
         * is the only way to handle clicks in recycler view
         */
        placesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        placesList.setAdapter(placesAdapter);
        placesList.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), placesList, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Opens up clicked location in google maps
                Location chosenPlace = attractions.get(position);
                String address = chosenPlace.getmAttractionName() + "," + chosenPlace.getmLocationName();
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

}
