package org.huangjl.ch16.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompressionFilter implements Filter{
	
	public void init(FilterConfig filterConfig) throws ServletException{
		
	}
	
	public void destroy(){}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String acceptEncodings = httpReq.getHeader("Accept-Encoding");
		if(acceptEncodings != null && acceptEncodings.indexOf("gzip") > -1){
			//�õ���Ӧ����ķ�װ�����
			CompressionResponseWrapper respWrapper = new CompressionResponseWrapper(httpResp);
			
			//����Content-Encodingʵ�屨ͷ�����������ʵ�����Ĳ�����gzipѹ������
			respWrapper.setHeader("Content-Encoding", "gzip");
			chain.doFilter(httpReq, respWrapper);
			
			//�õ�GZIPOutputStream���������
			GZIPOutputStream gzipos = respWrapper.getGZIPOutputStream();
			//����GZIPOutputStream����������finish()������ɽ�ѹ������д����Ӧ������Ĳ���������ر������
			gzipos.finish();
		}else{
			chain.doFilter(httpReq, httpResp);
		}
	}
	
}