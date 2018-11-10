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
		//�Ӳ����������л�ȡ��¼ҳ�����ҳ��URI��
		logon_page = filterConfig.getInitParameter(LOGON_URI);
		home_page = filterConfig.getInitParameter(HOME_URI);
		
		if(null == logon_page || null == home_page)
			throw new ServletException("û��ָ����¼ҳ�����ҳ��");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
			//������������Ӧ���������ת��ΪHttpServletRequest �� HttpServletResponse��
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpServletResponse httpResp = (HttpServletResponse)response;
			HttpSession session = httpReq.getSession();
			
			//�õ��û�������URI��
			String request_uri = httpReq.getRequestURI();
			//�õ�webӦ�ó����������·����
			String ctxPath = httpReq.getContextPath();
			//ȥ��������·�����õ�ʣ�ಿ�ֵ�·����
			String uri = request_uri.substring(ctxPath.length());
			
			//�ж��û����ʵ��Ƿ��ǵ�¼ҳ��
			if(logon_page.equals(uri)){
				//����ǵ�¼ҳ�棬��ͨ���鿴�Ƿ��и��ӵĲ�ѯ���������ж��û�
				//�Ƿ��ʵ�¼ҳ�棬�����ύ��¼��Ϣ��
				String strLogon = httpReq.getParameter("action");
				
				if("logon".equals(strLogon)){
					//������ύ��¼��Ϣ������û�������֤��
					String name = httpReq.getParameter("name");
					String password = httpReq.getParameter("password");
					
					if("zhangsan".equals(name) && "1234".equals(password)){
						//��֤ͨ������Session����������isLogon����Ϊtrue��
						session.setAttribute("isLogon", "true");
						//��Session�����б����û�����
						session.setAttribute("user", name);
						
						//�����������ȡ���û���ǰ����ҳ���URI��
						String origin_uri = httpReq.getParameter("origin_uri");
						//���origin_uri��Ϊ�գ��򽫿ͻ����ض����û���ǰ���ʵ�ҳ�棬
						//���򽫿ͻ����ض�����ҳ��
						if(null != origin_uri && !"".equals(origin_uri))
							httpResp.sendRedirect(origin_uri);
						else
							httpResp.sendRedirect(ctxPath + home_page);
						return;
					}else{
						//�����֤ʧ�ܣ������������л�ȡ�û���ǰ����ҳ���URI��
						//�����URI���ڣ����ٴν�����Ϊorigin_uri���Ե�ֵ����
						//����������С�
						String origin_uri = httpReq.getParameter("origin_uri");
						if(null != origin_uri && !"".equals(origin_uri))
							httpReq.setAttribute("origin_uri", origin_uri);
						httpResp.setContentType("text/html;charset=GB2312");
						PrintWriter out = httpResp.getWriter();
						out.println("<h2>�û���������������������롣</h2>");
						RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
						rd.include(httpReq, httpResp);
						return;
					}
				}else{
					//����û������ύ��¼��Ϣ�������chain.doFilter()������
					//���õ�¼ҳ�档
					chain.doFilter(request, response);
					return;
				}
			}else{
				//������ʵĲ��ǵ�¼ҳ�棬���ж��û��Ƿ��Ѿ���¼��
				String isLogon = (String) session.getAttribute("isLogon");
				if("true".equals(isLogon)){
					chain.doFilter(request, response);
					return;
				}else{
					//����û�û�е�¼�����û�������URI��Ϊorigin_uri���Ե�ֵ
					//���浽��������С�
					String strQuery = httpReq.getQueryString();
					if(null != strQuery)
						request_uri = request_uri + "?" + strQuery;
					httpReq.setAttribute("origin_uri", request_uri);
					
					//���û�����ת������¼ҳ�档
					RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
					rd.forward(httpReq, httpResp);
					return;
				}
			}
		}
		
		public void destroy(){}
}