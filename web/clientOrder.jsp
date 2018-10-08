<%@ page import="app.mrquan.pojo.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/7
  Time: 下午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<h1>订单</h1>
<form action="clientOrder">
    <td><input type="radio" name="select" value="allList" checked="checked"/>全部订单<input type="radio" name="select" value="list"/>待使用<input class="button" type="submit" value="查询"></td>
</form>
<%
    String fun = (String) request.getAttribute("fun");
    if ("allList".equals(fun)){
        List<Order> list= (List<Order>) request.getAttribute("lists");
        if (list!=null){
            out.print("<table border=\"1\" align=\"center\">");
            out.print("<tr><th colspan=\"6\">全部订单</th></tr>");
            out.print("<tr><td>订单编号</td><td>预定日期</td><td>预定场地编号</td><td>租借开始时间</td><td>租借结束时间</td><td>使用情况</td></tr>");
            for (int i = 0; i < list.size(); i++) {
                out.print("<tr><td>"+list.get(i).getNumber()+"</td>");
                out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getReservationDate())+"</td>");
                out.print("<td>"+list.get(i).getSiteNumber()+"</td>");
                out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getStartTime())+"</td>");
                out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getEndTime())+"</td>");
                String m;
                if (Boolean.TRUE.equals(list.get(i).getCancel())){
                    m="取消";
                }else {
                    if (list.get(i).getOnTime()==null){
                        m="待使用";
                    }else {
                        m="已使用";
                    }
                }
                out.print("<td>"+m+"</td></tr>");
            }
            out.print("</table>");
        }
    }else {
        if ("list".equals(fun)){
            List<Order> list= (List<Order>) request.getAttribute("lists");
            if (list!=null){
                out.print("<table border=\"1\" align=\"center\">");
                out.print("<tr><th colspan=\"6\">全部订单</th></tr>");
                out.print("<tr><td>订单编号</td><td>预定日期</td><td>预定场地编号</td><td>租借开始时间</td><td>租借结束时间</td><td>取消</td></tr>");
                for (int i = 0; i < list.size(); i++) {
                    out.print("<tr><td>"+list.get(i).getNumber()+"</td>");
                    out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getReservationDate())+"</td>");
                    out.print("<td>"+list.get(i).getSiteNumber()+"</td>");
                    out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getStartTime())+"</td>");
                    out.print("<td>"+new SimpleDateFormat("yyyy:MM:dd HH:mm").format(list.get(i).getEndTime())+"</td>");
                    out.print("<form action=\"clientOrder\"method=\"post\">");
                    out.print("<td><input type=\"submit\" name=\"num\" id=\"0\" value=\"取消\"><input type=\"hidden\" name=\"number\" value=\""+list.get(i).getNumber()+"\"></td>");
                    out.print("</form></tr>");
                }
                out.print("</table>");
            }
        }
    }
%>
</body>
</html>
