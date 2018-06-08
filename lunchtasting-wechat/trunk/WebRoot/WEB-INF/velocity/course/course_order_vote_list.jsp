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

    <link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/plugs/normalize.css">
    <link rel="stylesheet" type="text/css" href="$!staticPrefix/wechat/css/goods/common.css">
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/course/course_voteDet-002.css" />
</head>
<body>
<div class="wrapBox">
    <header class="vote-header">
        <img src="$!staticPrefix/wechat/images/vote/new/vote-logo.png" alt="">
        <div class="vote-btnGroup">
            <a class="header-explain" href="javascript:void(0)"><img src="$!staticPrefix/wechat/images/vote/tpsm.png" alt=""></a>
            <a href="http://wchat.mperfit.com/course/activity?course_id=921633867612291073"><img src="$!staticPrefix/wechat/images/vote/cansai.png" alt=""></a>
        </div>
    </header>
    <section class="vote-box">
        <section class="box-list">
            #if("$!list" != "" && $!list.size() > 0)
            #foreach($bean in $list)
            <article>
                <figure class="figure figure-l">
                    <span><a href="/course/vote/detail?order_id=$!bean.order_id"><img src="$!bean.user_img_url" alt=""></a></span>
                    <figcaption class="figure-l-fig">
                        <p class="figure-p1">
                            <span>$!bean.user_name</span>
                            #if("$!is_vote" == "0")
                            <a class="voteBtn" js-status="0" js-uid="$!bean.order_id" href="javascript:;"><i>投票</i></a>
                            #else
                            <a class="voteBtn vote-end" js-status="1" js-uid="$!bean.order_id" href="javascript:;"><i>已投</i></a>
                            #end
                        </p>
                        <p class="figure-p2">
                            <span>
                                <i class="stone-big"></i>x<em class="stone-big-num">$!bean.vote_hundred</em>
                                <i class="stone-mid"></i>x<em class="stone-mid-num">$!bean.vote_ten</em>
                                <i class="stone-small"></i>x<em class="stone-small-num">$!bean.vote_unit</em>
                            </span>
                            <span class="vote-count">$!bean.vote_count</span>
                        </p>
                    </figcaption>
                </figure>
                <figure class="figure figure-r">
                    <span class="figure-r-span">
                        #if("$!bean.user_list" != "" && $!bean.user_list.size() > 0)
                            #foreach($user in $!bean.user_list)
                            <a href="javascript:;"><img src="$!user.user_img_url" alt=""></a>
                            #end
                            #set($size = 9 - $!bean.user_list.size() )
                            #if($!size > 0)
                                #foreach($i in [1..$!size])
                                <a href="javascript:;"></a>
                                #end
                            #end	                    
                        #else
                            #foreach($i in [1..9])
                            <a href="javascript:;"></a>
                            #end
                        #end
                    </span>
                    <figcaption>
                        <p>他的狗主人</p>
                        <p><a href="/course/vote/list?order_id=$!bean.order_id&user_id=$!bean.user_id">查看更多</a></p>
                    </figcaption>
                </figure>
            </article>
            #end
            #end
        </section>
    </section>
    <footer class="footer-list">
        <div class="list-footer">
            <div class="footer-left">
                <a class="goPrevious" href="/course/order_vote/list?cm_id=$!cmId&page=$!previousPage">&lt;</a>
                <span><i>$!currentPage</i>/<i>$!totalPage</i>页</span>
                <a class="goNext" href="/course/order_vote/list?cm_id=$!cmId&page=$!nextPage">&gt;</a>
            </div>
            <form action="/course/order_vote/list" class="footer-right" method="#">
                <span>去<input type="number" name="page" id="page">页</span>
                <input type="hidden" name="cm_id" value="$!cmId">
                <input type="submit" value="确定">
            </form>
        </div>
    </footer> 
    <!--  投票说明  -->
    <div class="explainBox" style="font-size: .34rem;">
        <span><a class="eClose" href="javascript:void(0)"></a></span>
        <p style="margin-top:-.2rem;">狗粮说明</p>
        <p>1.每位用户只可为一<br>名选手投票一次即1根狗骨头。<br>2.1个狗粮包=10个狗爪=100根狗<br>骨头。 <br>3.活动将在2017年11月10日0点截止。 <br>4.活动结束后前三名将会获得比赛<br>大礼包其支持者也会获得线上助<br>威礼包其礼品会在活动结束<br>后现场发放。</p>
    </div>
    <div class="filter"></div>
</div>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="$!staticPrefix/wechat/js/course/course_vote-002.js"></script>
</body>
</html>