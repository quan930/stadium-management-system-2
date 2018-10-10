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

@WebServlet("/adminOneself")
public class AdminOneselfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Personnel personnel = (Personnel) request.getSession().getAttribute("user");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        if (personnel==null){
            out.print("<script language='javascript'>alert('失败');window.location.href='adminOneself.jsp';</script>");
            return;
        }

        boolean update = false;
        if (request.getParameter("telephone")!=null){
            update = true;
            String telephone = request.getParameter("tel");
            personnel.setTelephone(telephone);
        }
        if (request.getParameter("password")!=null){
            update = true;
            String password = request.getParameter("pass");
            personnel.setPassword(password);
        }
        if (request.getParameter("email")!=null){
            update = true;
            String email = request.getParameter("ema");
            if (fun(email)){
                personnel.setEmail(email);
            }else {
                out.print("<script language='javascript'>alert('邮箱格式错误');window.location.href='adminOneself.jsp';</script>");
                return;
            }
        }
        int m = 0;
        if (update){
            m = ServiceFactory.getIAdminService().changePersonnel(personnel);//更新
        }
        if (m==1){
            out.print("<script language='javascript'>alert('成功');window.location.href='adminOneself.jsp';</script>");
        }else {
            out.print("<script language='javascript'>alert('失败');window.location.href='adminOneself.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean update = true;
        request.setAttribute("update",update);
        request.getRequestDispatcher("adminOneself.jsp").forward(request,response);
    }
    private boolean fun(String s) {//判断邮箱
        if ((s.split("@")) != null) {

        } else {
            return false;
        }
        if ((s.split("\\.")) != null) {

        } else {
            return false;
        }
        int length = s.length();
        int m = s.indexOf("@");
        int n = s.indexOf(".");
        if ((m > 0) && (n < length - 1) && ((n - m) > 0)) {
            return true;
        } else {
            return false;
        }

    }
}