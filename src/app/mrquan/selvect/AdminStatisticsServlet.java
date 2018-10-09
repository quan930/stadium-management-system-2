package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminStatistics")
public class AdminStatisticsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menu = request.getParameter("menu");
        Personnel personnel = (Personnel) request.getSession().getAttribute("user");
        //没有登录
        if (menu!=null){
            List<Site> sites = null;
            switch (menu){
                case "orderNumber":
                    sites = ServiceFactory.getIAdminService().listSiteByReserve(personnel.getStadium());
                    for (Site s:sites) {
                        request.getSession().setAttribute(s.getNumber(),s.getPicture());
                    }
                    request.setAttribute("listSite",sites);
                    break;
                case "turnover":
                    sites = ServiceFactory.getIAdminService().listSiteByTurnover(personnel.getStadium());
                    request.setAttribute("listSite",sites);
                    break;
                default:
                    break;
            }
        }
        request.getRequestDispatcher("adminStatistics.jsp").forward(request,response);
    }
}
