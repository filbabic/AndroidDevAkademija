package ada.osc.taskie.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsUtil {
    public static final String TOKEN = "token";

    public static void storePreferencesField(Context context, String key,
                                              String value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = settings.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreferencesField(Context context, String key, String defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(key, defaultValue);
    }

    public static String getPreferencesField(Context context, String key) {
        return getPreferencesField(context, key, null);
    }
}
