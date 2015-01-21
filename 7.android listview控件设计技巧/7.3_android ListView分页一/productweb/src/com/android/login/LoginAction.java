package com.android.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginService service;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		service = new LoginDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		//获得客户端提交表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		boolean flag = service.isUserExitLogin(params);
		System.out.println("--->>"+flag);
		if(flag){
			ResultMessage message = new ResultMessage(1, "登录成功");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("result", message);
			String jsonString = JSONSerializer.toJSON(map).toString();
			writer.println(jsonString);
		}
		writer.flush();
		writer.close();
	}

}
