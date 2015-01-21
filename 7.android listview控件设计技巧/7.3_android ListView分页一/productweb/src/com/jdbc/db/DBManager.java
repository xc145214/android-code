package com.jdbc.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

/**
 * 负责访问数据库的工具类
 * 
 * @author jack
 * 
 */
public class DBManager {

	// 数据库的用户名和密码
	private final String USERNAME = "root";
	private final String PSWD = "admin";
	// 数据库的驱动
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/mydb2";
	private Connection connection;// 链接数据库
	private PreparedStatement pstmt;// 采用预编译的sql语句执行添加、删除、修改和查询的功能，效率高
	private ResultSet rs;// 查询返回的结果集合
	private static DBManager instance;

	private DBManager() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PSWD);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 使用单例模式获得数据库的访问对象:保证数据的安全性
	 * 
	 * @return
	 */
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	/**
	 * 释放连接:
	 */
	public void releaseConn() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * 功能：对数据库的表的添加删除和修改功能
	 * 
	 * @param sql
	 *            传递的Sql语句
	 * @param params
	 *            sql的占位符
	 * @return
	 */
	public boolean updateBySql(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		// 影响数据库的行数,如果大于0 表示操作成功
		int count = pstmt.executeUpdate();
		flag = count > 0 ? true : false;
		return flag;
	}

	/**
	 * 使用java的 反射机制封装查询,查询返回单条记录
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public <T> T querySimleResultRef(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T t = null;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		// 查询结果
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int cols_len = metaData.getColumnCount();
		if (rs.next()) {
			t = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = rs.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(t, cols_value);
			}
		}
		return t;
	}

	/**
	 * 使用java的 反射机制封装查询,查询返回多条记录
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryMultResultRef(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (rs.next()) {
			T t = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = rs.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(t, cols_value);
			}
			list.add(t);
		}
		return list;
	}

	/**
	 * 使用封装查询,查询返回一个Map表示一行记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> querySimpleMap(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 0; i < cols_len; i++) {
				String key = metaData.getColumnName(i + 1);
				Object value = rs.getObject(key);
				if (value == null) {
					value = "";
				}
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 使用封装查询,查询返回一个List<Map<String, Object>>表示多行记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryMultMap(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String key = metaData.getColumnName(i + 1);
				Object value = rs.getObject(key);
				if (value == null) {
					value = "";
				}
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}
}
