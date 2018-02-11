package com.kurtjlewis.knowyourself.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.kurtjlewis.knowyourself.DataRepository;
import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;
import com.kurtjlewis.knowyourself.model.Emotion;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddFeeling extends AppCompatActivity implements OnChartValueSelectedListener {
    final DataRepository repo = DataRepository.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeling);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();

        PieDataSet dataSet = new PieDataSet(yvalues, "Emotion");

        ArrayList<String> xVals = new ArrayList<String>();

        Emotion emotions[] = Emotion.values();
        int[] colors = new int[emotions.length];
        for(int i=0; i<emotions.length; i++){
            yvalues.add(new Entry(100/emotions.length, i));
            xVals.add(emotions[i].getLocalizedString(this));
            colors[i] = emotions[i].getColorRepresentation();
        }

        PieData data = new PieData(xVals, dataSet);

        dataSet.setDrawValues(false);
        pieChart.setData(data);

        pieChart.setDrawHoleEnabled(false);
        dataSet.setColors(colors);
        data.setValueTextSize(13f);


        pieChart.setDescription("");
        pieChart.getLegend().setEnabled(false);
        pieChart.setOnChartValueSelectedListener(this);
        pieChart.setRotationEnabled(false);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        final Integer index = e.getXIndex();
        FeelingEntity feeling = new FeelingEntity();
        final SeekBar seekBar = new SeekBar(this);
        seekBar.setMinimumWidth(500);
        seekBar.setMax(100);
        seekBar.setProgress(1);

        final AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setTitle("Select the Intensity of your Emotion");
        popup.setView(seekBar);


        ShapeDrawable thumb = new ShapeDrawable(new OvalShape());

        thumb.setIntrinsicHeight(80);
        thumb.setIntrinsicWidth(30);
        seekBar.setThumb(thumb);

        seekBar.setVisibility(View.VISIBLE);
        seekBar.setBackgroundColor(Color.WHITE);

        final TextView mDistance = new TextView(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
                mDistance.setText(Integer.toString(progress));
                //Get the thumb bound and get its left value
                int x = seekBar.getThumb().getBounds().left;
                //set the left value to textview x value
                mDistance.setX(x);
            }
        });

        popup.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context = AddFeeling.this;
                Intent intent  = new Intent(context, AddDetails.class);
                intent.putExtra("emotionIndex", index);
                intent.putExtra("intensity", seekBar.getProgress());
                context.startActivity(intent);
            }
        });
        popup.create();
        //popup.setView(mDistance);
        popup.show();
    }

    @Override
    public void onNothingSelected() {
       //Do nothing
    }

}

