#parse("./common/global_helper.jsp")
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="renderer" content="webkit">
    <title>报名信息</title>
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
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_regInfo.css" />
</head>

<body>
    <div class="wrapper-Box">
    	<h3 class="regInfo-tit">报名信息表<a href="/match/gongyi/skip"><img src="$!staticPrefix/wechat/images/intro/good-icon.png" alt=""></a></h3>
    	<div class="regInfo-box">
            <p><i>用户昵称：</i><span>$!order.name</span></p>
            <p><i>联系电话：</i><span>$!order.phone</span></p>
            <p><i>购票类型：</i><span>#if("$!order.type" == "1") 参赛票 #elseif("$!order.type" == "2") 观赛票 #else 公益票 #end</span></p>
            <p><i>购票编码：</i><span>$!order.order_id</span></p>
        </div>
        <p class="prompt">提示：比赛当天请出示截图或关注下方服务号报名订单页面信息进行检录</p>
        <img class="eqcode" src="$!staticPrefix/wechat/images/intro/eqcode.jpeg" alt="">
        <span class="eqcodeT">查看更多详情请扫描二维码</span>
    </div>
</body>

</html>