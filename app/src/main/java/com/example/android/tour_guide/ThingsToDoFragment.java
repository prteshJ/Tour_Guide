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
public class ThingsToDoFragment extends Fragment {

    public ThingsToDoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout file which contains recycler view so that we can use it
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        RecyclerView placesList = (RecyclerView) rootView.findViewById(R.id.placesList);

        // Populate list of things to do around Chicago
        final ArrayList<Location> things_to_do = new ArrayList<>(); // Contains all things to do

        things_to_do.add(0, new Location(getResources(), R.drawable.chicago_360, getString(R.string.streeterville_location), getString(R.string.things_to_do_1)));
        things_to_do.add(1, new Location(getResources(), R.drawable.art_institute_of_chicago, getString(R.string.grant_park_location), getString(R.string.things_to_do_2)));
        things_to_do.add(2, new Location(getResources(), R.drawable.humboldt_park, getString(R.string.humboldt_park_location), getString(R.string.things_to_do_3)));
        things_to_do.add(3, new Location(getResources(), R.drawable.ice_skating, getString(R.string.millenium_park_location), getString(R.string.things_to_do_4)));
        things_to_do.add(4, new Location(getResources(), R.drawable.lincoln_park_zoo, getString(R.string.lincoln_park_location), getString(R.string.things_to_do_5)));
        things_to_do.add(5, new Location(getResources(), R.drawable.navy_pier, getString(R.string.streeterville_location), getString(R.string.things_to_do_6)));
        things_to_do.add(6, new Location(getResources(), R.drawable.riverwalk, getString(R.string.the_privatebank_theatre_location), getString(R.string.things_to_do_7)));
        things_to_do.add(7, new Location(getResources(), R.drawable.shedd_aquarium, getString(R.string.adlter_planetarium_location), getString(R.string.things_to_do_8)));
        things_to_do.add(8, new Location(getResources(), R.drawable.willis_tower_skydeck, getString(R.string.the_privatebank_theatre_location), getString(R.string.things_to_do_9)));

        // Pass all things to do data and context to adapter
        LocationAdapter placesAdapter = new LocationAdapter(things_to_do, getContext());

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
                Location chosenPlace = things_to_do.get(position);
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
