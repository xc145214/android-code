package com.example.android_listview_div.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	/**
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static String sendPostMethod(String path, String encoding) {
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost post = new HttpPost(path);
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), encoding);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
}
