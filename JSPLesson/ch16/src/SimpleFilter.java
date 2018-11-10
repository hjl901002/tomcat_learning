package org.huangjl.ch16.filter;

import java.io.*;
import javax.servlet.*;

public class SimpleFilter implements Filter{
	
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		response.setContentType("text/html;charset=GB2312");
		PrintWriter out = response.getWriter();
		out.println("before doFilter()");
		chain.doFilter(request, response);
		out.println("after doFilter()");
		out.close();
	}
	
	public void destroy(){}
}