package com.example.android_asynctask_json.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param path ��ȡָ��·����json��Ϣ
	 * @param encode ָ�������ʽ
	 * @return
	 */
	public static String sendPostMethod(String path, String encode) {
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost post = new HttpPost(path);
			HttpResponse response = httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity(), encode);
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
