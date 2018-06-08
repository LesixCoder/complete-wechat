#parse("./global/base.jsp")
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
    <meta name="renderer" content="webkit">
    <meta name='apple-itunes-app' content='app-id=1155981225'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no"> 
    <link rel="stylesheet" href="$pathStatic/css/wap/wap_index/style.css" /> 
    <script src="$pathStatic/js/common_js/jquery.js"></script>
	<script src="$pathStatic/js/common_js/jquery.lazyload.js"></script>
	<script type="text/javascript">
		jQuery('.body-details img').addClass('lazy');
		$(function() {
	        $("img.lazy").lazyload({
	            placeholder : "$pathStatic/js/common_js/grey.gif",     //用图片提前占位
	            effect : "fadeIn",    //载入使用何种效果,effect(特效),值有show(直接显示),fadeIn(淡入),slideDown(下拉)等,常用fadeIn
	            threshold : 0,    //提前开始加载
	            event : "sporty"     //事件触发时才加载,event,值有click(点击),mouseover(鼠标划过),sporty(运动的),foobar(…).可以实现鼠标划过或点击图片才开始加载,后两个值未测试…
	            //failurelimit : 10,     //图片排序混乱时 ,
	        });
	    });
	</script>
</head>

<body style="overflow-x:hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>赛事详情</h1>
            </nav> 
        </header>
        <section class="content-com">
            <header class="header-details">
                <img src="$!bean.img_url" alt="">
                <h3>$!bean.name</h3>
                <div>
                    <p><span><i>$!bean.now_enroll_num</i>/<i>$!bean.enroll_num </i>报名</span></p>
                    <p><span><i>$!bean.price</i>元/人</span>&nbsp;<span>￥<i>$!bean.market_price</i></span></p>
                    <p><span><i>$!bean.time</i></span></p>
                    <p><span>$!bean.address</span></p>  
                </div>
            </header>
            <div class="body-details">
                <!--图文混编所在位置-->
                $!bean.img_text
            </div>
            <div class="footer-details">
                <div id="myMap"></div>
            </div>        
        </section>
        <footer class="footer footer-Sign" style="display:none;">
            <a href="javascript:void(0)" id="openApp">报名参加</a>        
        </footer>
        <!-- fixed footer! -->
	    <div class="footerBar" style="position: fixed;bottom: 0;left: 0;right: 0;background-color: rgba(0,0,0,0.61);
				border: 0;margin: 0;padding: 0;height: 60px;width: 100%;z-index: 9999;">
		
		<!-- logo -->
		<div style="display:inline-block; margin-top: 10px;margin-left: 10px;width: 175px;font-size:10px; text-align: left;color: white;line-height: 20px;">
			<img alt="logo" src="$pathStatic/image/wap/wap_index/logo.jpg"
				style="height:40px; border-radius:4px;float: left;margin-right: 7px;">
			<span style="font-size: 15px;font-weight: bold;">PERFIT</span>
			<br/><span>只为不可思议</span>
		</div>
		
		<!-- 跳转按钮 -->
		<a class="openapp_btn" onclick="openCourseInApp()"
			style="float: right;margin: 18px 18px 0;display: inline-block;z-index: 10000;color: white;border-radius:3px;
				font-size: 10px;text-align: center;width: 73px;height: 23px;line-height: 23px;border: 1px solid #fff;">打开APP</a>
	</div>
    </div>  
</body>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=633f2710cede41f1b68a1d6218d9edac"></script>
<script src="$pathStatic/js/wap/wap_index/util.js"></script>
<script type="text/javascript">
	//地图初始化 
	var map = new AMap.Map('myMap',{
	    zoom: 15, //地图显示的缩放级别，若center与level未赋值，地图初始化默认显示用户所在城市范围
	    center: [$bean.longitude,$bean.latitude], //地图中心点坐标值(经度，纬度)
	    //zooms: [3-19], //地图显示的缩放级别范围，在PC上，默认为[3,18]；在移动设备上,取值范围[3-19]
	    //lang: 'zh_cn', //地图语言类型，可选值：zh_cn：中文简体，en：英文，zh_en：中英文对照
	    //animateEnable: true, //地图平移过程中是否使用动画（如调用panBy、panTo、setCenter、setZoomAndCenter等函数
	    //isHotspot: false, //是否开启地图热点，默认false 不打开
	    //rotateEnable: false, //地图是否可旋转，默认false
	    //resizeEnable: false, //是否监控地图容器尺寸变化，默认值为false
	    //scrollWheel: true //地图是否可通过鼠标滚轮缩放浏览，默认为true。此属性可被setStatus/getStatus 方法控制
	});
	//点标记
	var marker = new AMap.Marker({
	    position: [$bean.longitude,$bean.latitude], //后台传入经纬度
	    map:map
	});
	//工具条和比例尺
	AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
	    //TODO  创建控件并添加
	    var toolBar = new AMap.ToolBar();
	    var scale = new AMap.Scale();
	    map.addControl(toolBar);
	    map.addControl(scale);
	});
    
    //在app中打开课程，没有app会跳转腾讯应用宝
	function openCourseInApp(){
		if(is_weixinBrowser()){
			showOpenBrowserMask("点击右上角在safari浏览器中打开");
		}else{
            window.location.href = "IOSPerfit://";//ios app协议
            window.setTimeout(function () {  
            	window.location.href = "https://itunes.apple.com/cn/app/id1155981225";
            }, 2000);
		}
	}
</script>
</html>
