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
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/temporaryEnroll/recruit.css" />
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
        <section id="content">
            <div class="content-inner">
                <h2 class="yellow">达人招募令</h2>
                <div class="zm-content">
                    <div class="container clearfix">
                        <figure class="zm-perCon">
                            <span class="zm-img"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/perBg.jpg" alt=""></span>
                            <figcaption>
                                <div class="text show">
                                    <span>昵称：<i>111</i></span><span>性别：<i>美女</i></span>
                                </div>
                                <div class="ing none">
                                    <span>组队中...</span>
                                </div>
                            </figcaption>
                        </figure>
                        <figure class="zm-perCon">
                            <span class="zm-img"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/perBg.jpg" alt=""></span>
                            <figcaption>
                                <div class="text none">
                                    <span>昵称：<i>111</i></span><span>性别：<i>美女</i></span>
                                </div>
                                <div class="ing show">
                                    <span>组队中...</span>
                                </div>
                            </figcaption>
                        </figure>
                    </div>
                </div>
                <div class="btn-group">
                    <a href="javascript:void(0)" onclick="JavaScript:history.back(-1);">返回</a>
                    <a id="recruit" href="javascript:void(0)" onclick="_system._guide(true)">招募</a>
                    <a href="javascript:void(0)" onclick="_system._guide(true)">分享</a>
                </div>
            </div>
        </section>
    </div>
    <div id="cover"></div>
    <div id="guide"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/guide1.png"></div>
</body>
<script src="http://static.mperfit.com/js/common_js/jquery-1.12.0.min.js"></script>
<script src="http://static.mperfit.com/js/wap/temporaryEnroll/share.js"></script>
<script>
    /*招募单击事件*/
    jQuery("#recruit").on('click',function(){
        
    })
</script>
</html>
