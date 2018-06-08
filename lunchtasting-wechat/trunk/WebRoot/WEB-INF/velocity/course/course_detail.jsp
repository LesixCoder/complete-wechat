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
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程名称</h4>
			<p>$!course.name</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程类型</h4>
			<p>$!course.mold</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程特点</h4>
			<p>$!course.characteristic</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程标签</h4>
			<p>
				#foreach($tag in $course.tag_list)
				<i>$!tag</i>
				#end
			</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程亮点</h4>
			<p>$!course.highlight</p>
			<p class="color-yellow">咆哮狗赛事训练营首发体验官：体验课程，可获赠咆哮狗运动束口袋和运动手环,数量有限,先到先得。</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">课程流程</h4>
			<p>$!course.process</p>
			
			<p>
				#foreach($img in $course.img_list)
				<img src="$!img" alt="">
				#end
			</p>
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">会员权益</h4>
			<p>付费会员将得到Crazy Dog运动头巾，Crazy Dog运动手环，Crazy Dog运动挂链</p>
			
			
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">报名流程</h4>
			<p class="c_intro_p color-yellow">
				<span>①公众号报名成功</span>
				<em>↓</em>
				<span>②个人中心—我的课程查看报名状态</span>
				<em>↓</em>
				<span>③课程前短信通知</span>
				<em>↓</em>
				<span>④现场上课签到</span>
			</p>
			<h4 class="color-yellow"><img src="$!staticPrefix/wechat/images/course/icon_kcjs.png" alt="">注意事项</h4>
			<p>1.以下人群不能参加：孕妇，产妇（顺产后不足6个月，剖腹产后不足1年），有心血管疾病者，有其他伤病（医生不建议参与剧烈锻炼者），未成年人（部分针对未成年的训练营例外）</p>
			<p>2.学员应根据课程时间，合理安排自己的时间，生病、出差、女性生理期、加班等，非咆哮狗课程原因导致的缺课，请假，均不予以补课退课退款</p>
			<p>3.学员因怀孕，伤残等原因导致的缺课请假（需出具医院证明），咆哮狗退回所有未进行课程费用，不予补课</p>
			<p>4.所有报名训练营用户报名支付后，一定要保存好支付凭据（可在我的订单中查看）</p>
			<p>5.禁止空腹参加训练</p>
			<p>6.必须穿着运动服装和鞋子参加课程</p>
			<p>重要：成功预约本课程即表示已阅读并同意《咆哮狗运动注意事项及免责声明》</p>
		</section>
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
						<a class="courList_intro_btn" href="/course/up/confirm?cm_id=$!course.id">报名</a>
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

</html>