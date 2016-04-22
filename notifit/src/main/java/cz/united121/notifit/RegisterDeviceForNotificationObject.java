package cz.united121.notifit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import cz.united121.notifit.Network.IJson;

/**
 * Send to server
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/16/2015}
 **/
public class RegisterDeviceForNotificationObject implements Serializable, IJson {
	public static final String TAG = RegisterDeviceForNotificationObject.class.getName();

	public String DeviceToken;
	public String DeviceTokenName = "NotificationToken";

	public String DeviceApiLevel;
	public String DeviceApiLevelName = "DeviceApiLevel";

	public String DeviceLanguage;
	public String DeviceLanguageName = "DeviceLanguage";

	public String DeviceModel;
	public String DeviceModelName = "DeviceModel";

	public String DeviceOperatingSystemVersion;
	public String DeviceOperatingSystemVersionName = "DeviceOperatingSystemVersion";

	public String DeviceProduct; //Locale.getDefault().getDisplayLanguage();
	public String DeviceProductName = "DeviceProduct";

	/**
	 * Object to be map on JSONObject
	 * @param deviceToken Device token - from device
	 * @param apiLevel apiLevel - from device
	 * @param device device - from device
	 * @param model model - from device
	 * @param product product - from device
	 * @param language language - from device
	 */
	public RegisterDeviceForNotificationObject(String deviceToken, String apiLevel, String device, String model, String product, String language) {
		DeviceToken = deviceToken;
		DeviceApiLevel = apiLevel;
		DeviceOperatingSystemVersion = device;
		DeviceModel = model;
		DeviceProduct = product;
		DeviceLanguage = language;
	}

	public JSONObject getJson(){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(DeviceTokenName,DeviceToken);
			jsonObject.put(DeviceApiLevelName,DeviceApiLevel);
			jsonObject.put(DeviceOperatingSystemVersionName,DeviceOperatingSystemVersion);
			jsonObject.put(DeviceModelName,DeviceModel);
			jsonObject.put(DeviceProductName,DeviceProduct);
			jsonObject.put(DeviceLanguageName,DeviceLanguage);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return  jsonObject;
	}
}
