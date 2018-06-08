#parse("./common/global_helper.jsp")
<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
	<meta name="format-detection" content="telphone=no, email=no">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<meta name="full-screen" content="true">
	<meta name="x5-screen" content="true">
	<title>玩美</title>
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/enlist/enlist_index.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/enlist/enlist_sign.css">
</head>

<body>
	<div class="wrapBox wrap_enterDz">
		<header class="enterDz_header"></header>
		<section class="enterDz_intro">
			<div class="enterDz_content">
				<h3 class="dzContent_tit"><span>大众组报名须知</span><em></em></h3>
				<p>大众组活动主要通过在商场内外根据参赛时发的任务地图，找到打卡任务点，在该任务点完成指定任务并向工作人员索取打卡印章，集满所有印章，并到达最终地点，即算任务成功</p>
				<h4>报名要求</h4>
				<p>年满18－55周岁，身体健康，无高血压、心脏病史及妨碍剧烈运动的其他疾病，有一定运动基础。</p>
			</div>
			<div class="enterDz_btn buyTicket">
				<div class="box_content">
					#foreach($bean in $ticketList)
					<a href="/match/ticket/meal?match_id=$!match.id&ticket_id=$!bean.id">
						$!bean.pay_price元<small>  $!bean.content</small>
					</a>
					#end
				</div>
			</div>
		</section>
	</div>
</body>

</html>