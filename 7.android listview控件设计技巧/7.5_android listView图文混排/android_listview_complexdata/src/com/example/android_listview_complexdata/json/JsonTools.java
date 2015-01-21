package com.example.android_listview_complexdata.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTools {

	/**
	 * {"id":100,"price":"34","address":"±±¾©","name":"ÄÌ·Û","img":"pro1.png"}
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> parseJsonMaps(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("products");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject ele = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", ele.getInt("id"));
				map.put("price", ele.getString("price"));
				map.put("address", ele.getString("address"));
				map.put("name", ele.getString("name"));
				map.put("img", ele.getString("img"));
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
