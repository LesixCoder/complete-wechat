#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="full-screen" content="true">
    <meta name="x5-screen" content="true">
    <title>人气投票</title>

	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
	<link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/course/course_voteDet-001.css" />
</head>

<body>
    <div class="wrapBox">
        <section class="vote-details">
            <header class="voteHeader">
                <a class="voteHeader-back" href="javascript:history.back();"><i class="fa fa-reply"></i></a>
                投票详情
                <a class="voteHeader-home" href="/course/order_vote/list?cm_id=$!vote.course_meal_id"><i class="fa fa-home"></i></a>
            </header>
            <div class="del-img">
                <article>
                    <figure class="figure figure-l">
                        <span><img src="$!vote.user_img_url" alt=""></span>
                        <figcaption class="figure-l-fig">
                            <p class="figure-p1">
                                <span>$!vote.user_name</span>
                                <input type="hidden" value="$!vote.order_id">
                                #if("$!is_vote" == "0")
                                <a class="voteBtn" js-status="0" js-uid="$!vote.order_id" href="javascript:;"><i>投票</i></a>
                                #else
                                <a class="voteBtn vote-end" js-status="1" js-uid="$!vote.order_id" href="javascript:;"><i>已投</i></a>
                                #end
                                
                            </p>
                            <p class="figure-p2">
                                <span>
                                    <i class="stone-big"></i>x<em class="stone-big-num">$!vote.vote_hundred</em>
                                    <i class="stone-mid"></i>x<em class="stone-mid-num">$!vote.vote_ten</em>
                                    <i class="stone-small"></i>x<em class="stone-small-num">$!vote.vote_unit</em>
                                </span>
                                <span class="vote-moreBtn"><a href="/course/vote/list?order_id=$!vote.order_id&user_id=$!vote.user_id">查看更多</a></span>
                                <span class="vote-count none">$!bean.vote_count</span>
                            </p>
                        </figcaption>
                    </figure>
                </article>
                #if("$!vote.sex" == "1")
                <span class="dog-img dog-nan"></span>
                #else
               	<span class="dog-img dog-nv"></span>
                #end
            </div>
            <a class="pullVote" href="javascript:void(0)">扫码喂我狗粮</a>
            <div class="del-ercode">
            	<input id="text" type="hidden" value="http://wchat.mperfit.com/course/vote/detail?order_id=$!vote.order_id" />
            	<div id="qrcode"></div>
                <p>截图分享到朋友圈，寻找更多的狗主人</p>
            </div>
        </section>
    </div>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="$!staticPrefix/wechat/js/qrcode.js"></script>
    <script src="$!staticPrefix/wechat/js/course/course_voteDet-003.js"></script>
    
    
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 100,
	height : 100
});

function makeCode () {		
	var elText = document.getElementById("text");
	
	if (!elText.value) {
		alert("Input a text");
		elText.focus();
		return;
	}
	
	qrcode.makeCode(elText.value);
}

makeCode();

$("#text").
	on("blur", function () {
		makeCode();
	}).
	on("keydown", function (e) {
		if (e.keyCode == 13) {
			makeCode();
		}
	});
</script>
</body>

</html>