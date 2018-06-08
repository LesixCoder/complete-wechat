jQuery(function () {
	// user
	var name_Boolean = false;
	var tel_Boolean = false;
	var address_Boolean = false;
	var detAddress_Boolean = false;
	var zipCode_Boolean = false;

	//姓名
	jQuery('#username').blur(function () {
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#username').val())) {
			jQuery('.usernameBox').html("√").css("color", "green");
			name_Boolean = true;
		} else {
			jQuery('.usernameBox').html("×").css("color", "red");
			name_Boolean = false;
		}
	});
	//电话
	jQuery('#tel').blur(function () {
		if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(jQuery('#tel').val())) {
			jQuery('.telBox').html("√").css("color", "green");
			tel_Boolean = true;
		} else {
			jQuery('.telBox').html("×").css("color", "red");
			tel_Boolean = false;
		}
	});
	//地址
	jQuery('#city').blur(function () {
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#city').val())) {
			jQuery('.addressBox').html("√").css("color", "green");
			address_Boolean = true;
		} else {
			jQuery('.addressBox').html("×").css("color", "red");
			address_Boolean = false;
		}
	});

	//详细地址
	jQuery('#detAddress').blur(function () {
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#detAddress').val())) {
			jQuery('.detAddBox').html("√").css("color", "green");
			detAddress_Boolean = true;
		} else {
			jQuery('.detAddBox').html("×").css("color", "red");
			detAddress_Boolean = false;
		}
	});

	//添加地址
	jQuery('#saveBtn').on('touchend', function () {
		var province,
			city,
			town;
		//姓名
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#username').val())) {
			name_Boolean = true;
		} else {
			name_Boolean = false;
		}
		//电话
		if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(jQuery('#tel').val())) {
			tel_Boolean = true;
		} else {
			tel_Boolean = false;
		}
		//地址
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#city').val())) {
			address_Boolean = true;
		} else {
			address_Boolean = false;
		}
		//详细地址
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#detAddress').val())) {
			jQuery('.detAddBox').html("√").css("color", "green");
			detAddress_Boolean = true;
		} else {
			jQuery('.detAddBox').html("×").css("color", "red");
			detAddress_Boolean = false;
		}
		//邮编
		if ((/(^[a-zA-Z0-9]{3,12}$)|(^$)/).test(jQuery('#zipCode').val())) {
			zipCode_Boolean = true;
		} else {
			zipCode_Boolean = false;
		}
		var address = {
			name: jQuery('#city').val(),
			getName: function () {
				nameArr = this.name.split(',');
				if (nameArr[0] == undefined) nameArr.push('');
				else province = nameArr[0];
				if (nameArr[1] == undefined) nameArr.push('');
				else city = nameArr[1];
				if (nameArr[2] == undefined) nameArr.push('');
				else town = nameArr[3];
				return {
					'province': nameArr[0],
					'city': nameArr[1],
					'town': nameArr[2]
				}
			}
		}
		var data = {
			'name': jQuery('#username').val(),
			'phone': jQuery('#tel').val(),
			'province': address.getName().province,
			'city': address.getName().city,
			'town': address.getName().town,
			'detail': jQuery('#detAddress').val(),
			'postal_code': jQuery('#zipCode').val()
		}
		if (name_Boolean && tel_Boolean && address_Boolean && detAddress_Boolean == true && zipCode_Boolean == true) {
			//ajax提交
			jQuery.ajax({
				type: 'POST',
				url: '/address/doAdd',
				data: data,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						console.log(data);
						
						//判断跳转
						var bizId = jQuery('#js-bizId').val();
						var bizType = jQuery('#js-bizType').val();
						var bizStr = jQuery('#js-bizStr').val();
						
						if(bizId == '' || bizId == null){
							window.location.href = "/address/list";
						}else{
							window.location.href = "/address/list?biz_id="+bizId+
								"&biz_type="+bizType+"&biz_str="+bizStr;
						}
					}
				},
				error: function (xhr, type) {

				}
			});
		} else {
			alert("请填写正确的信息!");
		}
	});

	//修改地址
	jQuery('#saveBtnAdd').on('touchend', function () {
		var province,
			city,
			town;
		//姓名
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#username').val())) {
			name_Boolean = true;
		} else {
			name_Boolean = false;
		}
		//电话
		if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(jQuery('#tel').val())) {
			tel_Boolean = true;
		} else {
			tel_Boolean = false;
		}
		//地址
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#city').val())) {
			address_Boolean = true;
		} else {
			address_Boolean = false;
		}
		//详细地址
		if ((/([^\x00-\xff]|[A-Za-z0-9_])+/).test(jQuery('#detAddress').val())) {
			jQuery('.detAddBox').html("√").css("color", "green");
			detAddress_Boolean = true;
		} else {
			jQuery('.detAddBox').html("×").css("color", "red");
			detAddress_Boolean = false;
		}
		//邮编
		if ((/(^[a-zA-Z0-9]{3,12}$)|(^$)/).test(jQuery('#zipCode').val())) {
			zipCode_Boolean = true;
		} else {
			zipCode_Boolean = false;
		}
		//地址分解
		var address = {
			name: jQuery('#city').val(),
			getName: function () {
				nameArr = this.name.split(',');
				if (nameArr[0] == undefined) nameArr.push('');
				else province = nameArr[0];
				if (nameArr[1] == undefined) nameArr.push('');
				else city = nameArr[1];
				if (nameArr[2] == undefined) nameArr.push('');
				else town = nameArr[3];
				return {
					'province': nameArr[0],
					'city': nameArr[1],
					'town': nameArr[2]
				}
			}
		}
		var data = {
			'name': jQuery('#username').val(),
			'phone': jQuery('#tel').val(),
			'province': address.getName().province,
			'city': address.getName().city,
			'town': address.getName().town,
			'detail': jQuery('#detAddress').val(),
			'postal_code': jQuery('#zipCode').val(),
			'address_id': jQuery('#addressId').val()
		}
		if (name_Boolean && tel_Boolean && address_Boolean && detAddress_Boolean == true && zipCode_Boolean == true) {
			//ajax提交
			jQuery.ajax({
				type: 'POST',
				url: '/address/doModify',
				data: data,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						
						//判断跳转
						var bizId = jQuery('#js-bizId').val();
						var bizType = jQuery('#js-bizType').val();
						var bizStr = jQuery('#js-bizStr').val();
						
						if(bizId == '' || bizId == null){
							window.location.href = "/address/list";
						}else{
							window.location.href = "/address/list?biz_id="+bizId+
								"&biz_type="+bizType+"&biz_str="+bizStr;
						}
					}
				},
				error: function (xhr, type) {

				}
			});
		} else {
			alert("请填写正确的信息!");
		}
	});
	//address_list
	//删除地址
	jQuery('.remoBtn').on('touchend', function () {
		if (confirm("确定删除该地址?")) {
			var addressId = $(this).attr("js-attr-addressId");
			if (addressId == '' || addressId == null) {
				alert("参数错误！");
				return;
			}
			$.post("/address/doRemove", {
				address_id: addressId
			}, function (data) {
				var code = data['code'];
				if (code == 100) {
					jQuery('#list' + addressId).hide();
					var listL = jQuery('.mana__list:visible').length;
					if (listL <= 0) jQuery('.tipsA').show();
					else jQuery('.tipsA').hide();
				} else {
					alert(data['message']);
				}
			});
		}
	});

	// 默认地址
	jQuery('.manaRadio').on('touchend', function () {
		console.log(1);
		var addressId = $(this).attr("js-attr-addressId");
		if (addressId == '' || addressId == null) {
			alert("参数错误！");
			return;
		}
		$.post("/address/doUpdateFrequently", {
			address_id: addressId
		}, function (data) {
			var code = data['code'];
			if (code == 100) {
				jQuery('.manaRadio').removeClass('checked').children('i').html("<span class='glyphicon glyphicon-check'></span>设为默认");
				jQuery('.manaRadio').children('input').removeAttr('checked');
				jQuery('.manaRadio[js-attr-addressId=' + addressId + ']').addClass('checked').children('i').html("<span class='glyphicon glyphicon-ok-sign'></span>默认地址");
				jQuery('.manaRadio[js-attr-addressId=' + addressId + ']').children('input').attr('checked', 'checked');
			} else {
				alert(data['message']);
			}
		});
	})
});