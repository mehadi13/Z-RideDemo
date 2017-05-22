package com.example.mehadihossain.z_ridedemo.fragment;

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

public class HeaderFragment extends Fragment implements View.OnClickListener{

    private OnHeaderFragmentListener mListener;
    private Button homeButton;
    private Button backButton;
    public HeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeButton = (Button) getActivity().findViewById(R.id.homeButton);
        backButton = (Button) getActivity().findViewById(R.id.backButton);
        homeButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.homeButton){
            mListener.onHeaderFragmentInteractionHome();
        }else if(v.getId()==R.id.backButton){
            mListener.onHeaderFragmentInteractionBack();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHeaderFragmentListener) {
            mListener = (OnHeaderFragmentListener) context;
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
    public interface OnHeaderFragmentListener {
        // TODO: Update argument type and name
        void onHeaderFragmentInteractionHome();
        void onHeaderFragmentInteractionBack();
    }
}
