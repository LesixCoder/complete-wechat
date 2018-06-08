<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>美女&野兽综合体能挑战赛</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/temporaryEnroll/recruit.css" />
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
    <div class="wrapper-Box">
        <section id="content">
            <div class="content-inner">
                <h2 class="yellow">达人招募令</h2>
                <div class="zm-content">
                    <div class="container clearfix">
                        <figure class="zm-perCon">
                            <span class="zm-img"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/perBg.jpg" alt="" id="img1"></span>
                            <figcaption>
                                <div class="text show">
                                    <span>昵称：<i id="name1"></i></span><span>性别：<i id="sex1"></i></span>
                                </div>
                                <div class="ing none">
                                    <span>组队中...</span>
                                </div>
                            </figcaption>
                        </figure>
                        <figure class="zm-perCon">
                            <span class="zm-img"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/perBg.jpg" alt="" id="img2"></span>
                            <figcaption>
                                <div class="text show" id="rightDiv1">
                                    <span>昵称：<i id="name2"></i></span><span>性别：<i id="sex2"></i></span>
                                </div>
                                <div class="ing none" id="rightDiv2">
                                    <span>组队中...</span>
                                </div>
                            </figcaption>
                        </figure>
                    </div>
                </div>
		        <div class="rule">
                    <h4>游戏玩法：</h4>
                    <p>已有队友，可将自己的招募信息直接分享给微信好友；<br>没有队友，可将招募信息分享到朋友圈，第一个完成报名的即为配对成功。<br><span class="yellow">组队成功，如何寻找TA？点击“查看活动”返回主页，扫描微信群二维码进群，TA在群里等着你！</span></p>
                    <p>（特殊说明：若在23日报名截止时仍未招募到队友，报名费用将在赛事结束后5个工作日内按原付款路径退回）</p>
                </div>
                <div class="btn-group">
                    <a id="btn1" href="/saishi/crossfit?orderId=$!orderIdkkk">查看活动</a>
                    <a id="btn2" class="none" href="/saishi/crossfit?orderId=$!orderIdkkk">点击报名</a>
                    <a id="btn3" href="javascript:void(0)" onclick="_system._guide(true)">招募</a>
                </div>
            </div>
        </section>
    </div>
    <div id="cover"></div>
    <div id="guide"><img src="http://static.mperfit.com/image/wap/temporaryEnroll/guide1.png"></div>
</body>
<script src="http://static.mperfit.com/js/common_js/jquery-1.12.0.min.js"></script>
<script src="http://static.mperfit.com/js/wap/temporaryEnroll/share.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

/*      wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '$!appId', // 必填，公众号的唯一标识
		    timestamp: '$!timestamp', // 必填，生成签名的时间戳
		    nonceStr: '$!nonceStr', // 必填，生成签名的随机串
		    signature: '$!signature',// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		}); */

	jQuery(function() {
	/* 	wx.ready(function(){
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		wx.onMenuShareTimeline({
		    title: '', // 分享标题
		    link: 'http://wap.mperfit.com/saishi/crossfit', // 分享链接
		    imgUrl: 'http://static.mperfit.com/image/wap/temporaryEnroll/guide1.png', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareAppMessage({
		    title: '', // 分享标题
		    desc: '', // 分享描述
		    link: 'http://wap.mperfit.com/saishi/crossfit2?orderId=$!orderId', // 分享链接
		    imgUrl: 'http://static.mperfit.com/image/wap/temporaryEnroll/guide1.png', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
	}); */
		var id = "$!orderIdkkk";
		jQuery.ajax({
			url : '/saishi/getData?orderId=' + id,
			data : null,
			type : 'post',
			success : function(data) {
				if (data.result == 1) {
					location = data.url;
				} else {
					var l = data.list.length;
					var show = data.show;
					var sex;
					var sex1;
					if(l==2){
						jQuery("#btn1,#btn3").addClass("i-show").removeClass("none");
						jQuery("#btn2").addClass("none").removeClass("i-show");
					}else{
						//他自己
						if(show==1){
							jQuery("#btn1,#btn3").addClass("i-show").removeClass("none");
							jQuery("#btn2").addClass("none").removeClass("i-show");
						}
						//别人看
						else if(show==2){
							jQuery("#btn2,#btn3").addClass("i-show").removeClass("none");
							jQuery("#btn1").addClass("none").removeClass("i-show");
						}
					
					}
					if (l != 0) {
						if (l == 1) {
							jQuery("#name1").html(data.list[0].name);
							if (data.list[0].sex == 1) {
								sex = "野兽";
							} else if (data.list[0].sex == 2) {
								sex = "美女";
							}
							jQuery("#sex1").html(sex);
							jQuery("#img1").attr('src',
									data.list[0].head_img_url);

							jQuery("#rightDiv1").hide();
							jQuery("#rightDiv2").show();
						} else if (l == 2) {
							for ( var i = 0; i < l; i++) {
								jQuery("#name1").html(data.list[0].name);
								if (data.list[0].sex == 1) {
									sex = "野兽";
								} else if (data.list[0].sex == 2) {
									sex = "美女";
								}
								jQuery("#sex1").html(sex);
								jQuery("#img1").attr('src',
										data.list[0].head_img_url);

								jQuery("#name2").html(data.list[1].name);
								if (data.list[1].sex == 1) {
									sex1 = "野兽";
								} else if (data.list[1].sex == 2) {
									sex1 = "美女";
								}
								jQuery("#sex2").html(sex1);
								jQuery("#img2").attr('src',
										data.list[1].head_img_url);
							}
							jQuery("#rightDiv2").hide();
							//jQuery("#recruit").hide();
						}
					}
				}
			},
			error : function() {
				alert("服务器连接失败！");
			}
		});
	});
</script>
</html>
