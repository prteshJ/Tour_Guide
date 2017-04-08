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
public class RestaurantsFragment extends Fragment {
    ArrayList<Location> restaurants = new ArrayList<>(); // Contains all restaurants

    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout file which contains recycler view so that we can use it
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        RecyclerView placesList = (RecyclerView) rootView.findViewById(R.id.placesList);

        // Populate list of restaurants around Chicago using setters to prevent recycler view
        // duplicating views which happened earlier when I used add method
        restaurants.set(0, new Location(getResources(), R.drawable.alinea, getString(R.string.lincoln_park_location), getString(R.string.restaurant_1)));
        restaurants.set(1, new Location(getResources(), R.drawable.elske, getString(R.string.west_loop_location), getString(R.string.restaurant_2)));
        restaurants.set(2, new Location(getResources(), R.drawable.giant, getString(R.string.logan_square_location), getString(R.string.restaurant_3)));
        restaurants.set(3, new Location(getResources(), R.drawable.honeys, getString(R.string.west_loop_location), getString(R.string.restaurant_4)));
        restaurants.set(4, new Location(getResources(), R.drawable.kitsune, getString(R.string.north_center_location), getString(R.string.restaurant_5)));
        restaurants.set(5, new Location(getResources(), R.drawable.oriole, getString(R.string.west_loop_location), getString(R.string.restaurant_6)));
        restaurants.set(6, new Location(getResources(), R.drawable.smyth, getString(R.string.west_loop_location), getString(R.string.restaurant_7)));

        // Pass all restaurants data and context to adapter
        LocationAdapter placesAdapter = new LocationAdapter(restaurants, getContext());

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
                Location chosenPlace = restaurants.get(position);
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
