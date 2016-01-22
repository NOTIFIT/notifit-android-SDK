package cz.united121.notifit.GCM;

import android.content.Intent;
import android.util.Log;

/**
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {11/11/2015}
 **/
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService {
	public static final String TAG = InstanceIDListenerService.class.getName();

	/**
	 * Called if InstanceID token is updated. This may occur if the security of
	 * the previous token had been compromised. This call is initiated by the
	 * InstanceID provider.
	 */
	@Override
	public void onTokenRefresh() {
		Log.d(TAG, "onTokenRefresh() called");
		// Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
		Intent intent = new Intent(this, RegistrationIntentService.class);
		startService(intent);
	}
}
