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
 * ����������ݿ�Ĺ�����
 * 
 * @author jack
 * 
 */
public class DBManager {

	// ���ݿ���û���������
	private final String USERNAME = "root";
	private final String PSWD = "admin";
	// ���ݿ������
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/mydb2";
	private Connection connection;// �������ݿ�
	private PreparedStatement pstmt;// ����Ԥ�����sql���ִ����ӡ�ɾ�����޸ĺͲ�ѯ�Ĺ��ܣ�Ч�ʸ�
	private ResultSet rs;// ��ѯ���صĽ������
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
	 * ʹ�õ���ģʽ������ݿ�ķ��ʶ���:��֤���ݵİ�ȫ��
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
	 * �ͷ�����:
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
	 * ���ܣ������ݿ�ı�����ɾ�����޸Ĺ���
	 * 
	 * @param sql
	 *            ���ݵ�Sql���
	 * @param params
	 *            sql��ռλ��
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
		// Ӱ�����ݿ������,�������0 ��ʾ�����ɹ�
		int count = pstmt.executeUpdate();
		flag = count > 0 ? true : false;
		return flag;
	}

	/**
	 * ʹ��java�� ������Ʒ�װ��ѯ,��ѯ���ص�����¼
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
		// ��ѯ���
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
	 * ʹ��java�� ������Ʒ�װ��ѯ,��ѯ���ض�����¼
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
	 * ʹ�÷�װ��ѯ,��ѯ����һ��Map��ʾһ�м�¼
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
	 * ʹ�÷�װ��ѯ,��ѯ����һ��List<Map<String, Object>>��ʾ���м�¼
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
