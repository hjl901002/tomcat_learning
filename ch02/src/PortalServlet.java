package org.huangjl.ch02.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortalServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>");
        out.println("登录页面");
        out.println("</title></head><body>");

        String name = req.getParameter("username");
        String pwd = req.getParameter("password");

        if ("zhangsan".equals(name) && "1234".equals(pwd))
        {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/welcome");
            rd.forward(req, resp);
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("login2");
            rd.include(req, resp);        
        }
        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doGet(req, resp);
    }
}
