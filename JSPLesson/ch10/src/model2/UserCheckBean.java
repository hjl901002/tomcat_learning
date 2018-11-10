package org.huangjl.ch10.model2.beans;

public class UserCheckBean{
	
	protected UserBean user;
	
	public UserCheckBean(){
		
	}
	
	public UserCheckBean(UserBean user){
		this.user = user;
	}
	
	public UserBean getUser(){
		return user;
	}
	
	public void setUser(UserBean user){
		this.user = user;
	}
	
	public boolean validate(){
		String name = user.getName();
		String password = user.getPassword();
		
		//System.out.println(name);
		//System.out.println(password);
		
		//实际应用中，应该查询数据库，验证用户名和密码
		if("张三".equals(name) && "1234".equals(password)){
			return true;
		}else{
			return false;
		}
	}
	
}