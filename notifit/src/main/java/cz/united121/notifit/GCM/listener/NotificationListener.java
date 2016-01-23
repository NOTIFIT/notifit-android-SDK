package cz.united121.notifit.GCM.listener;

import android.os.Bundle;

/**
 * Interface which will be fired up when notification arrived
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {1/23/2016}
 **/
public interface NotificationListener {
	/**
	 * Called when message is received.
	 *
	 * @param from SenderID of the sender.
	 * @param data Data bundle containing message data as key/value pairs.
	 *             For Set of keys use data.keySet().
	 */
	public void showNotification(String from, Bundle data);
}
