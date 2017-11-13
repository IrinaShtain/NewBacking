package ua.shtain.irina.newbacking.presentation.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Date;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.data.model.ThemeItem;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public abstract class NotificationManager {

    public static void sendNotificationsStrategy(Context context, ThemeItem themeItem) {
        int notificationId = getNotificationId();
        PendingIntent pi = getPendingIntent(context, themeItem);

        Resources resources = context.getResources();
        String someLongText = context.getString(R.string.msg_new_theme, themeItem.getUser(), themeItem.getTitle());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker(resources.getString(R.string.msg_new_theme))
                .setContentTitle(resources.getString(R.string.msg_title_new_theme_pokerStrategy))
                .setContentText(someLongText)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);

        Notification notification = new NotificationCompat.BigTextStyle(builder)
                .bigText(someLongText).build();

        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
        Log.i("mLog", "!!!!!!sendNotifications!!!!!!!!!!!!!!!!!!!!!!!!!!!!Got a new result: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
    }

    public static void sendNotificationsGipsy(Context context, ThemeItem themeItem) {
        int notificationId = getNotificationId();
        PendingIntent pi = getPendingIntent(context, themeItem);

        Resources resources = context.getResources();
        String someLongText = context.getString(R.string.msg_new_theme, themeItem.getUser(), themeItem.getTitle());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker(resources.getString(R.string.msg_new_theme))
                .setContentTitle(resources.getString(R.string.msg_title_new_theme_gipsy))
                .setContentText(someLongText)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.sound));

        Notification notification = new NotificationCompat.BigTextStyle(builder)
                .bigText(someLongText).build();


        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
        Log.i("mLog", "!!!!!!sendNotifications!!!!!!!!!!!!!!!!!!!!!!!!!!!!Got a new result: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
    }

    private static int getNotificationId() {
        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        return Integer.valueOf(last4Str);
    }

    private static PendingIntent getPendingIntent(Context context, ThemeItem themeItem) {
        Uri address = Uri.parse(themeItem.getLink());
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        return PendingIntent.getActivity(context, 0, openlinkIntent, 0);
    }


}
