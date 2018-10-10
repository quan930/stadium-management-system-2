<%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/8
  Time: 下午3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="shortcut icon" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <title>管理端</title>
    <style>
        .outer {
            height: calc(100% - 100px);
            box-sizing: border-box ;
            position: relative;
        }
        .outer_1{
            height: 100px;
            padding: 0;
            margin: 0;
            background-color: #b1d5d6;
        }
        .left {
            float: left;
            width: 300px;
            height: 100%;
            background-color: antiquewhite;
        }
        .right {
            background-color: azure;
            margin-left: 300px;
            height: 100%;
        }
        .top{
            text-align:center;
        }
        #submitRight{
            float: right;
        }
    </style>
</head>
<body>
<div class="outer_1">
    <th class="p">管理端</th>
    <form id="submitRight" action="switch"><input type="submit" value="切换账户"></form>
    <div class="top">
        <h1>综合体育管理系统</h1>
    </div>
</div>
<div class="content"></div>
<div class="outer">
    <div class="left">
        <form action="admin" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="submit" value="订单查询"><input type="hidden" name="menu" value="order">
        </form>
        <form action="admin" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="submit" value="统计"><input type="hidden" name="menu" value="statistics">
        </form>
        <form action="admin" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="submit" value="添加场地"><input type="hidden" name="menu" value="add">
        </form>
        <form action="admin" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="submit" value="个人"><input type="hidden" name="menu" value="oneself">
        </form>
    </div>
    <div class="right">
        <%
            String fun =  request.getParameter("menu");
            if (fun==null){
                fun="1";
            }
            switch (fun){
                case "order":
                    out.print("<iframe src=\"adminOrder.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "statistics":
                    out.print("<iframe src=\"adminStatistics.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "add":
                    out.print("<iframe src=\"adminAdd.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "oneself":
                    out.print("<iframe src=\"adminOneself.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "1" :
                    out.print("<iframe src=\"\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                default:
                    break;
            }

        %>
    </div>
</div>
</body>
</html>