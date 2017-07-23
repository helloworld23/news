<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: zhongziming
  Date: 2017/5/6
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Dashboard</title>

    <link href="/MyNews/css/bootstrap.min.css" rel="stylesheet">
    <link href="/MyNews/css/datepicker3.css" rel="stylesheet">
    <link href="/MyNews/css/styles.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="/MyNews/js/html5shiv.js"></script>
    <script src="/MyNews/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .navbar-inverse {
            background-color: rgba(25, 97, 60, 0.95);
            border-color: #080808;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>${user.email}</span></a>
            <a class="navbar-brand" href="#"><span id="timeSpan">
					<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
					<fmt:formatDate value="${date}" type="date" dateStyle="full"></fmt:formatDate>
				</span></a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                        <li><a href="/MyNews/login"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.container-fluid -->
</nav>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li class="active"><a href="/MyNews/user/systemInfo"><span class="glyphicon glyphicon-dashboard"></span>系统公告</a></li>
        <li><a href="/MyNews/news/publish"><span class="glyphicon glyphicon-th"></span>发布新闻</a></li>
        <li><a href="/MyNews/news/userNewsSearch"><span class="glyphicon glyphicon-stats"></span> 查看新闻</a></li>
        <li><a href="/MyNews/user/message"><span class="glyphicon glyphicon-list-alt"></span> 个人信息</a></li>
        <li><a href="/MyNews/index"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>新闻首页</a></li>
        <li><a href="/MyNews/login"><span class="glyphicon glyphicon-user"></span> Login Page</a></li>
    </ul>
</div>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">最近公告</h3>
            </div>
            <table class="table table-condensed table-hover table-bordered">
                <thead><tr><th>content</th><th>createTime</th></tr></thead>
                <tbody>
                <c:forEach var="systemInfo" items="${systemInfos}">
                    <tr><th>${systemInfo.content}</th>
                        <th><fmt:formatDate value="${systemInfo.createTime}" type="date" dateStyle="full"></fmt:formatDate></th></tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="/MyNews/js/jQuery/jquery-1.11.1.min.js"></script>
<script src="/MyNews/js/bootstrap/bootstrap.min.js"></script>
<script src="/MyNews/js/dataTables/jquery.dataTables.min.js"></script>
<script src="/MyNews/js/dataTables/dataTables.bootstrap.min.js"></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script src="/MyNews/js/userMain.js"></script>
<script>
</script>
</body>

</html>
