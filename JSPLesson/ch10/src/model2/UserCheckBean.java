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
		
		//ʵ��Ӧ���У�Ӧ�ò�ѯ���ݿ⣬��֤�û���������
		if("����".equals(name) && "1234".equals(password)){
			return true;
		}else{
			return false;
		}
	}
	
}