package cz.united121.notifit.Network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Take from http://danielnugent.blogspot.cz/2015/06/updated-jsonparser-with.html
 * It is library independent solution to send JSON to server (to take care of limit lines in Android apps)
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class JSONParser<T extends IJson> {

	String charset = "UTF-8";
	HttpURLConnection conn;
	DataOutputStream wr;
	StringBuilder result;
	URL urlObj;
	JSONObject jObj = null;

	/**
	 * Support only POST and GET, work only with RegisterPushObject derivates - create JSON object
	 * @param url URL to send request
	 * @param method POST or GET
	 * @param registerPushObject Object to by map on JSONObject
 	 * @return JSONObject with all information from RegisterPushObject
	 */
	public JSONObject makeHttpRequest(String url, String method, T registerPushObject, String communicationToken) {

		if ((method.equals("POST") || method.equals("PUT"))) {
			// request method is POST
			try {
				urlObj = new URL(url);

				conn = (HttpURLConnection) urlObj.openConnection();

				conn.setDoOutput(true);

				conn.setRequestMethod(method);

				conn.setRequestProperty("Accept-Charset", charset);
				conn.setRequestProperty("Content-Type", "application/json");
				if(communicationToken != null) {
					conn.setRequestProperty("Communication-Token", communicationToken);
				}

				conn.setReadTimeout(10000);
				conn.setConnectTimeout(15000);

				conn.connect();

				wr = new DataOutputStream(conn.getOutputStream());
				String json = registerPushObject.getJson().toString();
				wr.writeBytes(json);
				wr.flush();
				wr.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (method.equals("GET")) {
			// request method is GET
			try {
				urlObj = new URL(url);

				conn = (HttpURLConnection) urlObj.openConnection();

				conn.setDoOutput(false);

				conn.setRequestMethod("GET");

				conn.setRequestProperty("Accept-Charset", charset);

				conn.setConnectTimeout(15000);

				conn.connect();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			//Receive the response from the server
			InputStream in = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			Log.d("JSON Parser", "result: " + result.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		conn.disconnect();

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(result.toString());
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON Object
		return jObj;
	}
}
