#parse("./common/global_helper.jsp")
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>校园挑战赛</title>
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
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_group.css" />
    <!--微信浏览器中打开 -->
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
</head>

<body>
    <div class="wrapper-Box">
        <header class="header">
            <h2>$!inviteUser.name和$!user.name的战队</h2>
        </header>
        <section class="join-clan">
            <div class="clan-headImg">
                <figure>
                    <a href="/user/center?user_id=$!inviteUser.user_id">
                        <span><img src="$!imgPrefix$!inviteUser.img_url" alt=""></span>
                        <figcaption>
                            <i>$!inviteUser.name</i>
                        </figcaption>
                    </a>
                </figure>
                <figure>
                    <a href="/user/center?user_id=$!user.user_id">
                        <span><img src="$!imgPrefix$!user.img_url" alt=""></span>
                        <figcaption>
                            <i>$!user.name</i>
                        </figcaption>
                    </a>
                </figure>
            </div>
            <a id="js-group-add" href="javascript:void(0)" js-attr-orderId="$!user.order_id" 
            	js-attr-groupUserId="$!user.group_user_id" js-attr-inviteId="$!inviteUser.user_id"
            	js-attr-matchId="$!match.id">加入战队</a>
        </section>
        <section class="join-intro">
            $!match.img_text
            <div class="intro-map">
                <h3><i></i>活动地点</h3>
                <a href="javascript:void(0)"><img src="http://static.mperfit.com/wechat/images/joinClan/join_map.jpg" alt=""></a>
            </div>
        </section>
    </div>
</body>

<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
<script src="$!staticPrefix/wechat/js/plugs/jquery.ratyli.min.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_group.js"></script>
</html>