package com.example.mehadihossain.z_ridedemo.fragment.body;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehadihossain.z_ridedemo.R;

public class ViewDetailsFragment extends Fragment implements View.OnClickListener{
    private OnViewDetailsFragmentListener mListener;
    private FloatingActionButton driverInfoButton;

    public ViewDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_details, container, false);
        driverInfoButton = (FloatingActionButton) view.findViewById(R.id.driverInfoFloatingActionButton);
        driverInfoButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewDetailsFragmentListener) {
            mListener = (OnViewDetailsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onViewDetailsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.driverInfoFloatingActionButton){
            mListener.onViewDetailsFragmentInteraction();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnViewDetailsFragmentListener {
        // TODO: Update argument type and name
        void onViewDetailsFragmentInteraction();
    }
}
