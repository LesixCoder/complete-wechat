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

	// 提现
	var widthdraw = {
		init: function() { 
			var _this = this;
			_this.entry = {
				entry_withdraw: false
			}
			_this.pattern = {
				wx_Boolean : false,
				name_Boolean : false,
				tel_Boolean : false,
				gold_Boolean : false
			}
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
			_this.tabCut = false;
		},
		bind: function() { 
			var _this = this;
			//_this.detBtn = $('.courList_intro_btn');
			_this.closeBtn = $('.dialog_btn_close');
			_this.filter = document.querySelector('#filter');
			_this.withdraw = $('#withdraw');
			_this.apply_withdraw = $('#apply_withdraw');//提现按钮
			_this.wx_input = $('#user_wx');
			_this.name_input = $('#user_name');
			_this.tel_input = $('#user_tel');
			_this.gold_input = $('#user_gold');
		},
		render: function() { 
			var _this = this;
			jQuery(document).ready(function(){
				_this.methods.withdrawCut();
			});
			_this.closeBtn.on('touchend', function(e){
				e.stopPropagation();
				jQuery.proxy(_this.methods.tabSwitch());
			});
			_this.filter.addEventListener('touchend', function(){
				jQuery.proxy(_this.methods.tabHide());
				_this.filter.removeEventListener('touchend',function(){},false);
			});
			_this.withdraw.on('touchend', function(e){
				e.stopPropagation();
				if(_this.entry.entry_withdraw){
					jQuery.proxy(_this.methods.tabSwitch());
				}
			});
			_this.apply_withdraw.on('touchend', function(e){
				jQuery.proxy(_this.async.withdraw());
			});
			_this.wx_input.on('input propertychange', function(e) {
	　　  	jQuery.proxy(_this.methods.wx_fn());
			});
			_this.name_input.on('input propertychange', function(e) {
				jQuery.proxy(_this.methods.name_fn());
			});
			_this.tel_input.on('input propertychange', function(e) {
				jQuery.proxy(_this.methods.tel_fn());
			});
			_this.gold_input.on('input propertychange', function(e) {
	　　  	jQuery.proxy(_this.methods.gold_fn());
			});
		},
		methods: function(){
			var _this = this;
			return {
				tabSwitch: function(){
					if(_this.tabCut){
						jQuery('.dialog-model').removeClass('md-show');
						jQuery('#filter').hide();
						jQuery("body").css('overflow-y','auto');
					}else {
						jQuery('.dialog-model').addClass('md-show');
						jQuery('#filter').show();
						jQuery("body").css('overflow-y','hidden');
					}
					_this.tabCut = !_this.tabCut;
				},
				tabHide: function(){
					if(_this.tabCut === true){
						jQuery('.dialog-model').removeClass('md-show');
						jQuery('#filter').hide();
						jQuery("body").css('overflow-y','auto');
					}
					_this.tabCut = false;
				},
				withdrawCut: function(){
					if(_this.withdraw.hasClass('bgcolor-yellow')){
						_this.entry.entry_withdraw = true;
					}else if(_this.withdraw.hasClass('bgcolor-ccc')){
						_this.entry.entry_withdraw = false;
					}
					console.log(_this.entry.entry_withdraw);
				},
				wx_fn: function(){
					if ((/^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$/).test(_this.wx_input.val())) {
						jQuery('.user_wx_Box').html("√").css("color", "green");
						_this.pattern.wx_Boolean = true;
					} else {
						jQuery('.user_wx_Box').html("×").css("color", "red");
						_this.pattern.wx_Boolean = false;
					}
				},
				name_fn: function(){
					if ((/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/).test(_this.name_input.val())) {
						jQuery('.user_name_Box').html("√").css("color", "green");
						_this.pattern.name_Boolean = true;
					} else {
						jQuery('.user_name_Box').html("×").css("color", "red");
						_this.pattern.name_Boolean = false;
					}
				},
				tel_fn: function(){
					if ((/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/).test(_this.tel_input.val())) {
						jQuery('.user_tel_Box').html("√").css("color", "green");
						_this.pattern.tel_Boolean = true;
					} else {
						jQuery('.user_tel_Box').html("×").css("color", "red");
						_this.pattern.tel_Boolean = false;
					}
				},
				gold_fn: function(){
					if ((/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/).test(_this.gold_input.val())) {
						if(Number(_this.gold_input.val()) >= 20){
							jQuery('.user_gold_Box').html("√").css("color", "green");
							_this.pattern.gold_Boolean = true;
						}else {
							jQuery('.user_gold_Box').html("×").css("color", "red");
							_this.pattern.gold_Boolean = false;
						}
					} else {
						jQuery('.user_gold_Box').html("×").css("color", "red");
						_this.pattern.gold_Boolean = false;
					}
				}
			}
		},
		async: function() { 
			var _this = this;
			return {
				withdraw: function() {
					var data = {
						account: _this.wx_input.val(),
						money: _this.gold_input.val(),
						phone: _this.tel_input.val(),
						name: _this.name_input.val()
					}
					//console.log(data);
					//console.log(_this.pattern.wx_Boolean,_this.pattern.tel_Boolean,_this.pattern.name_Boolean,_this.pattern.gold_Boolean);
					if (_this.pattern.wx_Boolean && _this.pattern.tel_Boolean && _this.pattern.name_Boolean && _this.pattern.gold_Boolean) {
						jQuery.post("/user/withdraw/add", data, function (msg) {
							var code = msg['code'];
							if (code == 100) {
								//console.log('成功');
								window.location.href = "/user/withdraw/list";
								//_this.methods.tabSwitch();
							}else if(code == 110){
								alert("提现失败！您的账户余额不足")
							}else if(code == 111){
								alert("提现失败！提现金额错误")
							}else {
								//console.log('失败');
							}
						});
					} else {
						alert("请填写正确的信息!");
					}
				}
			}
		}
	}
	widthdraw.init();
});