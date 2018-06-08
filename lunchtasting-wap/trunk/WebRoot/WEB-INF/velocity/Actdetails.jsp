<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>活动</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="http://static.lunchtasting.com:8080/css/wap/wap_index/style.css" /> 
    <link rel="stylesheet" href="http://static.lunchtasting.com:8080/css/wap/wap_index/animate.css" /> 
</head>

<body>
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>Logo</h1>
                <a class="back" href="javascript:void(0)" onClick="javascript:history.back(-1);">&lt;</a>
                <button id="nav-btn" type="button"></button>
                <ul>
                    <li><a href="javascript:void(0)">文章</a></li>
                    <li><a href="javascript:void(0)">活动</a></li>
                    <li><a href="javascript:void(0)">全部</a></li>
                </ul>
            </nav> 
        </header>
        <section class="content-com">
            <header class="header-details">
                <img src="http://static.lunchtasting.com:8080/image/wap/wap_index/2.jpg" alt="">
                <h3>$!bean.name</h3>
                <div>
                    <p><span><i>$!applyNum</i>/<i>$!bean.enrollNum </i>报名</span></p>
                    <p><span><i>$!bean.price</i>元/人</span></p>
                    <p><span><i>$!begin_time - $!end_time</i>&nbsp;&nbsp;<i>17:30-19:00</i></span></p>
                    <p><span>$!bean.address</span></p>  
                </div>
            </header>
            <div class="body-details">
                <!--图文混编所在位置-->
                $!bean.imgText
            </div>        
        </section>
        <footer class="footer footer-Sign">
            <a href="javascript:void(0)" id="openApp">报名参加</a>        
        </footer>
        <div class="filter"></div>
 		<!--下载弹出框-->
        <div class="downloadCon">
            <h4>参加活动请下载<span>App</span></h4>    
            <p><a id="iosLoad" href="##">IOS下载</a><a id="adLoad" href="javascript:void(0)">安卓下载</a></p>
        </div>
    </div>  
</body>
<script type="text/javascript">
</script>
<script type="text/javascript" src="http://static.lunchtasting.com:8080/js/wap/wap_index/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="http://static.lunchtasting.com:8080/js/wap/wap_index/js.js"></script>
</html>
