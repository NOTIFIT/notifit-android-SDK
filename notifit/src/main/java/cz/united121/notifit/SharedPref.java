package cz.united121.notifit;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Utility class for hangling Shared Preferences (SP)
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {1/16/2016}
 **/
public class SharedPref {
	public static final String TAG = SharedPref.class.getName();

	public static final String TAG_APP_TOKEN = "TAG_APP_TOKEN";
	public static final String TAG_PROJECT_TOKEN = "TAG_PROJECT_TOKEN";
	public static final String TAG_MOBILE_TOKEN = "TAG_MOBILE_TOKEN";


	private static SharedPreferences getPref(Context context) {
		return context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
	}

	/**
	 * Save String to SP
	 *
	 * @param tag Tag is key in Key/Value
	 * @param value Value is value in Key/Value
	 * @param context Context
	 */
	public static void putString(String tag, String value, Context context) {
		SharedPreferences.Editor editor = getPref(context).edit();
		editor.putString(tag, value);
		editor.commit();
	}

	/**
	 * Get String from SP
	 *
	 * @param tag Tag is key in Key/Value
	 * @param context Context
	 * @return String found by tag or null otherwise
	 */
	public static String getString(String tag, Context context) {
		return getPref(context).getString(tag, null);
	}
}
