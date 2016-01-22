package cz.united121.notifit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import cz.united121.notifit.GCM.RegistrationIntentService;

/**
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class Notifit {
	public static final String TAG = Notifit.class.getName();

	public static final String REGISTRATION_COMPLETE = "registrationComplete";
	public static String APP_TOKEN ;
	public static String PROJECT_TOKEN;


	public static void register(String appToken, String projectToken, Context context) {
		APP_TOKEN = appToken;
		PROJECT_TOKEN = projectToken;

		SharedPref.putString(SharedPref.TAG_APP_TOKEN,appToken,context);
		SharedPref.putString(SharedPref.TAG_PROJECT_TOKEN,projectToken,context);

		// Start IntentService to register this application with GCM.
		Intent intent = new Intent(context, RegistrationIntentService.class);
		context.startService(intent);
	}

}
