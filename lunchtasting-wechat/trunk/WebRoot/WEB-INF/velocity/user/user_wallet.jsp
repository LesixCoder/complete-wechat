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
	<title>我的钱包</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course-116.css">
</head>

<body>
	<section class="wrapBox wrapBox_iphone">
		<section class="course_wallet">
				<p class="Invite_friend"><a class="color-yellow" href="/user/pull_new?user_id=$!user.user_id">邀请好友赢现金</a></p>
			<img class="wallet_icon" src="$!staticPrefix/wechat/images/course/money.png" alt="">
			<p class="my_income color-yellow">我的收益</p>
			<p class="income_count color-yellow">￥$!deposit &nbsp;</p>
			#if("$!is_draw" != "")
			<a class="withdrawals bgcolor-yellow" href="javascript:;" id="withdraw">提现</a>
			#else
			<a class="withdrawals bgcolor-ccc" href="javascript:;" id="withdraw">提现</a>
			#end
			<p class="residue_cash color-gray">超过20元可提现，今天还可提现<i class="color-yellow">$!count</i>次</p>
			<p class="income_details"><a class="color-yellow" href="/user/withdraw/list">收益明细</a></p>
			<p class="wallet_copy color-gray">点击提现，即表示您已同意<a href="/user/withdraw/rule" class="color-yellow">《奖励规则》</a></p>
		</section>
	</section>
	<section class="dialog-model md-modal md-effect-19">
		<section class="md-content">
			<section class="diialog-withdraw">
				<section class="diialog-withdraw-content">
					<h3></h3>
					<p><span>微 信 号:</span><input type="text" id="user_wx" placeholder="请填写您微信收款账号" pattern="^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$" required><i class="user_wx_Box"></i></p>
					<p><span>真实姓名：</span><input type="text" id="user_name" placeholder="请填写您的真实姓名" pattern="^[\u4e00-\u9fa5a-zA-Z0-9_]+$" required><i class="user_name_Box"></i></p>
					<p><span>手 机 号 :</span><input type="tel" id="user_tel" placeholder="请填写您的手机号" pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"
						required><i class="user_tel_Box"></i></p>
					<p><span>提现金额：</span><input type="number" id="user_gold" placeholder="提现金额不小于20" min="20" pattern="(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)"><i class="user_gold_Box"></i></p>
					<a class="apply_withdraw color-yellow" id="apply_withdraw" href="javascript:;">申请提现</a>
				</section>
				<div class="dialog_d_btn">
					<a class="dialog_btn_close" href="javascript:;"><i class="fa fa-times-circle-o"></i></a>
				</div>
			</section>
		</section>
	</section>
	<div class="filter" id="filter"></div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/course/course_withdraw-115.js"></script>
</body>

</html>