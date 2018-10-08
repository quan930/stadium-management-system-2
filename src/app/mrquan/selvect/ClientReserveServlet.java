package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Site;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/clientReserve")
public class ClientReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =(String)request.getSession().getAttribute("id");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String numbers[] = request.getParameterValues("number");//获取复选框选中内容
        if (numbers==null){
            return;
        }
        List<Order> orders = new ArrayList<>();
        boolean yOrN = true;//判断是否有时间错误
        for (String num:numbers) {
            String date = request.getParameter(num+"date");
            String startTime = request.getParameter(num+"startTime");//开始时间
            String endTime = request.getParameter(num+"endTime");//结束
            try {
                Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+startTime);
                Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+endTime);
                if (start.getTime()<end.getTime()){
                    /*
                     *  创造订单
                     *  订单编号，预定日期，预定场地编号，租借场地日期，租借场地开始时间，租借场地结束时间,顾客id
                     */
                    Order order = new Order();
                    order.setReservationDate(new Date());//预定日期
                    order.setSiteNumber(num);
                    order.setLoanDate( new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    order.setStartTime(start);//开始时间
                    order.setEndTime(end);//结束时间
                    order.setId(id);//顾客id
                    orders.add(order);
                }else {
                    yOrN = false;
                }
            } catch (ParseException e) {
                out.print("<script language='javascript'>alert('时间错误');window.location.href='clientReserve.jsp';</script>");
                e.printStackTrace();
            }
        }
        if (yOrN){
            String m = ServiceFactory.getIClientServiceInstance().reserve(orders);
            out.print("<script language='javascript'>alert('"+m+"');window.location.href='clientReserve.jsp';</script>");
        }else {
            out.print("<script language='javascript'>alert('时间错误');window.location.href='clientReserve.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menu= request.getParameter("menu");
        List<Site> sites = null;//判断 null
        switch (menu){
            case "sportName"://名字查找
                String name = request.getParameter("name");
                sites = ServiceFactory.getIClientServiceInstance().findSiteByName(name);
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",sites);
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            case "stadium"://场馆查找
                String stadium = request.getParameter("stadium");
                sites = ServiceFactory.getIClientServiceInstance().findSiteByStadium(stadium);
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",ServiceFactory.getIClientServiceInstance().findSiteByStadium(stadium));
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            case "typeDistrict"://运动类别 区域
                String type = request.getParameter("type");
                String district = request.getParameter("district");
                sites = ServiceFactory.getIClientServiceInstance().findSiteByTypeAndDistrict(type,district);
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",sites);
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            case "reserveJudge"://是否预定查找
                String reserve = request.getParameter("reserve");
                boolean yOrN = false;
                if ("true".equals(reserve)){
                    yOrN = true;
                }
                sites = ServiceFactory.getIClientServiceInstance().findSiteByReserve(yOrN);
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",sites);
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            case "rent"://租金排序
                sites = ServiceFactory.getIClientServiceInstance().listSiteByRent();
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",sites);
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            case "reserveNumber"://预定量排序
                sites = ServiceFactory.getIClientServiceInstance().listSiteByReserve();
                for (Site s:sites) {
                    request.getSession().setAttribute(s.getNumber(),s.getPicture());
                }
                request.setAttribute("listSport",sites);
                request.getRequestDispatcher("clientReserve.jsp").forward(request,response);
                break;
            default:
                break;
        }
    }
}
