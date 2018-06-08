#parse("./common/global_helper.jsp")
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>校园挑战赛</title>
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
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_user.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/share.css" />
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
        <header class="header">
        	#if("$!isGroup" != "")
        	<h2>$!user.name和$!groupUser.name的战队</h2>
        	#else
        	<h2>$!user.name的战队</h2>
        	#end        	
        </header>
        <section class="join-clan">
            <div class="clan-headImg">
                <figure>
                    <a href="/user/center?user_id=$!user.user_id">
                        <span><img src="$!imgPrefix$!user.img_url" alt=""></span>
                        <figcaption>
                            <i>$!user.name</i>
                        </figcaption>
                    </a>
                </figure>
                #if("$!isGroup" != "" && $!isGroup == 1)
                <figure>
                    <a href="/user/center?user_id=$!groupUser.user_id">
                        <span><img src="$!imgPrefix$!groupUser.img_url" alt=""></span>
                        <figcaption>
                            <i>$!groupUser.name</i>
                        </figcaption>
                    </a>
                </figure>
                #else
                <figure>
                    <a href="javascript:void(0)">
                        <span><img src="http://static.mperfit.com/wechat/images/joinClan/head-default.png" alt=""></span>
                        <figcaption>
                            <i>等待加入</i>
                        </figcaption>
                    </a>
                </figure>
                #end
            </div>
        </section>
        <section class="join-btn">
        	#if("$!isGroup" == "")
            <h3><i></i>招募队友</h3>
            <div class="btn-a">
                <a href="javascript:void(0)" onclick="_system._guide(true)">邀请好友</a>
                <a href="/match/mate/list?match_id=$!match.id">完美匹配</a>
            </div>
            #end
            
            #if($!invteList.size()>0)
            <div class="btn-list">
            	#foreach($bean in $invteList)
                <article class="list-box clearfix">
                    <div class="list-left">
                        <p><a href="/user/center?user_id=$!bean.user_id"><span>$!bean.name</span></a>邀请你加入<i>他的战队</i></p>
                        <p>留言：<span>$!bean.content</span></p>
                    </div>
                    <div class="list-right">
                        <a href="/match/group/mate?invite_id=$!bean.user_id&match_id=$!match.id">加入</a>
                    </div>
                </article>
                #end
            </div>
            #end
        </section>
        <!-- <section class="join-hot">
            <h3><i></i>热门候选人<a href="javascript:void(0)">我要上热门</a></h3>
            <div class="hot-box">
                <figure class="hot-person">
                    <a href="javascript:void(0)"><img src="http://static.mperfit.com/wechat/images/joinClan/sign-head.jpg" alt=""></a>
                    <figcaption>
                        <h4>不具名的悲伤</h4>
                        <a href="javascript:void(0)">邀请</a>
                    </figcaption>
                </figure>
                <figure class="hot-person">
                    <a href="javascript:void(0)"><img src="http://static.mperfit.com/wechat/images/joinClan/sign-head.jpg" alt=""></a>
                    <figcaption>
                        <h4>不具名的悲伤</h4>
                        <a href="javascript:void(0)">邀请</a>
                    </figcaption>
                </figure>
                <figure class="hot-person">
                    <a href="javascript:void(0)"><img src="http://static.mperfit.com/wechat/images/joinClan/sign-head.jpg" alt=""></a>
                    <figcaption>
                        <h4>不具名的悲伤</h4>
                        <a href="javascript:void(0)">邀请</a>
                    </figcaption>
                </figure>
            </div>
        </section> -->
    </div>
    <div id="cover"></div>
    <div id="guide"><img src="$!staticPrefix/wechat/images/joinClan/guide.png"></div>
</body>
<script type="text/javascript" src="$!staticPrefix/wechat/js/share.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	 wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '$!appId', // 必填，公众号的唯一标识
		    timestamp: '$!timestamp', // 必填，生成签名的时间戳
		    nonceStr: '$!nonceStr', // 必填，生成签名的随机串
		    signature: '$!signature',// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		}); 

	//jQuery(function() {
	wx.ready(function(){
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.onMenuShareTimeline({
		    title: '咆哮狗赛事组队', // 分享标题
		    link: 'http://wchat.mperfit.com/match/group/mate?match_id=$!match.id&invite_id=$!user.user_id', // 分享链接
		    imgUrl: '$!imgPrefix$!match.imgUrl', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareAppMessage({
		    title: '咆哮狗赛事组队', // 分享标题
		    desc: '这是一个描述', // 分享描述
		    link: 'http://wchat.mperfit.com/match/group/mate?match_id=$!match.id&invite_id=$!user.user_id', // 分享链接
		    imgUrl: '$!imgPrefix$!match.imgUrl', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
	});
//}); 
</script>
</html>