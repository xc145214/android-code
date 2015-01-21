package com.example.android_activity_state;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 第一种方式
				Intent intent = new Intent(MainActivity.this,
						NextActivity.class);
				intent.putExtra("name", "jack");
				intent.putExtra("age", 23);
				intent.putExtra("address", "北京");
				
				Bundle bundle = new Bundle();
				bundle.putString("code", "1001");
				
				intent.putExtra("bundle", bundle);
				// 启动Activity
				startActivity(intent);
				// 第二种
				// Intent intent = new Intent();
				// intent.setClass(MainActivity.this, NextActivity.class);
				// Intent intent = new
				// Intent("com.example.android_activity_state.nextActivty");
				// startActivity(intent);
				// Intent intent = new Intent();
				// intent.setAction("com.example.android_activity_state.nextActivty");
				// startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
