// JavaScript Document
jQuery(function () {
	var provs_data = null;
	var citys_data = null;
	var dists_data = null;
	jQuery.ajax({
		type: 'POST',
		url: '/address/getAllCity',
		dataType: 'json',
		success: function (data) {
			if (data.code == 100) {
				provs_data = data.data.province;
				citys_data = data.data.city[0];
				dists_data = data.data.town[0];
				//地址初始化
				var area = new LArea();
				area.init({
					'trigger': '#city', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
					'valueTo': '#valueTo', //选择完毕后id属性输出到该位置
					'keys': {
						id: 'value',
						name: 'text'
					}, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
					'type': 2, //数据源类型
					'data': [provs_data, citys_data, dists_data] //数据源
				});
			}
		},
		error: function (xhr, type) {

		}
	});
});