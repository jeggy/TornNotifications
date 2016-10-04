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
import net.jebster.TornNotifications.model.Globals;
import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornNotificationManager {

    private final String TAG = "TornNotificationManager";

    private Context context;
    private SaveData _cp;

    public TornNotificationManager(Context context)
    {
        this.context = context;
    }

    public void notificationsCheck(SaveData prefs, TornUser current, TornUser last)
    {
        _cp = prefs;
        if (prefs.EnergyNotification() && current.getEnergy().getCurrent() == current.getEnergy().getMaximum() && current.getEnergy().getCurrent() != last.getEnergy().getCurrent())
            EnergyNotification();

        if (prefs.HappyNotification() && current.getHappy().getCurrent() == current.getHappy().getMaximum() && current.getHappy().getCurrent() != last.getHappy().getCurrent())
            HappyNotification();

        if (prefs.NerveNotification() && current.getNerve().getCurrent() == current.getNerve().getMaximum() && current.getNerve().getCurrent() != last.getNerve().getCurrent())
            NerveNotification();

        if (prefs.TravelNotification() && current.getTravel().getTime_left() == 0 && current.getTravel().getTime_left() != last.getTravel().getTime_left())
            TravelNotification();

        if (prefs.EventsNotification() && current.getNotifications().getEvents() > last.getNotifications().getEvents() && current.getNotifications().getEvents() > 0)
            EventNotification();

        // Drug
        if(prefs.CooldownNotification() && current.getCooldowns().getDrug() == 0 && current.getCooldowns().getDrug() != last.getCooldowns().getDrug())
            DrugNotification();
        // Medical
        if(prefs.CooldownNotification() && current.getCooldowns().getMedical() == 0 && current.getCooldowns().getMedical() != last.getCooldowns().getMedical())
            MedicalNotification();
        // Booster
        if(prefs.CooldownNotification() && current.getCooldowns().getBooster() == 0 && current.getCooldowns().getBooster() != last.getCooldowns().getBooster())
            BoosterNotification();

    }


    private NotificationCompat.Builder basicNotificationBuilder()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("Torn")
                .setSmallIcon(R.drawable.notification_template_icon_bg);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                .addLine("Energy: " + Globals.User().getEnergy().getCurrent() + "/" + Globals.User().getEnergy().getMaximum())
                .addLine("Happy: " + Globals.User().getHappy().getCurrent() + "/" + Globals.User().getHappy().getMaximum())
                .addLine("Nerve: " + Globals.User().getNerve().getCurrent() + "/" + Globals.User().getNerve().getMaximum());
        if (Globals.User().getTravel().getTime_left() > 0)
            style.addLine("Travel time: " + Globals.User().getTravel().getTime_left()+ "s");

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
        builder.setContentText("You have " + Globals.User().getEnergy().getCurrent() + "/" + Globals.User().getEnergy().getMaximum() + " Energy!");

        finishNotification(builder, 0);
    }

    public void HappyNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You have " + Globals.User().getHappy().getCurrent() + "/" + Globals.User().getHappy().getMaximum()+ " Happy!");

        finishNotification(builder, 1);
    }

    public void NerveNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You have " + Globals.User().getNerve().getCurrent()+ "/" + Globals.User().getNerve().getMaximum() + " Nerve!");

        finishNotification(builder, 2);
    }

    public void TravelNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You landed in " + Globals.User().getTravel().getDestination());

        finishNotification(builder, 3);
    }

    public void EventNotification()
    {
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("You received a new event");

        finishNotification(builder, 4);
    }

    public void DrugNotification(){
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("Your drug cooldown is finished");

        finishNotification(builder, 5);
    }

    public void MedicalNotification(){
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText("Your drug cooldown is finished");

        finishNotification(builder, 6);
    }

    public void BoosterNotification(){
        BasicNotification("Your drug cooldown is finished", 7);
    }


    public void BasicNotification(String text, int id){
        NotificationCompat.Builder builder = basicNotificationBuilder();
        builder.setContentText(text);

        finishNotification(builder, id);
    }


    public void showBasicNotification(String s) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.drawable.notification_template_icon_bg)
                        .setContentTitle("Torn Notifications")
                        .setContentText(s);
        finishNotification(mBuilder, 1);
    }
}
