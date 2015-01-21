package com.android.divpage;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class EmpAction
 */
@WebServlet("/EmpAction")
public class EmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		EmpDataSource dataSource = new EmpDataSource();
		List<String> list = dataSource.getDataSource();

		String pageNo = request.getParameter("pageNo");
		int currentPage = 1;// 当前页是第一页
		if (pageNo != null) {
			currentPage = Integer.parseInt(pageNo);
		}
		DividePage pUtil = new DividePage(25, list.size(), currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();

		List<String> subList = list.subList(start, end);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emps", subList);
		String jsonString = JSONSerializer.toJSON(map).toString();
		writer.println(jsonString);
		writer.flush();
		writer.close();

	}

}
