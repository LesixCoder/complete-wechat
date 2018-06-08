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
	<title>亲子报名</title>
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/enlist/enlist_index.css">
</head>

<body>
	<div class="wrapBox wrapBox_iphone">
		<section class="subInfor_index">
			<div class="infor_input">
				<p><i class="fa fa-user-o"></i><input id="infor_name" type="text" placeholder="输入您的姓名" pattern="^[\u4e00-\u9fa5a-zA-Z0-9_]+$" required><em class="name_Box fa"></em></p>
				<p><i class="fa fa-phone"></i><input id="infor_tel" type="tel" placeholder="输入您的电话" pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"
					required><em class="tel_Box fa"></em></p>
				<p><i class="fa fa-child"></i><input id="infor_childName" type="text" placeholder="输入儿童姓名" pattern="^[\u4e00-\u9fa5a-zA-Z0-9_]+$" required><em class="childName_Box fa"></em></p>
				<p><i class="fa fa-heart-o"></i><input id="infor_childAge" type="number" placeholder="输入儿童年龄" min="0" max="99" pattern="^([1-9]\d|\d)$" required><em class="age_Box fa"></em></p>
			</div>
			<div class="infor_goods">
				<h4 class="goods_tit"><i class="fa fa-gift"></i>我的商品</h4>
				<figure class="goods_box">
					<img src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
					<figcaption>
						<div><h5>$!ticket.name</h5><em>*1</em></div>
						<span class="goods_price"><i>$!ticketPrice</i> 元</span>
					</figcaption>
				</figure>
				#foreach($bean in $goodsList)
				<figure class="goods_box">
					<img src="$!staticPrefix$!bean.img_url" alt="">
					<figcaption>
						<div><h5>$!bean.name</h5><em>*$!bean.price</em></div>
						<span class="goods_price"><i>$!bean.price</i> 元</span>
					</figcaption>
				</figure>
				#end
			</div>
		</section>
		<footer class="footer footer_setMeal footer_subInfor">
			<span class="priceText">合计：<em><i>$!price</i>元</em></span>
			<a class="nextBtn payBtn" href="javascript:;">去支付</a>
		</footer>
		<input id="matchId" type="hidden" value="$!matchId">
		<input id="goods_str" type="hidden" value="$!goodsStr">
		<input id="ticketId" type="hidden" value="$!ticket.id">
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/enlist/enlist_index.js"></script>
</body>

</html>