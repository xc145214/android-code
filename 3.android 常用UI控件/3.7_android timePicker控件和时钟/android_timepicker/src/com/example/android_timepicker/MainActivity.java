package com.example.android_timepicker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TimePicker timePicker;// 时间
	private AnalogClock analogClock;// 时钟
    private DigitalClock digitalClock;//数字时钟
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timePicker = (TimePicker) this.findViewById(R.id.timePicker1);
		timePicker
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						String value = hourOfDay + "时:" + minute + "分";
						Toast.makeText(MainActivity.this, value, 1).show();
					}
				});

		analogClock = (AnalogClock) this.findViewById(R.id.analogClock1);
		digitalClock = (DigitalClock)this.findViewById(R.id.digitalClock1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
