package com.example.android_progressbar2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private Button button;
	private ProgressBar bar;
	private SeekBar seekBar;
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		seekBar = (SeekBar) this.findViewById(R.id.seekBar1);
		ratingBar = (RatingBar) this.findViewById(R.id.ratingBar1);

		bar = (ProgressBar) this.findViewById(R.id.progressBar1);
		bar.setMax(100);// 设置最大值是100
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyTask().execute();
			}
		});

		seekBar.setMax(100);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				System.out.println("-->>"+progress);
			}
		});
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				System.out.println("-->>"+rating);
			}
		});
	}

	class MyTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			bar.setProgress(values[0]);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			int i = 1;
			while (i <= 100) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				publishProgress(i);
				i++;
			}
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
