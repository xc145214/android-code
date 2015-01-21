package com.example.android_lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";

	/**
	 * 1、第一被调用。仅仅被调用一次 2、初始化布局View或者绑定数据
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "--onCreate 方法->>");
		
		// String name = savedInstanceState.getString("name");
		// Toast.makeText(MainActivity.this, "--->>"+name, 1).show();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "--onStart 方法->>");
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "--onResume 方法->>");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "--onPause 方法->>");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "--onRestart 方法->>");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "--onStop 方法->>");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "--onDestroy 方法->>");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i(TAG, "--onSaveInstanceState 方法->>");
		outState.putString("name", "hello world");
	}

	
	// @Override
	// protected void onRestoreInstanceState(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onRestoreInstanceState(savedInstanceState);
	// Log.i(TAG, "--onRestoreInstanceState 方法->>");
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
