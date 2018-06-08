//初始化插件
$(function() {
	$('#main').fullpage({
		'verticalCentered' : false, //内容是否垂直居中
		'css3' : true, //是否使用 CSS3 transforms 滚动
		'sectionsColor' : [ '#fff', '#eee', '#fff', '#eee', '#fff' ], //设置背景颜色
		//anchors: ['page1', 'page2', 'page3', 'page4','page5'], //定义锚链接
		'navigation' : true, //是否显示项目导航
		'navigationPosition' : 'right', //项目导航的位置，可选 left 或 right
		//'navigationColor': '#000', //项目导航的颜色
		//'navigationTooltips': ['fullPage.js', 'Powerful', 'Amazing', 'Simple'], //项目导航的 tip
		resize : true, //字体是否随着窗口缩放而缩放
		slidesNavigation : false, //是否显示左右滑块的项目导航
		//'slidesNavPosition': bottom, //左右滑块的项目导航的位置，可选 top 或 bottom
		'controlArrowColor' : '#fff', //左右滑块的箭头的背景颜色
		loopBottom : false, //滚动到最底部后是否滚回顶部
		loopTop : false, //滚动到最顶部后是否滚底部
		loopHorizontal : true, //左右滑块是否循环滑动
		autoScrolling : true, //是否使用插件的滚动方式，如果选择 false，则会出现浏览器自带的滚动条
		scrollOverflow : false, //内容超过满屏后是否显示滚动条
		'paddingTop' : 0, //与顶部的距离
		'paddingBottom' : 0, //与底部距离
		keyboardScrolling : true, //是否使用键盘方向键导航
		continuousVertical : false
	//是否循环滚动，与 loopTop 及 loopBottom 不兼容
	})
});

$('#weixin').click(function() {
	$('.nav, .main').addClass('vague');
	$('#' + $(this).attr('data-prefix') + '_blank').show();
	$('#' + $(this).attr('data-prefix') + '_main').addClass('show');
});
$('.tips-close, .tips-blank').click(function() {
	$('.nav, .main').removeClass('vague');
	$('#' + $(this).attr('data-prefix') + '_blank').hide();
	$('#' + $(this).attr('data-prefix') + '_main').removeClass('show');
});
$('#about').click(function() {
	$('.main').moveTo(5);
});
$('.nav-title').click(function() {
	$('.main').moveTo(1);
});
$(".main .page-c").show();
$(".main .page-d").show();
$(".main .page-e").show();
$(".main .page-f").show();