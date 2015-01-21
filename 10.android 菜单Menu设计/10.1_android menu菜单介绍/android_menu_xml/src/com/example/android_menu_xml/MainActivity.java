package com.example.android_menu_xml;

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

	//µ¥¶À×¢²áÊÂ¼þ¼àÌý
	public void saveMessage(MenuItem item){
		Toast.makeText(MainActivity.this, "--->>+saveMessage", 1).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.open:
			Intent intent = new Intent(MainActivity.this, OpenActivity.class);
			startActivity(intent);
			break;

		case R.id.close:
			break;

		case R.id.save:
			break;

		case R.id.paste:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
