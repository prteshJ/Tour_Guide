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
public class MuseumFragment extends Fragment {
    ArrayList<Location> museums = new ArrayList<>(); // Contains all museums

    public MuseumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout file which contains recycler view so that we can use it
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        RecyclerView placesList = (RecyclerView) rootView.findViewById(R.id.placesList);

        // Populate list of museum's around Chicago
        museums.add(new Location(getResources(), R.drawable.adler_planetarium, getString(R.string.adlter_planetarium_location), getString(R.string.museum_1)));
        museums.add(new Location(getResources(), R.drawable.chicago_cultural_center, getString(R.string.the_privatebank_theatre_location), getString(R.string.museum_2)));
        museums.add(new Location(getResources(), R.drawable.chicago_history_museum, getString(R.string.lincoln_park_location), getString(R.string.museum_3)));
        museums.add(new Location(getResources(), R.drawable.museum_of_contemporary_art, getString(R.string.streeterville_location), getString(R.string.museum_4)));
        museums.add(new Location(getResources(), R.drawable.museum_of_science_and_industry, getString(R.string.hyde_park_location), getString(R.string.museum_5)));
        museums.add(new Location(getResources(), R.drawable.national_museum_of_mexican_art, getString(R.string.lower_west_side_location), getString(R.string.museum_6)));
        museums.add(new Location(getResources(), R.drawable.the_field_museum, getString(R.string.adlter_planetarium_location), getString(R.string.museum_7)));

        // Pass all museums data and context to adapter
        LocationAdapter placesAdapter = new LocationAdapter(museums, getContext());

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
                Location chosenPlace = museums.get(position);
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
