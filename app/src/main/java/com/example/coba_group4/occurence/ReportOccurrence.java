package com.example.coba_group4.occurence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.TimePicker;

import com.example.coba_group4.MainActivity;
import com.example.coba_group4.R;
import com.example.coba_group4.database.OccurrenceDB;
import com.example.coba_group4.fragment.DatePickerFragment;
import com.example.coba_group4.fragment.TimePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportOccurrence extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{

    //Initialize variable
    private DatePicker dtPicker;
    private TimePicker tmPicker;
    private DrawerLayout drawerLayout;
    private Spinner eventTypeDropdown;
    private EditText address, city, state, zipCode, date, time, description;
    private TextView errorText;
    private OccurrenceEventType eventTypeEnum;
    private Button datePickerBtn, timePickerBtn;
    private final String eventTypeTitle = "Event Type";
    private String timeString;
    private OccurrenceDB occurrenceDB = new OccurrenceDB();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_occurrence);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        eventTypeDropdown = findViewById(R.id.event_type_dropdown);
        ArrayList<String> types = new ArrayList<>();
        types.add(eventTypeTitle);
        types.addAll(eventTypeEnum.names());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportOccurrence.this, android.R.layout.simple_spinner_dropdown_item, types);
        eventTypeDropdown.setAdapter(adapter);

        address = findViewById(R.id.report_address);
        date = findViewById(R.id.report_date);
        time = findViewById(R.id.report_time);
        description = findViewById(R.id.report_description);
        city = findViewById(R.id.report_city);
        state = findViewById(R.id.report_state);
        zipCode = findViewById(R.id.report_zip);
        errorText = findViewById(R.id.report_error_text);

        datePickerBtn = (Button) findViewById(R.id.report_date_picker);
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        timePickerBtn = findViewById(R.id.report_time_picker);
        timePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cancel_report:
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                break;
            case R.id.add_report:

                String addressValue = address.getText().toString().trim();
                String cityValue = city.getText().toString().trim();
                String stateValue = state.getText().toString().trim();
                String zipValue = zipCode.getText().toString().trim();
                String dateValue = date.getText().toString().trim();
                String timeValue = time.getText().toString().trim();
                String descriptionValue = description.getText().toString().trim();
                String eventTypeValue = (String) eventTypeDropdown.getSelectedItem();
                boolean error = false;

                if(TextUtils.isEmpty(addressValue))
                {
                    address.setError("Address is required");
                    error = true;
                }
                if(TextUtils.isEmpty(cityValue))
                {
                    city.setError("City is required");
                    error = true;
                }
                if(TextUtils.isEmpty(stateValue))
                {
                    state.setError("Address is required");
                    error = true;
                }
                if(TextUtils.isEmpty(zipValue))
                {
                    zipCode.setError("Zip Code is required");
                    error = true;
                }
                if(TextUtils.isEmpty(dateValue))
                {
                    date.setError("Date is required");
                    error = true;
                }
                if(TextUtils.isEmpty(timeValue))
                {
                    time.setError("Time is required");
                    error = true;
                }
                if(TextUtils.isEmpty(descriptionValue))
                {
                    description.setError("Description is required");
                    error = true;
                }
                if(eventTypeTitle.equals(eventTypeValue))
                {
                    ((TextView)eventTypeDropdown.getSelectedView()).setError("Event Type is required");
                    error = true;
                }
                else
                {
                    ((TextView)eventTypeDropdown.getSelectedView()).setError(null);
                }

                Date parsedDate = null;
                if(!dateValue.equals("") && !timeValue.equals("")) {
                    try {
                        parsedDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(dateValue + " " + timeString);
                    } catch (ParseException e) {
                        date.setError("Date can not be determined.  Please try again.");
                        error = true;
                    }
                }
                if(error)
                {
                    return;
                }

                Occurrence occurrence = new Occurrence(addressValue, cityValue, stateValue, Integer.parseInt(zipValue), eventTypeValue, parsedDate, descriptionValue);
                Boolean added = occurrenceDB.addOne(occurrence);
                if(!added)
                {
                    errorText.setText("Error occurred while trying to add Occurrence.");
                    return;
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText((month+1)+"/"+dayOfMonth+"/"+year);
        date.setError(null);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String end = "AM";
        String min = minute < 10? "0"+minute: minute+"";
        timeString = hourOfDay+":"+min;
        if(hourOfDay > 12)
        {
            hourOfDay -= 12;
            end = "PM";
        }
        time.setText(hourOfDay+":"+min+end);
        time.setError(null);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}