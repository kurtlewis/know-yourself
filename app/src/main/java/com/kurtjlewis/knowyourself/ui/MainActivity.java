package com.kurtjlewis.knowyourself.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static List<FeelingEntity> feelingEntities;

    private static Map<Calendar, List<FeelingEntity>> feelingEntitiesByDate = new TreeMap<>(new Comparator<Calendar>(){
        @Override
        public int compare(Calendar d1, Calendar d2) {
            if (d1.get(Calendar.YEAR) < d2.get(Calendar.YEAR)) {
                return -1;
            } else if (d1.get(Calendar.YEAR) > d2.get(Calendar.YEAR)) {
                return 1;
            } else {
                if (d1.get(Calendar.DAY_OF_YEAR) < d2.get(Calendar.DAY_OF_YEAR)) {
                    return -1;
                } else if (d1.get(Calendar.DAY_OF_YEAR) > d2.get(Calendar.DAY_OF_YEAR)) {
                    return 1;
                } else {
                    return 0;
                }
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

        Calendar todayWithOffset = Calendar.getInstance();
        todayWithOffset.set(Calendar.HOUR_OF_DAY, 0);
        todayWithOffset.set(Calendar.MINUTE, 0);
        todayWithOffset.set(Calendar.SECOND, 0);
        todayWithOffset.set(Calendar.MILLISECOND, 0);
        todayWithOffset.add(Calendar.DAY_OF_YEAR, -98);

        feelingEntities = DataRepository
                .getInstance(this)
                .loadFeelingEntitiesAfterTimestamp(todayWithOffset);

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

        // Prepare Navigation Menu
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.main_navigation);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_hamburger);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        // handle home button to open navigation drawer
        if (id == android.R.id.home) {
            DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navigation_menu_notifications) {
            // Spawn navigation configuration activity
            Context context = getApplicationContext();
            Intent intent  = new Intent(this.getBaseContext(), NotificationActivity.class);
            context.startActivity(intent);
        }

        if (id == R.id.action_settings) {
            // Spawn settings menu
        }
        
        // close drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
        return true;
    }
}
