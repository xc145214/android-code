package com.example.android_menu_contextual;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private ActionMode mActionMode;
	private ActionMode.Callback callback = new ActionMode.Callback() {
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			mActionMode = null;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			// ¼ÓÔØ²Ëµ¥
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			switch (item.getItemId()) {
			case R.id.edit:
				Toast.makeText(MainActivity.this, "--->>edit", 1).show();
				mode.finish();
				break;
			case R.id.share:
				Toast.makeText(MainActivity.this, "--->>share", 1).show();
				mode.finish();
				break;
			case R.id.del:
				Toast.makeText(MainActivity.this, "--->>del", 1).show();
				mode.finish();
				break;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (mActionMode != null) {
					return false;
				}
				mActionMode = startActionMode(callback);
				view.setSelected(true);
				return true;
			}
		});
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("jack" + i);
		}
		return list;
	}

}
