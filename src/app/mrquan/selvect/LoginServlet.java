package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Personnel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        Personnel personnel = ServiceFactory.getILoginServiceInstance().login(id);
        if (personnel==null){
            out.print("<script language='javascript'>alert('账号错误');window.location.href='login';</script>");
        }else {
            if (personnel.getPassword().equals(password)){
                if (Boolean.TRUE.equals(personnel.getAdministrator())){
                    request.getSession().setAttribute("user",personnel);
                    response.sendRedirect("admin");
                }else {
                    request.getSession().setAttribute("user",personnel);
                    response.sendRedirect("client");
                }
            }else {
                out.print("<script language='javascript'>alert('密码错误');window.location.href='login';</script>");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Personnel personnel = (Personnel) request.getSession().getAttribute("user");
        if (personnel==null){
            request.getRequestDispatcher("index.html").forward(request, response);
        }else {
            if (Boolean.TRUE.equals(personnel.getAdministrator())){
                response.sendRedirect("admin");
            }else {
                response.sendRedirect("client");
            }
        }
    }
}