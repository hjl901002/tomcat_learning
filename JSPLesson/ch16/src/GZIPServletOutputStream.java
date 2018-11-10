package org.huangjl.ch16.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

public class GZIPServletOutputStream extends ServletOutputStream{
	
	private GZIPOutputStream gzipos;
	
	public GZIPServletOutputStream(ServletOutputStream sos) throws IOException{
		//ʹ����Ӧ�����������GZIPOutputStream����������
		this.gzipos = new GZIPOutputStream(sos);
	}
	
	public void setWriteListener(javax.servlet.WriteListener listener){}
	
	public boolean isReady(){
		return true;
	}
	
	public void write(int data) throws IOException{
		//��д�����ί�и�GZIPOutputStream�����write()�������Ӷ�ʵ����Ӧ�������ѹ��
		gzipos.write(data);
	}
	
	/**
	 * ����GZIPOutputStream���󣬹�������Ҫ������������Ա���ɽ�ѹ������д��������Ĳ���
	 * 
	 */
	public GZIPOutputStream getGZIPOutputStream(){
		return gzipos;
	}
}