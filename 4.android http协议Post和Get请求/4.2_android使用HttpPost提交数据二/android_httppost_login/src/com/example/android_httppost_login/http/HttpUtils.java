package com.example.android_httppost_login.http;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param path
	 * @param params
	 *            ģ��http������
	 * @param encode
	 * @return
	 */
	public static String sendPostMethod(String path,
			Map<String, Object> params, String encoding) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);

		String result = "";
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue().toString();
					BasicNameValuePair valuePair = new BasicNameValuePair(name,
							value);
					parameters.add(valuePair);
				}
			}
			// ���ı������������ļ�
			UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(
					parameters, encoding);
			httpPost.setEntity(encodedFormEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(),
						encoding);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
