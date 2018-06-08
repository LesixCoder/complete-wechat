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
</head>

<body>
	<div class="wrapBox">
		<header class="actpro_header">
			<h2 class="actpro_tit"></h2>
		</header>
		<section class="actpro_intro">
			<div class="actpro_box">
				<h3>【活动说明】</h3>
				<div class="box_content">
					<p>介绍是向别人展示你自我介绍好不好,甚至直接关系到你给别人的第一印象的好坏及以后交往的顺利与否。时,也是认识自我的手段。自我介绍是每一个职场中人都必然...</p>
				</div>
			</div>
			<div class="actpro_box">
				<h3>【大众组介绍】</h3>
				<div class="box_content">
					<p>介绍是向别人展示你自我介绍好不好,甚至直接关系到你给别人的第一印象的好坏及以后交往的顺利与否。时,也是认识自我的手段。自我介绍是每一个职场中人都必然...</p>
				</div>
			</div>
			<div class="actpro_box good_show">
				<h3>【物品展示】</h3>
				<div class="box_content">
					<img src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
					<img src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
				</div>
			</div>
			<div class="actpro_box actpro_task">
				<h3>【任务点】</h3>
				<div class="box_content">
					<p><i>1</i><span>在优客工厂区域找到，并得到奖励</span></p>
					<p><i>2</i><span>在优客工厂区域找到，并得到奖励</span></p>
					<p><i>3</i><span>在优客工厂区域找到，并得到奖励</span></p>
					<p><i>4</i><span>在优客工厂区域找到，并得到奖励</span></p>
					<div class="verticalLine"></div>
				</div>
			</div>
			<div class="actpro_box buyTicket">
				<h3>【购票】</h3>
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