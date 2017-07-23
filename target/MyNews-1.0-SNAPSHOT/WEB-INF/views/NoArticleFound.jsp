<%--
  Created by IntelliJ IDEA.
  User: zhongziming
  Date: 2017/5/14
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- BEGIN META -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Custom Theme">
    <title>出错提示页面</title>
    <script language="JavaScript" type="text/javascript">
        function delayURL(url) {
            var delay = document.getElementById("time").innerHTML;
            if (delay > 0) {
                delay--;
                document.getElementById("time").innerHTML = delay;
            } else {
                window.top.location.href = url;
            }
            t = setTimeout("delayURL('" + url + "')", 1000);
        }
        function stop1() {
            t && clearTimeout(t);
        }
    </script>
</head>

<body class="body-404">
<span>没有此新闻</span><br/>
<span id="time">3</span>秒钟后自动跳转，如果不跳转，请点击下面的链接<a href="/MyNews/index">新闻首页</a>
<input type="button" value="停止跳转" onclick="stop1();">
<script type="text/javascript">
    delayURL("/MyNews/index");
</script>
</body>
</html>
