package com.android.login;

import java.util.List;
import java.util.Map;

import com.jdbc.db.DBManager;

public class LoginDao implements LoginService {

	private DBManager manager;

	public LoginDao() {
		// TODO Auto-generated constructor stub
		manager = DBManager.getInstance();

	}

	@Override
	public boolean isUserExitLogin(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from userinfo where username = ?  and password = ? ";
		manager.getConnection();
		Map<String, Object> map = null;
		boolean flag = false;
		try {
			map = manager.querySimpleMap(sql, params);
			flag = map.isEmpty() ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

}
