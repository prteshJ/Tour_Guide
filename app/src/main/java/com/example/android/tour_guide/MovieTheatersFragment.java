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
public class MovieTheatersFragment extends Fragment {
    ArrayList<Location> movieTheaters = new ArrayList<>(); // Contains all movie theaters

    public MovieTheatersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout file which contains recycler view so that we can use it
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        RecyclerView placesList = (RecyclerView) rootView.findViewById(R.id.placesList);

        // Populate list of movie theatre's around Chicago
        movieTheaters.add(new Location(getResources(), R.drawable.a_wrinkle_in_time, getString(R.string.lifeline_theatre_location), getString(R.string.movie_theatre_1)));
        movieTheaters.add(new Location(getResources(), R.drawable.hamilton, getString(R.string.the_privatebank_theatre_location), getString(R.string.movie_theatre_2)));
        movieTheaters.add(new Location(getResources(), R.drawable.music_box_theatre, getString(R.string.wrigleyville_location), getString(R.string.movie_theatre_3)));
        movieTheaters.add(new Location(getResources(), R.drawable.spamilton, getString(R.string.lincoln_park_location), getString(R.string.movie_theatre_4)));
        movieTheaters.add(new Location(getResources(), R.drawable.the_resistible_rise_of_arturo_ui, getString(R.string.trap_door_theatre_location), getString(R.string.movie_theatre_5)));
        movieTheaters.add(new Location(getResources(), R.drawable.the_wiz, getString(R.string.theatre_wit_location), getString(R.string.movie_theatre_6)));

        // Pass all movie theaters data and context to adapter
        LocationAdapter placesAdapter = new LocationAdapter(movieTheaters, getContext());

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
                Location chosenPlace = movieTheaters.get(position);
                String address = chosenPlace.getmAttractionName() + " " + getString(R.string.theatre_word) + " " + getString(R.string.chicago_place);
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
