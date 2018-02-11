package com.kurtjlewis.knowyourself.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kurtjlewis.knowyourself.R;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
