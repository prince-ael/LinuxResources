package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    // Completed (7) Create a method called remindUserBecauseCharging which takes a Context.

    private static final int PENDING_INTENT_REQUEST_CODE = 458;
    private static final int NOTIFICATION_CODE = 299;

    private static final int[] FLAGS = {
            PendingIntent.FLAG_UPDATE_CURRENT
    };


    public static void remindUserBecauseCharging(Context context){

        NotificationCompat.Builder chargingReminder = new NotificationCompat.Builder(context);
        chargingReminder.setColor(ContextCompat.getColor(context , R.color.colorPrimary));
        chargingReminder.setSmallIcon(R.drawable.ic_drink_notification);
        chargingReminder.setLargeIcon(largeIcon(context));
        chargingReminder.setContentTitle(context.getString(R.string.charging_reminder_notification_title));
        chargingReminder.setContentText(context.getString(R.string.charging_reminder_notification_body));
        chargingReminder.setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)));
        chargingReminder.setDefaults(Notification.DEFAULT_VIBRATE);
        chargingReminder.setContentIntent(contentIntent(context));
        chargingReminder.setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            chargingReminder.setPriority(PRIORITY_HIGH);
        }

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_CODE , chargingReminder.build());

    }
    // This method will create a notification for charging. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
        // Completed (8) In the remindUser method use NotificationCompat.Builder to create a notification
        // that:
        // - has a color of R.colorPrimary - use ContextCompat.getColor to get a compatible color
        // - has ic_drink_notification as the small icon
        // - uses icon returned by the largeIcon helper method as the large icon
        // - sets the title to the charging_reminder_notification_title String resource
        // - sets the text to the charging_reminder_notification_body String resource
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
        // - sets the notification defaults to vibrate
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
        // - automatically cancels the notification when the notification is clicked
        // Completed (9) If the build version is greater than JELLY_BEAN, set the notification's priority
        // to PRIORITY_HIGH.
        // Completed (11) Get a NotificationManager, using context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Completed (12) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()



    // Completed (1) Create a helper method called contentIntent with a single parameter for a Context. It
    // should return a PendingIntent. This method will create the pending intent which will trigger when
    // the notification is pressed. This pending intent should open up the MainActivity.

    private static PendingIntent contentIntent(Context context){

        Intent openUpMainActivity = new Intent(context , MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context ,
                PENDING_INTENT_REQUEST_CODE ,
                openUpMainActivity,
                FLAGS[0]);

        return pendingIntent;

    }

        // Completed (2) Create an intent that opens up the MainActivity
        // Completed (3) Create a PendingIntent using getActivity that:
            // - Take the context passed in as a parameter
            // - Takes an unique integer ID for the pending intent (you can create a constant for
            //   this integer above
            // - Takes the intent to open the MainActivity you just created; this is what is triggered
            //   when the notification is triggered
            // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
            // intent but update the data


    // Completed (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
        // Completed (5) Get a Resources object from the context.
        // Completed (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px

    private static Bitmap largeIcon(Context context){
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources() , R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }


}
