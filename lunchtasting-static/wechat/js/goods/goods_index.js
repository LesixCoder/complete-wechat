// JavaScript Document
jQuery(function () {
	function bind(obj, ev, fn) {
		if (obj.addEventListener) {
			obj.addEventListener(ev, fn, false);
		}
	}

	function view() {
		return {
			w: document.documentElement.clientWidth,
			h: document.documentElement.clientHeight
		};
	}
	// 购买弹窗
	function fnBuy() {
		jQuery('#purchase').on('touchend', function (ev) {
			jQuery('.buyBox').removeClass('none').addClass('slideInUp');
			jQuery('.filterBox').removeClass('filterOut none').addClass('filterIn');
			jQuery("body").css({
				"overflow-y": "hidden"
			});
		});
		jQuery('.filterBox,#close').on('touchend', function () {
			jQuery('.buyBox').removeClass('slideInUp').addClass('none');
			jQuery('.filterBox').removeClass('filterIn').addClass('filterOut none');
			jQuery("body").css({
				"overflow-y": "auto"
			});
		})
	};
	fnBuy();

	function fnAmount() {
		jQuery("#amount").keypress(function (b) {
			var keyCode = b.keyCode ? b.keyCode : b.charCode;
			if (keyCode != 0 && (keyCode < 48 || keyCode > 57) && keyCode != 8 && keyCode != 37 && keyCode != 39) {
				return false;
			} else {
				return true;
			}
		}).keyup(function (e) {
			var keyCode = e.keyCode ? e.keyCode : e.charCode;
			console.log(keyCode);
			if (keyCode != 8) {
				var numVal = parseInt(jQuery("#amount").val()) || 0;
				numVal = numVal < 1 ? 1 : numVal;
				jQuery("#amount").val(numVal);
			}
		}).blur(function () {
			var numVal = parseInt(jQuery("#amount").val()) || 0;
			numVal = numVal < 1 ? 1 : numVal;
			numVal = numVal > 500 ? 500 : numVal;
			jQuery("#amount").val(numVal);
		});
		//增加
		jQuery("#add").click(function () {
			var num = parseInt(jQuery("#amount").val()) || 0;
			num = num + 1;
			num = num > 500 ? 500 : num;
			jQuery("#amount").val(num);
		});
		//减去
		jQuery("#sub").click(function () {
			var num = parseInt(jQuery("#amount").val()) || 0;
			num = num - 1;
			num = num < 1 ? 1 : num;
			jQuery("#amount").val(num);
		});
	}
	fnAmount();

	function fnNext() {
		var bCheck = null;
		jQuery('#nextStep').on("touchend", function () {
			fnEnd();
		});

		function fnEnd() {
			bCheck = fnChecked();
			if (bCheck) {
				var data = '';
				var order = '';
				var amount = jQuery('#amount').val();
				var inputCheck = jQuery('.buy__middle input:checked');
				var goodsSkuId = jQuery('#goodsSkuId').val();
				for (var i = 0; i < inputCheck.length; i++) {
					order += inputCheck[i].value + ',';
				}
				order = (order.substring(order.length - 1) == ',') ? order.substring(0, order.length - 1) : order;
				//填写完整,ajax提交
				if (goodsSkuId == '') {
					$.post("/goods/getSkuGoods?option_ids=" + order, function (data) {
						console.log(order);
						if (data.code == 100) {
							var goods_sku_id = data.data.goodsSku.goods_sku_id;
							window.location.href = "/goods/order/confirm?gs_id=" + goods_sku_id + '&number=' + amount;
						} else {

						}
					});
				} else {
					window.location.href = "/goods/order/confirm?gs_id=" + goodsSkuId + '&number=' + amount;
				}
			} else {
				return false;
			}
		}

		function fnChecked() {
			var cut = [];
			jQuery(".buy__middle p:not(:last-child)").each(function (index, ele) {
				var LL = jQuery(this).find("input:checked").length;
				if (LL == 0) {
					cut[index] = false;
				} else {
					cut[index] = true;
				}
			});
			for (var i = 0; i < cut.length; i++) {
				if (cut[i] == false) {
					return false;
				}
			}
			return true;
		}

		function fnBtnActive() {
			if (jQuery('.buy__middle p[lang=spec]').length == 0) {
				jQuery('#nextStep').addClass('submit');
			} else {
				jQuery('.buy__middle span[lang=radio]').each(function () {
					jQuery(this).children('label:first').find('input').attr('checked', 'checked');
					jQuery('#nextStep').addClass('submit');
				})
			}
		}
		fnBtnActive();
	};
	fnNext();

	function fnManaA() {
		jQuery('.manaRadio').each(function () {
			jQuery(this).on('touchend', function () {
				if (jQuery(this).hasClass('checked')) {
					return false;
				} else {
					if (confirm("要设为默认地址吗？")) {
						jQuery('.manaRadio').removeClass('checked');
						jQuery(this).addClass('checked');
						jQuery('.manaRadio').children('i').html("<span class='glyphicon glyphicon-check'></span>设为默认");
						jQuery('.manaRadio').children('input').removeAttr('checked');
						jQuery(this).children('i').html("<span class='glyphicon glyphicon-ok-sign'></span>默认地址");
						jQuery(this).children('input').attr('checked', 'checked');
					}
				}
			})
		})
		jQuery('.remoBtn').each(function () {
			jQuery(this).on('touchend', function () {
				if (confirm('确定要删除地址吗？')) {
					//ajax提交
					jQuery(this).parents('.mana__list').remove();
				}
			})
		})
	};
	fnManaA();

});