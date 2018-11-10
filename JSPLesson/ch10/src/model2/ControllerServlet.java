package org.huangjl.ch10.model2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huangjl.ch10.model2.beans.UserBean;
import org.huangjl.ch10.model2.beans.UserCheckBean;

public class ControllerServlet extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		
		String action = request.getParameter("action");
		
		if(!isValidated(request) && !("login".equals(action))){
			gotoPage("/WEB-INF/pages/login2.jsp", request, response);
			return;
		}
		if("login".equals(action)){
			UserBean user = new UserBean();
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			
			UserCheckBean uc = new UserCheckBean(user);
			
			if(uc.validate()){
				HttpSession session = request.getSession();
				//将user对象保存到Session对象中，在welcome.jsp中通过
				//<jsp:useBean>动作元素从Session中得到user对象。
				session.setAttribute("user", user);
				//验证成功，将请求转向welcome.jsp。
				gotoPage("/WEB-INF/pages/welcome.jsp", request, response);
			}else{
				//验证失败，将请求转向loginerr.jsp。
				gotoPage("/WEB-INF/pages/loginerr.jsp", request, response);
			}
		}
	}
	
	/**
	* 判断用户是否已经登录了。
	* 
	*/
	private boolean isValidated(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
			return true;
		else
			return false;
	}
	
	/**
	* 将请求导向指定页面。
	* 
	*/
	private void gotoPage(String targetURL, HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException{
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(targetURL);
		rd.forward(request, response);
	}
	
}