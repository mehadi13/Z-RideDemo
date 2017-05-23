package com.example.mehadihossain.z_ridedemo.experimental;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mehadihossain.z_ridedemo.R;

import java.util.ArrayList;

/**
 * Created by Mehadi Hossain on 5/21/2017.
 */

public class GridDialogFragment extends DialogFragment {
    private GridView gridView;
    private Context context;
    private TextView titleTextView;



    private ArrayList<String> listItmes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_dialog,null);
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewAdapter(listItmes,context));
        gridView.setColumnWidth(200);
        return view;
    }

    public void setListItmes(ArrayList<String> listItmes) {
        this.listItmes = listItmes;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
