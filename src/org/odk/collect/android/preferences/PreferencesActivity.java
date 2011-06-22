/*
/*
 * Copyright (C) 201 University of Washington
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.preferences;

import org.odk.collect.android.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

/**
 * @author yanokwaa, modified by Sam Ritchie
 */
public class PreferencesActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    protected static final int IMAGE_CHOOSER = 0;

    public static String KEY_LAST_VERSION = "lastVersion";
    public static String KEY_FIRST_RUN = "firstRun";
    public static String KEY_SHOW_SPLASH = "showSplash";
    public static String KEY_SPLASH_PATH = "splashPath";
    public static String KEY_FONT_SIZE = "font_size";

    public static String KEY_SERVER_URL = "server_url";
    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";

    public static String KEY_PROTOCOL = "protocol";
    public static String KEY_FORMLIST_URL = "formlist_url";
    public static String KEY_SUBMISSION_URL = "submission_url";

    private EditTextPreference mUsernamePreference;
    private EditTextPreference mPasswordPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setTitle(getString(R.string.app_name) + " > " + getString(R.string.general_preferences));
        updateUsername();
        updatePassword();
        updateFontSize();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
            this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_CANCELED) {
            // request was canceled, so do nothing
            return;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	if (key.equals(KEY_USERNAME)) {
            updateUsername();
        } else if (key.equals(KEY_PASSWORD)) {
            updatePassword();
        } else if (key.equals(KEY_FONT_SIZE)) {
            updateFontSize();
        }
    }

    private void updateUsername() {
        mUsernamePreference = (EditTextPreference) findPreference(KEY_USERNAME);
        mUsernamePreference.setSummary(mUsernamePreference.getText());
    }


    private void updatePassword() {
        mPasswordPreference = (EditTextPreference) findPreference(KEY_PASSWORD);
        mPasswordPreference.setSummary("***************");
    }


    private void updateFontSize() {
        ListPreference lp = (ListPreference) findPreference(KEY_FONT_SIZE);
        lp.setSummary(lp.getEntry());
    }
}