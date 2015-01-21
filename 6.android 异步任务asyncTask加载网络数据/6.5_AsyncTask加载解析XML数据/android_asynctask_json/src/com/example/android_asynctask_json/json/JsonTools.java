package com.example.android_asynctask_json.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static List<String> parseList(String jsonString) {
		List<String> list = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("citys");
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
