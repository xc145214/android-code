package com.product.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.product.dao.ProductDao;
import com.product.service.ProductService;

/**
 * Servlet implementation class ProductAction
 */
@WebServlet("/ProductAction")
public class ProductAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		service = new ProductDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductAction() {
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
		String action_flag = request.getParameter("action_flag");
		PrintWriter writer = response.getWriter();
		if (action_flag.equals("add")) {
			add(request, response);
		} else if (action_flag.equals("list")) {
			list(request, response);
		} else if (action_flag.equals("del")) {
			del(request, response);
		} else if (action_flag.equals("view")) {
			view(request, response);
		}

		writer.flush();
		writer.close();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		String id = request.getParameter("id");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Map<String, Object> map = service.viewProduct(params);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/view.jsp").forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		String id = request.getParameter("id");
		System.out.println("--id->>" + id);
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		boolean flag = service.delProduct(params);
		if (flag) {
			response.sendRedirect(path + "/ProductAction?action_flag=list");
		}
	}

	/**
	 * �б����
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = service.listProduct();
		request.setAttribute("list", list);
		String path = request.getContextPath();// ��ù��̵�·��
		System.out.println("-path-->>" + path);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	/**
	 * ʵ����Ӳ���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getContextPath();
		// ��Ҫ����������ܣ�
		// ��������˱����ļ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ʹ���ļ��ϴ�����
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		// �涨�ͻ����ϴ��ļ��Ĵ�С
		fileUpload.setSizeMax(4 * 1024 * 1024);// �ܴ�С
		fileUpload.setFileSizeMax(1 * 1024 * 1024);// ÿһ���ļ��Ĵ�С
		List<FileItem> list = null;// ����input ��ʼ�������
		List<Object> params = new ArrayList<Object>();// �������ݿ�
		try {
			list = fileUpload.parseRequest(request);
			for (FileItem item : list) {
				// �����ı�����
				if (item.isFormField()) {
					if (item.getFieldName().equals("name")) {
						String name = item.getString("utf-8");
						params.add(name);
					}
					if (item.getFieldName().equals("address")) {
						String address = item.getString("utf-8");
						params.add(address);
					}
					if (item.getFieldName().equals("price")) {
						String price = item.getString("utf-8");
						params.add(price);
					}
					// �ļ�
				} else {

					String img = item.getName();
					params.add(img);
					String file_upload_path = request.getRealPath("/upload")
							+ "/" + img;
					System.out.println("-file_upload_path-->>"
							+ file_upload_path);
					File file = new File(file_upload_path);
					// System.out.println("--->>"+file.getName());
					item.write(file);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		boolean flag = service.addProduct(params);
		if (flag) {
			// ֱ����ת���б���ʾ����
			// System.out.println("success!!!");
			response.sendRedirect(path + "/ProductAction?action_flag=list");
		}
	}
}
