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
					if (clientWidth >= 640) {
						docEl.style.fontSize = '100px';
					} else {
						docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
					}
				};

			if (!doc.addEventListener) return;
			win.addEventListener(resizeEvt, recalc, false);
			doc.addEventListener('DOMContentLoaded', recalc, false);
		})(document, window);
		setTimeout(function () {
			document.body.style.display = 'block';
		}, 100)
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
	<title>我的收货地址</title>

	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">

</head>

<body style="display:none;">
	<div class="wrapBox wrapOrder">
		<header class="header">
			<h1>管理收货地址</h1>
		</header>
		<section class="manaAddress">
			#if("$!list" != "" && $!list.size() > 0 ) #foreach($bean in $list)
			<figure class="mana__list" id="list$!bean.id">
				#if("$!bizType" == "1")
				<a href="/goods/order/confirm?address_id=$!bean.id&gs_id=$!bizId&number=$!bizStr">
					<div>
						<h3><span>$!bean.name</span><span>$!bean.phone</span><i class="glyphicon glyphicon-ok-circle"></i></h3>
						<p>$!bean.province$!bean.city$!bean.town$!bean.detail</p>
					</div>
				</a>
				#else
				<a href="#">
					<div>
						<h3><span>$!bean.name</span><span>$!bean.phone</span></h3>
						<p>$!bean.province$!bean.city$!bean.town$!bean.detail</p>
					</div>
				</a> 
				#end
				<figcaption>
					#if("$!bean.is_frequently" == "1")
					<label class="manaRadio checked" js-attr-addressId="$!bean.id">
					<i><span class="glyphicon glyphicon-ok-sign"></span>默认地址</i>
					<input type="radio" name="address" checked>
				</label> #else
					<label class="manaRadio" js-attr-addressId="$!bean.id">
					<input type="radio" name="address">
					<i><span class="glyphicon glyphicon-check"></span>设为默认</i>
				</label> #end
					<aside>
						<a class="editBtn" href="/address/modify?address_id=$!bean.id"><span class="glyphicon glyphicon-edit"></span>编辑</a>
						<a class="remoBtn" href="javascript:;" js-attr-addressId="$!bean.id"><span class="glyphicon glyphicon-trash"></span>删除</a>
					</aside>
				</figcaption>
			</figure>
			#end #else
			<!-- 没有收获地址的提示 -->
			<div class="tips">
				<span class="glyphicon glyphicon-alert"></span>
				<p>您还没有收货地址，快去创建吧</p>
			</div>
			#end
			<div class="tips tipsA" style="display:none;">
				<span class="glyphicon glyphicon-alert"></span>
				<p>您还没有收货地址，快去创建吧</p>
			</div>
		</section>
		<footer class="footer footer-address">
			<a class="purchase manaBtn" href="/address/add?biz_id=$!bizId&biz_type=$!bizType&biz_str=$!bizStr"><span class="glyphicon glyphicon-plus"></span>新增地址</a>
		</footer>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/address.js"></script>
</body>

</html>