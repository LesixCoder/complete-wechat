#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
    <meta name="msapplication-tap-highlight" content="no">
    <title>公益助跑</title>
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/share.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_publicGood.css" />
</head>

<body>
    <div class="wrapper-box">
        <header class="header">
            <span class="header-img"><img src="$!imgPrefix$!user.imgUrl" alt=""></span>
            <p>$!user.name</p>
        </header>
        <section class="public-box">
            <h4 class="box-good">已获得<i>$!money元</i>公益基金</h4>
            <p class="box-person"><span>$!count</span>人参与他的助跑</p>
        </section>
        <section class="public-text">
            <h3 class="text-tit">活动介绍</h3>
            <p class="text-del"><span><img src="$!staticPrefix/wechat/images/intro/eqcode.jpeg" alt=""><i>扫码获取更多信息</i></span><em style="text-indent:2em;display: block;">"打破隔阂，为爱咆哮"，6月25日咆哮狗户外障碍赛，邀请你“益”起奔跑，为爱咆哮。</em><em style="text-indent:2em;display: block;">点击活动指定页面"免费报名"，报名成功者即可免费获得观赛通票（包含观赛、电音趴体、市集、游戏），将页面分享给好友，邀请好友为爱助力，助力增一，咆哮狗就会为无国界爱心公益基金会捐助2元公益基金。</em><em style="text-indent:2em;display: block;">同时，邀请参加活动超过10人的用户会获得官方提供的炫酷赛事礼品一份，比赛当天现场领取。礼品不潮不酷，咆哮狗坚决不答应。"打破隔阂，为爱咆哮"吧！</em></p>
        </section>
        <footer class="footer">
        	#if("$!isSignup" == "1")
        		#if("$!isMe" == "1")
        			<a href="javascript:void(0)" onclick="_system._guide(true)">分享</a>
        		#else
        			<a href="/match/gongyi/skip">查看自己</a>
        		#end
            #else
            	#if("$!isMe" == "1")
           			<a href="/match/signup/watch?match_id=840043772388573183">免费报名</a>
            	#else
           			<a href="/match/signup/watch?match_id=840043772388573183&invite_id=$!userId">报名</a>
            	#end
            #end
        </footer>
    </div>
    <div id="cover"></div>
    <div id="guide"><img src="$!staticPrefix/wechat/images/vote/guide.png"></div>
</body>
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
		    title: '打破隔阂，为爱咆哮！为爱助跑！', // 分享标题
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '打破隔阂，为爱咆哮！为爱助跑！', // 分享标题
		    desc: '咆哮狗户外障碍赛是由咆哮狗发起公益跑，通过爱心公益和免参赛费的形式，呼吁所有人积极参与到比赛中来，一方面增强自己的体魄，一方面参与慈善事业，为社会奉献一份爱。', // 分享描述
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
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
<script src="$!staticPrefix/wechat/js/plugs/jquery-2.1.4.min.js"></script>
<script src="$!staticPrefix/wechat/js/share.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_publicGood.js"></script>
</html>