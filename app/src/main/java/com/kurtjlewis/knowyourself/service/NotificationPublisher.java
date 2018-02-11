package com.kurtjlewis.knowyourself.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kurtjlewis.knowyourself.R;
import com.kurtjlewis.knowyourself.ui.AddFeeling;

/**
 * Created by kurt on 2/11/18.
 */

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This should happen in a service. If this receiver takes longer than 10 seconds (however unlikely)
        // to complete, it will cause the app to get in trouble with our OS overlords
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Input your feelings!");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);

        Intent addFeelingIntent = new Intent(context, AddFeeling.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, addFeelingIntent, 0);
        //builder.setContentIntent(new Intent(context, AddFeeling.class))
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        int id = 0;
        notificationManager.notify(id, notification);

    }
}
