<%@ page import="app.mrquan.pojo.Personnel" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/8
  Time: 下午4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
 <h1>个人信息管理</h1>
<%
    Boolean update = (Boolean) request.getAttribute("update");
    Personnel personnel = (Personnel) request.getSession().getAttribute("user");
    if (update==null){
        if (personnel!=null){
            out.print("<table border=\"1\" align=\"center\">");
            out.print("<tr><td>ID</td><td>"+personnel.getId()+"</td><td>姓名</td><td>"+personnel.getName()+"</td></tr>");
            out.print("<tr><td>性别</td><td>"+((personnel.getSex().equals(Boolean.TRUE)) ? "男":"女")+"<td>年龄</td><td>"+personnel.getAge()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">电话</td><td colspan=\"2\">"+personnel.getTelephone()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">E-mail</td><td colspan=\"2\">"+personnel.getEmail()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">所属场馆</td><td colspan=\"2\">"+personnel.getStadium()+"</td></tr>");
            out.print("<form action=\"adminOneself\">");
            out.print("<tr><td colspan=\"4\" align=\"center\"><input type=\"submit\" value=\"修改\"></td></tr>");
            out.print("</form>");
            out.print("</table>");
        }
    }else {
        if (personnel!=null){
            out.print("<form action=\"adminOneself\" method=\"post\">");
            out.print("<table border=\"1\" align=\"center\">");
            out.print("<tr><td>ID</td><td>"+personnel.getId()+"</td><td>姓名</td><td>"+personnel.getName()+"</td></tr>");
            out.print("<tr><td>性别</td><td>"+((personnel.getSex().equals(Boolean.TRUE)) ? "男":"女")+"<td>年龄</td><td>"+personnel.getAge()+"</td></tr>");
            out.print("<tr><td colspan=\"2\">密码</td><td><input type=\"checkbox\" name=\"password\">修改</td><td><input type=\"password\" name=\"pass\" maxlength=\"10\" value=\""+personnel.getPassword()+"\"></td></tr>");
            out.print("<tr><td colspan=\"2\">电话</td><td><input type=\"checkbox\" name=\"telephone\">修改</td><td><input type=\"text\" name=\"tel\" value=\""+personnel.getTelephone()+"\" maxlength=\"11\"onkeypress=\"return event.keyCode>=48&&event.keyCode<=57\" ng-pattern=\"/[^a-zA-Z]/\"></td></tr>\n");
            out.print("<tr><td colspan=\"2\">E-mail</td><td><input type=\"checkbox\" name=\"email\">修改</td><td><input type=\"text\" name=\"ema\" value=\""+personnel.getEmail()+"\" maxlength=\"20\"></td></tr>");
            out.print("<tr><td colspan=\"2\">所属场馆</td><td colspan=\"2\">"+personnel.getStadium()+"</td></tr>");
            out.print("<tr><td colspan=\"4\" align=\"center\"><input type=\"submit\" value=\"修改\"></td></tr>");
            out.print("</table>");
            out.print("</form>");
        }
    }
%>
</body>
</html>
