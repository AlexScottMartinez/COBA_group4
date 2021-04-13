package com.example.coba_group4.occurence;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.coba_group4.R;

import java.util.ArrayList;

public class ReportOccurrence extends AppCompatActivity implements View.OnClickListener {
    Spinner eventTypeDropdown;
    EditText address, date, time, description;
    OccurrenceEventType eventTypeEnum;
    final String eventTypeTitle = "Event Type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_occurence);
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
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cancel_report:

                finish();

                break;
            case R.id.add_report:

                String addressValue = address.getText().toString().trim();
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
                if(error)
                {
                    return;
                }
                finish();
        }
    }
}