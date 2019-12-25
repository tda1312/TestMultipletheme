package vn.edu.usth.settingstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.IntentCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.key_switch_theme), false)) {
            setTheme(R.style.AppThemeDark);
            Log.i("ABCDE", "changing");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getDelegate().applyDayNight();
        }

        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            Preference.OnPreferenceChangeListener preferenceChangeListenerlistener = new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    //if (preference.getKey().equals(getString(R.string.key_switch_theme))) {
                        getActivity().recreate();
                    //}
                    return true;
                }
            };

            SharedPreferences.OnSharedPreferenceChangeListener mlistener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    Log.d("ABCDE", "String: " + s);
                    if (!s.equals(getString(R.string.key_switch_theme))){
                        Log.v("ABCDE", "yo!");
                        return;
                    }

                    Log.d("ABCDE", "hehe");
                    getActivity().recreate();

//                    Log.v("ABCDE", "I got here yay");
//                    getActivity().finish();
//                    final Intent intent = getActivity().getIntent();
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    getActivity().startActivity(intent);
                }
            };
        }
    }
}
