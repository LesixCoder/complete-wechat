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
	<title>新增收货地址</title>

	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/LArea.min.css">
	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/goods_index.css">
</head>

<body style="display:none;">
	<input type="hidden" id="js-bizType" value="$!bizType" />
	<input type="hidden" id="js-bizId" value="$!bizId" />
	<input type="hidden" id="js-bizStr" value="$!bizStr" />
	<div class="wrapBox wrapOrder">
		<header class="header">
			<h1>新建收货地址</h1>
		</header>
		<section class="newAddress">
			<p><span>收 件 人：</span><input id="username" type="text" placeholder="请输入收件人姓名" pattern="^[\u4e00-\u9fa5a-zA-Z0-9_]+$" required>
				<i class="usernameBox"></i>
			</p>
			<p><span>联系电话：</span><input id="tel" type="tel" placeholder="请输入联系电话" pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"
				    required><i class="telBox"></i></p>
			<p><span>收货地区：</span><input id="city" type="text" placeholder="选择省份 选择城市 选择地区" required readonly><input type="hidden" id="valueTo">
				<i class="addressBox"></i>
			</p>
			<p><span>详细地址：</span><input id="detAddress" type="text" placeholder="请输入收件人详细地址" required><i class="detAddBox"></i></p>
			<p><span>邮政编码：</span><input id="zipCode" type="number" placeholder="请输入邮政编码（选填）" pattern="(^[a-zA-Z0-9]{3,12}$)|(^$)"></p>
			<a class="saveBtn" id="saveBtn" href="javascript:;">保存</a>
		</section>
	</div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/plugs/LArea.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/address.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/goods/ajaxAdd.js"></script>
</body>

</html>