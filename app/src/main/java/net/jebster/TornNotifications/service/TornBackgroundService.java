package net.jebster.TornNotifications.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import net.jebster.TornNotifications.model.Globals;
import net.jebster.TornNotifications.tools.Preferences;
import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

import java.util.ArrayList;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornBackgroundService extends Service{
    public static final String TAG = "TornBackgroundService";

    public boolean Running;

    //public static TornUser CurrentTornUser;
    private TornUser _lastTornData;
    private SaveData _preferences;

    private TornNotificationManager TornNotificationManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate");
        TornNotificationManager = new TornNotificationManager(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        boolean startLoop = _preferences == null;

        int tmpUpdateSecs = _preferences == null ? -1 : _preferences.getUpdateSecs();

        LoadPreferences();

        if (startLoop)
        {
            StartChecking();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    public void LoadPreferences()
    {
        _preferences = Preferences.LoadData(this);
    }

    public void StartChecking()
    {
        Running = true;
        new Thread(){
            @Override
            public void run() {
                try {
                    while (Running) {
                        Globals.User(TornApiService.getUserData(_preferences.getApiKey(), getRequiredFields()));
                        tornDataToActivity(Globals.User());
                        if (Globals.User().getErrorText() != null && Globals.User().getErrorText().length() > 0) {
                            Log.d(TAG, Globals.User().getErrorText());
                            // TODO: Possible errors: Torn Api Errors, No Internet Connection.
                            // Maybe show in a notification?
                            Thread.sleep(_preferences.getUpdateSecs()* 1000);
                            continue;
                        }


                        if (_lastTornData == null) {
                            Log.d(TAG, "First time loaded");
                            _lastTornData = Globals.User();
                            Thread.sleep(_preferences.getUpdateSecs() * 1000);
                            continue;
                        }

                        TornNotificationManager.notificationsCheck(_preferences, Globals.User(), _lastTornData);

                        _lastTornData = Globals.User();
                        Thread.sleep(_preferences.getUpdateSecs() * 1000);

                    }
                }
                catch (InterruptedException e){
                    Log.d(TAG, "InterruptedException in StartChecking Thread: " + e.getMessage());
                    Running = false;
                }
            }
        }.start();
    }

    private String[] getRequiredFields() {

        ArrayList<String> list = new ArrayList<>();
        list.add(TornApiService.BASIC); // TODO: Maybe not this one?

        if(_preferences.HappyNotification() || _preferences.EnergyNotification() || _preferences.NerveNotification())
            list.add(TornApiService.BARS);

        if(_preferences.TravelNotification())
            list.add(TornApiService.TRAVEL);

        if(_preferences.EventsNotification())
            list.add(TornApiService.NOTIFICATIONS);

        if(_preferences.CoolDownNotification)
            list.add(TornApiService.COOLDOWNS);

        return list.toArray(new String[]{});
    }

    // Broadcast user to activity via intent
    private void tornDataToActivity(TornUser data) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
                new Intent(Globals.INTENT_FILTER_TORN_USER)
                .putExtra(Globals.EXTRA_TORN_USER, data)
        );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
