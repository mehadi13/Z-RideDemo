package com.example.mehadihossain.z_ridedemo.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mehadihossain.z_ridedemo.R;
import com.example.mehadihossain.z_ridedemo.fragment.body.PickAndDropFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Mehadi Hossain on 5/23/2017.
 */

public class ParserTask extends AsyncTask<String,String,String> {

    private Object object;
    private int objectId;
    private int taskId;

    public ParserTask(Object object, int objectId, int taskId) {
        this.object = object;
        this.objectId = objectId;
        this.taskId = taskId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... url) {
        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            Log.d("log",url[0]);
            String data = downloadUrl.readUrl(url[0]);
            Log.d("data",data);
            return data;
        } catch (IOException e) {
            //e.printStackTrace();
            Log.d("exception",e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String data) {
        //super.onPostExecute(data);
        try {
            JSONObject reader = new JSONObject(data);
            System.out.println("Data:"+reader.toString());
            //Toast.makeText()
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setUpdata(data);
    }

    private void setUpdata(String data) {
        switch (objectId){
            //case for Pick_drop class
            case R.string.pick_drop_class:
                PickAndDropFragment pickAndDropFragment = (PickAndDropFragment) object;
                try {
                    pickAndDropFragment.setData(taskId,data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            //

        }
    }
}
