#parse("./common/global_helper.jsp")
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>比赛报名</title>
    <meta name="renderer" content="webkit">
    <meta name='apple-itunes-app' content='app-id=1155981225'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=yes, email=yes">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <!--微信浏览器中打开-->
    <!--
    <script type="text/javascript">
        // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            // 以下代码是用javascript强行关闭当前页面
//            var opened = window.open('about:blank', '_self');
//            opened.opener = null;
//            opened.close();
            window.stop ? window.stop() : document.execCommand("Stop");
        }
    </script>
-->
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_intro.css" />
</head>

<body>
    <div class="wrapper-Box">
        <header class="header">
            <div class="header-img"><img src="$!staticPrefix/wechat/images/intro/bg_tit.png" alt=""></div>
            <div class="header-site">
                <time>比赛时间：2017年6月17日</time>
                <address>比赛地点：北京市黄草湾郊野公园</address>
            </div>
        </header>
        <div class="triangle"><i></i></div>
        <section class="intro">
            <h3><i>01</i><span>障碍跑&咆哮狗</span></h3>
            <p>障碍跑诞生于19世纪的英国，最初就是在野外举行，比赛选手跨越的障碍是树枝、河沟，每个障碍间的距离也长短不一。19世纪时的障碍跑的距离不统一，具有很大的随意性，短的440码，长的可达3英里。从1904年第3届奥运会起将障碍跑的距离确定为3000米，障碍数量也随之增加，规则沿用至今。</p>
            <p>咆哮狗户外障碍赛采用更加贴合年轻人的时尚障碍赛玩法，采用男女组队模式（美女与野兽模式）共同通过障碍赛，在比赛中，需要参赛双方相互配合携手通过终点。比赛采用天然障碍+人工障碍模式，使比赛更加接近自然，让用户可以再大自然中释放自己的荷尔蒙，宣泄咆哮出自己的压力。</p>
            <img src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
        </section>
        <section class="intro intro-gift">
            <h3><i>02</i><span>选手礼包</span></h3>
            <span>每个参赛选手都会获得我们为您提供个性化定制礼包</span>
            <ul class="intro-ul">
                <li><i>1</i>咆哮狗专属T恤一件</li>
                <li><i>2</i>咆哮狗护臂一套</li>
                <li><i>3</i>健康餐一份</li>
            </ul>
        </section>
        <section class="intro intro-gift">
            <h3><i>03</i><span>场地说明</span></h3>
            <ul class="intro-ul">
                <li>赛道长度：全程1.8KM</li>
                <li>关卡数量：10个</li>
                <li>参赛要求：参赛选手年龄在18—45岁</li>
                <li>完赛奖励：完赛礼包一份（T恤，头巾，护臂等）</li>
            </ul>
        </section>
        <section class="intro intro-gift">
            <h3><i>04</i><span>咆哮狗奖项设置</span></h3>
            <ul class="intro-ul">
                <li>第一名：5000元</li>
                <li>第二名：3000元</li>
                <li>第三名：1000元</li>
            </ul>
        </section>
        <section class="intro intro-care">
            <p><i>注意事项：</i>比赛按照分组计时统计最终名次，根据时间进行统计冠亚季军，获奖者可获得额外奖励</p>
        </section>
        <footer class="footer">
        	#if("$!isSignUp" == "")
        	<a id="signup" class="unchecked" href="/match/signup?match_id=$!matchId">点击报名</a>
            #else
            <a id="signup" class="unchecked" href="/match/user?match_id=$!matchId">查看信息</a>
            #end
        </footer>
    </div>
</body>
<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
</html>