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
	<title>$!course.name</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course.css">
</head>

<body>
	<section class="wrapBox">
		<header class="details_head">
			<img src="$!course.img_url" alt="">
		</header>
		<section class="d_c_intro">
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">活动名称</h4>
			<p>$!course.name</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">参数人群</h4>
			<p>方圆大厦优客工场入驻企业小伙伴</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">活动形式</h4>
			<p>一男一女现场组队</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">主办方</h4>
			<p>咆哮狗综合体能、优客工场方圆社区</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">活动亮点</h4>
			<p>$!course.highlight</p>
			
			<p>
				<img src="http://image.mperfit.com/goods/20171107164426608.jpg" alt="">
				<img src="http://image.mperfit.com/goods/20171107162655352.jpg" alt="">
			</p>
			
			<!-- 
			<p>
				<img src="http://image.mperfit.com/goods/20171107162729093.jpg" alt="">
				<img src="http://image.mperfit.com/goods/20171107163146130.jpg" alt="">
			</p> -->
			
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">活动流程</h4>
			<p>$!course.process</p>
			
			
		<section class="details_content">
			#if($!gymList != "" && $!gymList.size() > 0)
			#foreach($bean in $gymList)
			<section class="d_c_course">
				<h3 class="d_c_courTit"><span><i class="color-yellow">场馆信息：</i>$!bean.gym_name</span> <a class="color-yellow" href="/gym/map?gym_id=$!bean.gym_id"><i class="fa fa-angle-right"></i></a></h3>
				
				#foreach($course in $bean.course_meal_list)
				<div class="d_c_courList">
					<figure class="c_courList_intro">
						<div>
							<a href="/coach/$!course.coach_id"><img src="$!course.coach_img_url" alt=""></a>
							<b>$!course.coach_name</b>
						</div>
						<figcaption>
							<h4>$!course.name</h4>
							<i class="color-yellow">￥$!course.price<em class="color-gray">￥$!course.market_price</em> /#if("$!course.type" == "1") 课  #else 营 #end</i>
						</figcaption>
						#if("$!course.status" == "1")
						<a class="courList_intro_btn" href="/course/activity/confirm?cm_id=$!course.id">报名</a>
						#else
						<a class="courList_intro_btn btn-active" href="javascript:;">满员</a>
						#end
					</figure>
					<p>$!course.str_time</p>
				</div>
				#end
			</section>
			#end
			#end
		</section>
		<!-- 
		<footer class="details_footer">
			<a class="class-notice-btn" href="javascript:;">新课程通知</a>
		</footer> -->
	</section>
	<aside class="sidebar-serve">
		<!-- <a class="bgcolor-yellow" href="/user/pull_new?user_id=$!userId"><img src="$!staticPrefix/wechat/images/course/icon_invite.png" alt="">邀请好友</a> -->
		<a class="bgcolor-yellow" href="/course/order/list"><img src="$!staticPrefix/wechat/images/course/icon_watch.png" alt="">查看订单</a>
	</aside>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
var _href = location.href;
if(_href.indexOf('&')>-1){
	location.href = location.href.split('&')[0];
}


	 wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '$!appId', // 必填，公众号的唯一标识
		    timestamp: '$!timestamp', // 必填，生成签名的时间戳
		    nonceStr: '$!nonceStr', // 必填，生成签名的随机串
		    signature: '$!signature',// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		}); 

	//jQuery(function() {
	wx.ready(function(){
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.onMenuShareTimeline({
		    title: '119火力全开', // 分享标题
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/course/20171107155828084.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '119火力全开', // 分享标题
		    desc: '在如此美好的季节和年纪里,让我们一起解除懒惰的封印,赴一场运动的社交', // 分享描述
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/course/20171107155828084.jpg', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
	});
//}); 
</script>


</html>