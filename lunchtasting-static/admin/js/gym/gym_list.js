$(document).ready(function () {
	// 健身房列表
	var gym_list = {
		init: function () {
			var _this = this;
			//_this.flagCut = false;
			_this.str = '';
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
		},
		bind: function () {
			var _this = this;
			_this.lineBtn = jQuery('.lineBtn');
		},
		render: function () {
			var _this = this;
			window.addEventListener("load", function () {
				_this.lineBtn.each(function(item, index){
					if(jQuery(this).siblings('.flag').val() == 0){
						jQuery(this).removeClass('btn-Danger').addClass('btn-Success').html('上线').attr('data-str','下');
						jQuery(this).parent().siblings('.centerStatus').html('上线');
					}else{
						jQuery(this).removeClass('btn-Success').addClass('btn-Danger').html('下线').attr('data-str','上');
						jQuery(this).parent().siblings('.centerStatus').html('下线');
					}
				});
			});
			_this.lineBtn.on("click", function (e) { //切换上线下线
				_this.data_id = $(this).attr("data-id");
				_this.flag = $(this).siblings('.flag').val();
				_this.str = $(this).attr("data-str");
				//_this.gym_id = $(this).siblings('.gym_id').val();
				jQuery.proxy(_this.async.lineFn());
			});
		},
		methods: function () {
			var _this = this;
			return {

			}
		},
		async: function () {
			var _this = this;
			return {
				lineFn: function () {
					if (confirm("确定要" + _this.str + "线吗?")) {
						var data = {
							'gym_id': _this.data_id,
							'flag': ~_this.flag+2
						}
						// console.log(data);
						jQuery.post("/gym/doFlag", data, function (msg) {
							var code = msg['code'];
							var err = msg['message'];
							console.log(code)
							if (code == 100) {
								if (_this.flag == 0) {
									jQuery('.lineBtn[data-id='+_this.data_id+']').removeClass('btn-Success').addClass('btn-Danger').html('下线').attr('data-str','上');
									jQuery('.lineBtn[data-id='+_this.data_id+']').siblings('.flag').val('1').parent().siblings('.centerStatus').html('下线');
								} else {
									jQuery('.lineBtn[data-id='+_this.data_id+']').removeClass('btn-Danger').addClass('btn-Success').html('上线').attr('data-str','下');
									jQuery('.lineBtn[data-id='+_this.data_id+']').siblings('.flag').val('0').parent().siblings('.centerStatus').html('上线');
								}
								//window.location.href = "/gym/list";
							} else {
								console.log(err);
							}
						});
					}
				}
			}
		}
	}
	gym_list.init();
});