<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=zh-CN>

<head>
    <meta charset=utf-8>
    <meta http-equiv=X-UA-Compatible content="IE=edge">
    <title>新闻详情</title>
    <meta name=HandheldFriendly content=True>
    <meta name=viewport content="width=device-width,initial-scale=1">
    <link rel=stylesheet href=/MyNews/css/bootstrap.min.css>
    <link rel=stylesheet href=/MyNews/css/font-awesome.min.css>
    <link href="/MyNews/css/toastr.min.css" rel="stylesheet">
    <link rel=stylesheet href=/MyNews/css/article/vs.min_1.css>
    <link rel=stylesheet type=text/css href=/MyNews/css/article/screen.min_1.css>
</head>
<link rel="stylesheet" href="/MyNews/css/article/main.css">

<body class=post-template>

<section class=content-wrap>
    <div class=container>
        <div class=row>
            <main class="col-md-8 main-content">
                <div class="breadcrumb">
                    <a href="index"><span id="sLevel2">首页&gt;</span></a>
                    <span id="sLevel1">新闻详情</span>
                </div>
                <article class=post>
                    <header class=post-head>
                        <h1 class=post-title>${article.title}</h1>
                        <section class=post-meta>
                            <span class=author>作者：<a href=# target=_blank>${article.author}</a></span> &bull;
                            <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
                            <fmt:formatDate value="${article.createTime}" type="date" dateStyle="long"></fmt:formatDate>
                        </section>
                    </header>
                    <section class=post-content>
                        ${article.content}
                        <div style="float: right">热度：${article.hits.rate}</div>
                    </section>
                    <footer class="post-footer clearfix">

                    </footer>
                </article>
                <div class="about-author clearfix comment ">
                    <p>评论区</p>
                    <textarea placeholder="请输入评论内容" name="comment" id="comment" rows="5" style="resize:none"></textarea>
                    <button type="button" id="sendComment">提交评论</button>
                    <hr>
                    <div class="qh-cmt qh-reset-font">
                        <div class="qh-cmt-bar">
                            <c:forEach var="comment" items="${article.comments}">
                                <div class="qh-user-nt">
                                    <div class="qh-u-name">${comment.user.userName}:</div>
                                    <div class="qh-u-time">${comment.content}</div>
                                    <br/>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>

            </main>
            <aside class="col-md-4 sidebar">
                <div class=widget>
                    <c:if test="${user.userName != null}">
                        <p class=content>
                                ${user.userName},你好<a href="login">log out</a>
                        </p>
                    </c:if>
                    <c:if test="${user == null}">
                        <h4 class=title>登录</h4>
                        <div class="content community">
                            <form method="get" action="loginToArticle">
                                <input type="text" id="articleId" name="articleId" value="${article.id}" hidden>
                                <input type="email" class="form-control" id="email" name="email" placeholder="请输入用户邮箱">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入用户密码">
                                <button type="submit" id="login" name="login" class="btn">登录</button>
                            </form>
                        </div>
                    </c:if>
                </div>
                <div class=widget>
                    <h4 class=title>最热新闻</h4>
                    <div class="content community">
                        <c:forEach var="hit" items="${hits}">
                            <p><a href="article?id=${hit.article.id}">${hit.article.title}</a></p>
                        </c:forEach>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</section>
<script src=/MyNews/js/jQuery/jquery-1.11.1.min.js></script>
<script src=/MyNews/js/bootstrap/bootstrap.min.js></script>
<script src="/MyNews/js/toastr.min.js"></script>
<script>
    toastr.options.positionClass = 'toast-top-center';
    function addComment(userId, articleId, comment) {
        $.ajax({
            url: "news/addComment",
            method: 'GET',
            data: {
                "userId": userId,
                "articleId": articleId,
                "comment": comment
            },
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                toastr.success(data.msg);
                window.location.reload(true);
            },
            error: function (data) {
                toastr.error(data.msg);
            }
        });
    };
    $(document).ready(function () {
        $("#sendComment").click(function () {
            comment = $("#comment").val();
            articleId = "${article.id}";
            userId = "${user.id}";
            if (userId.length == 0) {
                toastr.error("请登录后再发表评论");
            } else {
                addComment(userId, articleId, comment);
            }
        });
    });
</script>
</body>

</html>