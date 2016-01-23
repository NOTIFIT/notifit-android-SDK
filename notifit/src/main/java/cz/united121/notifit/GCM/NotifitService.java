package cz.united121.notifit.GCM;

import android.os.Bundle;
import android.util.Log;

import cz.united121.notifit.BuildConfig;
import cz.united121.notifit.Notifit;

/**
 * Service which will just notify listener
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {1/23/2016}
 **/
public class NotifitService extends com.google.android.gms.gcm.GcmListenerService {
	public static final String TAG = NotifitService.class.getName();

	/**
	 * Called when message is received.
	 *
	 * @param from SenderID of the sender.
	 * @param data Data bundle containing message data as key/value pairs.
	 *             For Set of keys use data.keySet().
	 */
	@Override
	public void onMessageReceived(String from, Bundle data) {
		if(BuildConfig.DEBUG){
			Log.d(TAG, "onMessageReceived() called with: " + "from = [" + from + "], data = [" + data + "]");
		}

		Notifit.mListener.showNotification(from,data);
	}
}