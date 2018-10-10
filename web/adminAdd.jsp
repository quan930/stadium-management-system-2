<%@ page import="app.mrquan.pojo.Personnel" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/8
  Time: 下午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h1>添加场地</h1>
<form action="adminAdd" method="post" enctype="multipart/form-data">
    <table border="1" align="center">
        <tr>
            <th colspan="4">添加场地</th>
        </tr>
        <tr>
            <td>场地编号</td>
            <td align="center"><input type="text" name="number" maxlength="10"></td>
            <td>场地名称</td>
            <td align="center"><input type="text" name="name" maxlength="10"></td>
        </tr>
        <tr>
            <td>场馆名称</td>
            <%
                Personnel personnel = (Personnel) request.getSession().getAttribute("user");
                out.print("<td align=\"center\">"+personnel.getStadium()+"</td>");
            %>
            <td>场地区域</td>
            <td align="center"><input type="text" name="district" maxlength="10"></td>
        </tr>
        <tr>
            <td>年龄上限</td>
            <td align="center"><input type="number" name="ageUp"></td>
            <td>年龄下限</td>
            <td align="center"><input type="number" name="ageLow"></td>
        </tr>
        <tr>
            <td>租金</td>
            <td align="center"><input type="number" name="rent"></td>
            <td>运动类型</td>
            <td align="center"><input type="text" name="motionType" maxlength="10"></td>
        </tr>
        <tr>
            <td>运动简介</td>
            <td align="center" colspan="3">
                <textarea name="motionProfile" rows="2" cols="50"></textarea>
            </td>
        </tr>
        <tr>
            <td>场地图片</td>
            <td colspan="3" align="center"><input type="file" name="picture"></td>
        </tr>
        <tr>
            <td colspan="4" align="center"><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
