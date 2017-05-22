package com.example.mehadihossain.z_ridedemo.util;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.example.mehadihossain.z_ridedemo.R;
import com.example.mehadihossain.z_ridedemo.fragment.PickAndDropFragment;

public class TimePickerDialogFragment extends DialogFragment implements View.OnClickListener{

    private OnTimePickerFragmentInteractionListener mListener;
    private TimePicker timePicker;
    private Button setButton;
    private Button cancelButton;
    public TimePickerDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setCancelable(false);
        View view = inflater.inflate(R.layout.fragment_time_picker_dialog, null);

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        setButton = (Button) view.findViewById(R.id.setButton);
        setButton.setOnClickListener(this);

        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.setButton){
            int hh = timePicker.getCurrentHour();
            int mm = timePicker.getCurrentMinute();
            if(mm>15 || mm<45){
                mm=30;
            }else {
                mm=00;
            }
            mListener.onFragmentInteraction(Integer.toString(hh),Integer.toString(mm));
            dismiss();
            //mListener=null;

        }else if(v.getId()== R.id.cancelButton){
            dismiss();
        }
    }

    public void setContext(PickAndDropFragment pickAndDropFragment){
        if (pickAndDropFragment instanceof OnTimePickerFragmentInteractionListener) {
            mListener = (OnTimePickerFragmentInteractionListener) pickAndDropFragment;
        }
    }

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTimePickerFragmentInteractionListener) {
            mListener = (OnTimePickerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/
    public interface OnTimePickerFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String hh,String mm);
    }
}
