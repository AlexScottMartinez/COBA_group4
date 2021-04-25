package com.example.coba_group4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coba_group4.occurence.Occurrence;

import java.util.ArrayList;

public class OccurrenceListAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Occurrence> occurrenceList;

    public OccurrenceListAdapter(Context context, ArrayList<Occurrence> occurrenceList)
    {
        this.context = context;
        this.occurrenceList = occurrenceList;
    }

    @Override
    public int getCount()
    {
        return this.occurrenceList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return occurrenceList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_occurrences, null);
            TextView type = (TextView)convertView.findViewById(R.id.type);
            TextView time = (TextView)convertView.findViewById(R.id.time);
            TextView address = (TextView)convertView.findViewById(R.id.address);
            TextView city = (TextView)convertView.findViewById(R.id.city);
            TextView zip = (TextView)convertView.findViewById(R.id.zip);

           Occurrence occurrence = occurrenceList.get(position);

           type.setText(occurrence.getType());
           time.setText(String.valueOf(occurrence.getSubmittedTime()));
           address.setText(occurrence.getAddress());
           city.setText(occurrence.getCity());
           zip.setText(String.valueOf(occurrence.getZipCode()));

        return convertView;
    }
}
