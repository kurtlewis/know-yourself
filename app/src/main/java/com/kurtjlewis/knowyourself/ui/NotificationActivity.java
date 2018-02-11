package com.kurtjlewis.knowyourself.ui;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.service.NotificationPublisher;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Listener for switches
        ((Switch)findViewById(R.id.morningSwitch)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.afternoonSwitch)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.eveningSwitch)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.nightSwitch)).setOnCheckedChangeListener(this);

        // Load switch states
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        ((Switch)findViewById(R.id.morningSwitch)).setChecked(sharedPref.getBoolean(getString(R.string.morning), false));
        ((Switch)findViewById(R.id.afternoonSwitch)).setChecked(sharedPref.getBoolean(getString(R.string.afternoon), false));
        ((Switch)findViewById(R.id.eveningSwitch)).setChecked(sharedPref.getBoolean(getString(R.string.evening), false));
        ((Switch)findViewById(R.id.nightSwitch)).setChecked(sharedPref.getBoolean(getString(R.string.night), false));

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.morning), ((Switch)findViewById(R.id.morningSwitch)).isChecked());
        editor.putBoolean(getString(R.string.afternoon), ((Switch)findViewById(R.id.afternoonSwitch)).isChecked());
        editor.putBoolean(getString(R.string.evening), ((Switch)findViewById(R.id.eveningSwitch)).isChecked());
        editor.putBoolean(getString(R.string.night), ((Switch)findViewById(R.id.nightSwitch)).isChecked());
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // return to previous activity
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        Calendar calendar = Calendar.getInstance();
        int requestCode = 0;
        switch (v.getId()){
            case R.id.morningSwitch:
                // TODO: For debugging purposes this triggers a notificiation in 1 minute
                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
                //calendar.set(Calendar.HOUR_OF_DAY, 1);
                //calendar.set(Calendar.MINUTE, 14);
                requestCode = 1;
                break;
            case R.id.afternoonSwitch:
                calendar.set(Calendar.HOUR_OF_DAY, 13);
                requestCode = 2;
                break;
            case R.id.eveningSwitch:
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                requestCode = 3;
                break;
            case R.id.nightSwitch:
                calendar.set(Calendar.HOUR_OF_DAY, 22);
                requestCode = 4;
                break;
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        if (isChecked) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }

    }
}
