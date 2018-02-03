package org.hvdw.sofiaservernokill.fragments;

import org.hvdw.sofiaservernokill.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.content.Context;

public class PrefsFragment extends PreferenceFragment {

    public Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Line below should make it work according to 
        // https://github.com/rovo89/PlayStoreFixes/blob/master/src/de/robv/android/xposed/mods/playstorefix/SettingsActivity.java#L29
        getPreferenceManager().setSharedPreferencesMode(mContext.MODE_WORLD_READABLE);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
