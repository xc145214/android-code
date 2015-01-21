package com.example.android_menu_contextual;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("jack" + i);
		}
		return list;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.edit:
			String value = adapter.getItem(info.position);
			Toast.makeText(MainActivity.this, "--->>"+value, 1).show();
			break;

		case R.id.share:
			break;
		case R.id.del:
			break;
		}
		return super.onContextItemSelected(item);
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}
