<%@ page import="app.mrquan.factory.ServiceFactory" %>
<%@ page import="app.mrquan.pojo.Site" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/7
  Time: 下午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%
    out.print("<form action=\"clientReserve\">");
    Map<String,Set<String>> map = ServiceFactory.getIClientServiceInstance().listSportsInit();
    Set<String> name = map.get("name");
    out.print("<select name=\"name\">");
    for (String str:name) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"sportName\" checked=\"checked\"/>场地&emsp;");

    Set<String> stadium = map.get("stadium");
    out.print("<select name=\"stadium\">");
    for (String str:stadium) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"stadium\"/>场馆&emsp;");

    Set<String> type = map.get("type");
    out.print("<select name=\"type\">");
    for (String str:type) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    Set<String> district = map.get("district");
    out.print("<select name=\"district\">");
    for (String str:district) {
        out.print("<option value=\""+str+"\">"+str+"</option>");
    }
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"typeDistrict\"/>类别/区域&emsp;");

    out.print("<select name=\"reserve\">");
    out.print("<option value=\"true\">有预定</option>");
    out.print("<option value=\"false\">无预定</option>");
    out.print("</select>");
    out.print("<input type=\"radio\" name=\"menu\" value=\"reserveJudge\"/>是否预定&emsp;");

    out.print("<input type=\"radio\" name=\"menu\" value=\"rent\"/>租金排序&emsp;");

    out.print("<input type=\"radio\" name=\"menu\" value=\"reserveNumber\"/>预定量排序&emsp;");

    out.print("<input type=\"submit\" value=\"查询\">");
    out.print("<br>");
    out.print("</form>");
    List<Site> pojos = (List<Site>) request.getAttribute("listSport");
    if (pojos!=null){//有查询内容
        //显示查询后表格
        out.print("<form action=\"clientReserve\" method=\"post\">");
        out.print("<table border=\"1\" align=\"center\">");
        for (Site s:pojos) {
            out.print("<tr><th colspan=\"6\">"+s.getName()+"</th></tr>");
            out.print("<tr><td colspan=\"3\" rowspan=\"3\"><img src=\"image?id="+s.getNumber()+"\" height=\"160px\" width=\"250px\"></td><td>所属场馆:"+s.getStadium()+"</td><td>区域:"+s.getDistrict()+"</td><td>运动类型:"+s.getMotionType()+"</td></tr>");
            out.print("<tr><td>允许年龄:"+s.getAgeLow()+"~"+s.getAgeUp()+"</td><td>租金:"+s.getRent()+"</td><td align=\"center\">预定"+"<input type=\"checkbox\" name=\"number\" value=\""+s.getNumber()+"\">"+"</td></tr>");
            out.print("");
            out.print("<tr><td colspan=\"3\">租借时间");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR,7);
            String minDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR,1);
            String maxTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            out.print("<input type=\"date\" name=\""+s.getNumber()+"date\" value=\""+minDate+"\" min=\""+minDate+"\" max=\""+maxTime+"\">");
            //租借时间
            out.print("<select name=\""+s.getNumber()+"startTime"+"\">");
            for (int i = 6; i <= 22 ; i++) {
                out.print("<option value=\""+String.format("%02d",i)+":00\">"+String.format("%02d",i)+":00</option>");
            }
            out.print("</select>");
            out.print("～<select name=\""+s.getNumber()+"endTime"+"\">");
            for (int i = 6; i <= 22 ; i++) {
                out.print("<option value=\""+String.format("%02d",i)+":00\">"+String.format("%02d",i)+":00</option>");
            }
            out.print("</select>");
            out.print("</td></tr>");
        }
        out.print("<tr><td colspan=\"6\" align=\"center\"><input type=\"submit\" value=\"预定\"></td></tr>");
        out.print("</table>");
        out.print("</form>");
    }
%>

<%--<tr><td colspan="3" rowspan="3"><img src="image.jpeg" height="160px" width="250px"></td><td>所属场馆</td><td>区域</td><td>运动类型</td></tr>--%>
<%--<tr><td>允许年龄</td><td>租金</td><td>预定</td></tr>--%>

<%--<table border="1" align="center">--%>
    <%--<tr>--%>
        <%--<th colspan="6">场地名称</th>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="3" rowspan="3"><img src="image.jpeg" height="160px" width="250px"></td>--%>
        <%--<td>所属场馆</td>--%>
        <%--<td>区域</td>--%>
        <%--<td>运动类型</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>允许年龄</td>--%>
        <%--<td>租金</td>--%>
        <%--<td>预定</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="3">--%>
            <%--<input type="date" value="2018-09-30" min="2018-01-01" max="2020-01-01">--%>
            <%--开始时间--%>
            <%--<select name="name">--%>
                <%--<option value="06:00">06:00</option>--%>
                <%--<option value="07:00">07:00</option>--%>
                <%--<option value="08:00">08:00</option>--%>
                <%--<option value="09:00">09:00</option>--%>
                <%--<option value="10:00">10:00</option>--%>
                <%--<option value="11:00">11:00</option>--%>
                <%--<option value="12:00">12:00</option>--%>
                <%--<option value="13:00">13:00</option>--%>
                <%--<option value="14:00">14:00</option>--%>
                <%--<option value="15:00">15:00</option>--%>
                <%--<option value="16:00">16:00</option>--%>
                <%--<option value="17:00">17:00</option>--%>
                <%--<option value="18:00">18:00</option>--%>
                <%--<option value="19:00">19:00</option>--%>
                <%--<option value="20:00">20:00</option>--%>
                <%--<option value="21:00">21:00</option>--%>
                <%--<option value="22:00">22:00</option>--%>
            <%--</select>--%>
            <%--结束时间--%>
            <%--<select name="name">--%>
                <%--<option value="06:00">06:00</option>--%>
                <%--<option value="07:00">07:00</option>--%>
                <%--<option value="08:00">08:00</option>--%>
                <%--<option value="09:00">09:00</option>--%>
                <%--<option value="10:00">10:00</option>--%>
                <%--<option value="11:00">11:00</option>--%>
                <%--<option value="12:00">12:00</option>--%>
                <%--<option value="13:00">13:00</option>--%>
                <%--<option value="14:00">14:00</option>--%>
                <%--<option value="15:00">15:00</option>--%>
                <%--<option value="16:00">16:00</option>--%>
                <%--<option value="17:00">17:00</option>--%>
                <%--<option value="18:00">18:00</option>--%>
                <%--<option value="19:00">19:00</option>--%>
                <%--<option value="20:00">20:00</option>--%>
                <%--<option value="21:00">21:00</option>--%>
                <%--<option value="22:00">22:00</option>--%>
            <%--</select>--%>
        <%--</td>--%>
    <%--</tr>--%>

    <%--<tr>--%>
        <%--<th colspan="6">场地名称</th>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="3" rowspan="3"><img src="image.jpeg" height="160px" width="250px"></td>--%>
        <%--<td>所属场馆</td>--%>
        <%--<td>区域</td>--%>
        <%--<td>运动类型</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>允许年龄</td>--%>
        <%--<td>租金</td>--%>
        <%--<td>预定</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="3">--%>
            <%--<input type="date" value="2018-09-30" min="2018-01-01" max="2020-01-01">--%>
            <%--开始时间--%>
            <%--<select name="name">--%>
                <%--<option value="06:00">06:00</option>--%>
                <%--<option value="07:00">07:00</option>--%>
                <%--<option value="08:00">08:00</option>--%>
                <%--<option value="09:00">09:00</option>--%>
                <%--<option value="10:00">10:00</option>--%>
                <%--<option value="11:00">11:00</option>--%>
                <%--<option value="12:00">12:00</option>--%>
                <%--<option value="13:00">13:00</option>--%>
                <%--<option value="14:00">14:00</option>--%>
                <%--<option value="15:00">15:00</option>--%>
                <%--<option value="16:00">16:00</option>--%>
                <%--<option value="17:00">17:00</option>--%>
                <%--<option value="18:00">18:00</option>--%>
                <%--<option value="19:00">19:00</option>--%>
                <%--<option value="20:00">20:00</option>--%>
                <%--<option value="21:00">21:00</option>--%>
                <%--<option value="22:00">22:00</option>--%>
            <%--</select>--%>
            <%--结束时间--%>
            <%--<select name="name">--%>
                <%--<option value="06:00">06:00</option>--%>
                <%--<option value="07:00">07:00</option>--%>
                <%--<option value="08:00">08:00</option>--%>
                <%--<option value="09:00">09:00</option>--%>
                <%--<option value="10:00">10:00</option>--%>
                <%--<option value="11:00">11:00</option>--%>
                <%--<option value="12:00">12:00</option>--%>
                <%--<option value="13:00">13:00</option>--%>
                <%--<option value="14:00">14:00</option>--%>
                <%--<option value="15:00">15:00</option>--%>
                <%--<option value="16:00">16:00</option>--%>
                <%--<option value="17:00">17:00</option>--%>
                <%--<option value="18:00">18:00</option>--%>
                <%--<option value="19:00">19:00</option>--%>
                <%--<option value="20:00">20:00</option>--%>
                <%--<option value="21:00">21:00</option>--%>
                <%--<option value="22:00">22:00</option>--%>
            <%--</select>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td colspan="6" align="center">--%>
            <%--<input type="submit">--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</table>--%>
</body>
</html>
