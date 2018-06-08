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
	<title>选择套餐</title>
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/enlist/enlist_index.css">
</head>

<body>
	<div class="wrapBox wrapBox_black wrapBox_iphone">
		<section class="setMeal_index">
			<div class="setMeal_list setMeal_list_1">
				<input type="checkbox" id="setmeal_1" name="setmeal" value="" checked disabled>
				<figure class="list_figure">
					<label class="figure_label" for="setmeal_1"><em class="figure_em"></em></label>
					<img class="figure_img" src="$!staticPrefix//wechat/images/intro/bg_pic.png" alt="">
					<figcaption class="figure_text">
						<input id="matchId" type="hidden" value="$!matchId">
						<input id="ticketId" type="hidden" value="$!ticket.id">
						<input id="ticketKind" type="hidden" value="$!ticket.kind">
						<div class="text_tit">
							<h4>$!ticket.name<i></i></h4>
							<p class="tit_p">
								<a class="sub" href="javascript:;"><i>-</i></a>
								<input class="amount" type="number" value="1" min="0" max="100">
								<a class="add" href="javascript:;"><i>+</i></a>
							</p>
						</div>
						<span class="setMeal_price"><i>$!price</i> 元<em>$!price</em></span>
					</figcaption>
				</figure>
			</div>
			<h3 class="list_discount_tit"><span>优惠选购</span></h3>
			#foreach($bean in $goodsList)
			<div class="setMeal_list setMeal_list_2">
				<input type="checkbox" id="$!bean.match_goods_id" name="setmeal" value="">
				<figure class="list_figure">
					<label class="figure_label" for="$!bean.match_goods_id"><em class="figure_em"></em></label>
					<img class="figure_img" src="$!imgPrefix$!bean.img_url" alt="">
					<figcaption class="figure_text figure_text_list">
						<input class="match_goods_id" type="hidden" value="$!bean.match_goods_id">
						<div class="text_tit">
							<h4>$!bean.name<i></i></h4>
							<p class="tit_p">
								<a class="sub" href="javascript:;"><i>-</i></a>
								<input class="amount" type="number" value="1" min="0" max="100">
								<a class="add" href="javascript:;"><i>+</i></a>
							</p>
						</div>
						<span class="setMeal_price"><i>$!bean.price</i> 元<em>$!bean.price</em></span>
					</figcaption>
				</figure>
			</div>
			#end
		</section>
		<footer class="footer footer_setMeal">
			<span class="priceText">合计：<em><i id="priceAll">0</i>元</em></span>
			<a id="mealBtn" class="nextBtn" href="javascript:;">下一步</a>
		</footer>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/enlist/enlist_index.js"></script>
</body>

</html>