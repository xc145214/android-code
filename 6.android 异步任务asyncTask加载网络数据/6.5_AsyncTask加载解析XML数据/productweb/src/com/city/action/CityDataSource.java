package com.city.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CityDataSource {

	public CityDataSource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public static List<String> getCitysList() {
		List<String> list = new ArrayList<String>();
		list.add("����");
		list.add("�Ϻ�");
		list.add("����");
		list.add("����");
		return list;
	}

	/**
	 * ��xml�ļ�����xml���ַ���
	 * @return
	 */
	public static String getCityListXML() {
		InputStream inputStream = CityDataSource.class.getClassLoader()
				.getResourceAsStream("com/city/action/citys.xml");
		Reader reader = new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(reader);
		String value = "";
		StringBuilder builder = new StringBuilder();
		try {
			while ((value = bufferedReader.readLine()) != null) {
				builder.append(value);
			}
			return builder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		getCityListXML();
	}
}
