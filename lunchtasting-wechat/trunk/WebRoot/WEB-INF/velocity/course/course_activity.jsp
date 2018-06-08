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
	<title>报名</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course_act.css">
</head>

<body>
	<section class="wrapBox wrapBox_yellow">
		<section class="course_act">
			<header class="act-header">
				<img class="act-headImg" src="$!staticPrefix/wechat/images/course/act/act-headImg.png" alt="">
				<p>价值<span>168元</span>单次体验课限时<i>0</i>元抢</p>
				<img class="act-logo" src="$!staticPrefix/wechat/images/course/act/act-logo.png" alt="">
			</header>
			<section class="act-content">
				<p>
					<span>选择体验课时间 <i>*</i></span>
					<select name="" id="">
						<option value="0">--选择时间--</option>
						<option value="1">11月12日</option>
						<option value="2">11月15日</option>
					</select>
				</p>
				<p><span>请输入手机号 <i>*</i><input type="text"></span></p>
				<p><a id="act_signBtn" class="act-signBtn color-yellow" href="javascript:;">报名</a></p>
			</section>
			<section class="content-gym">
				<i class="gym-triangle"></i>
				<span class="gym-tit">训练场馆</span>
				<p class="gym-name">CrossFit ShiFu美式综合体能训练馆</p>
				<img class="gym-map" src="$!staticPrefix/wechat/images/course/act/act-map.jpg" alt="">
				<p class="gym-address">地址：朝阳区朝阳公园路19号佳隆国际大厦地下一层B1-110</p>
			</section>
			<section class="content-public">
				<img class="public-qrcode" src="$!staticPrefix/wechat/images/course/act/act-qrcode.jpg" alt="">
				<span class="public-text1">扫码关注公众号</span>
				<span class="public-text2">咆 · 哮 · 狗</span>
				<span class="public-careful">注意事项</span>
				<ul class="public-carefulC">
					<li>每个用户只可兑换1课时综合体能训练</li>
					<li>兑换成功后需关注咆哮狗服务号，用户在24小时内可在服务号→0元体验活动中查询报名订单</li>
					<li>本次活动名额有限，我们将按照报名时间顺序安排课程。如遇报名过多情况，客服将会与您联系调换时间</li>
					<li>本次活动的最终解释权归北京稼优佳文化传媒有限公司所有。</li>
				</ul>
			</section>
		</section>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</body>

</html>