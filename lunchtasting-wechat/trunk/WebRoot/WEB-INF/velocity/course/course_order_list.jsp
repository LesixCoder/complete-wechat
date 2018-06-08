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
	<title>我的课程</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course.css">
</head>

<body>
	<section class="wrapBox wrapBox_iphone">
		<!-- 
		<header class="myClass_head">
			<article><i class="color-yellow" id="trainTime-count"></i><p>累计训练次数</p></article>
			<article><i class="color-yellow" id="burnCal-count"></i><p>约消耗卡路里</p></article>
			<article><i class="color-yellow" id="fat-count"></i><p>累计减脂</p></article>
		</header> -->
		<section class="myClass_content">
			<h3 class="m_c_tit color-yellow"><span>我的课程</span></h3>
			#if("$!list" != "" && $!list.size() > 0)
			#foreach($bean in $list)
			<article class="m_c_list">
				<div class="m_list_left">
					<figure class="list_left_figure">
						<a href="/coach/$!bean.coach_id"><img src="$!bean.coach_img_url" alt=""></a>
						<figcaption>
							<h4>$!bean.course_name</h4>
							<p class="color-yellow">
								<span>$!bean.enroll_count/$!bean.people_number 
									<!-- #if($!bean.enroll_count != $!bean.people_number)组队中 #else 组队成功 #end -->
								</span>
								#if("$!bean.status" == "2")
								<i class="course_tag">待开课</i>
								#elseif("$!bean.status" == "3")
								<i class="course_tag i-over">已退款</i>
								#elseif("$!bean.status" == "4")
								<i class="course_tag">已开课</i>
								#elseif("$!bean.status" == "5")
								<i class="course_tag i-over">已结束</i>
								#end
							</p>
							<p class="color-yellow"><span>￥$!bean.course_price/#if("$!bean.type" == "1") 课  #else 营 #end</span></p>
						</figcaption>
					</figure>
					<p class="list_left_p color-yellow">$!bean.gym_name</p>
					<p class="list_left_p">时间：$!bean.str_time</p>
					<p class="list_left_p">地点：$!bean.address</p>
				</div>
				#if("$!bean.status" == "2")
				<div class="m_list_right">
					<!--<a class="color-yellow refund_btn" href="javascript:;" js-attr-orderId="$!bean.order_id">退款</a>  -->
					<a class="order_det_btn color-yellow" href="/course/order/detail?order_id=$!bean.order_id">订单详情</a>
					<a class="order_det_btn color-yellow" href="/course/vote/detail?order_id=$!bean.order_id">查看狗粮</a>
				</div>
				#end
			</article>
			#end
			#else
			<div class="myClass_empty">
				<img src="$!staticPrefix/wechat/images/course/kc.png" alt="">
				<p>您还没有报名的课程哦~</p>
			</div>
			#end
		</section>
	</section>
	<div class="filter" id="filter"></div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/plugs/countUp.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/course/count.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/course/course.js"></script>
</body>

</html>