package app.mrquan.selvect;

import app.mrquan.pojo.Personnel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String name = request.getParameter("menu");
        request.setAttribute("menu",name);
        Personnel personnel = (Personnel) request.getSession().getAttribute("user");
        if (personnel==null){
            out.print("<script language='javascript'>alert('没有登录');window.location.href='/system2';</script>");
        }else {
            request.getRequestDispatcher("client.jsp").forward(request, response);
        }
    }
}