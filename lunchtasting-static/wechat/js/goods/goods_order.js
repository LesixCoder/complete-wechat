jQuery(function () {

	function fnOrderTab() {
		var urlData = GetQueryString("status");
		if(urlData == null) jQuery('.oM__nav a').eq(0).addClass('active').siblings('a').removeClass('active');
		if(urlData == '1') jQuery('.oM__nav a').eq(1).addClass('active').siblings('a').removeClass('active');
		if(urlData == '2') jQuery('.oM__nav a').eq(2).addClass('active').siblings('a').removeClass('active');
		if(urlData == '3') jQuery('.oM__nav a').eq(3).addClass('active').siblings('a').removeClass('active');
	};
	fnOrderTab();

	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	}

	//order_confirm
	//确认支付
	jQuery('.orderBtn').on(
		'click',
		function () {
			var goodsSkuId = jQuery("#goodsSkuId").val();
			var addressId = jQuery("#addressId").val();
			var remark = jQuery("#remark").val();
			var number = jQuery("#number").val();


			if (addressId == '' || addressId == null) {
				alert("请选择收货地址！");
				return;
			}

			if (goodsSkuId == '' || goodsSkuId == null) {
				alert("当前商品不存在或已停止售卖！");
				return;
			}

			location.href = "/wxpay/goods_model?gs_id=" + goodsSkuId +
				"&address_id=" + addressId + "&remark=" + remark+"&number=" +number;

		});

	//order_list
	//尚未付款，取消订单
	jQuery('.orderCancel').on('click', function () {
		if (confirm('确定要取消订单吗？')) {
			var orderId = $(this).attr("js-attr-orderId");
			//alert(orderId);

			if (orderId == '' || orderId == null) {
				alert("参数错误！");
				return;
			}

			$.post("/goods/order/doCancel", {
				order_id: orderId
			}, function (data) {
				//var data = JSON.parse(result);
				if (data.code == 100) {
					jQuery('.order__btn[js-attr-orderId='+orderId+']').siblings('.order__number').find('em').text('已取消');
					jQuery('.order__btn[js-attr-orderId='+orderId+']').remove();
				} else {
					alert(data.message);
				}
			});
		}
	});
});