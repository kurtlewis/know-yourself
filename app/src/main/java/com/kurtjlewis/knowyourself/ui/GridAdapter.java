package com.kurtjlewis.knowyourself.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Zach on 2/10/2018.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;

    public GridAdapter(Context context) {
        mContext = context;
    }

    public int getCount() {
        return 28;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view = new ImageView(mContext);
        int width = view.getWidth();
        Drawable drawable = new ColorDrawable(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        drawable = new ScaleDrawable(drawable, 0, 50, 50).getDrawable();
        drawable.setBounds(0, 0, 50, 50);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
        view.setLayoutParams(layoutParams);
        view.setImageDrawable(drawable);

        return view;
    }

}
