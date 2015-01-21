package com.example.android_scrollview;

import com.example.android_scrollview.http.HttpUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LinearLayout layout;
	private ProgressDialog dialog;
    private final String HTML_PATH = "http://192.168.114.121:8080/productweb/news.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("loading.....");
		layout = (LinearLayout) this.findViewById(R.id.line);
		// for (int i = 0; i < 10; i++) {
		// ImageView imageView = new ImageView(this);
		// Drawable drawable = getResources().getDrawable(R.drawable.a);
		// imageView.setImageDrawable(drawable);
		// layout.addView(imageView, i);
		// }
        new MyTask().execute(HTML_PATH);
	}

	class MyTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String result = HttpUtils.sendPostMethod(params[0], "utf-8");
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			TextView textView = new TextView(MainActivity.this);
			Spanned spanned = Html.fromHtml(result);
			textView.setText(spanned);
			//处理html中超链接的事件
			textView.setMovementMethod(new LinkMovementMethod());
			layout.addView(textView);
			dialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
