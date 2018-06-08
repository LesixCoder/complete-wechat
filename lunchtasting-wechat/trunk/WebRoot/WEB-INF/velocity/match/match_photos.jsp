#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>

<head>
	<meta charset="utf-8">
	<script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
	<meta name="format-detection" content="telphone=no, email=no">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="renderer" content="webkit">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<meta name="full-screen" content="true">
	<meta name="x5-screen" content="true">
	<title>119火力全开</title>

	<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap-glyphicons/1.0/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" href="$!staticPrefix/wechat/css/goods/common.css" />
	<link rel="stylesheet" href="$!staticPrefix/wechat/css/plugs/commes.css" />
	<link rel="stylesheet" href="$!staticPrefix/wechat/css/plugs/photoswipe.css" />
	<link rel="stylesheet" href="$!staticPrefix/wechat/css/plugs/default-skin/default-skin.css" />
	<link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_photos-003.css" />
	<script src="$!staticPrefix/wechat/js/plugs/placeholder.min.js"></script>
</head>

<body>

	<input type="hidden" name="album_id" value="$!albumId">
	<input type="hidden" name="tag_id" value="$!tagId">

	<div class="wrapper-box">
		<header class="photo-header">
			<div class="header-top">
				<img src="$!imgUrl" alt="">
				<!-- 
				<div class="top-inner">
					<h3></h3>
					<p class="box-person"><span>$!totalCount</span>张</p>
					<img src="$!staticPrefix/wechat/images/photos/header-icon.png" alt="">
				</div> -->
			</div>
			<!-- <div class="header-nav">
                <a class="active" href="javascript:;">精彩推荐</a><i>丨</i>
                <a href="javascript:;">全部照片</a><i>丨</i>
                <a href="javascript:;">无号码</a><i>丨</i>
                <a href="javascript:;">周边环境</a>
            </div> -->
		</header>
		<!--  
		<section class="photo-search">
			<form class="box-search" action="/match/album/image/list#voteEQ" method="#">
				<input type="text" name="code" value="$!code" placeholder="搜参赛牌" maxlength="12">
				<input type="submit" value="搜索">
			</form>
			<a class="box-all" href="javascript:void(0)">查看全部</a> 
		</section>-->
		<section class="photo-box">
			<div class="photo-list">
				<!--    照片墙    -->
				<div class="list-box my-gallery" id="list-box">
					#foreach($bean in $imageList)
					<figure class="box opacity">
						<a href="$!bean.img_url_big" data-size="1920x1280">
							<img src="$!bean.img_url" onerror="this.src=placeholder.getData({size: '256x128', text: '图片加载中...', bgcolor: '#ccc', color: '#969696'})" />
						</a>
						<figcaption style="display:none;"><a href="/match/album/image/upload?image_id=$!bean.id" class="downImg"><i class="glyphicon glyphicon-download-alt"></i>下载图片</a></figcaption>
					</figure>
					#end
				</div>
				<!--以下内容不要管-->
				<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="pswp__bg"></div>
					<div class="pswp__scroll-wrap">
						<div class="pswp__container">
							<div class="pswp__item"></div>
							<div class="pswp__item"></div>
							<div class="pswp__item"></div>
						</div>
						<div class="pswp__ui pswp__ui--hidden">
							<div class="pswp__top-bar">
								<div class="pswp__counter"></div>
								<button class="pswp__button pswp__button--close" title="Close (Esc)">x</button>
								<div class="pswp__preloader">
									<div class="pswp__preloader__icn">
										<div class="pswp__preloader__cut">
											<div class="pswp__preloader__donut"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
								<div class="pswp__share-tooltip"></div>
							</div>
							<button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
							<button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
							<div class="pswp__caption">
								<div class="pswp__caption__center"></div>
							</div>
						</div>
					</div>
				</div>
				<!--分页-->
				<div class="list-footer">
					<div class="footer-left">
						<a class="goPrevious" href="/match/album/image/list?$!parameter&page=$!previousPage#voteZone">&lt;</a>
						<span><i>$!currentPage</i>/<i>$!totalPage</i>页</span>
						<a class="goNext" href="/match/album/image/list?$!parameter&page=$!nextPage#voteZone">&gt;</a>
					</div>
					<form action="/match/album/image/list#voteZone" class="footer-right">
						<span>去<input type="number" name="page" id="page">页</span>
						<input type="hidden" name="code" value="$!code">
						<input type="submit" value="确定">
					</form>
				</div>
			</div>
		</section>
	</div>

	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="$!staticPrefix/wechat/js/plugs/photoswipe.min.js"></script>
	<script src="$!staticPrefix/wechat/js/plugs/photoswipe-ui-default.min.js"></script>
	<script type="text/javascript" src="$!staticPrefix/wechat/js/match/match_photos.js"></script>
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	 wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '$!appId', // 必填，公众号的唯一标识
		    timestamp: '$!timestamp', // 必填，生成签名的时间戳
		    nonceStr: '$!nonceStr', // 必填，生成签名的随机串
		    signature: '$!signature',// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		}); 

	//jQuery(function() {
	wx.ready(function(){
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.onMenuShareTimeline({
		    title: '119火力全开精彩瞬间图集', // 分享标题
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/course/20171107155828084.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '119火力全开精彩瞬间图集', // 分享标题
		    desc: '119火力全开 咆哮狗×优客工场平行国度综合体能赛精彩瞬间', // 分享描述
		    link: '$!url', // 分享链接
		    imgUrl: 'http://image.mperfit.com/course/20171107155828084.jpg', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
	});
//}); 
</script>
	
	
</body>

</html>