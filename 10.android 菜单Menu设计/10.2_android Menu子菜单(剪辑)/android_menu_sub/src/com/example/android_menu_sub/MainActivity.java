package com.example.android_menu_sub;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
		case R.id.create_new:
			Toast.makeText(MainActivity.this, "create_new", 1).show();
			//Intent intent = new Intent(packageContext, cls);
			//startActivity(intent);
			break;

		case R.id.open:
			Toast.makeText(MainActivity.this, "open", 1).show();
			break;
		}
    	return super.onMenuItemSelected(featureId, item);
    }
	
}
