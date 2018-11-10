package org.huangjl.ch16.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyResponseWrapper extends HttpServletResponseWrapper{
	private ByteArrayOutputStream baos;
	private ByteArrayServletOutputStream basos;
	private PrintWriter pw;
	
	public MyResponseWrapper(HttpServletResponse response){
		super(response);
		//创建ByteArrayOutputStream对象。
		baos = new ByteArrayOutputStream();
		
		//用ByteArrayOutputStream对象作为参数，
		//构造ByteArrayServletOutputStream对象。
		basos = new ByteArrayServletOutputStream(baos);
		
		//用ByteArrayOutputStream对象作为参数，
		//构造PrinteWriter对象
		pw = new PrintWriter(baos);
	}
	
	public PrintWriter getWriter(){
		return pw;
	}
	
	public ServletOutputStream getOutputStream(){
		return basos;
	}
	
	/**
	 * 以字节的形式返回输出流缓冲区中的内容。
	 * 
	 */
	 public byte[] toByteArray(){
		 return baos.toByteArray();
	 }
}