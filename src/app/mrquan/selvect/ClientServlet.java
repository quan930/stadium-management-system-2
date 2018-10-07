package app.mrquan.selvect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("menu");
//        System.out.println("查询"+name);
        request.setAttribute("menu",name);
        request.getSession().setAttribute("id","a00002");
        request.getRequestDispatcher("client.jsp").forward(request, response);
    }
}
