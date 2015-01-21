package com.example.android_httppost_login.json;

import org.json.JSONObject;

import com.example.android_httppost_login.domain.ResultMessage;

public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {"result":{"resultCode":1,"resultMessage":"µÇÂ¼³É¹¦"}}
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ResultMessage getResultMessage(String jsonString) {
		ResultMessage message = null;
		try {
			JSONObject rootObject = new JSONObject(jsonString);
			JSONObject jsonObject = rootObject.getJSONObject("result");
			message = new ResultMessage();
			message.setResultCode(jsonObject.getInt("resultCode"));
			message.setResultMessage(jsonObject.getString("resultMessage"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;
	}
}
