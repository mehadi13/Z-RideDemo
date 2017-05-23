package com.example.mehadihossain.z_ridedemo.fragment.body;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mehadihossain.z_ridedemo.R;

public class OperationsFragment extends Fragment implements View.OnClickListener{

    private Button newRequest;
    private Button viewDetails;
    private OnOperationFragmentListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newRequest = (Button) getActivity().findViewById(R.id.rideRequestButton);
        viewDetails = (Button) getActivity().findViewById(R.id.viewDetailsButton);
        newRequest.setOnClickListener(this);
        viewDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.rideRequestButton){
            mListener.OnOperationFragmentInteraction("new");
        }else if(v.getId()==R.id.viewDetailsButton){
            mListener.OnOperationFragmentInteraction("view");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOperationFragmentListener) {
            mListener = (OnOperationFragmentListener) context;
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

    public interface OnOperationFragmentListener {
        // TODO: Update argument type and name
        void OnOperationFragmentInteraction(String title);
    }

}
