package com.qq.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;


/*
 * JdbcUtils�ĵ������޸�
 * ���Դ�������,�Լ����̲߳�������
 */
public class JdbcUtils {
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	private static ResultSet rs = null;
	private static java.sql.PreparedStatement ps = null;
	
	
	//�����������,���Դ�����߳�����
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws SQLException
	{
		//�õ����̵߳�����
		Connection con = tl.get();
		if(con==null)
			return cpds.getConnection();
		return con;
	}
	
	public static DataSource getDataSource()
	{
		return cpds;
	}
	
	//������ķ���
	//��������
	public static void beginTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con!=null)
			throw new SQLException("���Ѿ�����������");
		con = getConnection();
		con.setAutoCommit(false);
		
		//�ðѵ�ǰ��������ӱ��浽�߳���
		tl.set(con);
	}
	
	//ִ������
	public static void commitTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con==null)
			throw new SQLException("����û�п�������, �����ύ");
		con.commit();
		con.close();    //����͹黹�����ӳ�
//		con = null;    //����������Ϊnull��
		tl.remove();
	}
	
	//�ع�����
	public static void rollbackTransaction() throws SQLException
	{
		Connection con = tl.get();
		if(con==null)
			throw new SQLException("����û�п�������,���ܻع�");
		con.rollback();
		con.close();
//		con = null;
		
		tl.remove();
	}
	
	//�����������,���ͷ�����
	public static void releaseConnection(Connection conn) throws SQLException
	{
		Connection con = tl.get();
		//ֻҪconn�����������������,�͹ر�,���ظ����ӳ�
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
	
	//�ر���Դ
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
