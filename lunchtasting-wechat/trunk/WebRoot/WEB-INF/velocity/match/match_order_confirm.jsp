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
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_order_confirm.css" />
</head>

<body>
    <div class="wrapper-Box">
        <header class="header">
            <img src="$!order.img_url" alt="">
        </header>
        <section class="pay">
            <h2>$!order.name</h2>
            <p>报名费：<span>￥<i>$!order.pay_price</i></span></p>
            <a href="/wxpay/match?order_id=$!order.order_id"><img src="$!staticPrefix/wechat/images/pay/icon-wechat.png" alt="">微信支付</a>
        </section>
    </div>
</body>

</html>