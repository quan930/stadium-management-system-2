package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("menu");
//        System.out.println("查询"+name);
        request.setAttribute("menu",name);
//        request.getSession().setAttribute("id","a00001");
        request.getSession().setAttribute("user",ServiceFactory.getILoginServiceInstance().login("a00001"));
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}
