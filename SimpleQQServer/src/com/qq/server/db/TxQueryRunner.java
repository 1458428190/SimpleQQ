package com.qq.server.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;


/*
 * 此类就再也不用处理事务连接以及非事务连接的关闭问题了,自动会关闭
 */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = JdbcUtils.getConnection();
		int result[] =  super.batch(con, sql, params);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = JdbcUtils.getConnection();
		T result =  super.query(con, sql, param, rsh);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		T result =  super.query(con, sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		T result =  super.query(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		T result =  super.query(con, sql, rsh);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		int result =  super.update(con, sql, params);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		int result =  super.update(con, sql, param);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		int result =  super.update(con, sql);
		JdbcUtils.releaseConnection(con);
		
		return result;
	}
	
	
}
