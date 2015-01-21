package com.example.android_activity_state;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NextActivity extends Activity {
	private final String TAG = "NextActivity";

	public NextActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		Log.i(TAG, "-->>"+name);
		//System.out.println("--name->>" + name);
		int age = intent.getIntExtra("age", 0);
		Log.i(TAG, "-->>"+age);
		//System.out.println("--age->>" + age);
		String address = intent.getStringExtra("address");
		Log.i(TAG, "-->>"+address);
		//System.out.println("--address->>" + address);
		Bundle bundle = intent.getBundleExtra("bundle");
		String code = bundle.getString("code");
		Log.i(TAG, "-->>"+code);
		//System.out.println("--code->>" + code);
	}
}
