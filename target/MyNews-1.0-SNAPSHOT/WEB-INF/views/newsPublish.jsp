<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Dashboard</title>

    <link href="/MyNews/css/bootstrap.min.css" rel="stylesheet">
    <link href="/MyNews/css/font-awesome.min.css" rel="stylesheet">
    <link href="/MyNews/css/styles.css" rel="stylesheet">
    <link href="/MyNews/css/toastr.css" rel="stylesheet">
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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse">
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                            class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
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
        <li><a href="/MyNews/user/systemInfo"><span class="glyphicon glyphicon-dashboard"></span>系统公告</a></li>
        <li class="active"><a href="/MyNews/news/publish"><span class="glyphicon glyphicon-th"></span>发布新闻</a></li>
        <li><a href="/MyNews/news/userNewsSearch"><span class="glyphicon glyphicon-stats"></span> 查看新闻</a></li>
        <li><a href="/MyNews/user/message"><span class="glyphicon glyphicon-list-alt"></span> 个人信息</a></li>
        <li><a href="/MyNews/index"><span class="glyphicon glyphicon-th-list"></span>新闻首页</a></li>
        <li><a href="/MyNews/login"><span class="glyphicon glyphicon-user"></span> Login Page</a></li>
    </ul>
</div>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">发布新闻</li>
        </ol>
    </div><!--/.row-->
    <form class="form-horizontal" method="post" action="#">
        <div class="form-group" style="margin: 10px">
            <label for="title" class="col-sm-2 control-label">新闻标题</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="title" name="title" value="${article.title}">
            </div>
        </div>
        <div class="form-group" class="col-sm-2 control-label">
            <label class="col-sm-2 control-label">选择类别</label>
            <div class="col-sm-10">
                <select class="form-control" id="category" name="category">
                    <option value="娱乐">娱乐</option>
                    <option value="体育">体育</option>
                    <option value="体育">科技</option>
                    <option value="军事">军事</option>
                    <option value="时政">时政</option>
                    <option value="生活">生活</option>
                </select>
            </div>
        </div>
        <div class="form-group" style="margin-top: 5px;padding: 5px;">
            <textarea id="content" name="content" cols="20" rows="2" class="ckeditor"></textarea>
        </div>
        <button type="button" class="btn btn-primary" id="reset">清空</button>
        <button type="button" class="btn btn-primary" id="publish">发布</button>
    </form>
</div>


<script src="/MyNews/js/jQuery/jquery-1.11.1.min.js"></script>
<script src="/MyNews/js/bootstrap/bootstrap.min.js"></script>
<script src="/MyNews/js/dataTables/jquery.dataTables.min.js"></script>
<script src="/MyNews/js/dataTables/dataTables.bootstrap.min.js"></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script type="text/javascript" src="/MyNews/ckeditor/ckeditor.js"></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script id="container" type="text/plain">
    ${article.content}
</script>
<script>
    CKEDITOR.replace('content');
    CKEDITOR.instances.content.setData($("#container").html());
    function checkArticleExits(){
        var title = "${article.title}";
        if(title.length !=0 ){
            return true;
        }else{
            return false;
        }
    };
    function addArticle(){
        title = $("#title").val();
        content = CKEDITOR.instances.content.getData();
        category = $("#category").val();
        console.log(CKEDITOR.instances.content.getData());
        console.log(category);
        $.ajax({
            url: "add",
            method: "GET",
            data: {
                "title": title,
                "content": content,
                "category": category,
            },
            dataType: "json",
            success: function (data) {
                toastr.success(data.msg);
                reset();
            },
            error: function (data) {
                toastr.error(data.msg);
            }
        });
    };
    function updateArticle(){
        id = "${article.id}";
        title = $("#title").val();
        content = CKEDITOR.instances.content.getData();
        category = $("#category").val();
        $.ajax({
            url: "updateArticle",
            method: "GET",
            data: {
                "id":id,
                "title": title,
                "content": content,
                "category": category,
            },
            dataType: "json",
            success: function (data) {
                toastr.success(data.msg);
            },
            error: function (data) {
                toastr.error(data.msg);
            }
        });
    };
    $(document).ready(function () {
        $("#publish").click(function () {
            var flag = checkArticleExits();
            if(flag == true){
                updateArticle();
            }else{
                addArticle();
            }
        });
        $("#reset").click(function () {
            reset();
        });
    });

    function reset() {
        $("#title").val("");
        CKEDITOR.instances.content.setData("");
    }
</script>
</body>

</html>
