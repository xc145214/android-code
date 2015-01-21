package com.example.android_pickers;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DatePicker datePicker;// ���ڿؼ�
	private int year, monthOfYear, dayOfMonth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datePicker = (DatePicker) this.findViewById(R.id.datePicker1);
		// ���ϵͳ������
		Calendar calendar = Calendar.getInstance();
		//��ʼ��������
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, monthOfYear, dayOfMonth,
				new DatePicker.OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
                          String value = year+"��-"+(monthOfYear+1)+"��-"+dayOfMonth+"��";
                          Toast.makeText(MainActivity.this, value, 1).show();
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
