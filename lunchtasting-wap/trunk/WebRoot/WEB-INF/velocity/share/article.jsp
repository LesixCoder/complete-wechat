#parse("./global/base.jsp")
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
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
    <link rel="stylesheet" href="$pathStatic/css/wap/wap_index/style.css" /> 
    <script src="$pathStatic/js/common_js/jquery.js"></script>
    <script src="$pathStatic/js/common_js/jquery.lazyload.js"></script>
    <script type="text/javascript">
        jQuery('.body-details img').addClass('lazy');
        $(function() {
            $("img.lazy").lazyload({
                placeholder : "http://static.mperfit.com/image/wap/wap_index/grey.gif",     //用图片提前占位
                effect : "show",    //载入使用何种效果,effect(特效),值有show(直接显示),fadeIn(淡入),slideDown(下拉)等,常用fadeIn
                threshold : 500,    //提前开始加载
                //event : "click",      //事件触发时才加载,event,值有click(点击),mouseover(鼠标划过),sporty(运动的),foobar(…).可以实现鼠标划过或点击图片才开始加载,后两个值未测试…
                //failurelimit : 10,     //图片排序混乱时 ,
            });
        });
    </script>
</head>

<body style="overflow-x: hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>文章详情</h1>
            </nav>
        </header>
        <section class="content-com content-art">
            <header class="header-details">
                <img src="$!article.img_url" alt="">
            </header>
            <div class="body-details">
                <!--图文混编所在位置-->
                $!article.img_text
            </div>
        </section>
        <footer class="footer"></footer>
    </div>  
</body>
</html>
