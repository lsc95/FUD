package com.coderli.dao.imp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InterfaceAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.coderli.utils.DBUtils;

@SuppressWarnings("all")
public class BaseDaoImp {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 查询的父类摸板
	 * 
	 * @param sql
	 *            执行的sql
	 * @param clazz
	 *            数据对应的实体类
	 * @param params
	 *            sql所需要的参数
	 * @param back
	 *            查询结果的回调函数
	 * @return 所需要的数据
	 */
	public Object QueryTemplate(String sql, Class clazz, Object[] params,
			Callback back) {
		try {
			conn = DBUtils.getConnection();
			ps = DBUtils.getPreparedStatement(sql, conn);
			// 给占位符赋值
			if (params != null) {
				DBUtils.bindParams(ps, params);
			}
			// 开始查询
			try {
				rs = ps.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return back.callback(conn, ps, rs, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return null;
	}

	/**
	 * 根据sql和参数查询符合条件的所有数据
	 * 
	 * @param sql
	 *            执行的sql
	 * @param clazz
	 *            数据对应的实体类
	 * @param params
	 *            sql所需要的参数
	 * @return sql语句筛选的数据集
	 */
	public Object queryAll(String sql, Class clazz, Object[] params) {
		return QueryTemplate(sql, clazz, params, new Callback() {

			@Override
			public Object callback(Connection conn, PreparedStatement ps,
					ResultSet rs, Class clazz) {
				List list = new ArrayList<>();
				// 开始遍历
				// 获得所有的字段名
				Field[] fields = clazz.getDeclaredFields();
				// 获取所有的方法名
				Method[] methods = clazz.getMethods();
				// 存放set方法的集合
				List<Method> lm = new ArrayList<>();
				// 筛选所有set方法
				for (Method method : methods) {
					if (method.getName().toLowerCase().startsWith("set")) {
						lm.add(method);
					}
				}
				try {
					while (rs.next()) {
						Object obj = clazz.newInstance();
						for (Field field : fields) {
							if (field.getName().equals("serialVersionUID"))
								continue;
							Method method = null;
							for (Method m : lm) {
								if (m.getName()
										.toLowerCase()
										.equals("set"
												+ field.getName().toLowerCase())) {
									method = m;
								}
							}
							try {
								method.invoke(obj,
										rs.getObject(field.getName()));
							} catch (Exception e) {
								//需要调试sql，自行开启
								// e.printStackTrace();
							}
						}
						list.add(obj);
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return list;
			}

		});

	}

	/**
	 * 根据sql语句和条件查询某条记录
	 * 
	 * @param sql
	 *            执行的sql
	 * @param clazz
	 *            这条数据所对应的实体类
	 * @param params
	 *            sql的参数
	 * @return 需要查询的那条数据
	 */
	public Object quertyOneRow(String sql, Class clazz, Object[] params) {
		List list = (List) queryAll(sql, clazz, params);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据sql语句和参数查询某个值
	 * 
	 * @param sql
	 *            执行的sql
	 * @param params
	 *            sql所需要的参数
	 * @return 需要的某个字段的值
	 */
	public Object queryOneValue(String sql, Object[] params) {
		return QueryTemplate(sql, null, params, new Callback() {

			@Override
			public Object callback(Connection conn, PreparedStatement ps,
					ResultSet rs, Class clazz) {
				try {
					if (rs.next()) {
						return rs.getObject(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}

		});

	}

	interface Callback {
		Object callback(Connection conn, PreparedStatement ps, ResultSet rs,
				Class clazz);
	}
}
