package com.example.mehadihossain.z_ridedemo.fragment.body;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mehadihossain.z_ridedemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideRequestFragment extends Fragment implements View.OnClickListener{


    private Button pickButton;
    private Button dropButton;
    private OnRideRequestFragmentListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_request, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pickButton = (Button) getActivity().findViewById(R.id.pickButton);
        dropButton = (Button) getActivity().findViewById(R.id.dropButton);
        pickButton.setOnClickListener(this);
        dropButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pickButton){
            mListener.OnRideRequestFragmentInteraction(getResources().getString(R.string.pick));
        }else if(v.getId()==R.id.dropButton){
            mListener.OnRideRequestFragmentInteraction(getResources().getString(R.string.drop));
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRideRequestFragmentListener) {
            mListener = (OnRideRequestFragmentListener) context;
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

    public interface OnRideRequestFragmentListener {
        // TODO: Update argument type and name
        void OnRideRequestFragmentInteraction(String title);
    }
}
