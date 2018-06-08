jQuery(function () {
	// 确认订单
	var confirm = {
		init: function() { 
			var _this = this;
			_this.cm_pattern = {
				tel_Boolean : false,
			}
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
		},
		bind: function() { 
			var _this = this;
			_this.pay_btn = document.querySelector('#dialog_btn_pay');
			_this.cm_tel_input = jQuery('#cm_tel');
		},
		render: function() { 
			var _this = this;
			_this.pay_btn.addEventListener('touchend', function(e){
				jQuery.proxy(_this.async.cm_post());
			}, false);
			_this.cm_tel_input.on('input propertychange', function(e) {
				jQuery.proxy(_this.methods.cm_tel_fn());
			});
		},
		methods: function(){
			var _this = this;
			return {
				cm_tel_fn: function(){
					if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(_this.cm_tel_input.val())) {
						_this.cm_pattern.tel_Boolean = true;
					} else {
						_this.cm_pattern.tel_Boolean = false;
					}
					console.log(_this.cm_pattern.tel_Boolean)
				}
			}
		},
		async: function() { 
			var _this = this;
			return {
				cm_post: function(){
					if(_this.cm_pattern.tel_Boolean){
						var course_meal_id = jQuery('#course_meal_id').val();
						var phone = jQuery('#cm_tel').val();
						var sex = jQuery('#cm_sex').val();
						var href = "/wxpay/course?cm_id="+course_meal_id+"&phone="+phone+"&sex="+sex;
						window.location.href = href;
						console.log(href);
					}else{
						alert("请填写正确的电话号码")
					}
				}
			}
		}
	}
	confirm.init();
});