package com.example.android_httppost_login;

import java.util.HashMap;
import java.util.Map;

import com.example.android_httppost_login.domain.ResultMessage;
import com.example.android_httppost_login.http.HttpUtils;
import com.example.android_httppost_login.json.JsonTools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private final String LOGIN_PATH = "http://192.168.114.121:8080/productweb/LoginAction";
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		usernameEditText = (EditText) this.findViewById(R.id.editText1);
		passwordEditText = (EditText) this.findViewById(R.id.editText2);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在登录,请稍后....");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name_value = usernameEditText.getText().toString()
						.trim();
				String pswd_value = passwordEditText.getText().toString()
						.trim();
				Map<String, String> params = new HashMap<String, String>();
				params.put("url", LOGIN_PATH);
				params.put("username", name_value);
				params.put("password", pswd_value);
				try {
					String result = new LoginAsyncTask().execute(params).get();
					ResultMessage message = JsonTools.getResultMessage(result);
					if (message.getResultCode() == 1) {
						Toast.makeText(MainActivity.this,
								message.getResultMessage(), 1).show();
						Intent intent = new Intent(MainActivity.this,
								NextMainActivity.class);
						startActivity(intent);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

	class LoginAsyncTask extends AsyncTask<Map<String, String>, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected String doInBackground(Map<String, String>... params) {
			// TODO Auto-generated method stub
			Map<String, String> map = params[0];
			// 表单数据
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("username", map.get("username"));
			params2.put("password", map.get("password"));
			String result = HttpUtils.sendPostMethod(map.get("url"), params2,
					"utf-8");
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
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
