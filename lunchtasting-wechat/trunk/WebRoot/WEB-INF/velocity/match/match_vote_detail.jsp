#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
    <meta name="msapplication-tap-highlight" content="no">
    <title>人气投票</title>
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/share.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_vote.css" />
     <!--微信浏览器中打开-->
    <script type="text/javascript"> 
        // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            // 以下代码是用javascript强行关闭当前页面
//            var opened = window.open('about:blank', '_self');
//            opened.opener = null;
//            opened.close();
            window.stop ? window.stop() : document.execCommand("Stop");
        }
    </script>
</head>

<body>
    <div class="wrapper-box">
        <section class="vote-details">
            <div class="del-img">
                <figure class="list-fig">
                    <span>
                        <img src="$!imgPrefix$!vote.img_url" alt="">
                    </span>
                    <figcaption>
                        <div class="fp-left">
                            <p>$!vote.name</p>
                        </div>
                        <div class="fp-right">
							#if("$!vote.is_vote" == "")
                            <a class="voteBtn" href="javascript:void(0)" js-attr-orderId="$!vote.order_id" js-attr-type="2">投票</a>
                            #else
                           	<a class="voteBtn active">已投</a>
                            #end                            
                           	<span>$!vote.vote_count</span>
                        </div>
                    </figcaption>
                </figure>
            </div>
            <a class="pullVote" href="javascript:void(0)" onclick="_system._guide(true)">为我拉票</a>
            <div class="del-ercode">
                <img src="$!staticPrefix/wechat/images/vote/ercode.png" alt="">
                <p>扫码参赛即可参加活动</p>
            </div>
        </section>
        <div id="cover"></div>
        <div id="guide"><img src="$!staticPrefix/wechat/images/vote/guide.png"></div>
    </div>
    
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
		    title: '咆哮狗人气王-为我拉票', // 分享标题
		    link: 'http://wchat.mperfit.com/match/order/vote/detail?order_id=$!vote.order_id', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '咆哮狗人气王-为我拉票', // 分享标题
		    desc: '很多朋友叫我上传艺术照。这一刻，我偏不！咆哮狗户外挑战赛，给你一个真实！放声咆哮！', // 分享描述
		    link: 'http://wchat.mperfit.com/match/order/vote/detail?order_id=$!vote.order_id', // 分享链接
		    imgUrl: 'http://image.mperfit.com/paoxiaogou/logo.jpg', // 分享图标
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
<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_vote.js"></script>
<script src="$!staticPrefix/wechat/js/share.js"></script>
</body>
</html>