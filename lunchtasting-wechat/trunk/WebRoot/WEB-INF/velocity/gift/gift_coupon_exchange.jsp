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
	<title>兑换成功</title>
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/gift/gift_coupon.css">
</head>

<body>
	<section class="wrapBox">
		<section class="course_coupon_list wrapBox-phone">
			<section class="coupon_list_content">
				#if("$!list" != "" && $!list.size() > 0)
					#foreach($bean in $list)
					#if("$!bean.id" != "1")
					<article class="coupon_article">
						<a href="/gift_coupon/detail?id=$!bean.id">
							<div class="article_left">
								#if("$!bean.type" == "1")
								<p class="article_left_p1"><span><i>$!bean.discount</i>折</span><span><i></i>折扣抵用券</span></p>
								#else
								<p class="article_left_p1"><span><i>$!bean.money</i>元</span><span><i></i>现金抵用券</span></p>
								#end
								<p class="article_left_p2"><span>券码：</span>$!bean.code</p>
								<p class="article_left_p2"><span>地址：</span>$!bean.seller_name $!bean.seller_address</p>
								<p class="article_left_p2"><span>有效期：</span>$!bean.str_begin_time - $!bean.str_end_time</p>
							</div>
							<div class="article_right">
								<img src="$!bean.seller_logo" alt="">
							</div>
						</a>
					</article>
					#else
					<article class="coupon_article coupon_article_new">
						<a href="/gift_coupon/detail?id=$!bean.id">
							<div class="article_left">
								<p class="article_left_p1"><span><i>$!bean.money</i>元</span><span><i>日本三年签证</i>现金抵用券</span></p>
								<p class="article_left_p2"><span>券码：</span>$!bean.code</p>
								<p class="article_left_p2"><span>地址：</span>$!bean.seller_name $!bean.seller_address</p>
								<p class="article_left_p2"><span>有效期：</span>$!bean.str_begin_time - $!bean.str_end_time</p>
							</div>
							<div class="article_right">
								<img src="$!bean.seller_logo" alt="">
							</div>
						</a>
					</article>
					#end
					#end
				#else
				<p class="coupon-empty">-- 你还没有卡券哦 --</p>
				#end
				<!--  
				<article class="coupon_article coupon_article-end">
					<a href="javascript:;">
						<div class="article_left">
							<p class="article_left_p1"><span><i>7</i>折</span><span><i></i>购买折扣券</span></p>
							<p class="article_left_p2"><span>券码：</span>ERDFDIJGDFLADFFA</p>
							<p class="article_left_p2"><span>地址：</span>80秒咖啡西直门店</p>
							<p class="article_left_p2"><span>有效期：</span>17/10/10-17/12/12</p>
						</div>
						<div class="article_right">
							<img src="$!staticPrefix/images/course/coupon/coupon-icon.png" alt="">
						</div>
					</a>
				</article>-->
			</section>
		</section>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="$!staticPrefix/wechat/js/gift/gift_coupon.js"></script>	
</body>

</html>