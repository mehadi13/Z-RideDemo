package com.example.mehadihossain.z_ridedemo.fragment;


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
public class BasketFragment extends Fragment implements View.OnClickListener{

    private Button paymentButton;
    private OnBasketFragmentListener mListener;

    public BasketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        paymentButton = (Button) getActivity().findViewById(R.id.paymentButton);
        paymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.OnBasketFragmentInteraction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBasketFragmentListener) {
            mListener = (OnBasketFragmentListener) context;
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

    public interface OnBasketFragmentListener {
        // TODO: Update argument type and name
        void OnBasketFragmentInteraction();
    }
}
