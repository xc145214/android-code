package com.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import com.jdbc.db.DBManager;
import com.product.service.ProductService;

public class ProductDao implements ProductService {

	private DBManager dbManager;
	public ProductDao() {
		// TODO Auto-generated constructor stub
		dbManager = DBManager.getInstance();
	}

	@Override
	public boolean addProduct(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "insert into pro(name,address,price,img) values(?,?,?,?)";
		boolean flag = false;
		dbManager.getConnection();
		try {
			flag = dbManager.updateBySql(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void releaseConn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> listProduct() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = null;
		String sql = "select * from pro";
		try {
			dbManager.getConnection();
			list = dbManager.queryMultMap(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public boolean delProduct(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "delete from  pro where id = ? ";
		boolean flag = false;
		dbManager.getConnection();
		try {
			flag = dbManager.updateBySql(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Map<String, Object> viewProduct(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from pro where id = ? ";
		Map<String, Object> map = null;
		try {
			dbManager.getConnection();
			map = dbManager.querySimpleMap(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return map;
	}

}
