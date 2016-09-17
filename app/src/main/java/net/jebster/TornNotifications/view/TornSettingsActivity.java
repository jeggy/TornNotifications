package net.jebster.TornNotifications.view;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.service.TornBackgroundService;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class TornSettingsActivity extends AppCompatActivity {

    public static final String PACKAGE_NAME = "net.jebster.tornnotifications";
    private static final String TAG = "TornSettingsActivity";

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_empty);


        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentTransaction.add(R.id.content, settingsFragment, "SETTINGS_FRAGMENT");
        fragmentTransaction.commit();

        Log.d(TAG, "StartBackgroundService");

        startService(new Intent(this, TornBackgroundService.class));
    }


    public static class SettingsFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_torn_notifications);
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    UpdatePreference(findPreference(key), key);
                    getActivity().startService(new Intent(getActivity(), TornBackgroundService.class));
                }
            });


        }

        @Override
        public void onResume()
        {
            super.onResume();
            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i)
            {
                Preference preference = getPreferenceScreen().getPreference(i);
                if (preference instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup = (PreferenceGroup)preference;
                    for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j)
                    {
                        Preference singlePref = preferenceGroup.getPreference(j);
                        UpdatePreference(singlePref, singlePref.getKey());
                    }
                } else {
                    UpdatePreference(preference, preference.getKey());
                }
            }
        }

        private void UpdatePreference(Preference preference, String key)
        {
            if (preference == null) return;
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            String text = getResources().getString(getResources().getIdentifier(key+"Summary", "string" , getActivity().getPackageName()));
            if (preference instanceof EditTextPreference)
            {
                EditTextPreference editTextPref = (EditTextPreference)preference;
                preference.setSummary(text + " : " + sharedPrefs.getString(key, editTextPref.getText()));
            }

            if(preference instanceof RingtonePreference)
            {
                RingtonePreference ringtonePref = (RingtonePreference)preference;
                Uri testa = Uri.parse(sharedPrefs.getString("NotificationSound", "default ringtone"));
                preference.setSummary(text + " : " + testa);
            }
        }
    }


}
