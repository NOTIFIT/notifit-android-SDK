package cz.united121.notifit.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONObject;

import java.util.Locale;

import cz.united121.notifit.Network.PostNetworkTask;
import cz.united121.notifit.Notifit;
import cz.united121.notifit.R;
import cz.united121.notifit.RegisterDeviceForNotificationObject;
import cz.united121.notifit.RegisterPushObject;
import cz.united121.notifit.SharedPref;

/**
 * Register device to the server and send him all necessary information to filter device on server side
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {11/11/2015}
 **/
public class RegistrationIntentService extends IntentService {
	public static final String TAG = RegistrationIntentService.class.getName();

	public RegistrationIntentService() {
		super(TAG);
	}

	public RegistrationIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent() called with: " + "intent = [" + intent + "]");

		try {

			InstanceID instanceID = InstanceID.getInstance(this);
			String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
					GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
			Log.d(TAG, "GCM Registration Token: " + token);

			savedRegistrationToSP(token);
			SharedPreferences myPrefs = getSharedPreferences("NOTIFIT", MODE_PRIVATE);
			String communicationToken = myPrefs.getString("CommunicationToken", null);
			if(communicationToken == null) {
				sendRegistrationToServer();
			}
			sendDeviceUpdateToServer(token, communicationToken);

		} catch (Exception e) {
			Log.d(TAG, "Failed to complete token refresh", e);
		}
	}

	private void savedRegistrationToSP(String token) {
		SharedPref.putString(SharedPref.TAG_MOBILE_TOKEN, token, this);
	}

	/**
	 * Send token and necessary data to server with additional information
	 */
	private void sendRegistrationToServer() {
		RegisterPushObject registerPushObject = new RegisterPushObject(
				Notifit.PROJECT_TOKEN,
				Notifit.APP_TOKEN,
				Build.VERSION.SDK_INT + "",
				Build.DEVICE,
				Build.MODEL,
				Build.PRODUCT,
				Locale.getDefault().getDisplayLanguage()
		);

		JSONObject result = new PostNetworkTask<RegisterPushObject>(registerPushObject, "http://notifit.io/api/DeviceAndroid", "POST", null).Send();
		if (result != null) {
			try{
			String communicationToken =  result.getString("CommunicationToken");
				SharedPreferences myPrefs = getSharedPreferences("NOTIFIT", MODE_PRIVATE);
				SharedPreferences.Editor e = myPrefs.edit();
				e.putString("CommunicationToken", communicationToken);
				e.commit();
			} catch (Exception e) {
				return;
			}
		}
	}

	/**
	 * Send token and necessary data to server with additional information
	 * @param token The new token.
	 */
	private void sendDeviceUpdateToServer(String token, String communicationToken) {
		RegisterDeviceForNotificationObject updatePushObject = new RegisterDeviceForNotificationObject(
				token,
				Build.VERSION.SDK_INT + "",
				Build.DEVICE,
				Build.MODEL,
				Build.PRODUCT,
				Locale.getDefault().getDisplayLanguage()
		);

		JSONObject result = new PostNetworkTask<RegisterDeviceForNotificationObject>(updatePushObject, "http://notifit.io/api/DeviceAndroid", "PUT", communicationToken).Send();

	}
}
