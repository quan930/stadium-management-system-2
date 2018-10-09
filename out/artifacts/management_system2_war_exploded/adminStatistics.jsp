<%@ page import="app.mrquan.factory.ServiceFactory" %>
<%@ page import="app.mrquan.pojo.Personnel" %>
<%@ page import="java.util.List" %>
<%@ page import="app.mrquan.pojo.Site" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/8
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>数据统计</h1>
<form action="adminStatistics">
    <input type="radio" name="menu" value="orderNumber" checked="checked"/>预定量&emsp;
    <input type="radio" name="menu" value="turnover"/>营业额&emsp;
    <input type="submit" value="查询"><br>
</form>
    <%
        String menu = request.getParameter("menu");
        if (menu!=null){
            List<Site> sites = (List<Site>) request.getAttribute("listSite");
            if (sites!=null){
                switch (menu){
                    case "orderNumber":
                        out.print("<table border=\"1\" align=\"center\">");
                        for (Site s:sites) {
                            out.print("<tr><th colspan=\"5\">编号:"+s.getNumber()+"</th></tr>");
                            out.print("<tr><td colspan=\"3\" rowspan=\"3\"><img src=\"image?id="+s.getNumber()+"\" height=\"160px\" width=\"250px\"></td><td>场地名称:"+s.getName()+"</td><td>区域:"+s.getDistrict()+"</td></tr>");
                            out.print("<tr><td>允许年龄:"+s.getAgeLow()+"~"+s.getAgeUp()+"</td><td>租金:"+s.getRent()+"</td></tr>");
                            out.print("<tr><td>运动类型:"+s.getMotionType()+"</td><td>预定量:"+s.getOrderNumber()+"</td></tr>");
                        }
                        out.print("</table>");
                        break;
                    case "turnover":
                        if (sites==null){
                            break;
                        }
                        out.print("<table border=\"1\" align=\"center\">");
                        out.print("<tr><th colspan=\"3\">"+sites.get(0).getStadium()+"营业额统计</th></tr>");
                        out.print("<tr><td>场地编号</td><td>场地名字</td><td>营业额</td></tr>");
                        double rent = 0;
                        for (Site s:sites) {
                            out.print("<tr><td>"+s.getNumber()+"</td><td>"+s.getName()+"</td><td>"+s.getRent()+"</td></tr>");
                            rent += s.getRent();
                        }
                        out.print("<tr><td colspan=\"3\">总计:"+rent+"</td></tr>");
                        out.print("</table>");
                        break;
                    default:
                        break;
                }
            }
        }
    %>
</body>
</html>
