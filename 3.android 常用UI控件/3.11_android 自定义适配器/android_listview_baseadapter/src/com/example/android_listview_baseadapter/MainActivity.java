package com.example.android_listview_baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView listView;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		adapter = new MyAdapter(getData());
		listView.setAdapter(adapter);
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("jack" + i);
		}
		return list;
	}

	public class MyAdapter extends BaseAdapter {
		private List<String> list;

		public MyAdapter(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// View view = LayoutInflater.from(MainActivity.this).inflate(
			// R.layout.item, null);
			// TextView textView = (TextView)view.findViewById(R.id.textView1);
			// textView.setText(list.get(position));
			// // System.out.println("---->>"+position);
			// // TextView textView = new TextView(MainActivity.this);
			// // textView.setText(list.get(position));
			// System.out.println("---->>" + position);
			// return view;
			View view = null;
			if (convertView == null) {
				view = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.item, null);
			} else {
				view = convertView;
			}
			TextView textView = (TextView) view.findViewById(R.id.textView1);
			textView.setText(list.get(position));
			return view;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
