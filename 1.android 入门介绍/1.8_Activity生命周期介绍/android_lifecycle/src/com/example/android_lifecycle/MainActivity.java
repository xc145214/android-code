package com.example.android_lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";

	/**
	 * 1����һ�����á�����������һ�� 2����ʼ������View���߰�����
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "--onCreate ����->>");
		
		// String name = savedInstanceState.getString("name");
		// Toast.makeText(MainActivity.this, "--->>"+name, 1).show();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "--onStart ����->>");
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "--onResume ����->>");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "--onPause ����->>");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "--onRestart ����->>");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "--onStop ����->>");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "--onDestroy ����->>");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i(TAG, "--onSaveInstanceState ����->>");
		outState.putString("name", "hello world");
	}

	
	// @Override
	// protected void onRestoreInstanceState(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onRestoreInstanceState(savedInstanceState);
	// Log.i(TAG, "--onRestoreInstanceState ����->>");
	// String name = savedInstanceState.getString("name");
	// Toast.makeText(MainActivity.this, "--->>"+name, 1).show();
	// }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
