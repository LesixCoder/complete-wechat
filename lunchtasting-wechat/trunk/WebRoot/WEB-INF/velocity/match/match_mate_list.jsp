#parse("./common/global_helper.jsp")
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>玩美匹配</title>
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
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/fontawesome/4.4.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_mate_list.css" />
</head>

<body>
    <div class="wrapper-Box">
    	#if("$!userList" != "" && $!userList.size() > 0)
        <section class="marry">  	
        	#foreach($bean in $userList)
            <figure class="marry-list">
                <a href="/user/center?user_id=$!bean.user_id"><img src="$!imgPrefix$!bean.img_url" alt=""></a>
                <figcaption>
                    <div class="list-name">
                        <div class="name-left">
                            <h4><a href="/user/center?user_id=$!bean.user_id">$!bean.name</a><span class="ratyli" data-rate="5" data-ratemax="5"></span></h4>
                            <span><i>$!bean.age</i>&nbsp;<i>$!bean.constellation</i></span>
                        </div>
                        <div class="name-right">
                            <a class="js-group-invite" href="javascript:void(0)" js-attr-matchId="$!matchId" 
                            	js-attr-userId="$!bean.user_id">邀请TA</a>
                        </div>
                    </div>
                    <div class="list-label">
                    	#foreach($tagMap in $bean.tag_list)
                    	<i>$!tagMap.tag</i>	
                    	#end
                       
                    </div>
                    <div class="list-text">
                        <p>参赛宣言：<span>$!bean.signature</span></p>
                        <p>兴趣爱好：<span>$!bean.hobby</span></p>
                        <p>情感状况：<span>$!bean.feeling</span></p>
                    </div>
                </figcaption>
            </figure>  
        </section>
        <footer class="footer">
            <p>- 每天推荐三个队友，明日再来哦 -</p>
        </footer>
        #end
        #else
        <footer class="footer footer-no">
            <p>- 暂无推荐哦~ -</p>
        </footer>
        #end
    </div>
</body>
<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
<script src="$!staticPrefix/wechat/js/plugs/jquery.ratyli.min.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_mate_list.js"></script>

</html>