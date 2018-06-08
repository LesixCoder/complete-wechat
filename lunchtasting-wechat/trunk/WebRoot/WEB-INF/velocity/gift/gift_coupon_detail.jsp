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
	<title>兑换优惠券</title>
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/gift/gift_coupon.css">
</head>

<body>
	<section class="wrapBox wrapBox-phone">
		<section class="course_coupon">
			<section class="coupon_head">
				#if("$!giftCoupon.type" == "1")
				<p class="coupon_h_zhe"><span>$!giftCoupon.discount折</span>&nbsp;$!giftCoupon.seller_name专属礼券</p>
				#else
				<p class="coupon_h_zhe"><span>$!giftCoupon.money元</span>&nbsp;$!giftCoupon.seller_name专属礼券</p>
				#end
				
				<p class="coupon_h_time">有效期至$!giftCoupon.str_end_time</p>
			</section>
			<section class="coupon_rule">
				<h3 class="coupon_rule_tit"><span>使用规则</span></h3>
				<ul class="coupon_rule_list">
					$!giftCoupon.explain
				</ul>
				<input id="giftCouponId" type="hidden" value="$!giftCoupon.id">
				#if("$!isCode" == "")
				<a class="convert_btn bgcolor-brown" id="convert_btn" js-convert="1" href="javascript:;">立即兑换</a>
				#else
				<a class="convert_btn bgcolor-ccc" id="convert_btn" js-convert="0" href="javascript:;">已兑换</a>
				#end
			</section>
		</section>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="$!staticPrefix/wechat/js/gift/gift_coupon.js"></script>	
</body>

</html>