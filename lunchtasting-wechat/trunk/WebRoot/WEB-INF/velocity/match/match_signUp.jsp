#parse("./common/global_helper.jsp")
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>报名信息</title>
    <meta name="renderer" content="webkit">
    <meta name='apple-itunes-app' content='app-id=1155981225'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=yes, email=yes">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <meta name="msapplication-tap-highlight" content="no">
    <!--微信浏览器中打开--> 
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
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/plugs/nice-select-01310.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/plugs/mobiscroll_date-01310.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_signUp-01310.css" />
</head>

<body>
    <div class="wrapper-Box">
        <header class="header">
            <span><img src="$!imgPrefix$!user.imgUrl" alt=""></span>
        </header>
        <section class="recruit-sign">
            <h3>填写报名信息</h3>
            <input type="hidden" id="matchId" value=$!matchId>
            <input type="hidden" id="inviteId" value=$!inviteId>
            <div class="sign-form">
                <p>
                    <input type="text" id="nickname" placeholder="昵称（最多5个中文或10个英文）" maxlength="10" required pattern="^[\u4e00-\u9fa5]{1,5}$|^[\dA-Za-z_]{1,10}$">
                </p>
                <p>
                    <select class="wide">
                        <option value="1" data-display="男">男</option>
                        <option value="2">女</option>
                    </select>
                </p>
           		<p>
                    <input type="text" id="user-age" placeholder="出生日期">
                </p> 
                <p class="p-tel clearfix">
                    <input type="text" id="tel" name="tel" placeholder="手机号" required pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$">
                    <input class="getCode" id="verNum" type="button" value="获取验证码">
                </p>
                <p>
                    <input type="text" id="verCode" placeholder="验证码" maxlength="6" pattern="^\d{6}\b" required>
                </p>
                <p>
                    <input type="text" id="realName" placeholder="真实姓名（请填写真实信息用于保险服务）" maxlength="5" pattern="^[\u2E80-\u9FFF]+$" required>
                </p>
                <p>
                    <input type="text" id="identity" placeholder="身份证（请填写真实信息用于保险服务）" maxlength="18" pattern="^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$" required>
                </p>
                <p>
                    <input type="text" id="invite_code" placeholder="邀请码(没有可不填)" value="$!inviteCode" pattern="^[a-zA-Z0-9]+$" required>
                </p>
            </div>
        </section>
        <footer class="footer">
            <a id="signBtn" href="javascript:void(0)">确认信息</a>
        </footer>
    </div>
</body>
<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
<script src="$!staticPrefix/wechat/js/plugs/jquery.nice-select-01310.min.js"></script>
<script src="$!staticPrefix/wechat/js/plugs/mobiscroll_date-01310.js" charset="gb2312"></script> 
<script src="$!staticPrefix/wechat/js/plugs/mobiscroll-01310.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_signUp-01310.js"></script>
</html>