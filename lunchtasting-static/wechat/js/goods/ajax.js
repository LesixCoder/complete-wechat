// JavaScript Document
jQuery(function () {
	// 页数
	var page = 1;
	// 每页展示5个
	//var size = 2;
	// dropload
	jQuery('.main').dropload({
		scrollArea: window,
		loadDownFn: function (me) {
			page++;
			var pageTotal;
			// 拼接HTML
			var result = '';
			jQuery.ajax({
				type: 'POST',
				url: 'http://test-wechat.mperfit.com/goods/listMore?page=' + page,
				dataType: 'json',
				success: function (data) {
					if (data.code == 100) {
						var arrLen = data.data.list;
						pageTotal = data.data.total_page;
						if (arrLen.length > 0 && page <= pageTotal) {
							//console.log(page);
							for (var i = 0; i < arrLen.length; i++) {
								result += '<figure class="main__list opacity">' +
									'<a href="' + arrLen[i].goods_id + '"><img src="' + arrLen[i].img_url + '" alt=""></a>' +
									'<figcaption>' +
									'<p>【' + arrLen[i].name + '】' + arrLen[i].content + '</p>' +
									'<summary>来自：<i>' + arrLen[i].channel_name + '</i></summary>' +
									'</figcaption>' +
									'</figure>';
							}
						} else { // 如果没有数据
							// 锁定
							me.lock();
							// 无数据
							me.noData();
						}
						// 为了测试，延迟1秒加载
						setTimeout(function () {
							// 插入数据到页面，放到最后面
							jQuery('.mainIn').append(result);
							// 每次数据插入，必须重置
							me.resetload();
						}, 200);
					}
				},
				error: function (xhr, type) {
					//alert('Ajax error!');
					// 即使加载出错，也得重置
					me.resetload();
				}
			});
		}
	});
});