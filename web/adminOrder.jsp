<%@ page import="app.mrquan.pojo.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="app.mrquan.pojo.Personnel" %>
<%@ page import="app.mrquan.factory.ServiceFactory" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/8
  Time: 下午4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>订单查询</h1>
<%
    Personnel personnel = (Personnel) request.getSession().getAttribute("user");
    List<Order> list = ServiceFactory.getIAdminService().listOrder(personnel.getStadium());
    if (list!=null) {
        out.print("<table border=\"1\" align=\"center\">");
        out.print("<tr><th colspan=\"6\">全部订单</th></tr>");
        out.print("<tr><td>订单编号</td><td>预定日期</td><td>预定场地编号</td><td>租借开始时间</td><td>租借结束时间</td><td>使用情况</td></tr>");
        for (int i = 0; i < list.size(); i++) {
            out.print("<tr><td>" + list.get(i).getNumber() + "</td>");
            out.print("<td>" + new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getReservationDate()) + "</td>");
            out.print("<td>" + list.get(i).getSiteNumber() + "</td>");
            out.print("<td>" + new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getStartTime()) + "</td>");
            out.print("<td>" + new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getEndTime()) + "</td>");
            String m;
            if (Boolean.TRUE.equals(list.get(i).getCancel())) {
                m = "取消";
            } else {
                if (list.get(i).getOnTime() == null) {
                    m = "待使用";
                } else {
                    m = "已使用";
                }
            }
            out.print("<td>" + m + "</td></tr>");
        }
        out.print("</table>");
    }
%>
</body>
</html>