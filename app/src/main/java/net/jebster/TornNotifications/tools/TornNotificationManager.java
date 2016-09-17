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

    public void notificationsCheck(SaveData prefs, TornUser current, TornUser last)
    {
        _cp = prefs; _cu = current;
        if (prefs.EnergyNotification() && current.getEnergy() == current.getMaximumEnergy() && current.getEnergy() != last.getEnergy())
            EnergyNotification();

        if (prefs.HappyNotification() && current.getHappy() == current.getMaximumHappy() && current.getHappy()!= last.getHappy())
            HappyNotification();

        if (prefs.NerveNotification() && current.getNerve() == current.getMaximumNerve()&& current.getNerve()!= last.getNerve())
            NerveNotification();

        if (prefs.TravelNotification() && current.getTravelTimeLeft() == 0 && current.getTravelTimeLeft()!= last.getTravelTimeLeft())
            TravelNotification();
    }


    public NotificationCompat.Builder basicNotificationBuilder()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("Torn")
                .setSmallIcon(R.drawable.notification_template_icon_bg);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine("Energy: " + _cu.getEnergy() + "/" + _cu.getMaximumEnergy())
                .addLine("Happy: " + _cu.getHappy() + "/" + _cu.getMaximumHappy())
                .addLine("Nerve: " + _cu.getNerve() + "/" + _cu.getMaximumNerve());
        if (_cu.getTravelTimeLeft() > 0)
            style.addLine("Travel time: " + _cu.getTravelTimeLeft()+ "s");

        PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 1,
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://torn.com/"))
                , PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.drawable.abc_text_cursor_material, "Open Torn.com", actionPendingIntent);

        builder.setStyle(style);

        if (_cp.Sound())
            builder.setSound(_cp.getSound());

        if (_cp.Vibrate())
            builder.setVibrate(new long[] { 0, 1000, 500 });

        if (_cp.Led()) {
            builder.setLights(Color.LTGRAY, 2000, 5000);
        }
        return builder;
    }

    public void finishNotification(NotificationCompat.Builder builder, int id)
    {
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notification);
    }

        /* -------------- Notification Types -------------- */

    public void EnergyNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You have " + _cu.getEnergy() + "/" + _cu.getMaximumEnergy() + " Energy!");

        finishNotification(builder, 0);
    }

    public void HappyNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You have " + _cu.getHappy() + "/" + _cu.getMaximumHappy()+ " Happy!");

        finishNotification(builder, 1);
    }

    public void NerveNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You have " + _cu.getNerve()+ "/" + _cu.getMaximumNerve() + " Nerve!");

        finishNotification(builder, 2);
    }

    public void TravelNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You landed in " + _cu.getTravelDestination());

        finishNotification(builder, 3);
    }

}
