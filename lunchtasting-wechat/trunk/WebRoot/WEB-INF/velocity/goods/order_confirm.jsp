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
	<title>提交订单</title>
	
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">
	
</head>

<body style="display:none;">
	<div class="wrapBox">
		<header class="header">
			<h1>提交订单</h1>
		</header>
		<section class="order">
			<!--  无地址  -->
			#if("$!address" == "")
			<div class="order__top">
				<a class="newOrder" id="newOrder" href="/address/list?biz_id=$!goods.goods_sku_id&biz_type=1&biz_str=$!number"><span class="glyphicon glyphicon-plus"></span>新建收货地址</a>
			</div>
			#else
			<!--  有地址  -->
			<div class="order__top order__topNew">
				<a href="/address/list?biz_id=$!goods.goods_sku_id&biz_type=1&biz_str=$!number">
					<div>
						<h3><span>$!address.name</span><span>$!address.phone</span></h3>
						<p>$!address.province$!address.city$!address.town$!address.detail</p>
					</div>
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
			#end
			<div class="order__middle">
				<h4 class="order__mTit"><span class="glyphicon glyphicon-home"></span>$!goods.channel_name</h4>
				<div class="order__mImg">
					<img src="$!imgPrefix$!goods.img_url" alt="">
					<summary>
						<p><em>$!goods.goods_name</em><span><i>$!goods.price</i>&nbsp;元</span></p>
						<p><em>$!goods.goods_property_name<span>*<i>$!number</i></span></p>
				</summary>
			</div>
			<p class="order__ship">配送方式<span><i>快递</i>&nbsp;<i>免邮</i></span></p>
			<p class="order__lWord">买家留言：<input type="text" id="remark" name="remark" placeholder="点击给商家留言"></p>
		</div>
		<div class="order__bottom">
			<p>商品金额：<span><i>$!price</i>&nbsp;元</span></p>
			<p>商品运费：<span><i>0</i>&nbsp;元</span></p>
		</div>
	</section>
	<footer class="footer footer-order">
		<div class="footer__order">
			<input type="hidden" id="addressId" name="addressId" value="$!address.id">
			<input type="hidden" id="goodsSkuId" name="goodsSkuId" value="$!goods.goods_sku_id">
			<input type="hidden" id="number" name="number" value="$!number">
			
			<a class="orderBtn">提交订单</a>
			<span>合计：<i>$!price元</i></span>
		</div>
	</footer>
</div>
</body>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="$!staticPrefix/wechat/js/goods/goods_order.js"></script>
</html>