package cz.united121.notifit.Network;

import org.json.JSONObject;

/**
 * Take from http://danielnugent.blogspot.cz/2015/06/updated-jsonparser-with.html
 * It is third-library independent solution to send JSON to server
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class PostNetworkTask<T extends IJson> {
	public static final String TAG = PostNetworkTask.class.getName();
	JSONParser jsonParser = new JSONParser();

	private T objectToSend;
	private static String METHOD;
	private static String URL;
	private static String COMMUNICATIONTOKEN;

	/**
	 * Send POST request to LOGIN_URL (notifit servers) - wrapper to AsyncCall
	 * @param registerPushObject Object to be map to JSONObject and then send
	 */
	public PostNetworkTask(T registerPushObject, String url, String method, String communicationToken) {
		objectToSend = registerPushObject;
		URL = url;
		METHOD = method;
		COMMUNICATIONTOKEN = communicationToken;
	}

	public JSONObject Send() {
		JSONObject json = null;
		try {
			json = jsonParser.makeHttpRequest(URL, METHOD, objectToSend, COMMUNICATIONTOKEN);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}