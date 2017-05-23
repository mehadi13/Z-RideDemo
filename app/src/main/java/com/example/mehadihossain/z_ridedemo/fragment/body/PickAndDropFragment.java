package com.example.mehadihossain.z_ridedemo.fragment.body;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehadihossain.z_ridedemo.R;
import com.example.mehadihossain.z_ridedemo.util.CalculateWorkingDay;
import com.example.mehadihossain.z_ridedemo.experimental.GridDialogFragment;
import com.example.mehadihossain.z_ridedemo.fragment.dialog.TimePickerDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickAndDropFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener,TimePickerDialogFragment.OnTimePickerFragmentInteractionListener {

    String title;
    Button submitButton;
    private TextView titleTextView;
    private OnPickAndDropFragmentListener mListener;
    private FragmentManager fragmentManager;
    private Spinner timeFrameSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private CalculateWorkingDay calculateWorkingDay;
    int month,day,timeFrame;
    private Button timePickButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getFragmentManager();
        return inflater.inflate(R.layout.fragment_pick_and_drop, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submitButton = (Button) getActivity().findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        titleTextView = (TextView) getActivity().findViewById(R.id.titleTextView);
        titleTextView.setText(title);

        monthSpinner = (Spinner) getActivity().findViewById(R.id.monthSpinner);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = position;
                ArrayList<String> dayList = calculateWorkingDay.getDaysOfMonth(position);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,dayList);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                daySpinner.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        daySpinner = (Spinner) getActivity().findViewById(R.id.daySpinner);
        calculateWorkingDay = new CalculateWorkingDay();
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = Integer.parseInt((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timeFrameSpinner = (Spinner) getActivity().findViewById(R.id.timeFrameSpinner);
        timeFrameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeFrame = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timePickButton = (Button) getActivity().findViewById(R.id.timePickButton);
        timePickButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submitButton) {
            GridDialogFragment gridDialogFragment = new GridDialogFragment();
            gridDialogFragment.setContext(getActivity());
            gridDialogFragment.show(fragmentManager, "gdf");
            //mListener.onPickAndDropFragmentInteraction();
        }/*else if(v.getId()==R.id.monthButton){
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(getActivity().getResources().getStringArray(R.array.months)));
            GridDialogFragment gridDialogFragment = new GridDialogFragment();
            gridDialogFragment.setContext(getActivity());
            gridDialogFragment.setListItmes(arrayList);
            gridDialogFragment.show(fragmentManager,"dfasdf");
        }*/
        else if(v.getId()==R.id.timePickButton){
            TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
            timePickerDialogFragment.setContext(this);
            timePickerDialogFragment.show(fragmentManager,"time");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPickAndDropFragmentListener) {
            mListener = (OnPickAndDropFragmentListener) context;
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

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),Integer.toString(view.getId()),Toast.LENGTH_SHORT).show();
        if(view.getId()==R.id.monthSpinner){
            month = position;
            ArrayList<String> dayList = calculateWorkingDay.getDaysOfMonth(position);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,dayList);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(spinnerArrayAdapter);
            System.out.println("Days:"+dayList);
        }else if(view.getId()==R.id.daySpinner){

        }else if(view.getId()==R.id.timeFrameSpinner){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFragmentInteraction(String hh, String mm) {
        timePickButton.setText(hh+":"+mm);
    }

    public interface OnPickAndDropFragmentListener {
        // TODO: Update argument type and name
        void onPickAndDropFragmentInteraction();
    }
}
