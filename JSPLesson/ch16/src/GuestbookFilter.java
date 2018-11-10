package org.huangjl.ch16.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GuestbookFilter implements Filter{
	
	private static final String WORD_FILE = "word_file";
	
	HashMap<String, String> hm = new HashMap<String, String>();
	
	/**
	 * ��init()�����У���ȡ�����˲����־���滻���ݵ��ļ���
	 * ���Բ����־���Ϊkey���滻������Ϊvalue�����浽Hashmap�����С�
	 * 
	 */
	 public void init(FilterConfig filterConfig) throws ServletException{
		 String configPath = filterConfig.getInitParameter(WORD_FILE);
		 
		 ServletContext sc = filterConfig.getServletContext();
		 String filePath = sc.getRealPath(configPath);
		 
		 try{
			 FileReader fr = new FileReader(filePath);
			 BufferedReader br = new BufferedReader(fr);
			 
			 String line;
			 while(null != (line = br.readLine())){
				 String[] strTemp = line.split("=");
				 hm.put(strTemp[0], strTemp[1]);
			 }
		 }catch(IOException ie){
			 throw new ServletException("��ȡ�����ļ���Ϣ����");
		 }
	 }
	 
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		 HttpServletRequest httpReq = (HttpServletRequest)request;
		 HttpServletResponse httpResp = (HttpServletResponse)response;
		 
		 //�õ��������Ӧ����ķ�װ����
		 MyRequestWrapper reqWrapper = new MyRequestWrapper(httpReq);
		 MyResponseWrapper respWrapper = new MyResponseWrapper(httpResp);
		 
		 chain.doFilter(reqWrapper, respWrapper);
		 
		 String content = new String(respWrapper.toByteArray());
		 String result = replaceText(content);
		 httpResp.setContentType("text/html;charset=GB2312");
		 PrintWriter out = httpResp.getWriter();
		 out.println(result);
		 out.close();
	 }
	 
	 /**
	  * �������еĲ����־���й��ˡ�
	  * 
	  */
	  public String replaceText(String content) throws IOException{
		  StringBuffer sb = new StringBuffer(content);
		  Set keys = hm.keySet();
		  Iterator it = keys.iterator();
		  while(it.hasNext()){
			  String key = (String)it.next();
			  int index = sb.indexOf(key);
			  while(-1 != index){
				  sb.replace(index, index + key.length(), (String)hm.get(key));
				  index = sb.indexOf(key);
			  }
		  }
		  return sb.toString();
	  }
	  
	  public void destroy(){}
}