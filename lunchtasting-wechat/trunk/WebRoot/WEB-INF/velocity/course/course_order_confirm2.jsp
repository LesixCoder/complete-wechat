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
	<title>课程确认</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course.css">
</head>

<body>
	<section class="wrapBox wrapBox_iphone">
		<section class="order_confirm">
			<section class="confirm_box">
				<h3><span>课程确认</span><em></em></h3>
				<section class="dialog_d_list">
					<p><i class="color-yellow">活动名称：<em></em></i><span>$!courseMeal.course_name</span></p>
					<p><i class="color-yellow">价     格：<em></em></i><span>$!courseMeal.price元</span></p>
					<p><i class="color-yellow">时     间：<em></em></i><span>$!courseMeal.str_time</span></p>
					<p><i class="color-yellow">人     数：<em></em></i><span>$!courseMeal.people_number</span></p>
					<p class="dialog_d_list_tel"><i class="color-yellow">电话号码：<em></em></i><span><input type="tel" id="cm_tel" placeholder="请正确填写您的手机号" required pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"></span></p>
					<p class="dialog_d_list_sex"><i class="color-yellow">性别：<em></em></i><span><select id="cm_sex"><option value="1" selected>男</option><option value="2">女</option></select></p>
					<input type="hidden" id="course_meal_id" value="$!courseMeal.course_meal_id">
				</section>
				<div class="dialog_d_btn">
					<a id="dialog_btn_pay" class="dialog_btn_pay bgcolor-yellow" href="javascript:;">去支付</a>
				</div>
			</section>
		</section>
	</section>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/course/course_confirm.js"></script>
</body>

</html>