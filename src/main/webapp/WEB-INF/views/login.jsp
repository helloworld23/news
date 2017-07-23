<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Forms</title>

<link href="/MyNews/css/bootstrap.min.css" rel="stylesheet">
<link href="/MyNews/css/datepicker3.css" rel="stylesheet">
<link href="/MyNews/css/styles.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="/MyNews/js/html5shiv.js"></script>
<script src="/MyNews/js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">请登录</div>
				<div class="panel-body">
					<form role="form" name="loginForm" id="loginForm" action="login/userCheck" method="get">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="E-mail" name="email"  type="email" autofocus="">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password" name="password"  type="password" value="">
							</div>
							<div class="checkbox">
								<label>
									<input id="remember" name="remember" type="checkbox" value="Remember" checked>Remember Me
								</label>
							</div>
							<div class="radio">
								<label>
									<input name="login" type="radio" value="userLogin" checked>普通用户登录
								</label>
								<label>
									<input name="login" type="radio" value="adminLogin">管理员登陆
								</label>
							</div>
							<button type="submit" class="btn btn-primary">Login</button>
							<button type="button" class="btn btn-primary" id="sign" name="sign" onClick="location.href='user/addPage'">sign</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div><!-- /.col-->
	</div><!-- /.row -->



	<script src="/MyNews/js/jQuery/jquery-1.11.1.min.js"></script>
	<script src="/MyNews/js/bootstrap/bootstrap.min.js"></script>
	<script src="/MyNews/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="/MyNews/js/dataTables/dataTables.bootstrap.min.js"></script>
	<script src="/MyNews/js/toastr.min.js"></script>
	<script src="/MyNews/js/login.js"></script>
	<script>
	</script>	
</body>

</html>
