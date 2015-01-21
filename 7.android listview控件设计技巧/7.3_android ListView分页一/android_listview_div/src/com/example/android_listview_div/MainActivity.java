package com.example.android_listview_div;

import java.util.ArrayList;
import java.util.List;

import com.example.android_listview_div.http.HttpUtils;
import com.example.android_listview_div.json.JsonTools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.DialerFilter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String EMP_PATH = "http://192.168.114.121:8080/productweb/EmpAction?pageNo=";
	private ListView listView;
	private MyAdapter adapter;
	private boolean is_divpage;// 是否进行分页操作
	private ProgressDialog dialog;
	List<String> total = new ArrayList<String>();// 放置记录的总条数
	private static int pageNo = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("loading.....");
		new MyTask().execute(EMP_PATH + pageNo);
		adapter = new MyAdapter();
		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (is_divpage
						&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					new MyTask().execute(EMP_PATH + pageNo);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				is_divpage = (firstVisibleItem + visibleItemCount == totalItemCount);
			}
		});
	}

	class MyTask extends AsyncTask<String, Void, List<String>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected List<String> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String jsonString = HttpUtils.sendPostMethod(params[0], "utf-8");
			List<String> list = JsonTools.parseJsonList(jsonString);
			return list;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			total.addAll(result);
			adapter.bindData(total);
			if(pageNo==1){
				listView.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();
			pageNo++;
			dialog.dismiss();
		}

	}

	class MyAdapter extends BaseAdapter {
		List<String> list;

		public MyAdapter() {
			// TODO Auto-generated constructor stub
		}

		public void bindData(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView = null;
			if (convertView == null) {
				textView = new TextView(MainActivity.this);
			} else {
				textView = (TextView) convertView;
			}
			textView.setTextSize(20);
			textView.setText(list.get(position));
			return textView;
		}

	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 30; i++) {
			list.add("jack"+ i);
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
