package com.kurtjlewis.knowyourself.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.kurtjlewis.knowyourself.DataRepository;
import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.db.DataGenerator;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private static List<FeelingEntity> feelingEntities;

    private static Map<Calendar, List<FeelingEntity>> feelingEntitiesByDate = new TreeMap<>(new Comparator<Calendar>(){
        @Override
        public int compare(Calendar d1, Calendar d2) {
            d1.set(Calendar.HOUR_OF_DAY, 0);
            d1.set(Calendar.MINUTE, 0);
            d1.set(Calendar.SECOND, 0);
            d1.set(Calendar.MILLISECOND, 0);
            d2.set(Calendar.HOUR_OF_DAY, 0);
            d2.set(Calendar.MINUTE, 0);
            d2.set(Calendar.SECOND, 0);
            d2.set(Calendar.MILLISECOND, 0);

            if (d1.getTimeInMillis() < d2.getTimeInMillis()) {
                return -1;
            } else if (d1.getTimeInMillis() > d2.getTimeInMillis()) {
                return 1;
            } else {
                return 0;
            }
        }
    });

    public static Map<Calendar, List<FeelingEntity>> getFeelingEntitiesByDate() {
        return feelingEntitiesByDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*List<FeelingEntity> genFeelings = DataGenerator.generateFeelingEntityList(40);
        for (FeelingEntity f : genFeelings) {
            DataRepository.getInstance(this).insertFeelingEntity(f);
        }*/

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        today.add(Calendar.DAY_OF_YEAR, -98);

        feelingEntities = DataRepository
                .getInstance(this)
                .loadFeelingEntitiesAfterTimestamp(today);

        for (FeelingEntity f : feelingEntities) {
            List<FeelingEntity> l = feelingEntitiesByDate.get(f.getTimestamp());
            if (l == null) {
                l = new ArrayList<>();
                feelingEntitiesByDate.put(f.getTimestamp(), l);
            }
            l.add(f);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent  = new Intent(context, AddFeeling.class);
                context.startActivity(intent);
            }
        });

        GridView gridView = findViewById(R.id.historyGrid);
        gridView.setAdapter(new GridAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
