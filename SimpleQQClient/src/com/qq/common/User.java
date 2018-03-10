package com.qq.common;
/**
 * @author Àµ³Ì·æ
 *
 */
public class User implements java.io.Serializable{

	private String useId;
	private String password;
	private String username;
	private String qianming;
	private int age;
	private int phone;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQianming() {
		return qianming;
	}
	public void setQianming(String qianming) {
		this.qianming = qianming;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [useId=" + useId + ", password=" + password + ", username=" + username + ", qianming=" + qianming
				+ ", age=" + age + ", phone=" + phone + "]";
	}
}
