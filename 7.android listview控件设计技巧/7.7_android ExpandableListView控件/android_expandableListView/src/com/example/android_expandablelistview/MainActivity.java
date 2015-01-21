package com.example.android_expandablelistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ExpandableListView listView;
    private MyAdapter adapter;
    private List<String> group;
    private List<List<String>> child;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ExpandableListView)this.findViewById(R.id.expandableListView1);
		adapter = new MyAdapter();
		initData();
		listView.setAdapter(adapter);
		listView.setGroupIndicator(null);
	}

	public void initData(){
		group = new ArrayList<String>();
		child = new ArrayList<List<String>>();
		addInfo("北京",new String[]{"东城区","西城区","朝阳区"});
		addInfo("河北",new String[]{"石家庄","邯郸","邢台"});
		addInfo("广东",new String[]{"深圳","珠海","广州"});
	}
	public void addInfo(String g,String[] c){
		group.add(g);//添加组
		List<String> list = new ArrayList<String>();
		for(int i=0;i<c.length;i++){
			list.add(c[i]);
		}
		child.add(list);
	}
	
    class MyAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return group.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return child.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return group.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			TextView textView = null;
			if(convertView==null){
				textView = new TextView(MainActivity.this);
			}else{
				textView = (TextView)convertView;
			}
			textView.setText(group.get(groupPosition));
			textView.setTextSize(30);
			textView.setPadding(36, 10, 0, 10);
			return textView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView = null;
			if(convertView==null){
				textView = new TextView(MainActivity.this);
			}else{
				textView = (TextView)convertView;
			}
			textView.setText(child.get(groupPosition).get(childPosition));
			textView.setTextSize(20);
			textView.setPadding(72, 10, 0, 10);
			return textView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
    	
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
