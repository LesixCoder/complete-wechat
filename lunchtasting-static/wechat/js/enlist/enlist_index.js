jQuery(function () {
	function view() {
		return {
			w: document.documentElement.clientWidth,
			h: document.documentElement.clientHeight
		};
	}
	function heightAuto(){
		let wrapHeight = document.querySelector('.wrapBox').clientHeight;
		let domHeight = document.documentElement.clientHeight;
		if(wrapHeight < domHeight){
			jQuery('.wrapBox_iphone').height(view().h+"px");
		}else{
			jQuery('.wrapBox_iphone').css('height','auto');
		}
	}	
	heightAuto();
	// 计算商品总价
	let prices = jQuery('.setMeal_list input[type="checkbox"]:checked + figure').find('.setMeal_price em');
	//let goods_price = jQuery('.goods_box .goods_price i');
	let CalculPrice = function(eles){
		this.eles = eles;
		this.cal = function(){
			let total = new Number();
			if (this.eles.length > 0) {
				Array.prototype.slice.call(this.eles).forEach(function (val) {
					total += parseFloat(val.innerHTML);
				});
				//return total;
				jQuery('#priceAll').html(total.toFixed(2));
			} else {
				total = 0
				jQuery('#priceAll').html(total.toFixed(2));
			}
		}
	}
	let cal1 = new CalculPrice(prices).cal();

	let list_cut = false;
	jQuery('.setMeal_list_2 .figure_label').on('touchend', function (event) {
		if(jQuery(this).parents('.setMeal_list').find('input[type="checkbox"]').is(':checked')){
			list_cut = true;
		}else{
			list_cut = false;
		}
		if(list_cut) jQuery(this).siblings('.figure_text').find('.tit_p').css('display','none');
		else jQuery(this).siblings('.figure_text').find('.tit_p').css('display','flex');
		setTimeout(function(){
			cal1 = new CalculPrice(jQuery('.setMeal_list input[type="checkbox"]:checked + figure').find('.setMeal_price em')).cal();
		},100);
	});
	jQuery('.figure_img').on('click', function (event) {
		event.stopPropagation();
	});

	// 套餐选择
	let matchId = jQuery('#matchId').val();
	let ticketId = jQuery('#ticketId').val();
	let ticketKind = jQuery('#ticketKind').val();
	if(ticketKind == '3'){ //团体报名
		jQuery('.setMeal_list_1').find('.tit_p').css('display','flex');
	}
	jQuery('#mealBtn').on('touchend', function(e){
		//let goods_ids = jQuery('.setMeal_list input[type="checkbox"]:checked + figure input.match_goods_id');
		let list1_num = jQuery('.setMeal_list_1 .amount').val();//团体票数
		let goods_ids = jQuery('.setMeal_list input[type="checkbox"]:checked + figure .figure_text_list');
		let goods_str = '';
		if(goods_ids.length > 0){
			Array.prototype.slice.apply(goods_ids).forEach(function(val){
				let num = jQuery(val).find('.amount').val();
				//console.log(num);
				val = jQuery(val).find('.match_goods_id').val();
				//console.log(val);
				goods_str += val+'@'+num+'|'
			})
			goods_str = goods_str.substr(0, goods_str.length-1);
			//console.log(goods_str);
		}else{
			goods_str = '';
		}
		//console.log('/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str);
		if(ticketKind == '1'){ //普通报名
			 //let url = '/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str;
			window.location.href = '/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str;
			//console.log('/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str);
		}else if(ticketKind == '2'){ //亲子报名
			//window.location.href = '/match/signup/qz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str;
			//console.log('/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str);
		}else if(ticketKind == '3'){ //团体报名
			window.location.href = '/match/signup/tt?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&number='+list1_num;
			//console.log('/match/signup/dz?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&number='+list1_num);
		}
	})

	// 填写信息
	let name_Boolean = false;
	let tel_Boolean = false;
	let childName_Boolean = false;
	let age_Boolean = false;
	let id_Boolean = false;
	//姓名
	jQuery('#infor_name').blur(function () {
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#infor_name').val())) {
			jQuery('.name_Box').addClass("fa-check-circle").removeClass("fa-times").css("color", "green");;
			name_Boolean = true;
		} else {
			jQuery('.name_Box').addClass("fa-times").removeClass("fa-check-circle").css("color", "red");
			name_Boolean = false;
		}
	});
	//电话
	jQuery('#infor_tel').blur(function () {
		if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(jQuery('#infor_tel').val())) {
			jQuery('.tel_Box').addClass("fa-check-circle").removeClass("fa-times").css("color", "green");
		} else {
			jQuery('.tel_Box').addClass("fa-times").removeClass("fa-check-circle").css("color", "red");
		}
	});
	//儿童姓名
	jQuery('#infor_childName').blur(function () {
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#infor_childName').val())) {
			jQuery('.childName_Box').addClass("fa-check-circle").removeClass("fa-times").css("color", "green");
		} else {
			jQuery('.childName_Box').addClass("fa-times").removeClass("fa-check-circle").css("color", "red");
		}
	});
	//儿童年龄
	jQuery('#infor_childAge').blur(function () {
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#infor_childAge').val())) {
			jQuery('.age_Box').addClass("fa-check-circle").removeClass("fa-times").css("color", "green");
		} else {
			jQuery('.age_Box').addClass("fa-times").removeClass("fa-check-circle").css("color", "red");
		}
	});
	//身份证
	jQuery('#infor_id').blur(function () {
		if ((/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/).test(jQuery('#infor_id').val())) {
			jQuery('.id_Box').addClass("fa-check-circle").removeClass("fa-times").css("color", "green");
		} else {
			jQuery('.id_Box').addClass("fa-times").removeClass("fa-check-circle").css("color", "red");
		}
	});
	
	let goods_str = jQuery('#goods_str').val();
	let ticketNumber = jQuery('#ticketNumber').val();
	//支付
	jQuery('.payBtn').on('touchend', function(e){
		let infor_name = jQuery('#infor_name').val();
		let infor_tel = jQuery('#infor_tel').val();
		let infor_childName = jQuery('#infor_childName').val();
		let infor_childAge = jQuery('#infor_childAge').val();
		let infor_id = jQuery('#infor_id').val();
		let infor_gender = jQuery('#infor_gender').val();
		let signup_str = ''
		//姓名
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#infor_name').val())) name_Boolean = true;
		else name_Boolean = false;
		//电话
		if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(jQuery('#infor_tel').val())) tel_Boolean = true; 
		else tel_Boolean = false;
		//宝宝姓名
		if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(jQuery('#infor_childName').val())) childName_Boolean = true;
		else childName_Boolean = false;
		//宝宝年龄
		if ((/^([1-9]\d|\d)$/).test(jQuery('#infor_childAge').val())) age_Boolean = true;
		else age_Boolean = false;
		//身份证
		if ((/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/).test(jQuery('#infor_id').val())) id_Boolean = true;
		else id_Boolean = false;

		if(infor_childName!=undefined){//亲子
			//console.log('亲子');
			signup_str = infor_name+'@'+infor_tel+'@'+infor_childName+'@'+infor_childAge;
			if (name_Boolean && tel_Boolean && childName_Boolean && age_Boolean) {
				//console.log('/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str)
				window.location.href = '/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str;
			}else{
				return alert('请填写正确信息');
			}
		} else if(infor_id!=undefined){//团体
			//console.log('团体');
			signup_str = infor_name+'@'+infor_tel+'@'+infor_id+'@'+infor_gender;
			if (name_Boolean && tel_Boolean && id_Boolean) {
				//console.log('/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str+'&number='+ticketNumber)
				window.location.href = '/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str+'&number='+ticketNumber;
			}else{
				return alert('请填写正确信息');
			}
		} else if(infor_childName==undefined && infor_id==undefined){//大众
			//console.log('大众');
			signup_str = infor_name+'@'+infor_tel;
			if (name_Boolean && tel_Boolean) {
				//console.log('/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str)
				window.location.href = '/wxpay/match?match_id='+matchId+'&ticket_id='+ticketId+'&goods_str='+goods_str+'&signup_str='+signup_str;
			}else{
				return alert('请填写正确信息');
			}
		}
	});	

	function fnAmount() {
		jQuery(".amount").keypress(function (b) {
			var keyCode = b.keyCode ? b.keyCode : b.charCode;
			if (keyCode != 0 && (keyCode < 48 || keyCode > 57) && keyCode != 8 && keyCode != 37 && keyCode != 39) {
				return false;
			} else {
				return true;
			}
		}).keyup(function (e) {
			var keyCode = e.keyCode ? e.keyCode : e.charCode;
			//console.log(keyCode);
			if (keyCode != 8) {
				var numVal = parseInt(jQuery(".amount").val()) || 0;
				numVal = numVal < 1 ? 1 : numVal;
				jQuery(".amount").val(numVal);
			}
		}).blur(function (event) {
			event.stopPropagation();
			let numVal = parseInt(jQuery(".amount").val()) || 0;
			let this_price = parseFloat(jQuery(this).parents('.figure_text').find('.setMeal_price i').html());
			numVal = numVal < 1 ? 1 : numVal;
			numVal = numVal > 500 ? 500 : numVal;
			jQuery(".amount").val(numVal);
			jQuery(this).parents('.figure_text').find('.setMeal_price em').html(this_price * numVal);
		});
		//增加
		jQuery(".add").on('touchend',function (event) {
			event.stopPropagation();
			let num = parseInt(jQuery(this).siblings(".amount").val()) || 0;
			let this_price = parseFloat(jQuery(this).parents('.figure_text').find('.setMeal_price i').html());
			num = num + 1;
			num = num > 500 ? 500 : num;
			jQuery(this).siblings(".amount").val(num);
			jQuery(this).parents('.figure_text').find('.setMeal_price em').html(this_price * num);
			setTimeout(function(){
				cal1 = new CalculPrice(jQuery('.setMeal_list input[type="checkbox"]:checked + figure').find('.setMeal_price em')).cal();
			},100);
			//console.log(this_price * num);
		});
		//减去
		jQuery(".sub").on('touchend',function (event) {
			event.stopPropagation();
			let num = parseInt(jQuery(this).siblings(".amount").val()) || 0;
			let this_price = parseFloat(jQuery(this).parents('.figure_text').find('.setMeal_price i').html());
			num = num - 1;
			num = num < 1 ? 1 : num;
			jQuery(this).siblings(".amount").val(num);
			jQuery(this).parents('.figure_text').find('.setMeal_price em').html(this_price * num);
			setTimeout(function(){
				cal1 = new CalculPrice(jQuery('.setMeal_list input[type="checkbox"]:checked + figure').find('.setMeal_price em')).cal();
			},100);
			//console.log(this_price * num);
		});
	}
	fnAmount();
});