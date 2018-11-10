package org.huangjl.ch16.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogonFilter implements Filter{
	
	private static final String LOGON_URI = "logon_uri";
	private static final String HOME_URI = "home_uri";
	
	private String logon_page;
	private String home_page;
	
	public void init(FilterConfig filterConfig)
		throws ServletException{
		//从部署描述符中获取登录页面和首页的URI。
		logon_page = filterConfig.getInitParameter(LOGON_URI);
		home_page = filterConfig.getInitParameter(HOME_URI);
		
		if(null == logon_page || null == home_page)
			throw new ServletException("没有指定登录页面或主页！");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
			//将请求对象和响应对象的类型转换为HttpServletRequest 和 HttpServletResponse。
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpServletResponse httpResp = (HttpServletResponse)response;
			HttpSession session = httpReq.getSession();
			
			//得到用户的请求URI。
			String request_uri = httpReq.getRequestURI();
			//得到web应用程序的上下文路径。
			String ctxPath = httpReq.getContextPath();
			//去除上下文路径，得到剩余部分的路径。
			String uri = request_uri.substring(ctxPath.length());
			
			//判断用户访问的是否是登录页面
			if(logon_page.equals(uri)){
				//如果是登录页面，则通过查看是否有附加的查询参数，来判断用户
				//是访问登录页面，还是提交登录信息。
				String strLogon = httpReq.getParameter("action");
				
				if("logon".equals(strLogon)){
					//如果是提交登录信息，则对用户进行验证。
					String name = httpReq.getParameter("name");
					String password = httpReq.getParameter("password");
					
					if("zhangsan".equals(name) && "1234".equals(password)){
						//验证通过后，在Session对象中设置isLogon属性为true。
						session.setAttribute("isLogon", "true");
						//在Session对象中保存用户名。
						session.setAttribute("user", name);
						
						//从请求对象中取出用户先前访问页面的URI。
						String origin_uri = httpReq.getParameter("origin_uri");
						//如果origin_uri不为空，则将客户端重定向到用户先前访问的页面，
						//否则将客户端重定向到首页。
						if(null != origin_uri && !"".equals(origin_uri))
							httpResp.sendRedirect(origin_uri);
						else
							httpResp.sendRedirect(ctxPath + home_page);
						return;
					}else{
						//如果验证失败，则从请求对象中获取用户先前访问页面的URI。
						//如果该URI存在，则再次将它作为origin_uri属性的值保存
						//到请求对象中。
						String origin_uri = httpReq.getParameter("origin_uri");
						if(null != origin_uri && !"".equals(origin_uri))
							httpReq.setAttribute("origin_uri", origin_uri);
						httpResp.setContentType("text/html;charset=GB2312");
						PrintWriter out = httpResp.getWriter();
						out.println("<h2>用户名或密码错误，请重新输入。</h2>");
						RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
						rd.include(httpReq, httpResp);
						return;
					}
				}else{
					//如果用户不是提交登录信息，则调用chain.doFilter()方法，
					//调用登录页面。
					chain.doFilter(request, response);
					return;
				}
			}else{
				//如果访问的不是登录页面，则判断用户是否已经登录。
				String isLogon = (String) session.getAttribute("isLogon");
				if("true".equals(isLogon)){
					chain.doFilter(request, response);
					return;
				}else{
					//如果用户没有登录，则将用户的请求URI作为origin_uri属性的值
					//保存到请求对象中。
					String strQuery = httpReq.getQueryString();
					if(null != strQuery)
						request_uri = request_uri + "?" + strQuery;
					httpReq.setAttribute("origin_uri", request_uri);
					
					//将用户请求转发到登录页面。
					RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
					rd.forward(httpReq, httpResp);
					return;
				}
			}
		}
		
		public void destroy(){}
}