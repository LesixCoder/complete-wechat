#parse("/common/global_helper.jsp")

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>error</title>
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
    <style>
        html {
            font-size: 625%;
            overflow-x: hidden;
        }
        body {
            position: relative;
            font-family: "Microsoft YaHei", "SimSun", Verdana, Arial, Helvetica, sans-serif;
            background-color: #202125;
            color: #fff;
            font-size: .17rem;
            position: relative;
            overflow-x: hidden;
        }
        .wrapper-Box {
            width: 100%;
            height: 100%;
            max-width: 6.4rem;
            margin: 0 auto;  
            overflow: hidden;
        }
        .error{
            position: absolute;
            top: 35%;
            left: 50%;
            max-width: 6.4rem;
            width: 60%;
            font-size: .2rem;
            -webkit-transform: translate(-50%,-50%);
            transform: translate(-50%,-50%);
            text-align: center;
        }
        .error img{
            width: 100%;
        }
        .error h3{
            margin: 8% 0 0;
            color: #eee;
            font-size: .25rem;
        }
        .error p{
            margin: 3% 0 0;
            color: #999;
        }
        /*媒体查询，小于375px*/
        @media screen and (max-width:375px){
            .error{
                font-size: .18rem;
            }
            .error h3{
                font-size: .23rem;
            }
        }
        /*媒体查询，小于320px*/
        @media screen and (max-width:320px){
            .error{
                font-size: .16rem;
            }
            .error h3{
                font-size: .2rem;
            }
        }
    </style>
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
    <div class="wrapper-Box">
        <section class="error">
            <img src="$!staticPrefix/wechat/images/error/404.png" alt="">
            <h3>出错啦！</h3>
            <p>找不到你访问的页面</p>
        </section>
    </div>
</body>
<script>
    window.onload = function() {
        let wH = window.innerHeight > 0 ? window.innerHeight : document.documentElement.clientHeight;
        document.body.style.height = (wH - 16) + 'px';
    }
</script>

</html>