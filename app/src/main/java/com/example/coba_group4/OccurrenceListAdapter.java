package com.example.coba_group4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coba_group4.occurence.Occurrence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OccurrenceListAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Occurrence> occurrenceList;

    public OccurrenceListAdapter(Context context, ArrayList<Occurrence> occurrenceList)
    {
        this.context = context;
        this.occurrenceList = occurrenceList;
    }

    public String createAddressString(Occurrence occurrence)
    {
        return occurrence.getAddress() +", "
                + occurrence.getCity() +", "
                + occurrence.getState()+", "
                + occurrence.getZipCode();
    }

    public String createDateString(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        return simpleDateFormat.format(date);
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

        Occurrence occurrence = occurrenceList.get(position);

        type.setText(occurrence.getType());
        time.setText(createDateString(occurrence.getSubmittedTime()));
        address.setText(createAddressString(occurrence));

        return convertView;
    }
}
