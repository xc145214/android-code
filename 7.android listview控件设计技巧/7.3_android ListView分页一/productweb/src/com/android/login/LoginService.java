package com.android.login;

import java.util.List;

public interface LoginService {

	// 判断用户是否存在
	public boolean  isUserExitLogin(List<Object> params);
	
}
