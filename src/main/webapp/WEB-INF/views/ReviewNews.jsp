<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="/MyNews/css/font-awesome.min.css" rel="stylesheet">
    <link href="/MyNews/css/styles.css" rel="stylesheet">
    <link href="/MyNews/css/toastr.min.css" rel="stylesheet">
    <link href="/MyNews/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/MyNews/css/userMessage.css" rel="stylesheet">

    <style type="text/css">
        .navbar-inverse {
            background-color: rgba(25, 97, 60, 0.95);
            border-color: #080808;
        }

    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>${admin.email}</span></a>
            <a class="navbar-brand" href="#"><span id="timeSpan">
					<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
					<fmt:formatDate value="${date}" type="date" dateStyle="full"></fmt:formatDate>
				</span></a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                            class="glyphicon glyphicon-user"></span> admin <span class="caret"></span></a>
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
        <li><a href="/MyNews/admin/systemInfo"><span
                class="glyphicon glyphicon-dashboard"></span>系统公告</a></li>
        <li class="active"><a href="/MyNews/news/review"><span class="glyphicon glyphicon-th"></span>审核新闻</a></li>
        <li><a href="/MyNews/news/adminNewsSearch"><span class="glyphicon glyphicon-stats"></span> 查找新闻</a></li>
        <li><a href="/MyNews/user/search"><span class="glyphicon glyphicon-list-alt"></span> 用户管理</a></li>
        <li><a href="/MyNews/admin/search"><span class="glyphicon glyphicon-list-alt"></span> 管理员信息管理</a></li>
        <li><a href="/MyNews/index"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>新闻首页</a></li>
        <li><a href="/MyNews/login"><span class="glyphicon glyphicon-user"></span> Login Page</a></li>
    </ul>
</div>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">审核新闻</h3>
            </div>
            <table id="dataTable" class="table table-condensed table-hover table-bordered">
                <thead>
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>status</th>
                    <th>createTime</th>
                    <th>lastModifyTime</th>
                    <th>operation</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>


<script src="/MyNews/js/jQuery/jquery-1.11.1.min.js"></script>
<script src="/MyNews/js/bootstrap/bootstrap.min.js"></script>
<script src="/MyNews/js/dataTables/jquery.dataTables.min.js"></script>
<script src="/MyNews/js/dataTables/dataTables.bootstrap.min.js"></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script>
    toastr.options.positionClass = 'toast-top-center';
    var dtable = null;
    var table = $('#dataTable');
    var initTable = function () {
        if (dtable == null) {
            dtable = table.dataTable({
                serverSide: true,
                ordering: false,
                info: true,
                destroy: true,
                paging: true,
                pagingType: "full_numbers",
                pageLength: 10,
                searching: false,
                stateSave: true,
                lengthChange: false,
                ajax: {
                    type: "get",
                    url: "getArticleToReview",
                    async: "false",
                    dataSrc: "data",
                    data: function (d) {
                        var param = {};
                        param.draw = d.draw;
                        param.start = d.start;
                        param.length = d.length;
                        return param;//自定义需要传递的参数
                    }
                },
                columns: [
                    {"data": "id"},
                    {"data": "title"},
                    {"data": "status"},
                    {"data": "createTime"},
                    {"data": "lastModifyTime"}
                ],
                columnDefs: [
                    {
                        "targets": 5,
                        "data": "id",
                        "render": function (data, type, full) {
                            return "<button id='editrow' class='btn btn-primary'  type='button'><i class='fa fa-check'></i></button><button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-close'></i></button>";
                        }
                    }

                ]
            });
        }
    };
    function  updateNewsStatus(id,status) {
        $.ajax({
            url:"updateNewsStatus",
            method:'GET',
            data:{
                "id":id,
                "status":status
            },
            dataType:'json',
            contentType: "application/json",
            success:function(data){
                toastr.success(data.msg);
                dtable.api().ajax.reload(null, false);
            },
            error:function(data){
                toastr.error(data.msg);
            }
        });
    }
    $(document).ready(function () {
        initTable();
        $("#dataTable").on('click', 'button#editrow', function () {
            var data = $("#dataTable").DataTable().row($(this).parents("tr")).data();
            updateNewsStatus(data.id,1);
        });
        $("#dataTable").on('click', 'button#delrow', function () {
            var data = $("#dataTable").DataTable().row($(this).parents("tr")).data();
            updateNewsStatus(data.id,0);
        });

    });
</script>
</body>

</html>
