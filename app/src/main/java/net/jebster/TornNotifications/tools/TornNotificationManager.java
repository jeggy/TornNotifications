package net.jebster.TornNotifications.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornNotificationManager {

    private final String TAG = "TornNotificationManager";

    private Context context;
    private SaveData _cp;
    private TornUser _cu;

    public TornNotificationManager(Context context)
    {
        this.context = context;
    }

    public void NotificationsCheck(SaveData prefs, TornUser current, TornUser last)
    {
        _cp = prefs; _cu = current;
        if (prefs.energyNotification && current.energy == current.maximumEnergy && current.energy != last.energy)
            EnergyNotification(current.energy, current.maximumEnergy);

        if (prefs.happyNotification && current.happy == current.maximumHappy && current.happy != last.happy)
            HappyNotification(current.happy, current.maximumHappy);

        if (prefs.nerveNotification && current.nerve == current.maximumNerve && current.nerve != last.nerve)
            NerveNotification(current.nerve, current.maximumNerve);

        if (prefs.travelNotification && current.travelTimeLeft == 0 && current.travelTimeLeft != last.travelTimeLeft)
            TravelNotification(current.travelDestination);
    }


    private NotificationCompat.Builder BasicNotificationBuilder()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("Torn")
                .setSmallIcon(R.drawable.notification_template_icon_bg);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine("Energy: " + _cu.energy + "/" + _cu.maximumEnergy)
                .addLine("Happy: " + _cu.happy + "/" + _cu.maximumHappy)
                .addLine("Nerve: " + _cu.nerve + "/" + _cu.maximumNerve);
        if (_cu.travelTimeLeft > 0)
            style.addLine("Travel time: " + _cu.travelTimeLeft + "s");

        PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 1,
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://torn.com/"))
                , PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.drawable.abc_text_cursor_material, "Open Torn.com", actionPendingIntent);

        builder.setStyle(style);

        if (_cp.sound)
            builder.setSound(_cp.getSound());

        if (_cp.vibrate)
            builder.setVibrate(new long[] { 0, 1000, 500 });

        if (_cp.led) {
            builder.setLights(Color.LTGRAY, 2000, 5000);
        }
        return builder;
    }

    public void FinishNotification(NotificationCompat.Builder builder, int id)
    {
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notification);
    }

        /* -------------- Notification Types -------------- */

    public void EnergyNotification(int current, int max)
    {
        NotificationCompat.Builder builder = BasicNotificationBuilder();
        builder.setContentText("You have " + current + "/" + max + " Energy!");

        FinishNotification(builder, 0);
    }

    public void HappyNotification(int current, int max)
    {
        NotificationCompat.Builder builder = BasicNotificationBuilder();
        builder.setContentText("You have " + current + "/" + max + " Happy!");

        FinishNotification(builder, 1);
    }

    public void NerveNotification(int current, int max)
    {
        NotificationCompat.Builder builder = BasicNotificationBuilder();
        builder.setContentText("You have " + current + "/" + max + " Nerve!");

        FinishNotification(builder, 2);
    }

    public void TravelNotification(String country)
    {
        NotificationCompat.Builder builder = BasicNotificationBuilder();
        builder.setContentText("You landed in " + country);

        FinishNotification(builder, 3);
    }

}
