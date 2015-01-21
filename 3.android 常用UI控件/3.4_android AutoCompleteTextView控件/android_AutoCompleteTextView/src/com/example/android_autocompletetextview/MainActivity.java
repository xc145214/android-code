package com.example.android_autocompletetextview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	private AutoCompleteTextView textView;
	//
	private ArrayAdapter<String> adapter;// 数组适配器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (AutoCompleteTextView) this.findViewById(R.id.auto);
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, getDataSource());
		// textView.setAdapter(adapter);// 将适配器的内容放到AutoCompleteTextView中
		String[] countries = getResources().getStringArray(
				R.array.countries_array);
		// adapter = ArrayAdapter.createFromResource(context, textArrayResId,
		// textViewResId);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, countries);
		textView.setAdapter(adapter);
	}

	// 数据源
	public List<String> getDataSource() {
		List<String> list = new ArrayList<String>();
		list.add("Albania");
		list.add("Afghanistan");
		list.add("Algeria");
		list.add("American Samoa");
		list.add("Andorra");
		list.add("Angola");
		list.add("Anguilla");
		list.add("Antarctica");
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
