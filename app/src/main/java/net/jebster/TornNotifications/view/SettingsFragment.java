package net.jebster.TornNotifications.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;

import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.service.TornBackgroundService;

/**
 * Created by jeggy on 9/22/16.
 */

public class SettingsFragment extends PreferenceFragment implements TornInfoUpdateInterface
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
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(preference.getContext());

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

    @Override
    public void tornUser(TornUser user) {

    }
}