$(document).ready(function () {
	// $('#chanpinzhutu').on('change',function(e){
	// 	console.log(this.files[0].name);
	// })
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'chanpinzhutu',
		url : '/image/upload',
		filters: {
		  mime_types : [ //只允许上传图片文件
		    { title : "图片文件", extensions : "jpg,gif,png,bmp" }
		  ],
		  max_file_size : '1500kb', //最大只能上传100kb的文件
		  prevent_duplicates : true //不允许队列中存在重复文件
		},
		multipart_params: {//上传时的附加参数，以键/值对的形式传入
		},
		multi_selection: false, //true:ctrl多文件上传, false 单文件上传
		multipart: true,//为true时将以multipart/form-data的形式来上传文件，为false时则以二进制的格式来上传文件
		init: {
			BeforeUpload: function(up, file) {//当队列中的某一个文件正要开始上传前触发
				uploader.setOption("multipart_params",{
					type: $('#type').val(),
					name: file.name
				});
			},
			FilesAdded: function(up, files) { //当文件添加到上传队列后触发
				for(var i = 0, len = files.length; i<len; i++){
					var file_name = files[i].name; //文件名
					console.log(file_name)
				}
				uploader.start();
			},
			UploadProgress: function(up, file) { //上传中，显示进度条
				var percent = file.percent;
				// $("#" + file.id).find('.bar').css({
				// 	"width": percent + "%"
				// });
				// $("#" + file.id).find(".percent").text(percent + "%");
				console.log(percent)
				console.log(up.getOption().multipart_params)
			},
			FileUploaded: function(up, file, info) { //当队列中的某一个文件上传完成后触发
				var data = eval("(" + info.response + ")");
				console.log(data);
				//$("#" + file.id).html("<div class='img'><img src='" + data.pic + "'/></div><p>" + data.name + "</p>");
			},
			UploadComplete: function(up, files){//当队列中的所有文件上传完成后触发

			},
			Error: function(up, err) { //上传出错的时候触发
				console.log(err.code+'  '+err.message+'  '+err.file);
			}
		}
	});
	uploader.init(); //初始化
	
	// 添加健身房
	var gym_add = {
		init: function () {
			var _this = this;
			_this.bind();
			_this.render();
			_this.methods = _this.methods();
			_this.async = _this.async();
		},
		bind: function () {
			var _this = this;
			_this.fileBtn = document.querySelector('#chanpinzhutu');
			_this.uploadBtn = jQuery('#uploadBtn');
		},
		render: function () {
			var _this = this;
			jQuery(document).ready(function () {

			});
			_this.uploadBtn.on('click', function (e) {
				if (event.preventDefault) event.preventDefault();
				else event.returnValue = false; //对于IE的取消方式
				jQuery.proxy(_this.async.uploadImg());
			})
		},
		methods: function () {
			var _this = this;
			return {

			}
		},
		async: function () {
			var _this = this;
			return {
				uploadImg: function () {
					if (_this.fileBtn.value.length > 0) {
						//console.log(_this.fileBtn.files)
						var formDOM = document.forms.namedItem("gym_form_add");
						//将form的DOM对象当作FormData的构造函数
						var formData = new FormData(formDOM);
						jQuery.ajax({
							type: 'POST',
							url: 'http://admin.mperfit.com/image/upload',
							data: formData,
							dataType: 'json',
							contentType: false,
							processData: false,
							success: function (data) {
								console.log(data);
							},
							error: function (err) {
								console.log(err.statusText);
							}
						});
					} else {
						alert('请上传图片');
					}

				}
			}
		}
	}
	gym_add.init();
});