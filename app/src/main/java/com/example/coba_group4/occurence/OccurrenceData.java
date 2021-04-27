package com.example.coba_group4.occurence;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.coba_group4.MainActivity;
import com.example.coba_group4.R;
import com.example.coba_group4.database.OccurrenceDB;
import com.example.coba_group4.fragment.DatePickerFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class OccurrenceData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    public ListView occurrence_List;
    public OccurrenceDB occurrenceDB;
    public static ArrayList<Occurrence> occurrences = new ArrayList<>();
    private EditText attrField;
    private Spinner attributeDropDown;
    private Button toDateBtn, fromDateBtn;
    boolean toDateSelected = true;
    BarGraphSeries<DataPoint> series;
    Date toDate, fromDate;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occurrence_data);
        graph = (GraphView) findViewById(R.id.occurrence_graph);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));

        occurrence_List = findViewById(R.id.occurrence_list);
        attrField = findViewById(R.id.attr_text_value);
        toDateBtn = findViewById(R.id.to_date_btn);
        toDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDateSelected = true;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "to date picker");
            }
        });

        fromDateBtn = findViewById(R.id.from_date_btn);
        fromDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDateSelected = false;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "from date picker");
            }
        });

        occurrenceDB = new OccurrenceDB();

        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("City");
        attributes.add("State");
        attributes.add("Zip Code");
        attributes.add("Occurrence Type");

        attributeDropDown = findViewById(R.id.attr_dropdown);
        ArrayAdapter<String> attrAdapter = new ArrayAdapter<String>(OccurrenceData.this, android.R.layout.simple_spinner_dropdown_item, attributes);
        attributeDropDown.setAdapter(attrAdapter);

    }

    private void loadDataInList()
    {
        String attrTypeString = (String) attributeDropDown.getSelectedItem();
        String attrValueString = attrField.getText().toString().trim();
        String toDateStr = toDateBtn.getText().toString().trim();
        String fromDateStr = fromDateBtn.getText().toString().trim();

        boolean error = false;
        switch (attrTypeString)
        {
            case "City": {
                attrTypeString = "city";
                break;
            }
            case "State": {
                attrTypeString = "state";
                break;
            }
            case "Zip Code": {
                attrTypeString = "zip";
                break;
            }
            case "Occurrence Type": {
                attrTypeString = "type";
                break;
            }
            default:{
                attrTypeString = null;
            }
        }

        if(TextUtils.isEmpty(attrTypeString) && !TextUtils.isEmpty(attrValueString))
        {
            ((TextView)attributeDropDown.getSelectedView()).setError("You must add a value to search for if you choose an attribute to search for.");
            error = true;
        }

        if(!TextUtils.isEmpty(attrTypeString) && TextUtils.isEmpty(attrValueString))
        {
            attrField.setError("You must set an attribute to be able to search for occurrences.");
            error = true;
        }

        long longToDate = 0, longFromDate = 0;
        if(!toDateStr.equals("") && !toDateStr.equals("To Date")) {
            try {
                toDate = sdf.parse(toDateStr);
                longToDate = toDate.getTime();
            } catch (ParseException e) {
                toDateBtn.setError("Date can not be determined.  Please try again.");
                error = true;
            }
        }

        if(!fromDateStr.equals("") && !fromDateStr.equals("From Date")) {
            try {
                fromDate = sdf.parse(fromDateStr);
                longFromDate = fromDate.getTime();
            } catch (ParseException e) {
                toDateBtn.setError("Date can not be determined.  Please try again.");
                error = true;
            }
        }

        if(error)
        {
            return;
        }

        occurrences = occurrenceDB.getOccurrenceByAttributeAndTime(attrTypeString, attrValueString, longFromDate ,longToDate );

        drawGraph();
    }

    public void onClick(View v) {
        loadDataInList();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(toDateSelected)
        {
            toDateBtn.setText(month+1+"/"+dayOfMonth+"/"+year);
        }
        else
        {
            fromDateBtn.setText(month+1+"/"+dayOfMonth+"/"+year);
        }
    }

    private void drawGraph()
    {


        series = new BarGraphSeries<>();
        graph.removeAllSeries();
        if(occurrences.isEmpty())
        {
            return;
        }
        HashMap<Date, Double> dataMap = new HashMap<>();
        populateDataMap(dataMap);
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < occurrences.size(); i++)
        {
            Occurrence occurrence = occurrences.get(i);
            calendar.setTime(occurrence.getSubmittedTime());
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.AM_PM, 0);
            Date key = calendar.getTime();
            Double value = dataMap.get(key);
            value+=1;
            dataMap.put(key, value);
        }
        ArrayList<Date> sortedKeys = new ArrayList<>(dataMap.keySet());
        Collections.sort(sortedKeys);

        graph.addSeries(series);
        for(int i = 0; i < sortedKeys.size(); i++) {
            Date key = sortedKeys.get(i);
            DataPoint dataPoint = new DataPoint(key, (Double) dataMap.get(key));
            series.appendData(dataPoint, true, 60);
        }
        graph.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });
        series.setSpacing(25);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);


    }

    private void populateDataMap( HashMap<Date, Double> dataMap)
    {
        if(toDate == null)
        {
            toDate = new Date();
        }
        int diff = (int) Math.round((toDate.getTime() - fromDate.getTime()) / (double) 86400000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        for(int i = 0; i <= diff; i++)
        {
            Date key = calendar.getTime();
            dataMap.put(key, 0.0);
            calendar.add(Calendar.DATE, 1);
        }
    }
}