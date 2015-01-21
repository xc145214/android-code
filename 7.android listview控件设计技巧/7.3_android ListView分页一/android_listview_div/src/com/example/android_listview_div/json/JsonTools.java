package com.example.android_listview_div.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTools {

	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<String> parseJsonList(String jsonString) {
		List<String> list = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("emps");
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(jsonArray.getString(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
}
