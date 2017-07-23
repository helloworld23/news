<%--
  Created by IntelliJ IDEA.
  User: zhongziming
  Date: 2017/5/7
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userAdd</title>
    <link href="/MyNews/css/bootstrap.css" rel="stylesheet"/>
    <link href="/MyNews/css/userMessage.css" rel="stylesheet"/>
    <link href="/MyNews/css/toastr.min.css" rel="stylesheet"/>
    <link href="/MyNews/css/styles.css" rel="stylesheet"/>
    <style type="text/css">
        .col-sm-10 .form-control {
            width: 60%;
            background-color: #ddd;
        }
    </style>
</head>
<body>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">sign up</div>
            <div class="panel-body">
                <form role="form" name="signForm" id="signForm" action="add" method="post">
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="userName" name="userName" id="userName" type="text" autofocus>
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="E-mail" name="email" id="email" type="email">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="phoneNumber" id="phoneNumber" name="phoneNumber" type="text">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="QQ" name="qq" id="qq" type="text">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Password" name="password1" id="password1" type="password" value="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Password again" name="password" id="password" type="password" value="">
                        </div>
                        <button type="button" id="sign" class="btn btn-primary">sign up</button>
                        <button type="button" class="btn btn-primary" id="reset" name="reset">reset</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div><!-- /.col-->
</div>
<script src="/MyNews/js/jQuery/jquery-1.11.1.min.js"></script>
<script src="/MyNews/js/bootstrap/bootstrap.min.js"></script>
<script src="/MyNews/js/dataTables/jquery.dataTables.min.js"></script>
<script src="/MyNews/js/dataTables/dataTables.bootstrap.min.js"></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script>
    toastr.options = {
        positionClass: "toast-top-center"
    };
    function checkPassword() {
        var password1 = $("#password1").val();
        var password = $("#password").val();
        console.log(password1);
        console.log(password);
        if(password1 == "" &&password == "") {toastr.error("密码不能为空");return 0;}
        else if (password1 == password) return 1;
        else {toastr.error("密码不正确！");return 0;}
    };
    function checkEmail() {
        var email = $("#email").val();
        var pattern=new RegExp("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        if(pattern.test(email)){
            return 1;
        }else{
            toastr.error("邮箱格式不正确！");
            return 0;
        }
    };
    $(document).ready(function () {
        $("#sign").click(function () {
            var msg = checkPassword()+checkEmail();
            if(msg == 2) $("#signForm").submit();
        });
    });
</script>
</body>
</html>
