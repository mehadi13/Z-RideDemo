package com.example.mehadihossain.z_ridedemo.fragment.body;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mehadihossain.z_ridedemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceListFragment extends Fragment {


    private OnPlaceListFragmentInteractionListener mListener;
    private ListView placeList;
    public PlaceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //init list view and open pick/drop
        placeList = (ListView) getActivity().findViewById(R.id.place_listView);
        placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //cal method of main activity
                mListener.onPlaceListFragmentInteraction();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlaceListFragmentInteractionListener) {
            mListener = (OnPlaceListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPlaceListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPlaceListFragmentInteraction();
    }
}
