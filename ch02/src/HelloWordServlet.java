package org.huangjl.ch02.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWordServlet implements Servlet
{
    private ServletConfig config;
    public void destroy(){}

    public ServletConfig getServletConfig()
    {
        return config;
    }

    /**
     * 该方法很少使用，因此返回null即可
     */
    public String getServletInfo()
    {
        return null;
    }

    /**
     * ServletConfig对象有容器构造。容器在调用init()方法时，将其作为参数传给Servlet
     */
    public void init(ServletConfig config) throws ServletException
    {
        this.config = config;
    }

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException
    {
        //得到PrintWriter对象。Servlet使用输出流来产生响应
        PrintWriter out = res.getWriter();
        //向客户发送字符数据
        out.println("Hello Word");
        //关闭输出流
        out.close();
    }
}
