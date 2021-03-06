package com.kurtjlewis.knowyourself.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kurtjlewis.knowyourself.DataRepository;
import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;
import com.kurtjlewis.knowyourself.model.Emotion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDetails extends AppCompatActivity {
    final DataRepository repo = DataRepository.getInstance(MainActivity.mcontext);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        final int emotionIndex = getIntent().getIntExtra("emotionIndex", -1);
        final int intensity = getIntent().getIntExtra("intensity", -1);
        final Emotion e = Emotion.values()[emotionIndex];


        final TextView feeling = (TextView) findViewById(R.id.Feeling);
        final EditText date = (EditText) findViewById(R.id.Date);
        final EditText time = (EditText) findViewById(R.id.Time);
        final EditText notes = (EditText) findViewById(R.id.Notes);
        final Button save = (Button) findViewById(R.id.save);
        final TextView intense = (TextView) findViewById(R.id.Intensity);

        intense.append(Integer.toString(intensity));

        int feelColor = e.getColorRepresentation();
        int red = Color.red(feelColor);
        int green = Color.green(feelColor);
        int blue = Color.blue(feelColor);
        int backgroundColor = Color.argb((255-50)/100*intensity, red, green, blue);

        feeling.setText(e.getLocalizedString(this));
        feeling.setBackgroundColor(backgroundColor);

        final Calendar cal = Calendar.getInstance();

        SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat hhmm = new SimpleDateFormat("hh:mm a");

        date.setText(mmddyyyy.format(cal.getTime()));
        time.setText(hhmm.format(cal.getTime()));

        final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date.setText(sdf.format(cal.getTime()));
            }

        };

        final TimePickerDialog.OnTimeSetListener timePicker = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // TODO Auto-generated method stub
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);

                String myFormat = "HH:mm";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                time.setText(sdf.format(cal.getTime()));
            }

        };

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddDetails.this, datePicker, cal
                        .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(AddDetails.this, timePicker, cal
                        .get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                        false).show();
            }
        });




        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FeelingEntity entity = new FeelingEntity(e, cal, intensity, notes.getText().toString());
                repo.insertFeelingEntity(entity);

                Intent i = new Intent(AddDetails.this, MainActivity.class);
                startActivityForResult(i, 1);
                AddDetails.this.finish();
            }
        });
    }
}
