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
	<title>商品详情</title>
	
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">
</head>

<body style="display:none;">
	<input type="hidden" id="goodsSkuId" value="$!goodsSkuId">
	<div class="wrapBox">
		<header class="header">
			<h1>商品详情</h1>
		</header>
		<section class="details">
			<figure class="main__list details__top">
				<a href="javascript:;"><img src="$!imgPrefix$!goods.img_url" alt=""></a>
				<figcaption>
					<p>$!goods.name</p>
					<summary><span>$!goods.price<i>元</i></span><em>免运费</em></summary>
				</figcaption>
			</figure>
			#if("$!guigeList" != "" && $!guigeList.size() > 0 )
			<section class="details__text">
				<h3>— 商品详情 —</h3>
				<div class="text__box">
					#foreach($bean in $guigeList)
					<p><span>$!bean.name：</span>$!bean.content</p>
					#end
				</div>
			</section>
			#end
			<section class="details__intro">
				<h3>— 商品介绍 —</h3>
				<div class="intro__box">
					<img src="$!staticPrefix/wechat/images/signup/sign-head.jpg" alt="">
					<p>何一种商品都有自己独特的特点。不要认为不是品牌名牌的产品没有优点，最起码的价格便宜是个大优点吧。当然做产品，价格不是突出的特点。介绍商品不能一律对待，要根据不同客户的不同需求，要有针对性，突出重点，满足客户的需求。介绍优点，但要准确真实，不可欺骗消费者。</p>
					<p>何一种商品都有自己独特的特点。不要认为不是品牌名牌的产品没有优点，最起码的价格便宜是个大优点吧。当然做产品，价格不是突出的特点。介绍商品不能一律对待，要根据不同客户的不同需求，要有针对性，突出重点，满足客户的需求。介绍优点，但要准确真实，不可欺骗消费者。</p>
				</div>
			</section>
		</section>
		<footer class="footer">
			<a class="purchase" id="purchase" href="javascript:void(0);">立即购买</a>
		</footer>
		<!-- 购买详情 -->
		<div class="buyBox none animated">
			<div class="buy__top">
				<a href="javascript:void(0)"><img src="$!imgPrefix$!goods.img_url" alt=""></a>
				<summary>
					<h4>$!goods.name</h4><span>$!goods.price<i>元</i></span><span id="close"><em class="glyphicon glyphicon-remove-circle"></em></span></summary>
			</div>
			<div class="buy__middle">
				#set($i = 1) #foreach($bean in $propertyList)
				<p lang="spec">
					<em>$!bean.name</em>
					<span class="middle__color" lang="radio">
					#foreach($property in $!bean.option_list)
					<label>
						<input type="radio" name="color$i" value="$!property.option_id">
						<i>$!property.option_name</i>
					</label>
					#end
					#set($i = $i+1)
				</span>
				</p>
				#end
				<p>
					<em>购买数量</em>
					<span class="middle__amount">
					<a id="sub" href="javascript:;">－</a>
					<input id="amount" type="number" min="1" max="99" value="1">
					<a id="add" href="javascript:;">＋</a>
				</span>
				</p>
			</div>
			<div class="buy__bottom">
				<a id="nextStep" class="nextStep" href="javascript:void(0)">下一步</a>
			</div>
		</div>
		<div class="filterBox none animated"></div>
	</div>
	
	<script type="text/javascript">
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
		    title: '商品详情', // 分享标题
		    link: 'http://wchat.mperfit.com/match/order/vote/detail?order_id=$!vote.order_id', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '咆哮狗人气王-为我拉票', // 分享标题
		    desc: '很多朋友叫我上传艺术照。这一刻，我偏不！咆哮狗户外挑战赛，给你一个真实！放声咆哮！', // 分享描述
		    link: 'http://wchat.mperfit.com/match/order/vote/detail?order_id=$!vote.order_id', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
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
	
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/goods_index.js"></script>

</body>
</html>