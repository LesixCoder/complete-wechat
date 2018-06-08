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
	<title>$!gym.name</title>
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/course/course.css">
</head>

<body>
	<section class="wrapBox wrapBox_iphone">
		<section class="course_map">
			<header class="map_nav"><a href="#" onclick="javascript:history.back(-1);"><i class="fa fa-angle-left"></i></a>地图</header>
			<section class="map_box" id="myMap"></section>
			<input id="longitude" type="hidden" value="$!gym.longitude">
			<input id="latitude" type="hidden" value="$!gym.latitude">
			<section class="map_intro">
				<h4>$!gym.name</h4>
				<p>$!gym.address</p>
			</section>
		</section>
	</section>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=c2309cd978ac70749966f7c47ae6ce81"></script> 
	<script type="text/javascript" src="$!staticPrefix/wechat/js/course/common.js"></script>
	<script type="text/javascript">
		//$!gym.longitude,$!gym.latitude
		var longitude = jQuery('#longitude').val();
		var latitude = jQuery('#latitude').val();
		console.log(longitude+' - '+latitude);
        var map = new AMap.Map('myMap',{
            resizeEnable: true,
            zoom: 15, //地图显示的缩放级别，若center与level未赋值，地图初始化默认显示用户所在城市范围
            center: [longitude,latitude], //地图中心点坐标值(经度，纬度)
		});
		//map.setZoom(15);
		//map.setCenter([longitude,latitude]); //坐标
		//添加点标记，并使用自己的icon
		new AMap.Marker({
			map: map,
			position: [longitude,latitude], //坐标
			icon: new AMap.Icon({            
				size: new AMap.Size(50, 60),  //图标大小
				image: "$!staticPrefix/wechat/images/course/way_btn2.png",
				imageOffset: new AMap.Pixel(0, 0)
			})        
		});
		//工具条和比例尺
		AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
			//TODO  创建控件并添加
			var toolBar = new AMap.ToolBar();
			var scale = new AMap.Scale();
			map.addControl(toolBar);
			map.addControl(scale);
		});
    </script>
</body>

</html>