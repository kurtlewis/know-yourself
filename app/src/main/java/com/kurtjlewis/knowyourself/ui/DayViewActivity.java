package com.kurtjlewis.knowyourself.ui;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DayViewActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static Calendar today;

    public static int sel_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_view, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Calendar todayWithOffset = (Calendar) today.clone();
            todayWithOffset.add(Calendar.DAY_OF_YEAR, -getArguments().getInt(ARG_SECTION_NUMBER));
            LinearLayout masterLayout = new LinearLayout(inflater.getContext());

            masterLayout.setOrientation(LinearLayout.VERTICAL);
            TextView tv = new TextView(inflater.getContext());
            tv.setText("Hello");
            masterLayout.addView(tv);


            List<FeelingEntity> feelingEntities = MainActivity.getFeelingEntitiesByDate().get(todayWithOffset);
            for (FeelingEntity f : feelingEntities) {
                RelativeLayout rl = new RelativeLayout(inflater.getContext());
                TextView feelingText = new TextView(inflater.getContext());
                feelingText.setText(f.getEmotion().toString());
                feelingText.setTextSize(30);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                feelingText.setLayoutParams(lp);
                rl.addView(feelingText);
                masterLayout.addView(rl);

                /*TextView timeTitle = new TextView(inflater.getContext());
                timeTitle.setText("Timestamp");
                masterLayout.addView(timeTitle);

                TextView timeText = new TextView(inflater.getContext());
                timeText.setText(todayWithOffset.toString());
                masterLayout.addView(timeText);*/

                TextView intensityTitle = new TextView(inflater.getContext());
                intensityTitle.setText("Intensity");
                intensityTitle.setTextSize(25);
                masterLayout.addView(intensityTitle);

                TextView intensityText = new TextView(inflater.getContext());
                intensityText.setText(new Integer(f.getIntensity()).toString());
                intensityText.setTextSize(20);
                intensityText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cpv_alpha, 0, 0, 0);
                intensityText.setCompoundDrawablePadding(10);
                masterLayout.addView(intensityText);

                TextView descriptionTitle = new TextView(inflater.getContext());
                descriptionTitle.setText("Notes");
                descriptionTitle.setTextSize(25);
                masterLayout.addView(descriptionTitle);

                TextView descriptionText = new TextView(inflater.getContext());
                descriptionText.setText(f.getNotes());
                descriptionText.setTextSize(20);
                descriptionText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cpv_alpha, 0, 0, 0);
                descriptionText.setCompoundDrawablePadding(10);
                masterLayout.addView(descriptionText);

                TextView blankLine = new TextView(inflater.getContext());
                blankLine.setText("");
                blankLine.setTextSize(20);
                masterLayout.addView(blankLine);

                TextView blankLine2 = new TextView(inflater.getContext());
                blankLine2.setText("");
                blankLine2.setTextSize(20);
                masterLayout.addView(blankLine2);
            }

            return masterLayout;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            sel_position += 1;
            return PlaceholderFragment.newInstance(sel_position - 1);
        }

        @Override
        public int getCount() {
            return 98;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar todayWithOffset = (Calendar) today.clone();
            todayWithOffset.add(Calendar.DAY_OF_YEAR, -position);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            return "Hello" /*format1.format(todayWithOffset)*/;
        }
    }
}
