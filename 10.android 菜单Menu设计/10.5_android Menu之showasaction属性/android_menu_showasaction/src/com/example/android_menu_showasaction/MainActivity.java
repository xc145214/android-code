package com.example.android_menu_showasaction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.cut:
			Toast.makeText(MainActivity.this, "-->>cut", 1).show();
			break;

		case R.id.create_new:
			Toast.makeText(MainActivity.this, "-->>create_new", 1).show();
			Intent intent = new Intent(MainActivity.this, NewActivity.class);
			startActivity(intent);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
