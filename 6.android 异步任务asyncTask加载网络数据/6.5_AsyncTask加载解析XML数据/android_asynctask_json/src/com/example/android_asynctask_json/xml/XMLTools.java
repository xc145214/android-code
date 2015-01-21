package com.example.android_asynctask_json.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.android_asynctask_json.domain.City;

public class XMLTools {

	public XMLTools() {
		// TODO Auto-generated constructor stub
	}

	public static List<City> parseXML(String xmlString) {
		List<City> list = null;
		City city = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
                 switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<City>();
					break;
				case XmlPullParser.START_TAG:
					if("city".equals(parser.getName())){
						city = new City();
					}else if("name".equals(parser.getName())){
						String value = parser.nextText();
						city.setName(value);
					}
					break;
				case XmlPullParser.END_TAG:
					if("city".equals(parser.getName())){
						list.add(city);
						city = null;
					}
					break;
				}
                 eventType =  parser.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
