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
import java.sql.SQLException;

@WebServlet("/clientOrder")
public class ClientOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("number");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        int m = ServiceFactory.getIClientServiceInstance().cancelReserve(number);
        if (m==1){
            out.print("<script language='javascript'>alert('取消成功');window.location.href='clientOrder.jsp';</script>");
        }else {
            out.print("<script language='javascript'>alert('不可取消');window.location.href='clientOrder.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Personnel pojo = (Personnel) request.getSession().getAttribute("user");
        String select = request.getParameter("select");
        if ("allList".equals(select)){
            request.setAttribute("fun","allList");
            request.setAttribute("lists",ServiceFactory.getIClientServiceInstance().listOrders(pojo.getId()));
            request.getRequestDispatcher("clientOrder.jsp").forward(request,response);
        }else {
            //待使用订单
            if ("list".equals(select)){
                request.setAttribute("fun","list");
                request.setAttribute("lists",ServiceFactory.getIClientServiceInstance().findOrdersToBeUsed(pojo.getId()));
                request.getRequestDispatcher("clientOrder.jsp").forward(request,response);
            }
        }
    }
}