package com.example.location_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LocationAdapter extends ArrayAdapter {
    private int resource;
    private Context context;
    private List<MyLocation> myLocations;

    public LocationAdapter(@NonNull Context context, int resource, List<MyLocation> myLocations) {
        super(context, resource, myLocations);
        this.resource = resource;
        this.context = context;
        this.myLocations = myLocations;
    }

    @Override
    public int getCount() {
        return myLocations.size();
    }
    public void update(MyLocation myLocation){
        myLocations.add(myLocation);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.latitude.setText("Latitude: " + myLocations.get(position).getLatitude());
        viewHolder.longitude.setText("Longitude: " + myLocations.get(position).getLongitude());
        return convertView;

    }

    private static class ViewHolder{
        private TextView latitude;
        private TextView longitude;
        ViewHolder(View v){
            latitude = v.findViewById(R.id.lat);
            longitude = v.findViewById(R.id.lon);
        }
    }
}
