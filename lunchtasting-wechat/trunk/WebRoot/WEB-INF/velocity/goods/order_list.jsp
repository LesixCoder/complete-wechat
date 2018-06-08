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
	<title>我的订单</title>

	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">

</head>

<body style="display:none;">
	<div class="wrapBox wrapOrder">
		<header class="header">
			<h1>我的订单</h1>
		</header>
		<section class="orderMain">
			<div class="oM__nav">
				<a #if( "$！status"=="" ) class="active" #end href="/goods/order/list">全部</a>
				<a #if( "$！status"=="1" ) class="active" #end href="/goods/order/list?status=1">待付款</a>
				<a #if( "$！status"=="3" ) class="active" #end href="/goods/order/list?status=2">待发货</a>
				<a #if( "$！status"=="5" ) class="active" #end href="/goods/order/list?status=3">已完成</a>
			</div>
			<!-- 订单列表 -->
			<div class="oM__Box">
				#if("$!list" != "" && $!list.size() > 0 ) #foreach($bean in $list)
				<div class="order__middle">
					<p class="order__number">
						<span><i>订单编号：</i>$!bean.code</span> #if("$!bean.status" == "1")
						<em>等待买家付款</em> #elseif("$!bean.status" == "2")
						<em>已取消</em> #elseif("$!bean.status" == "3")
						<em>待发货</em> #elseif("$!bean.status" == "4")
						<em>已发货</em> #elseif("$!bean.status" == "5")
						<em>已完成</em> #elseif("$!bean.status" == "6")
						<em>已退款</em> #end
					</p>
					<!--<h4 class="order__mTit"><span class="glyphicon glyphicon-home"></span>$!bean.channel_name</h4> -->
					#foreach($goods in $bean.goods_list)
					<div class="order__mImg">
						<img src="$!imgPrefix$!goods.img_url" alt="">
						<summary>
							<p><em>$!goods.name</em>
								<!--<span><i>248</i>&nbsp;元</span></p>  -->
								<p><em>$!goods.property</em><span>*<i>$!goods.number</i></span></p>
						</summary>
					</div>
					#end
					<p class="order__ship"><span><i>合计：</i>&nbsp;<i>$!bean.price</i>元</span></p>
					<p class="order__btn" js-attr-orderId="$!bean.order_id">
						#if("$!bean.status" == "1")
						<a class="orderCancel" href="javascript:;" js-attr-orderId="$!bean.order_id">取消</a>
						<a class="payment" href="/wxpay/goods_model2?order_id=$!bean.order_id">付款</a> #end
					</p>
				</div>
				#end #else
				<!-- 没有订单的提示 -->
				<div class="tips">
				
					<span class="glyphicon glyphicon-alert"></span>
					<p>&nbsp;&nbsp;您还没有相关的订单，快去看看有哪些想买的</p>
				</div>
				#end
			</div>
		</section>
		<footer class="footer">
			<div class="footer__box">
				<a href="/goods"><span class="glyphicon glyphicon-eye-open"></span><p>发现</p></a>
				<a class="active" href="/goods"><span class="glyphicon glyphicon-list-alt"></span><p>订单</p></a>
				<a href="/user/center"><span class="glyphicon glyphicon-user"></span><p>我的</p></a>
			</div>
		</footer>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="$!staticPrefix/wechat/js/goods/goods_order.js"></script>
</body>

</html>