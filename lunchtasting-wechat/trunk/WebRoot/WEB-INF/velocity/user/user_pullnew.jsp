#parse("./common/global_helper.jsp")
<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
	<meta name="format-detection" content="telphone=no, email=no">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="renderer" content="webkit">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<meta name="full-screen" content="true">
	<meta name="x5-screen" content="true">
	<title>提现记录</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/share.css" />
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course-121.css">
</head>

<body>
	<section class="wrapBox wrapBox_iphone">
		<section class="user_pullnew">
			<section class="pullnew_head">
				<img src="$!imgPrefix$!user.img_url" alt="">
			</section>
			<section class="pullnew_body">
				<section class="pullnew_body_inner bgcolor-yellow">
					<h3>奖励说明</h3>
					<p>您可以分享您的专属链接给您的好友或分享到朋友圈，只要他们通过该链接注册并购买课程后，您将会获得相应的消费奖励。</p>
					<h3>具体流程</h3>
					<p>您的朋友在购买课程后，现金奖励会计入到您的账号中，您可通过平台申请提现，经平台核实通过后3个工作日内拨款到您的专属账户里。</p>
					<h3>提现规则</h3>
					<p>每个独立账号三天内只可提现一次，每次提现金额不得低于50元。</p>
					<h3>奖励依据</h3>
					<p>课程提供方制定的奖励规则。</p>
					<h3></h3>
					<p>任何违规套取奖励的行为，一经发现，将不予奖励，情节严重的将封停账号或依法追究法律责任。</p>
					<p>有任何关于本活动的问题和帮助请联系客服。</p>
					<a id="share_friend" class="share_friend color-yellow" href="javascript:;" onclick="_system._guide(true)">分享给好友</a>
				</section>
			</section>
		</section>
		<div id="cover"></div>
        <div id="guide"><img src="$!staticPrefix/wechat/images/vote/guide.png"></div>
	</section>

</body>
<script src="$!staticPrefix/wechat/js/share.js"></script>
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
		    title: '咆哮狗赛事训练营', // 分享标题
		    link: '$!url', // 分享链接
		    imgUrl: '$!imgPrefix$!user.img_url', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareAppMessage({
		    title: '咆哮狗赛事训练营', // 分享标题
		    desc: '我是"$!user.name"在这里赚的满盆金波，你也快来吧！', // 分享描述
		    link: '$!url', // 分享链接
		    imgUrl: '$!imgPrefix$!user.img_url', // 分享图标
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