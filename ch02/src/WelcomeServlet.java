package org.huangjl.ch02.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet
{
    private String greeting;

    public void init()
    {
        greeting = getInitParameter("greeting");
        //greeting = getServletConfig().getInitParameter("greeting");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String welcomeInfo = greeting + ", " + username;
        
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>");
        out.println("Welcome Page");
        out.println("</title></head>");
        out.println("<body>");
        out.println(welcomeInfo);
        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doGet(req, resp);    
    }
}
