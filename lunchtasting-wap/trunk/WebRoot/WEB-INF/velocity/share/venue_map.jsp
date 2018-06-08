#parse("./global/base.jsp")
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="$pathStatic/css/wap/wap_index/venue.css" />
</head>

<body style="position:relative;overflow-y:hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>地图</h1>
                <a class="back" href="javascript:void(0)" onClick="javascript:history.back(-1);">&lt;</a>         
            </nav> 
        </header>
        <section class="content-com" id="mapCon">
            <!--场馆地图 start-->
            <div id="venueMap"></div>
            <!--场馆地图 end-->
            <div class="body-details mapDetails">   
                <div>
                    <h4>$!seller.name</h4>
                    <p>$!seller.specific_address</p>
                </div>  
                <div>
                    <h4>营业时间</h4>
                    <p>$!seller.business_hours</p>
                </div>
            </div> 
        </section>
    </div>  
</body>
<script type="text/javascript" src="$pathStatic/js/common_js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=633f2710cede41f1b68a1d6218d9edac"></script>
<script type="text/javascript">
    //地图初始化 
    var map = new AMap.Map('venueMap',{
        zoom: 15, //地图显示的缩放级别，若center与level未赋值，地图初始化默认显示用户所在城市范围
        center: [$seller.longitude,$seller.latitude], //地图中心点坐标值(经度，纬度)
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
        position: [$seller.longitude,$seller.latitude], //后台传入经纬度
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
    /*监听页面变化动态高度*/
    $(function(){
        window.addEventListener('resize', function(e){
            var winH=$(window).height();
            $("#mapCon").height(winH);
        }, false);
        var winH=$(window).height();
        $("#mapCon").height(winH);
    });
    var scrollOff=0;
    document.addEventListener("touchmove",function(e){
        if(scrollOff==0){
            e.preventDefault();
            e.stopPropagation();
        }
    },false);
	
</script>
</html>
