package com.example.android_asynctask_json;

import java.util.ArrayList;
import java.util.List;

import com.example.android_asynctask_json.domain.City;
import com.example.android_asynctask_json.http.HttpUtils;
import com.example.android_asynctask_json.json.JsonTools;
import com.example.android_asynctask_json.xml.XMLTools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";
	private Spinner spinner;
	// private final String CITY_PATH =
	// "http://192.168.114.121:8080/productweb/CityAction?type=json";
	private final String CITY_PATH = "http://192.168.114.121:8080/productweb/CityAction?type=xml";
	private ArrayAdapter<String> adapter;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dialog = new ProgressDialog(this);
		dialog.setTitle("ÌבÊ¾");
		dialog.setMessage("loading......");
		spinner = (Spinner) this.findViewById(R.id.spinner1);
		new MyTask().execute(CITY_PATH);
	}

	class MyTask extends AsyncTask<String, Void, List<City>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected List<City> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<City> list = null;
			String xmlString = HttpUtils.sendPostMethod(params[0], "utf-8");
			// Log.i(TAG, "--->>"+xmlString);
			// System.out.println("----->>"+xmlString);
			// list = JsonTools.parseList(jsonString);
			list = XMLTools.parseXML(xmlString);
			System.out.println("---list-->>"+list);
			return list;
		}

		@Override
		protected void onPostExecute(List<City> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			List<String> list = new ArrayList<String>();
			for(int i=0;i<result.size();i++){
				list.add(result.get(i).getName());
			}
			adapter = new ArrayAdapter<String>(MainActivity.this,
					android.R.layout.simple_spinner_dropdown_item, list);
			spinner.setAdapter(adapter);
			dialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
