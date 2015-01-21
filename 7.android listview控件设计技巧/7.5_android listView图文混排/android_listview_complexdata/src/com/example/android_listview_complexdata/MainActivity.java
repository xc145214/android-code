package com.example.android_listview_complexdata;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.example.android_listview_complexdata.common.CommonUrl;
import com.example.android_listview_complexdata.http.HttpUtils;
import com.example.android_listview_complexdata.json.JsonTools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView listView;
	private ProductAdapter adapter;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		dialog = new ProgressDialog(MainActivity.this);
		dialog.setTitle("XX");
		dialog.setMessage("loading.....");
		adapter = new ProductAdapter();
		new MyTask().execute(CommonUrl.PRODUCT_PATH);
	}

	class MyTask extends AsyncTask<String, Void, List<Map<String, Object>>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String json = HttpUtils.sendPostMethod(params[0], "utf-8");
			List<Map<String, Object>> list = JsonTools.parseJsonMaps(json);
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter.setData(result);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			dialog.dismiss();
		}

	}

	public class ProductAdapter extends BaseAdapter {

		List<Map<String, Object>> list;

		public void setData(List<Map<String, Object>> list) {
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
			View view = null;
			if (convertView == null) {
				view = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.item, null);
			} else {
				view = convertView;
			}
			TextView textView1 = (TextView) view.findViewById(R.id.textView1);
			textView1.setText(list.get(position).get("name").toString());
			TextView textView2 = (TextView) view.findViewById(R.id.textView2);
			textView2.setText(list.get(position).get("address").toString());
			TextView textView3 = (TextView) view.findViewById(R.id.textView3);
			textView3.setText(list.get(position).get("price").toString());
			ImageView imageView = (ImageView) view
					.findViewById(R.id.imageView1);
			try {
				System.out.println("--->>" + CommonUrl.PRODUCT_IMG
						+ list.get(position).get("img"));
				// Drawable drawable = Drawable.createFromStream(
				// new URL(CommonUrl.PRODUCT_IMG
				// + list.get(position).get("img")).openStream(),
				// "myfile");
				// imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
				byte[] data = HttpUtils.getImageView(CommonUrl.PRODUCT_IMG
						+ list.get(position).get("img"));
				// System.out.println("----->>"+data.length);
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				imageView.setImageBitmap(bitmap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return view;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
