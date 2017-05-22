package com.example.mehadihossain.z_ridedemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mehadihossain.z_ridedemo.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mehadi Hossain on 5/21/2017.
 */

public class MonthAdapter extends BaseAdapter {
    ArrayList<String> months;
    Context context;
    MonthAdapter(Context context){
        Log.d("dd","sadf");
        Resources resources = context.getResources();
        months = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.months)));
        this.context = context;
    }

    @Override
    public int getCount() {
        return months.size();
    }

    @Override
    public Object getItem(int position) {
        return months.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView itemTextView;
        ViewHolder(View view){
            itemTextView = (TextView) view.findViewById(R.id.monthtextView);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = view;
        ViewHolder viewHolder = null;
        if(item==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = layoutInflater.inflate(R.layout.monthitem, parent, false);
            viewHolder = new ViewHolder(item);
            item.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) item.getTag();
        }
        viewHolder.itemTextView.setText(months.get(position));
        return item;
    }
}
