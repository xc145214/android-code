package com.example.android_listview1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//设置listview的选中模式
		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_single_choice,
				getDataSource());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"-->>" + adapter.getItem(position), 1).show();
			}
		});
	}

	public List<String> getDataSource() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("jack" + i);
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
