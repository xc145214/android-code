package com.example.android_alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button;
	private AlertDialog.Builder builder;
	private Button button2;
	private Button button3;
	private Button button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
		button4 = (Button) this.findViewById(R.id.button4);

		builder = new AlertDialog.Builder(this);
		builder.setTitle("提示框");
		builder.setMessage("您确定要删除吗？");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// 完成业务逻辑代码
				Toast.makeText(MainActivity.this, "确定删除!!", 1).show();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "取消删除!!", 1).show();
			}
		});
		builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "忽略操作!!", 1).show();
			}
		});
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder.show();
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("请选择爱好");
				final String[] hobby = { "上网", "聊天", "打游戏" };
				// builder.setMessage("");
				builder.setMultiChoiceItems(hobby, new boolean[] { true, false,
						true },
						new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								if (isChecked) {
									Toast.makeText(MainActivity.this,
											"-->>" + hobby[which], 1).show();
								}
								// TODO Auto-generated method stub

							}
						});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
				builder.show();
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				// builder.setAdapter(adapter, listener);
				builder.setTitle("XX");
				builder.setSingleChoiceItems(
						new String[] { "上网", "聊天", "打游戏" }, -1,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.show();
			}
		});

		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("请输入信息");
				View view = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.dialog, null);
				final EditText username = (EditText)view.findViewById(R.id.editText1);
				final EditText password  = (EditText)view.findViewById(R.id.editText2);
				builder.setView(view);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String a = username.getText().toString().trim();
						String b = password.getText().toString().trim();
						Toast.makeText(MainActivity.this, "-->>"+a+"-->>"+b, 1).show();
					}
				});
				builder.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.show();
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
