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
import net.jebster.TornNotifications.tools.TornApiService;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornBackgroundService extends Service{
    public static final String TAG = "TornBackgroundService";

    public boolean Running;

    private TornUser _currentTornData;
    private TornUser _lastTornData;
    private SaveData _preferences;

    private net.jebster.TornNotifications.tools.TornNotificationManager TornNotificationManager;

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
                        _currentTornData = TornApiService.getUserData(_preferences.getApiKey());
                        tornDataToActivity(_currentTornData);
                        if (_currentTornData.getErrorText() != null && _currentTornData.getErrorText().length() > 0) {
                            Log.d(TAG, _currentTornData.getErrorText());
                            // TODO: Possible errors: Torn Api Errors, No Internet Connection.
                            // Maybe show in a notification?
                            Thread.sleep(_preferences.getUpdateSecs()* 1000);
                            continue;
                        }


                        if (_lastTornData == null) {
                            Log.d(TAG, "First time loaded");
                            _lastTornData = _currentTornData;
                            Thread.sleep(_preferences.getUpdateSecs() * 1000);
                            continue;
                        }

                        TornNotificationManager.notificationsCheck(_preferences, _currentTornData, _lastTornData);

                        _lastTornData = _currentTornData;
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
