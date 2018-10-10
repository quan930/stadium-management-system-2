<%--
  Created by IntelliJ IDEA.
  User: daquan
  Date: 2018/10/7
  Time: 下午6:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="shortcut icon" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <title>顾客端</title>
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
    <th class="p">客户端</th>
    <form id="submitRight" action="switch"><input type="submit" value="切换账户"></form>
    <div class="top">
        <h1>综合体育管理系统</h1>
    </div>
</div>
<div class="content"></div>
<div class="outer">
    <div class="left">
        <form action="client" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="hidden" name="menu" value="reserve">
            <input type="submit" value="预订">
        </form>
        <form action="client" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="hidden" name="menu" value="oneself">
            <input type="submit" value="个人">
        </form>
        <form action="client" method="get" enctype="multipart/form-data" accept-charset="UTF-8" >
            <input type="hidden" name="menu" value="order">
            <input type="submit" value="历史订单">
        </form>
    </div>
    <div class="right">
        <%
            String fun =  request.getParameter("menu");
            if (fun==null){
                fun="1";
            }
            switch (fun){
                case "reserve":
                    out.print("<iframe src=\"clientReserve.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "oneself":
                    out.print("<iframe src=\"clientOneself.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
                    break;
                case "order":
                    out.print("<iframe src=\"clientOrder.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\">");
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
