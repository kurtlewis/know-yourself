package com.kurtjlewis.knowyourself.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Zach on 2/10/2018.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private Calendar today;

    public GridAdapter(Context context) {
        mContext = context;
        today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public int getCount() {
        //return MainActivity.getFeelingEntitiesByDate().size();
        return 98;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageView view = new ImageView(mContext);
        Calendar todayWithOffset = (Calendar)today.clone();
        todayWithOffset.add(Calendar.DAY_OF_YEAR, -position);
        List<FeelingEntity> feelingEntities = MainActivity.getFeelingEntitiesByDate().get(todayWithOffset);
        Drawable drawable;
        if (feelingEntities == null) {
            drawable = new ColorDrawable(Color.rgb(200, 200, 200));
        } else {
            FeelingEntity strongest = feelingEntities.get(0);
            for (FeelingEntity f : feelingEntities) {
                if (f.getIntensity() > strongest.getIntensity()) {
                    strongest = f;
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DayViewActivity.SetPosition(position);
                    Context context = view.getContext();
                    Intent intent  = new Intent(context, DayViewActivity.class);
                    context.startActivity(intent);
                }
            });
            int feelColor = strongest.getEmotion().getColorRepresentation();
            int red = Color.red(feelColor);
            int green = Color.green(feelColor);
            int blue = Color.blue(feelColor);
            int backgroundColor = Color.argb((255-50)/100*strongest.getIntensity(), red, green, blue);
            drawable = new ColorDrawable(strongest.getEmotion().getColorRepresentation());
        }

        drawable = new ScaleDrawable(drawable, 0, 70, 70).getDrawable();
        drawable.setBounds(0, 0, 50, 50);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, 70);
        view.setLayoutParams(layoutParams);
        view.setImageDrawable(drawable);

        return view;
    }

}
