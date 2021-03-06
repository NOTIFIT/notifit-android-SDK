package cz.united121.notifit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import cz.united121.notifit.Network.IJson;

/**
 * Send to server
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/16/2015}
 **/
public class RegisterPushObject implements Serializable, IJson {
	public static final String TAG = RegisterPushObject.class.getName();

	public String ProjectToken;
	public String ProjectTokenName = "ProjectToken";

	public String AppToken;
	public String AppTokenName = "ApplicationToken";

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
	 * @param projectToken ProjectToken - from server
	 * @param appToken AppToken - from server
	 * @param apiLevel apiLevel - from device
	 * @param device device - from device
	 * @param model model - from device
	 * @param product product - from device
	 * @param language language - from device
	 */
	public RegisterPushObject(String projectToken, String appToken, String apiLevel, String device, String model, String product, String language) {
		ProjectToken = projectToken;
		AppToken = appToken;
		DeviceApiLevel = apiLevel;
		DeviceOperatingSystemVersion = device;
		DeviceModel = model;
		DeviceProduct = product;
		DeviceLanguage = language;
	}

	public JSONObject getJson(){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(ProjectTokenName,ProjectToken);
			jsonObject.put(AppTokenName,AppToken);
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
