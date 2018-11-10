package org.huangjl.ch16.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class MyRequestWrapper extends HttpServletRequestWrapper{
	
	public MyRequestWrapper(HttpServletRequest request){
		super(request);
	}
	
	/**
	 * 覆盖基类的getParameter()方法，对请求参数的值进行过滤
	 * 
	 */
	public java.lang.String getParameter(java.lang.String name){
		String value = super.getParameter(name);
		if(null != value)
			return toHtml(value.trim());
		else
			return null;
	}
	
	/**
	 * 将特殊字符转换为对应的实体引用或字符引用
	 * 
	 */
	private String toHtml(String str){
		if(str == null)
			return null;
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for(int i = 0; i < len; i++){
			char c = str.charAt(i);
			switch(c){
				case ' ':
					sb.append("&nbsp;");
					break;
				case '\n':
					sb.append("<br>");
					break;
				case '\r':
					break;
				case '\'':
					sb.append("&#39;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '"':
					sb.append("&#34;");
					break;
				case '\\':
					sb.append("&#92;");
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}
}