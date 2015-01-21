package com.android.divpage;

import java.util.ArrayList;
import java.util.List;

public class EmpDataSource {

	public EmpDataSource() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getDataSource() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 200; i++) {
			list.add("jack" + i);
		}
		return list;
	}
}
