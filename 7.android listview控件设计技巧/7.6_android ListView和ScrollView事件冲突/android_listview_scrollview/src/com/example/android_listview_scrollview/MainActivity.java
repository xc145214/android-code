package com.example.android_listview_scrollview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private boolean is_move;
	private boolean is_scroll_move;
	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView1);
		scrollView = (ScrollView) this.findViewById(R.id.scrollView1);
		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (is_move
						&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// 表示用户从ListView滑动到底端
					is_scroll_move = true;
				} else {
					is_scroll_move = false;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				is_move = (firstVisibleItem + visibleItemCount == totalItemCount);
			}
		});
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("XXX" + i);
		}
		return list;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			// 想让那个控件先执行，直接调用
			if (is_scroll_move) {
				scrollView.dispatchTouchEvent(ev);
			} else {
				listView.dispatchTouchEvent(ev);
			}
		}
		return super.dispatchTouchEvent(ev);
		// return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
