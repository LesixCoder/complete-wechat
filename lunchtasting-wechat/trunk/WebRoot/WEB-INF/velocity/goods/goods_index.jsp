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
	<title>商城首页</title>
	
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/dropload.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">

</head>

<body style="display:none;">
	<div class="wrapBox">
		<header class="header">
			<h1>商城</h1>
		</header>
		<section class="main">
			<div class="mainIn">
				#foreach($bean in $list)
				<figure class="main__list opacity">
					<a href="/goods/detail?goods_id=$!bean.goods_id"><img src="$!imgPrefix$!bean.img_url" alt=""></a>
					<figcaption>
						<p>【$!bean.name】$!bean.content</p>
						<summary>来自：<i>$!bean.channel_name</i>
							<!-- <em>丨</em><i>9</i>人已购买 --></summary>
					</figcaption>
				</figure>
				#end
			</div>
		</section>
		<footer class="footer">
			<div class="footer__box">
				<a class="active" href="/goods"><span class="glyphicon glyphicon-eye-open"></span><p>发现</p></a>
				<a href="/goods/order/list"><span class="glyphicon glyphicon-list-alt"></span><p>订单</p></a>
				<a href="/user/center"><span class="glyphicon glyphicon-user"></span><p>我的</p></a>
			</div>
		</footer>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/plugs/dropload.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/ajax.js"></script>
</body>

</html>