/**
 * 
 */
package com.qq.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.qq.common.User;

/**
 * @author 赖程锋
 * 下午8:43:20
 */
public class QqUserDao {

	QueryRunner qr = new TxQueryRunner();
	//验证账号密码正确性
	public User loginCheck(User u)
	{
		boolean b = false;
		User user = null;
		String sql = "select * from qquser where qqid=? and qqpassword=?";
		Object[] params = {u.getUseId(), u.getPassword()};
		
		//有记录,返回user
		ResultSet rs = JdbcUtils.query(sql, params);
		try {
		if(rs.next())
		{
				user = new User();
				//rs.next();

				for(int i=1; i<7; i++)
				{
					System.out.println(rs.getString(i));
				}
				
				user.setUseId(u.getUseId());
				user.setPassword(u.getPassword());
				user.setAge(rs.getInt("age"));
				user.setPhone(Integer.parseInt(rs.getString("phone")));
				user.setUsername(rs.getString("username"));
				user.setQianming(rs.getString("qianming"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JdbcUtils.closeRsAndPs();
		return user;
	}
	//注册Qq用户
	public String addQqUser(User u)
	{
		String newID = "";
		int count=0;
		String userId = (Integer.parseInt(this.getLastId())+1)+"";
		System.out.println("userId" + userId);
		
		String sql = "insert into qquser values(?,?,?,?,?,?)";
		Object[] params={userId,u.getPassword(),u.getUsername(),
				u.getQianming(),u.getAge(),u.getPhone()};
		try {
			count = qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("注册QQ失败:"+u);
			newID = "fail";
		}
		if(count>0)
			return userId;
		else
			return "fail";
	}
	
	//得到最后的一个id
	public String getLastId()
	{
		Object[] params = {};
		String count="";
		String count1="";
		String sql1 = "select count(*) from qquser";
		ResultSet rs1 = JdbcUtils.query(sql1, params);
		if(rs1!=null)
		{
			try {
				rs1.next();
				count1 = rs1.getString(1);
				System.out.println(count1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String sql = "select qqid from qquser limit ?, ?";
		Object[] paramss = {Integer.parseInt(count1)-1, 1};
		
		ResultSet rs = JdbcUtils.query(sql, paramss);
		if(rs!=null)
		{
			try {
				rs.next();
				count = rs.getString("qqid");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				JdbcUtils.closeRsAndPs();
			}
		}
		return count;
	}
	
	//改密
	public boolean updatePassword(User u, String password)
	{
		int count = 0;
		String sql = "update qquser set qqpassword=? where qqid=? and username=? and phone=?";
		Object[] params={password,u.getUseId(),u.getUsername(),u.getPhone()};
		try {
			count = qr.update(sql, params);
			if(count>0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改QQ密码失败:"+u);
			return false;
		}
		//return false;
	}
	
	//获取所有好友
	public String getAllFriend()
	{
		String friends = "";
		int k=0;
		String sql = "select username from qquser";
		Object[] params={};
		try
		{
			ResultSet rs = JdbcUtils.query(sql, params);
			while(rs.next())
			{
				friends += rs.getString(1)+" ";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcUtils.closeRsAndPs();
		}
		
		return friends;
	}
	
	
	//单元测试数据库的操作
	@Test
	public void fun1()
	{
//		//登录
//		User u = new User();
//		u.setUseId("666666");
//		u.setPassword("666666");
//		System.out.println("logi + " + this.loginCheck(u));
		
/*		//注册
		User u1 = new User();
		u1.setUsername("紫曲娜");
		u1.setAge(20);
		u1.setQianming("生活少时诵诗书所");
		u1.setPassword("123456");
		u1.setPhone(212);
		System.out.println(this.addQqUser(u1));
		*/
/*		//改密
		User u2 = new User();
		u2.setUseId("666666");
		u2.setUsername("赖程锋");
		u2.setPhone(666);
		System.out.println(this.updatePassword(u2, "666666"));*/
		
		System.out.println(this.getAllFriend());
	}
}
