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

        .table > thead > tr > th {
            height: 0px;
        }

        div.dataTables_wrapper div.dataTables_filter {
            text-align: right;
            margin-right: 56px;
        }
        .col-sm-10 .form-control{
            width: 60%;
            background-color: #ddd;
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
        <li class="active"><a href="/MyNews/admin/systemInfo"><span
                class="glyphicon glyphicon-dashboard"></span>系统公告</a></li>
        <li><a href="/MyNews/news/review"><span class="glyphicon glyphicon-th"></span>审核新闻</a></li>
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
                <h3 class="box-title">公告列表</h3>
            </div>
            <table id="dataTable" class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>id</th>
                    <th>content</th>
                    <th>createTime</th>
                    <th>lastModifyTime</th>
                    <th>Operation</th>
                </tr>
                </thead>

            </table>

        </div>
    </div>
    <div class="row">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">发布公告</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" method="post" action="addSystemInfo">

                <div class="form-group">
                    <label class="col-sm-2 control-label">公告内容</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="content" name="content"
                               autofocus="autofocus">
                    </div>
                </div>

                <!-- /.box-body -->
                <div class="box-footer">
                    <button type="button" class="btn btn-default" id="addSystemInfo">发布</button>
                    <button type="button" class="btn btn-default" id="reset">重置</button>
                </div>
                <!-- /.box-footer -->
            </form>
        </div>
    </div>
</div>
<!-- /模态框 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">修改公告</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group" style="display: none">
                        <label for="infoID" class="control-label">公告ID:</label>
                        <input type="text" class="form-control" id="infoID">
                    </div>
                    <div class="form-group" style="display: none">
                        <label for="infoID" class="control-label">创建时间:</label>
                        <input type="text" class="form-control" id="infoCreateTime">
                    </div>
                    <div class="form-group">
                        <label for="infoContent" class="control-label">公告内容:</label>
                        <textarea class="form-control" id="infoContent"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                <button type="button" class="btn btn-primary" id="update">更新</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
                        url: "getSystemInfos",
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
                        {"data": "content"},
                        {"data": "createTime2"},
                        {"data": "lastModifyTime2"}
                    ],
                    columnDefs: [
                        {
                            "targets": 4,
                            "data": "id",
                            "render": function (data, type, full) {
                                return "<button id='editrow' class='btn btn-primary'  type='button'><i class='fa fa-pencil-square-o'></i></button><button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button>";
                            }
                        }

                    ]
                });
            }
    };
    var updateSystemInfo = function (content, id,createTime) {
        $.ajax({
            url: 'updateSystemInfo',
            type: 'get',
            dataType: "json",
            data: {
                "content": content,
                "id": id,
                "createTime":createTime,
            },
            success: function (data) {
                if (data != null) {
                    toastr.success(data.msg);
                    $("#editModal").modal("hide");
                    dtable.api().ajax.reload(null, false);
                }
            }
        });
    };
    var deleteSystemInfo = function (id) {
        $.ajax({
            url: 'deleteSystemInfo',
            type: 'get',
            dataType: "json",
            data: {
                "id": id
            },
            success: function (data) {
                if (data != null) {
                    toastr.success(data.msg);
                    $("#editModal").modal("hide");
                    dtable.api().ajax.reload(null, false);
                }
            }
        });
    }
    $(document).ready(function () {
        initTable();
        $('#editModal').on('click', 'button#update', function () {
            content = $("#infoContent").val();
            id = $("#infoID").val();
            createTime = $("#infoCreateTime").val();
            updateSystemInfo(content, id,createTime);
        });
        $("#dataTable").on('click', 'button#editrow', function () {
            var data = $("#dataTable").DataTable().row($(this).parents("tr")).data();
            $("#infoID").val(data.id);
            $("#infoContent").val(data.content);
            $("#infoCreateTime").val(data.createTime);
            $("#editModal").modal("show");
        });
        $("#dataTable").on('click', 'button#delrow', function () {
            var data = $("#dataTable").DataTable().row($(this).parents("tr")).data();
            id = data.id;
            deleteSystemInfo(id);
        });
        $("#addSystemInfo").click(function () {
            var content = $("#content").val();
            $.ajax({
                url:"addSystemInfo",
                method:'GET',
                data:{
                    "content":content
                },
                dataType:'json',
                contentType: "application/json",
                success:function(data){
                    toastr.success("发布成功！");
                    $("#content").val("");
                    dtable.api().ajax.reload(null, false);
                },
                error:function(data){
                    toastr.error("发布失败！");
                }
            });
        });
        $("#reset").click(function () {
            $("#content").val("");
        });
    });
</script>
</body>

</html>
