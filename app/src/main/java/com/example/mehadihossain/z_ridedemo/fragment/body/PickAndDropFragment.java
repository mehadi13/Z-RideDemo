package com.example.mehadihossain.z_ridedemo.fragment.body;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.example.mehadihossain.z_ridedemo.util.ParserTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickAndDropFragment extends Fragment implements View.OnClickListener,TimePickerDialogFragment.OnTimePickerFragmentInteractionListener {

    String title;
    Button submitButton;
    private TextView titleTextView;
    private OnPickAndDropFragmentListener mListener;
    private FragmentManager fragmentManager;
    private Spinner timeFrameSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private Spinner startpointSpinner;
    private Spinner destinationPointSpinner;
    private CalculateWorkingDay calculateWorkingDay;
    String month,day,timeFrame;
    private Button timePickButton;
    private Button fairButton;
    private TextView fairTextView;
    String startTime,startPoint,destinationPoint;
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

        //set title of fragment
        titleTextView = (TextView) getActivity().findViewById(R.id.titleTextView);
        titleTextView.setText(title);

        //set task and url to download start point list
        ParserTask fetchStartPoint = new ParserTask(this,R.string.pick_drop_class,R.string.start_point_list);
        fetchStartPoint.execute("https://fir-testapplication-76706.firebaseio.com/z-rider/pick_drop.json");

        //set task and url to download destination point list
        ParserTask fetchDestination = new ParserTask(this,R.string.pick_drop_class,R.string.destination_point_list);
        fetchDestination.execute("https://fir-testapplication-76706.firebaseio.com/z-rider/pick_drop.json");

        // 1
        monthSpinner = (Spinner) getActivity().findViewById(R.id.monthSpinner);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = monthSpinner.getSelectedItem().toString();
                ArrayList<String> dayList = calculateWorkingDay.getDaysOfMonth(position);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,dayList);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                daySpinner.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 2
        daySpinner = (Spinner) getActivity().findViewById(R.id.daySpinner);
        calculateWorkingDay = new CalculateWorkingDay();
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = daySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // 3
        timePickButton = (Button) getActivity().findViewById(R.id.timePickButton);
        timePickButton.setOnClickListener(this);

        // 4
        startpointSpinner = (Spinner) getActivity().findViewById(R.id.startPointSpinner);
        startpointSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startPoint = startpointSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 5
        destinationPointSpinner = (Spinner) getActivity().findViewById(R.id.destinationPointSpinner);
        destinationPointSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinationPoint = destinationPointSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 6
        timeFrameSpinner = (Spinner) getActivity().findViewById(R.id.timeFrameSpinner);
        timeFrameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeFrame = timeFrameSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // 7
        fairButton = (Button) getActivity().findViewById(R.id.fairCalculationButton);
        fairButton.setOnClickListener(this);
        fairTextView = (TextView) getActivity().findViewById(R.id.fairTextView);

        // 8
        submitButton = (Button) getActivity().findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitButton:
                mListener.onPickAndDropFragmentInteraction();
                break;
            case R.id.timePickButton:
                TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
                timePickerDialogFragment.setContext(this);
                timePickerDialogFragment.show(fragmentManager,"time");
                break;
            case R.id.fairCalculationButton:
                //set task and url to download calculated fair
                ParserTask calculateFair = new ParserTask(this,R.string.pick_drop_class,R.string.fair_calculation);
                calculateFair.execute("https://fir-testapplication-76706.firebaseio.com/z-rider/pick_drop/fair.json");
                break;
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

/*
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

    }*/



    @Override
    public void onFragmentInteraction(String hh, String mm) {
        startTime = hh+":"+mm;
        timePickButton.setText(startTime);

    }

    public void setData(int taskId,String data) throws JSONException {
        switch (taskId){
            case R.string.start_point_list:
                Toast.makeText(getActivity(),data,Toast.LENGTH_SHORT).show();
                JSONObject reader = new JSONObject(data);
                String placelist[] = reader.getString("place_list").split(",");
                //String placelist[] = data.split(",");
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,placelist);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                startpointSpinner.setAdapter(spinnerArrayAdapter);
                break;
            case R.string.destination_point_list:
                Toast.makeText(getActivity(),data,Toast.LENGTH_SHORT).show();
                JSONObject reader1 = new JSONObject(data);
                String placelist1[] = reader1.getString("place_list").split(",");
                //String placelist[] = data.split(",");
                ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,placelist1);
                spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destinationPointSpinner.setAdapter(spinnerArrayAdapter1);
                break;
            case R.string.fair_calculation:
                fairTextView.setText(data);
                break;
        }
    }

    public interface OnPickAndDropFragmentListener {
        // TODO: Update argument type and name
        void onPickAndDropFragmentInteraction();
    }
}
