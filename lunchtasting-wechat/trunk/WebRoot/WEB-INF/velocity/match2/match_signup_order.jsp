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
	<title>我的订单</title>
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/enlist/enlist_index.css">
</head>

<body>
	<div class="wrapBox wrapBox_iphone">
		<section class="order_index">
            <!-- 订单列表 -->
           	#if($!list != "" && $!list.size() > 0)
	            #foreach($bean in $list)
				<div class="order_list">
					#if("$!bean.ticket_kind" == "1")
					<img class="order_img" src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
					#elseif("$!bean.ticket_kind" == "2")
					<img class="order_img" src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
					#else
					<img class="order_img" src="$!staticPrefix/wechat/images/intro/bg_pic.png" alt="">
					#end
					<div class="order_infor">
						<p><em>订单编号：</em><span>$!bean.order_id</span></p>
						<p><em>支付时间：</em><span>$!bean.create_time</span></p>
						<p><em>联系人：</em><span>$!bean.field1</span></p>
						<p><em>联系电话：</em><span>$!bean.field2</span></p>
					</div>
					<div class="cutOffLine"></div>
					#if($!bean.goods_list != "" && $!bean.goods_list.size() > 0)
						#foreach($goods in $!bean.goods_list)
						<div class="order_goods">
							<h3>购物清单</h3>
							<p><span>$!goods.name</span><i>$!goods.total_price元</i></p>
						</div>
						#end
					#end
				</div>
				#end
			#end
		</section>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/plugs/dropload.min.js"></script>
</body>

</html>