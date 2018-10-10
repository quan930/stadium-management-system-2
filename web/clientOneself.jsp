<%@ page import="app.mrquan.pojo.Personnel" %>
<%@ page import="app.mrquan.factory.ServiceFactory" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/7
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>个人</h1>
<%
    Boolean update = (Boolean) request.getAttribute("update");
    Personnel pojo = (Personnel) request.getSession().getAttribute("user");
    if (update==null){
        if (pojo!=null){
            out.print("<table border=\"1\" align=\"center\">");
            out.print("<tr><td>ID</td><td>"+pojo.getId()+"</td><td>姓名</td><td>"+pojo.getName()+"</td></tr>");
            out.print("<tr><td>性别</td><td>"+((pojo.getSex().equals(Boolean.TRUE)) ? "男":"女")+"</td><td>账户余额</td><td>"+pojo.getBalance()+"</td></tr>");

            out.print("<tr><td>年龄</td><td>"+pojo.getAge()+"</td><td>违约次数</td><td>"+(pojo.getAbrogate()==null?0:pojo.getAbrogate())+"</td></tr>");
            out.print("<tr><td colspan=\"2\">电话</td><td colspan=\"2\">"+pojo.getTelephone()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">E-mail</td><td colspan=\"2\">"+pojo.getEmail()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">区域</td><td colspan=\"2\">"+pojo.getDistrict()+"</td></tr>");
            out.print("<form action=\"clientOneself\">");
            out.print("<tr><td colspan=\"4\" align=\"center\"><input type=\"submit\" value=\"修改\"></td></tr>");
            out.print("</form>");
            out.print("</table>");
        }
    }else{
        if (pojo!=null){
            out.print("<form action=\"clientOneself\" method=\"post\">");
            out.print("<table border=\"1\" align=\"center\">");
            out.print("<tr><td>ID</td><td>"+pojo.getId()+"</td><td>姓名</td><td>"+pojo.getName()+"</td></tr>");
            out.print("<tr><td>性别</td><td>"+((pojo.getSex().equals(Boolean.TRUE)) ? "男":"女")+"</td><td>账户余额</td><td>"+pojo.getBalance()+"</td></tr>");
            out.print("<tr><td>年龄</td><td>"+pojo.getAge()+"</td><td>违约次数</td><td>"+(pojo.getAbrogate()==null?0:pojo.getAbrogate())+"</td></tr>");
            out.print("<tr><td colspan=\"2\">密码</td><td><input type=\"checkbox\" name=\"password\">修改</td><td><input type=\"password\" name=\"pass\" maxlength=\"10\" value=\""+pojo.getPassword()+"\"></td></tr>");
            out.print("<tr><td colspan=\"2\">电话</td><td><input type=\"checkbox\" name=\"telephone\">修改</td><td><input type=\"text\" name=\"tel\" value=\""+pojo.getTelephone()+"\" maxlength=\"11\"onkeypress=\"return event.keyCode>=48&&event.keyCode<=57\" ng-pattern=\"/[^a-zA-Z]/\"></td></tr>\n");
            out.print("<tr><td colspan=\"2\">E-mail</td><td><input type=\"checkbox\" name=\"email\">修改</td><td><input type=\"text\" name=\"ema\" value=\""+pojo.getEmail()+"\" maxlength=\"20\"></td></tr>");
            out.print("<tr><td colspan=\"2\">区域</td><td><input type=\"checkbox\" name=\"district\">修改</td><td><input type=\"text\" name=\"dis\" maxlength=\"10\" value=\""+pojo.getDistrict()+"\"></td></tr>");
            out.print("<tr><td colspan=\"4\" align=\"center\"><input type=\"submit\" value=\"修改\"></td></tr>");
            out.print("</table>");
            out.print("</form>");
        }
    }

%>
</body>
</html>
