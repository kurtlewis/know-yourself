package com.kurtjlewis.knowyourself.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kurtjlewis.knowyourself.R;

import java.util.ArrayList;

public class AddFeeling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeling);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(12.5f, 0));
        yvalues.add(new Entry(12.5f, 1));
        yvalues.add(new Entry(12.5f, 2));
        yvalues.add(new Entry(12.5f, 3));
        yvalues.add(new Entry(12.5f, 4));
        yvalues.add(new Entry(12.5f, 5));
        yvalues.add(new Entry(12.5f, 6));
        yvalues.add(new Entry(12.5f, 7));
        PieDataSet dataSet = new PieDataSet(yvalues, "Emotion");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Angry");
        xVals.add("Stressed");
        xVals.add("Happy");
        xVals.add("Anxious");
        xVals.add("Sad");
        xVals.add("Excited");
        xVals.add("Depressed");
        xVals.add("Neutral");

        PieData data = new PieData(xVals, dataSet);

       // data.setValueFormatter(new PercentFormatter(null));

        pieChart.setData(data);

        pieChart.setDrawHoleEnabled(false);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(13f);

    }


}
