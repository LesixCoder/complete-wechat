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

	function mapHeight(){
		var scale = 976/1336;
		jQuery('#myMap').height(view().h * scale + 30 + "px");
	}
	mapHeight();

	// 事件详情页
	var course = {
		init: function() { 
			var _this = this;
			_this.entry = {
				entry_refund: true
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
		},
		render: function() { 
			var _this = this;
			// _this.detBtn.on('touchend', function(e){
			// 	e.stopPropagation();
			// 	$.proxy(_this.methods.tabSwitch());
			// });
			_this.closeBtn.on('touchend', function(e){
				e.stopPropagation();
				$.proxy(_this.methods.tabSwitch());
			});
			_this.filter.addEventListener('touchend', function(){
				$.proxy(_this.methods.tabHide());
			});
			_this.withdraw.on('touchend', function(e){
				e.stopPropagation();
				$.proxy(_this.methods.tabSwitch());
			});
		},
		methods: function(){ 
			var _this = this;
			return {
				tabSwitch: function(){
					if(_this.tabCut){
						$('.dialog-model').removeClass('md-show');
						$('#filter').hide();
						$("body").css('overflow-y','auto');
					}else {
						$('.dialog-model').addClass('md-show');
						$('#filter').show();
						$("body").css('overflow-y','hidden');
					}
					_this.tabCut = !_this.tabCut;
					//console.log(_this.tabCut);
				},
				tabHide: function(){
					if(_this.tabCut === true){
						$('.dialog-model').removeClass('md-show');
						$('#filter').hide();
						$("body").css('overflow-y','auto');
					}
					_this.tabCut = false;
				}
			}
		},
		async: function() { 
			var _this = this;
			return {
				// abClass: function() {
				// 	$.ajax({
				// 		url: '/getCode/query',
				// 		dataType: 'json',
				// 		success: function(res){

				// 		},
				// 		error: function(err){
				// 			var xhr = new XMLHttpRequest()
				// 		}
				// 	})
				// }
			}
		}
	}
	//course.init();

	var order_list = {
		init: function() {
			var _this = this;
			_this.entry = {
				entry_refund: true
			}
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
		},
		bind: function() { 
			var _this = this;
			_this.refundBtn = $('.refund_btn');
		},
		render: function() { 
			var _this = this;
			_this.refundBtn.on('touchend', function(e){
				e.stopPropagation();
				_this.order_id = $(this).attr("js-attr-orderId");
				$.proxy(_this.async.refund());
			});
		},
		methods: function(){ 
			var _this = this;
			return {
				
			}
		},
		async: function() {
			var _this = this;
			return {
				refund: function() {
					if(!_this.entry.entry_refund){
						return
					}
					if (confirm("确定要退款吗?")) {
						order_id=_this.order_id;
						// $('.refund_btn[js-attr-orderId='+order_id+']').hide()
						// $('.refund_btn[js-attr-orderId='+order_id+']').parents('.m_c_list').find('.course_tag').addClass('i-over').text('已退款');
						$.post("/course/order/refund", {
							order_id: order_id
						},function (data) {
							var code = data['code'];
							if (code == 100) {
								$('.refund_btn[js-attr-orderId='+order_id+']').hide();
								$('.refund_btn[js-attr-orderId='+order_id+']').parents('.m_c_list').find('.course_tag').addClass('i-over').text('已退款');
							} else {
								
							}
						});
					}
				}
			}
		}
	}
	order_list.init();
});