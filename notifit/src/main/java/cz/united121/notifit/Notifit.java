package cz.united121.notifit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import cz.united121.notifit.GCM.RegistrationIntentService;
import cz.united121.notifit.GCM.listener.NotificationListener;

/**
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class Notifit {
	public static final String TAG = Notifit.class.getName();

	public static final String REGISTRATION_COMPLETE = "registrationComplete";
	public static String APP_TOKEN ;
	public static String PROJECT_TOKEN;

	public static  NotificationListener mListener = null;

	/**
	 * Register this device to Notifit server. You can get your AppToken and ProjectToken on the website under your Project site.
	 * Or you can follow tutorial on the website.
	 * @param appToken AppToken - from website
	 * @param projectToken ProjectToken - from website
	 * @param context Content
	 * @param listener NotificationListener to handle incoming push notification
	 */
	public static void register(String appToken, String projectToken, Context context, NotificationListener listener) {
		APP_TOKEN = appToken;
		PROJECT_TOKEN = projectToken;

		SharedPref.putString(SharedPref.TAG_APP_TOKEN,appToken,context);
		SharedPref.putString(SharedPref.TAG_PROJECT_TOKEN,projectToken,context);

		mListener = listener;

		// Start IntentService to register this application with GCM.
		Intent intent = new Intent(context, RegistrationIntentService.class);
		context.startService(intent);
	}

}
