<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>美女&野兽综合体能挑战赛</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/temporaryEnroll/watch.css" />
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
</head>

<body>
    <div class="wrapper-Box" id="wrapper-Box">
        <div id="content">
            <div class="content-header">
                <img src="http://static.mperfit.com/image/wap/temporaryEnroll/watchH.png" alt="">
                <h1>美女&野兽综合体能挑战赛</h1>
                <h3 class="yellow">“为自己，为队友，战斗至死”</h3>
            </div>
            <h2 class="watchCode yellow">观赛券：<i id="random"></i><span class="wcp">(截屏保存此图，现场时出示)</span></h2>
            <div class="content-address">
                <span>2016年12月25日</span>
                <address>北京市海淀区海淀中街中关村国际创客中心B2-J07</address>
                <img src="http://static.mperfit.com/image/wap/temporaryEnroll/anna-qrCode.png" alt="">
            </div>
        </div>
    </div>
</body>
<script src="http://static.mperfit.com/js/common_js/jquery-1.12.0.min.js"></script>
<script src="http://static.mperfit.com/js/wap/temporaryEnroll/watch.js"></script>
<script type="text/javascript">
    var userId = "$!userId";
    var random = "$!random";
    if(random == "" && userId != ""){
	    jQuery.ajax({
				url : '/saishi/saveCinema?userId='+userId,
				data : null,
				type : 'post',
				success : function(data) {
					if (data.result == 2) {
						jQuery("#random").html(data.watch);
						
					} else {
						alert("fail");
						return;
					}
				},
				error : function() {
					alert("服务器连接失败！");
				}
			});
    }else{
        jQuery("#random").html(random);
    }
</script>
</html>
