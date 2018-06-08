$(document).ready(function () {
	// 添加健身房
	var gym_add = {
		init: function () {
			var _this = this;
			_this.lng1=_this.lng2=_this.lat1=_this.lat2 = null;
			_this.entry = {
				entry_gym_cover: false
			}
			_this.gym_pattern = {
				gym_name_Boolean: false,
				gym_phone_Boolean: false,
				gym_address_Boolean: false,
				gym_simpleAddress_Boolean: false,
				gym_introduce_Boolean: false,
				gym_cover_Boolean: false,
				gym_photos_Boolean: false,
				gym_coordinate1_Boolean: false,
				gym_coordinate2_Boolean: false
			}
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
		},
		bind: function () {
			var _this = this;
			_this.fileBtn = document.querySelector('#gym_cover_file'); //健身房封面文件流
			_this.uploadBtn = document.querySelector('#gym_cover_upload'); //健身房封面上传按钮
			_this.formDOM = jQuery("#gym_form_cover"); ////健身房封面提交表单
			_this.fileBtnP = document.querySelector('#gym_photos_file'); ////健身房照片文件流
			_this.formDOMP = jQuery("#gym_form_photos"); //健身房照片提交表单
			_this.gym_photos_box = document.querySelector('.gym_photos_box'); //健身房照片存放
			_this.gym_pub_btn = document.querySelector('#gym_pub_btn'); //健身房发布
			// 输入框
			_this.gym_name_input = jQuery('#gym_name');
			_this.gym_phone_input = jQuery('#gym_phone');
			_this.gym_address_input = jQuery('#gym_address');
			_this.gym_simpleAddress_input = jQuery('#gym_simple_address');
			_this.gym_introduce_input = jQuery('#gym_introduce');
		},
		render: function () {
			var _this = this;
			window.addEventListener("load", function(){
				_this.methods.domRender();
			});
			_this.fileBtn.addEventListener("change", function (e) { //健身房封面文件流监听
				var file = this.files[0];
				if (_this.methods.checkFile(file)) {
					_this.entry.entry_gym_cover = true
					jQuery.proxy(_this.async.formSubmit());
					// _this.uploadBtn.removeAttribute('disabled');
					// _this.uploadBtn.className = 'labelBtn2';
					//_this.formDOM.parent().find('img').remove();
					//_this.formDOM.parent().append("<img style=\"width:150px;\" class=\"photo\" src=\"" + _this.methods.createObjectURL(file) + "\"/>");
				} else {
					_this.entry.entry_gym_cover = false
					// _this.uploadBtn.className = 'labelBtn2 disabled';
					// _this.uploadBtn.setAttribute('disabled', 'disabled');
				}
			}, false);
			// _this.uploadBtn.addEventListener('click', function (e) { //健身房封面上传监听
			// 	if (_this.entry.entry_gym_cover) {
			// 		jQuery.proxy(_this.async.formSubmit());
			// 	}
			// 	if (e.preventDefault) e.preventDefault();
			// 	else e.returnValue = false; //对于IE的取消方式
			// }, false);
			_this.fileBtnP.addEventListener("change", function (e) { //健身房照片上传监听
				jQuery.proxy(_this.async.formPSubmit());
				if (e.preventDefault) e.preventDefault();
				else e.returnValue = false; //对于IE的取消方式
			}, false);
			_this.gym_photos_box.addEventListener('click', function (e) { //健身房照片删除监听
				var target = e.target;
				if (target.nodeName.toLowerCase() === 'i') {
					target.parentNode.parentNode.removeChild(target.parentNode);
				}
			}, false);
			_this.gym_pub_btn.addEventListener('click', function (e) { //新增健身房发布事件监听
				jQuery.proxy(_this.async.publicFn());
			}, false);
			// 输入框事件
			_this.gym_name_input.on('input propertychange', function (e) {
				jQuery.proxy(_this.methods.gym_name_fn());
			});
			_this.gym_phone_input.on('input propertychange', function (e) {
				jQuery.proxy(_this.methods.gym_phone_fn());
			});
			_this.gym_address_input.on('input propertychange', function (e) {
				jQuery.proxy(_this.methods.gym_address_fn());
			});
			_this.gym_simpleAddress_input.on('input propertychange', function (e) {
				jQuery.proxy(_this.methods.gym_simpleAddress_fn());
			});
			_this.gym_introduce_input.on('input propertychange', function (e) {
				jQuery.proxy(_this.methods.gym_introduce_fn());
			});
		},
		methods: function () {
			var _this = this;
			return {
				createObjectURL: function (blob) {
					if (window.URL) {
						return window.URL.createObjectURL(blob);
					} else if (window.webkitURL) {
						return window.webkitURL.createObjectURL(blob);
					} else {
						return null;
					}
				},
				checkFile: function (file) {
					//获取文件
					//var file = _this.fileBtn.files[0];
					//文件为空判断
					if (file == null) {
						alert("请选择您要上传的文件！");
						return false;
					}
					//检测文件类型
					if (file.type.indexOf('image') === -1) {
						alert("请选择图片文件！");
						return false;
					}
					//计算文件大小
					var size = Math.floor(file.size / 1024);
					if (size > 2000) {
						alert("上传文件不得超过2M!");
						return false;
					};
					return true;
				},
				nonEmpty: function (val) {
					val = jQuery.trim(val);
					if (val != '') {
						return true;
					} else {
						return false
					}
				},
				gym_name_fn: function () {
					var gym_name = _this.gym_name_input.val();
					if (_this.methods.nonEmpty(gym_name)) {
						_this.gym_pattern.gym_name_Boolean = true
					} else {
						_this.gym_pattern.gym_name_Boolean = false
					}
				},
				gym_phone_fn: function () {
					var gym_phone = _this.gym_phone_input.val();
					if (_this.methods.nonEmpty(gym_phone)) {
						if ((/^[^，]*$/).test(gym_phone)) {
							_this.gym_pattern.gym_phone_Boolean = true
						} else {
							_this.gym_pattern.gym_phone_Boolean = false
						}
					} else {
						_this.gym_pattern.gym_phone_Boolean = false
					}
				},
				gym_address_fn: function () {
					var gym_address = _this.gym_address_input.val();
					if (_this.methods.nonEmpty(gym_address)) {
						_this.gym_pattern.gym_address_Boolean = true
					} else {
						_this.gym_pattern.gym_address_Boolean = false
					}
					// 地图自动输入完成
					AMap.plugin(['AMap.Autocomplete'], function () {
						var autoOptions = {
							input: "gym_address" //使用联想输入的input的id
						};
						autocomplete = new AMap.Autocomplete(autoOptions);
						AMap.event.addListener(autocomplete, "select", function (e) {
							_this.gym_pattern.gym_coordinate1_Boolean = true;
							_this.lng1 = e.poi.location.lng;
							_this.lat1 = e.poi.location.lat;
						});
					});
					// 经纬度
					AMap.service('AMap.Geocoder', function () { //回调函数
						geocoder = new AMap.Geocoder();
						geocoder.getLocation(_this.gym_address_input.val(), function (status, result) {
							if (status === 'complete' && result.info === 'OK') {
								//console.log(result.geocodes[0].location.lng, result.geocodes[0].location.lat);
								_this.gym_pattern.gym_coordinate2_Boolean = true;
								_this.lng2 = result.geocodes[0].location.lng;
								_this.lat2 = result.geocodes[0].location.lat;
								//console.log(longitude,latitude)
							} else {
								//获取经纬度失败
								//console.log('地址填写错误')
								_this.gym_pattern.gym_coordinate2_Boolean = false;
							}
						});
					})
				},
				gym_simpleAddress_fn: function () {
					var gym_simpleAddress = _this.gym_simpleAddress_input.val();
					if (_this.methods.nonEmpty(gym_simpleAddress)) {
						_this.gym_pattern.gym_simpleAddress_Boolean = true
					} else {
						_this.gym_pattern.gym_simpleAddress_Boolean = false
					}
				},
				gym_introduce_fn: function () {
					var gym_introduce = _this.gym_introduce_input.val();
					if (_this.methods.nonEmpty(gym_introduce)) {
						_this.gym_pattern.gym_introduce_Boolean = true
					} else {
						_this.gym_pattern.gym_introduce_Boolean = false
					}
				},
				domRender: function(){
					_this.methods.gym_name_fn();
					_this.methods.gym_phone_fn();
					_this.methods.gym_address_fn();
					_this.methods.gym_simpleAddress_fn();
					_this.methods.gym_introduce_fn();
				}
			}
		},
		async: function () {
			var _this = this;
			//var file = _this.fileBtn.files[0];
			return {
				formSubmit: function () {
					if (_this.methods.checkFile(_this.fileBtn.files[0])) {
						_this.formDOM.submit();
						_this.fileBtn.value = "";
					}
				},
				formPSubmit: function () {
					if (_this.methods.checkFile(_this.fileBtnP.files[0])) {
						//_this.gym_pattern.gym_photos_Boolean = true
						_this.formDOMP.submit();
						_this.fileBtnP.value = "";
					}
				},
				publicFn: function () {
					var cover_img = document.querySelector('.gym_cover_img').getElementsByTagName('img')[0];
					var photos_img = document.querySelector('.gym_photos_box').getElementsByTagName('div');
					if (jQuery(cover_img).attr('src') != null) _this.gym_pattern.gym_cover_Boolean = true;
					else _this.gym_pattern.gym_cover_Boolean = false;
					if (jQuery(photos_img).length > 0) _this.gym_pattern.gym_photos_Boolean = true;
					else _this.gym_pattern.gym_photos_Boolean = false;
					//console.log(_this.gym_pattern.gym_cover_Boolean+'   '+_this.gym_pattern.gym_photos_Boolean);
					var img_array = Array.prototype.slice.call(photos_img).map(function (item, index) {
						return item.getAttribute('data-name');
					});
					img_array = img_array.join(",");
					var data = {
						'name': _this.gym_name_input.val(),
						'phone': _this.gym_phone_input.val(),
						'address': _this.gym_address_input.val(),
						'simple_address': _this.gym_simpleAddress_input.val(),
						'img_url': jQuery(cover_img).attr('data-name'),
						'img_array': img_array,
						'longitude': _this.lng1 || _this.lng2,
						'latitude': _this.lat1 || _this.lat2,
					}
					//console.log(data);
					//console.log(_this.gym_pattern.gym_name_Boolean && _this.gym_pattern.gym_phone_Boolean && _this.gym_pattern.gym_address_Boolean && _this.gym_pattern.gym_simpleAddress_Boolean && _this.gym_pattern.gym_introduce_Boolean && _this.gym_pattern.gym_cover_Boolean && _this.gym_pattern.gym_photos_Boolean && (_this.gym_pattern.gym_coordinate3_Boolean || _this.gym_pattern.gym_coordinate1_Boolean || _this.gym_pattern.gym_coordinate2_Boolean));
					if (_this.gym_pattern.gym_name_Boolean && _this.gym_pattern.gym_phone_Boolean && _this.gym_pattern.gym_address_Boolean && _this.gym_pattern.gym_simpleAddress_Boolean && _this.gym_pattern.gym_introduce_Boolean && _this.gym_pattern.gym_cover_Boolean && _this.gym_pattern.gym_photos_Boolean && (_this.gym_pattern.gym_coordinate3_Boolean || _this.gym_pattern.gym_coordinate1_Boolean || _this.gym_pattern.gym_coordinate2_Boolean)) {
						//console.log(data);
						jQuery.ajax({
							type: 'POST',
							url: '/gym/doAdd',
							data: data,
							dataType: 'json',
							success: function (msg) {
								var code = msg['code'];
								var err = msg['message']
								if (code == 100) {
									alert('添加成功');
									window.location.href = "/gym/add"
								} else {
									console.log(err);
								}
							},
							error: function (xhr, type) {

							}
						});
					} else {
						alert("请填写正确的信息")
					}
				}
			}
		}
	}
	gym_add.init();
});