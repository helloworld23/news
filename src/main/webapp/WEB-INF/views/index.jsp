<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>广东工业大学 新闻通知网</title>

    <link href="/MyNews/index/css/reset.css" rel="stylesheet" type="text/css">
    <link href="/MyNews/index/css/style.css" rel="stylesheet" type="text/css">
</head>

<body style=" background-color:#FAFAFA">

<div id="header">
</div>

    <div id="nav">
        <c:if test="${user != null}">
            ${user.userName},你好
        </c:if>
        <div id="loginbar">
            <span><a href="user/addPage">首次登录</a></span>
            <form  action="login/loginToIndex" method="get">
            <label for="email">用户邮箱：</label>
            <input type="text" id="email" name="email" style="width:70px;">
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" style="width:70px;">
            &nbsp;<input type="submit" name="ctl00$login" value="登录" id="login">
            </form>
        </div>
    </div>
    <div id="container">


        <div id="left">
            <div id="news">
                <div class="title">
                    <h2>最近时政</h2><a href="http://news.gdut.edu.cn/ArticleList.aspx?category=4">更多》</a>
                    <div style=" clear:both"></div>
                </div>
                <ul>
                    <li><a href="article?id=21">中共中央总书记习近平电贺吴敦义当选国民党主席</a></li>
                    <li><a href="article?id=22">刘奇葆强调 全力做好迎接党的十九大宣传各项工作</a></li>
                    <li><a href="article?id=23">十九大前夕，总书记基层听民声，问计于民</a></li>
                    <li><a href="article?id=24">天津港有限公司原董事长于汝民被开除党籍</a></li>
                    <li><a href="article?id=25">八项规定贯彻情况网上调查推出 欢迎参与</a></li>
                    <li><a href="article?id=26">朴槿惠23日将受审 成韩第3位站上被告席前总统</a></li>
                </ul>
            </div>

            <div id="pic_news">
                <div class="title">
                    <h2>最近军事</h2><a href="http://news.gdut.edu.cn/ArticleList.aspx?category=4">更多》</a>
                    <div style=" clear:both"></div>
                </div>
                <ul>
                    <li><a href="article?id=27">普京：特朗普泄密给俄罗斯？俄外长啥都没告诉我啊</a></li>
                    <li><a href="article?id=28">特朗普首次出访 美国沙特签1100亿美元军售协议</a></li>
                    <li><a href="article?id=29">中国在南海开采可燃冰将成王牌：或令世界油价崩溃</a></li>
                    <li><a href="article?id=30">韩军方称朝鲜今天下午试射弹道导弹！或不是洲际导弹</a></li>
                    <li><a href="article?id=31">中国空军还会购买苏35战机吗 一原因将会令俄失望</a></li>
                </ul>
            </div>
        </div><!--left end-->
        <div id="hot_news">
            <div class="title">
                <h2>最近体育</h2><a href="http://news.gdut.edu.cn/ArticleList.aspx?category=4">更多》</a>
                <div style=" clear:both"></div>
            </div>
            <ul>
                <li><a href="article?id=14">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
                <li><a href="article?id=15">杜兰特33分库里21分 勇士轻取马刺3-0取赛点</a></li>
                <li><a href="article?id=16">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
                <li><a href="article?id=17">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
                <li><a href="article?id=20">本场上半场杜兰特拿下14分，第三节他进入杀神模式</a></li>
                <li><a href="article?id=42">这么惨都还不是最惨! 保罗你当年58分到底咋输的</a></li>
                <li><a href="article?id=43">不服!唐斯保罗不满落选最佳阵 利拉德:不意外呵呵</a></li>
                <li><a href="article?id=16">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
                <li><a href="article?id=16">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
                <li><a href="article?id=16">平威少！KD单节19分什么水平 第一秒杀科詹MJ</a></li>
            </ul>
        </div>

        <div id="right">

            <div id="hot_inform">
                <div class="title">
                    <h2>最近科技</h2><a href="http://news.gdut.edu.cn/ArticleList.aspx?category=5">更多》</a>
                    <div style=" clear:both"></div>
                </div>
                <ul>
                    <li><a href="article?id=32">谈判：苹果希望中国开发商禁用“打赏”功能</a></li>
                    <li><a href="article?id=33">iPhone 8必然会涨： 仅3D Touch模块就涨了2.5倍</a></li>
                    <li><a href="article?id=34">谷歌确认眼镜团队没跟AR/VR团队合作</a></li>
                    <li><a href="article?id=35">苹果专利曝光 为手表研发柔性OLED屏幕</a></li>
                    <li><a href="article?id=36">苹果“印度制造”iPhoneSE已经小范围上架</a></li>
                </ul>
            </div>
            <div id="hot_msg">
                <div class="title">
                    <h2>最近娱乐</h2><a href="http://news.gdut.edu.cn/ArticleList.aspx?category=6">更多》</a>
                    <div style=" clear:both"></div>
                </div>
                <ul>
                    <li><a href="article?id=37">大S真的瘦回来了！看汪小菲把她拍得这么时髦</a></li>
                    <li><a href="article?id=38">孙燕姿已是4岁儿子的妈 和老公过节还是这么浪漫</a></li>
                    <li><a href="article?id=39">天王不是白叫的!周杰伦演唱会后还写歌到凌晨5点</a></li>
                    <li><a href="article?id=40">张馨予工作室公开律师函 澄清怀念李晨传言</a></li>
                    <li><a href="article?id=41">鹿晗上节目大方秀手机屏保 却意外泄露这个秘密</a></li>
                </ul>
            </div><!--hot_news end-->
        </div><!--right end-->
        <!--bottom end-->

    </div><!--container end-->
</form>
</body>
</html>
  



