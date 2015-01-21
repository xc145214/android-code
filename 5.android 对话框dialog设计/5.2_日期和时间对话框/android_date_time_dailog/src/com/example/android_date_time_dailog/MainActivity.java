package com.example.android_date_time_dailog;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button;
	private Button button2;
	private EditText editText;
	private DatePickerDialog dialog;// 日期对话框
	private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
	private TimePickerDialog dialog2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		editText = (EditText) this.findViewById(R.id.editText1);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		dialog = new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				String text = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
				editText.setText(text);
			}
		}, year, monthOfYear, dayOfMonth);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.show();
			}
		});

		dialog2 = new TimePickerDialog(MainActivity.this,
				new OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this,
								hourOfDay + ":" + minute, 1).show();
					}
				}, hourOfDay, minute, true);

		button2 = (Button) this.findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog2.show();
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
