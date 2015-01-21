package com.example.android_radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onRadioButtonClicked(View view) {
		RadioButton button = (RadioButton) view;
		boolean isChecked = button.isChecked();
		switch (view.getId()) {
		case R.id.radio0:
			if (isChecked) {
				Toast.makeText(MainActivity.this, button.getText(), 1).show();
			}
			break;

		case R.id.radio1:
			if (isChecked) {
				Toast.makeText(MainActivity.this, button.getText(), 1).show();
			}
			break;
		case R.id.radio2:
			if (isChecked) {
				Toast.makeText(MainActivity.this, button.getText(), 1).show();
			}
			break;
		}
	}

	public void onToggleClicked(View view) {
		boolean isChecked = ((Switch) view).isChecked();
		if (isChecked) {
			Toast.makeText(MainActivity.this, "´ò¿ª", 1).show();
		} else {
			Toast.makeText(MainActivity.this, "¹Ø±Õ", 1).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
