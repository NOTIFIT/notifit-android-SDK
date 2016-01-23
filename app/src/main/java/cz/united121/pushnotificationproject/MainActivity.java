package cz.united121.pushnotificationproject;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import cz.united121.notifit.GCM.listener.NotificationListener;
import cz.united121.notifit.Notifit;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getName();
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
		setContentView(R.layout.activity_main);

		if (checkPlayServices()) {
			Log.d(TAG, "checkPlayServices=" + true);
			Notifit.register("ee854de0-95ad-e511-9427-00155d000710", "5a77c122-099a-e511-9426-00155d000710", this, new NotificationListener() {
				@Override
				public void showNotification(String from, Bundle data) {
					Log.d(TAG, "showNotification");

					String message = data.getString("message");
					String title = data.getString("title");

					Log.d(TAG, "showNotification() called with: " + "message = [" + message + "]");
					Intent intent = new Intent(MainActivity.this, MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0 /* Request code */, intent,
							PendingIntent.FLAG_ONE_SHOT);

					Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
					NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
							.setSmallIcon(cz.united121.notifit.R.drawable.common_google_signin_btn_icon_dark)
							.setContentTitle(title + " LIB")
							.setContentText(message + " LIB")
							.setAutoCancel(true)
							.setSound(defaultSoundUri)
							.setContentIntent(pendingIntent);

					NotificationManager notificationManager =
							(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

					notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

				}
			});
		}else{
			Log.d(TAG, "checkPlayServices=" + false);
		}
	}

	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	private boolean checkPlayServices() {
		GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
		int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (apiAvailability.isUserResolvableError(resultCode)) {
				apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
						.show();
			} else {
				Log.d(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}
}
