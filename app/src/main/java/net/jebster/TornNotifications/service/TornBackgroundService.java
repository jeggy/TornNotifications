package net.jebster.TornNotifications.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import net.jebster.TornNotifications.tools.Preferences;
import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.tools.TornApiService;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.view.TornNotificationManager;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornBackgroundService extends Service{
    public static final String TAG = "TornBackgroundService";

    public boolean Running = true;

    private TornUser _currentTornData;
    private TornUser _lastTornData;
    private SaveData _preferences;

    private net.jebster.TornNotifications.view.TornNotificationManager TornNotificationManager;

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
        new Thread(){
            @Override
            public void run() {
                try {
                    while (Running) {
                        _currentTornData = TornApiService.getUserData(_preferences.apiKey);
                        if (_currentTornData.errorText != null && _currentTornData.errorText.length() > 0) {
                            Log.d(TAG, _currentTornData.errorText);
                            // TODO: Possible errors: Torn Api Errors, No Internet Connection.
                            // Maybe show in a notification?
                            Thread.sleep(_preferences.updateSecs * 1000);
                            continue;
                        }


                        if (_lastTornData == null) {
                            Log.d(TAG, "First time loaded");
                            _lastTornData = _currentTornData;
                            Thread.sleep(_preferences.updateSecs * 1000);
                            continue;
                        }

                        TornNotificationManager.NotificationsCheck(_preferences, _currentTornData, _lastTornData);

                        _lastTornData = _currentTornData;
                        Thread.sleep(_preferences.updateSecs * 1000);
                    }
                }
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
