#parse("./common/global_helper.jsp")
<!doctype html>
<html style="font-size:50px">
<head>
<meta charset="utf-8">
<script>
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if(clientWidth>=640){
                    docEl.style.fontSize = '100px';
                }else{
                    docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
    setTimeout(function(){
        document.body.style.display = 'block';
    },100)
</script>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
<meta name="format-detection" content="telphone=no, email=no">
<meta name="msapplication-tap-highlight" content="no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="true">
<meta name="x5-screen" content="true">
<title>个人中心</title>

<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/mobileSelect.css">
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">

</head>

<body style="display:none;">
<div class="wrapBox wrapOrder">
	<header class="header">
		<h1>个人中心</h1>
	</header>
	<section class="user">
		<div class="user__list">
			<p class="user__head"><a href="javascript:;"><span>头像</span><i><img src="$!imgPrefix$!user.img_url" alt=""></i></a></p>
			<p class="user__name"><a href="javascript:;"><span>昵称</span><i id="user__name">$!user.name</i></a></p>
			<p class="user__gender"><a href="javascript:;"><span>性别</span><i id="user__gender">#if("$!user.sex" == "1") 男 #else 女 #end</i></a></p>
		</div>
		<div class="user__list">
			<p><a href="/address/list"><span>我的收货地址</span><i class="glyphicon glyphicon-chevron-right"></i></a></p>
			<p><a href="javascript:;"><span>联系客服</span><i class="glyphicon glyphicon-chevron-right"></i></a></p>
			<p><a href="javascript:;"><span>帮助中心</span><i class="glyphicon glyphicon-chevron-right"></i></a></p>
		</div>
	</section>
	<footer class="footer">
		<div class="footer__box">
			<a href="/goods"><span class="glyphicon glyphicon-eye-open"></span><p>发现</p></a>
			<a href="/goods/order/list"><span class="glyphicon glyphicon-list-alt"></span><p>订单</p></a>
			<a class="active"><span class="glyphicon glyphicon-user"></span><p>我的</p></a>
		</div>
	</footer>
</div>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="$!staticPrefix/wechat/js/plugs/mobileSelect.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/goods_index.js"></script>
<script>
var mobileSelect = new MobileSelect({
    trigger: '#user__gender', 
    title: '选择性别',  
    wheels: [
                {data:['男','女']}
            ],
    position:[0], //初始化定位
    callback:function(indexArr, data){
        //console.log(indexArr+'-'+data); //返回选中的json数据
    } 
});
</script>
</body>
</html>		