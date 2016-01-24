## Usage

To use library you have to call Notifit.register(..) in activity's method OnCreate(). Method register your device to server and connect your **appToken** and **projectToken** with the device.

Signature is

   ```Java
   public static void register(java.lang.String appToken,
                            java.lang.String projectToken,
                            @NotNull android.content.Context context,
                            @Nullable cz.united121.notifit.GCM.listener.NotificationListener listener)
   ```
For example in your project (mainly in OnCreate function)

   ```Java
   public static int REQUEST_CODE = 1234;
   public static int ID_NOTIFICATION = 1235;
   
   ...
   
  Notifit.register("ee854de0-95ad-e511-9427-00155d000710", "5a77c122-099a-e511-9426-00155d000710", this, new NotificationListener() {
			@Override
			public void showNotification(String from, Bundle data) {

				String message = data.getString("message");
				String title = data.getString("title");

				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, REQUEST_CODE, intent,
						PendingIntent.FLAG_ONE_SHOT);

				Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
						.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
						.setContentTitle("LIB")
						.setContentText("LIB")
						.setAutoCancel(true)
						.setSound(defaultSoundUri)
						.setContentIntent(pendingIntent);

				NotificationManager notificationManager =
						(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				notificationManager.notify(ID_NOTIFICATION, notificationBuilder.build());

			}
	});
   ```

## Installation

We use jCenter. You need to add dependeciens to your gradle:
```
    compile 'com.github.notifit:notifit:1.0.2'
```

## Version

Version 1.0.2

## Author

Petr Lorenc, Lorenc55Petr@seznam.cz

## License

```
Copyright [2016] [Petr Lorenc]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
