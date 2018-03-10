package com.qq.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;


/*
 * JdbcUtils的第三次修改
 * 可以处理事务,以及多线程并发问题
 */
public class JdbcUtils {
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	private static ResultSet rs = null;
	private static java.sql.PreparedStatement ps = null;
	
	
	//用于事务操作,可以处理多线程问题
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws SQLException
	{
		//得到此线程的连接
		Connection con = tl.get();
		if(con==null)
			return cpds.getConnection();
		return con;
	}
	
	public static DataSource getDataSource()
	{
		return cpds;
	}
	
	//事务处理的方法
	//开启事务
	public static void beginTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con!=null)
			throw new SQLException("您已经开启了事务");
		con = getConnection();
		con.setAutoCommit(false);
		
		//得把当前事务的连接保存到线程中
		tl.set(con);
	}
	
	//执行事务
	public static void commitTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con==null)
			throw new SQLException("您还没有开启事务, 不能提交");
		con.commit();
		con.close();    //用完就归还给连接池
//		con = null;    //不用再设置为null了
		tl.remove();
	}
	
	//回滚事务
	public static void rollbackTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con==null)
			throw new SQLException("您还没有开启事务,不能回滚");
		con.rollback();
		con.close();
//		con = null;
		
		tl.remove();
	}
	
	//如果不是事务,得释放连接
	public static void releaseConnection(Connection conn) throws SQLException
	{
		Connection con = tl.get();
		//只要conn不是用于事务的连接,就关闭,返回给连接池
		if(con==null)
			conn.close();
		if(conn!=con)
			conn.close();
		
	}
	
	public static ResultSet query(String sql, Object[] params)
	{
		try {
			ps =  getConnection().prepareStatement(sql);
			for(int i=0; i<params.length; i++)
				ps.setObject(i+1, params[i]);
			
			rs = ps.executeQuery();
			
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//关闭资源
	public static void closeRsAndPs()
	{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
