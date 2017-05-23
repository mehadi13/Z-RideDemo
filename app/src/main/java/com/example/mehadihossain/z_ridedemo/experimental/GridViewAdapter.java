package com.example.mehadihossain.z_ridedemo.experimental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mehadihossain.z_ridedemo.R;

import java.util.ArrayList;

/**
 * Created by Mehadi Hossain on 5/21/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    ArrayList<String> items;
    Context context;

    public GridViewAdapter(ArrayList<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView itemTextView;
        ViewHolder(View view){
            itemTextView = (TextView) view.findViewById(R.id.gridItemTextView);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder viewHolder = null;
        if(item==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = layoutInflater.inflate(R.layout.gridviewdialogitem, parent, false);
            viewHolder = new ViewHolder(item);
            item.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) item.getTag();
        }
        viewHolder.itemTextView.setText(items.get(position));
        return item;
    }
}
