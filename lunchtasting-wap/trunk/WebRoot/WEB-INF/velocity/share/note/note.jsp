#parse("././global/base.jsp")
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
    <meta name="renderer" content="webkit">
    <meta name='apple-itunes-app' content='app-id=1155981225'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="$pathStatic/css/wap/note/note.css" />
</head>

<body style="overflow-x:hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>帖子详情</h1>
            </nav>
        </header>
        <section id="note-con">
            <figure class="note-list">
                <div class="list-header">
                    <img src="$!bean.icon" alt="">
                    <div>
                        <h4>$!bean.user_name</h4>
                        <time>$!bean.time</time>
                    </div>
                </div>
                <img src="$!bean.img_url" alt="">
                <figcaption>
                    <p>$!bean.content</p>
                    <div class="list-btn">
                        <a href="javascript:void(0)"><i>$!bean.like_count</i></a>
                        <a href="javascript:void(0)"><i>$!bean.comment_count</i></a>
                        <a href="javascript:void(0)">...</a>
                    </div>
                </figcaption>
            </figure>
        </section>
        <hr class="fenge">
        <section id="note-comment">
            <h3>最新评论</h3>
             #foreach ($bean22 in $bean2)
            <div class="comment-list">
                <div>
                    <span>$!bean22.name</span>
                    <time>$!bean22.time</time>
                </div>
                <p>$!bean22.content</p>
            </div>
            #end
        </section>
<!--
        <footer class="footer">
            <a href="javascript:void(0)" id="openApp">报名参加</a>
        </footer>
-->
        <!-- fixed footer! -->
        <div class="footerBar" style="position: fixed;bottom: 0;left: 0;right: 0;background-color: rgba(0,0,0,0.61);
				border: 0;margin: 0;padding: 0;height: 60px;width: 100%;z-index: 9999;">

            <!-- logo -->
            <div style="display:inline-block; margin-top: 10px;margin-left: 10px;width: 175px;font-size:10px; text-align: left;color: white;line-height: 20px;">
                <img alt="logo" src="http://static.mperfit.com/image/wap/wap_index/logo.jpg" style="height:40px; border-radius:4px;float: left;margin-right: 7px;">
                <span style="font-size: 15px;font-weight: bold;">PERFIT</span>
                <br/><span>只为不可思议</span>
            </div>

            <!-- 跳转按钮 -->
            <a class="openapp_btn" onclick="openCourseInApp()" style="float: right;margin: 18px 18px 0;display: inline-block;z-index: 10000;color: white;border-radius:3px;
				font-size: 10px;text-align: center;width: 73px;height: 23px;line-height: 23px;border: 1px solid #fff;">下载APP
		</a>
        </div>
    </div>
</body>
<script src="http://static.mperfit.com/js/wap/wap_index/util.js"></script>
<script type="text/javascript">
    //在app中打开课程，没有app会跳转腾讯应用宝
    function openCourseInApp() {
        if (is_weixinBrowser()) {
            //showOpenBrowserMask("点击右上角在浏览器中打开");
            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.mperfit.perfit"; 
        } else {
            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.mperfit.perfit"; //ios app协议
//            window.setTimeout(function() {
//                window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.mperfit.perfit";
//            }, 2000);
        }
    }
</script>

</html>
