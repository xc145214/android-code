package com.example.android_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private GridView gridView;
	private ImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView = (GridView) this.findViewById(R.id.gridview);
		adapter = new ImageAdapter();
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "-->>" + position, 1).show();
			}
		});
	}

	// 图片的资源
	private int[] images = { R.drawable.pro1, R.drawable.pro2, R.drawable.pro3,
			R.drawable.pro4, R.drawable.pro5, R.drawable.pro6, R.drawable.pro7,
			R.drawable.pro8 };

	public class ImageAdapter extends BaseAdapter {

		public ImageAdapter() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return images[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 手工创建View对象
			// 加载xml文件
			ImageView imageView = null;
			if (convertView == null) {
				imageView = new ImageView(MainActivity.this);
			} else {
				imageView = (ImageView) convertView;
			}
			// 设置GridView的显示的格子的间距
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
			imageView.setImageResource(images[position]);
			return imageView;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
