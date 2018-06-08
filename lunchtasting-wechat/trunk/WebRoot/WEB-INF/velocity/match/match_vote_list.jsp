#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
<meta name="msapplication-tap-highlight" content="no">
<title>谁是人气王者</title>

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
    <header class="vote-header">
        <img src="$!staticPrefix/wechat/images/vote/logo.png" alt="">
        <a class="header-explain" href="javascript:void(0)"><img src="$!staticPrefix/wechat/images/vote/tpsm.png" alt=""></a>
        #if("$!orderId" != "")
        <a class="header-lapiao" href="/match/order/vote/detail?order_id=$!orderId"><img src="$!staticPrefix/wechat/images/vote/wwlp.png" alt=""></a>
        #end
    </header>
    <section class="vote-box">
        <h3 class="box-tit" id="voteZone"><span>投票区</span></h3>
        <div class="box-btnG">
        	#if("$!sort" == "1")
        	<a class="btnG1 active" href="/match/order/vote/list?sort=1&$!parameter#voteZone">投票排行</a>
            <a class="btnG2" href="/match/order/vote/list?sort=2&$!parameter#voteZone">新晋选手</a>
        	#else
            <a class="btnG1" href="/match/order/vote/list?sort=1&$!parameter#voteZone">投票排行</a>
            <a class="btnG2 active" href="/match/order/vote/list?sort=2&$!parameter#voteZone">新晋选手</a>
        	#end
        </div>
        <form class="box-search" action="/match/order/vote/list#voteZone">
        	<input type="hidden" name="match_id" value="$!matchId">
            <input type="text"  name="name" value="$!name" placeholder="搜昵称投票" maxlength="10">
            <input type="submit" value="搜索">
        </form>
        <!--    投票排行    -->
        <div class="box-list list1">
            <div class="list-l">
            	#foreach($bean in $list)
                <figure class="list-fig">
                    <span>
                        <a href="/match/order/vote/detail?order_id=$!bean.order_id"><img src="$!imgPrefix$!bean.img_url" alt=""></a>
                        <!--<i>1</i>-->
                    </span>
                    <figcaption>
                        <div class="fp-left">
                            <p>$!bean.name</p>
                        </div>
                        <div class="fp-right">
                        	#if("$!bean.is_vote" == "")
                            <a class="voteBtn" id="$!bean.order_id" href="javascript:void(0)" js-attr-orderId="$!bean.order_id" js-attr-type="1">投票</a>
                            #else
                           	<a class="voteBtn active">已投</a>
                            #end
                            <span>$!bean.vote_count</span>
                        </div>
                    </figcaption>
                </figure>
                #end
            </div>
            <div class="list-footer" id="footer">
                <div class="footer-left">
                    <a class="goPrevious" href="/match/order/vote/list?$!parameter&page=$!previousPage#voteZone">&lt;</a>
                    <span><i>$!currentPage</i>/<i>$!totalPage</i>页</span>
                    <a class="goNext" href="/match/order/vote/list?$!parameter&page=$!nextPage#voteZone">&gt;</a>
                </div>
                <form action="/match/order/vote/list#voteZone" class="footer-right">
                    <span>去<input type="number" name="page" id="page">页</span>
                   	<input type="hidden" name="match_id" value="$!matchId">
                    <input type="hidden" name="name" value="$!name">
                    <input type="hidden" name="sort" value="$!sort">
                    <input type="submit" value="确定">
                </form>
            </div>
        </div>
    </section>
    <!--  投票说明  -->
    <div class="explainBox">
        <span><a class="eClose" href="javascript:void(0)"></a></span>
        <p>最佳人气王</p>
        <p>为你钟爱的选手投票，</p><p>并通过分享链接为他拉票。</p><p>截止比赛结束会在现场公布最</p><p>佳人气王并给予奖励，还会从</p><p>为人气王投票的支持者中选</p><p>取10名用户获得纪</p>念奖
    </div>
    <div class="filter"></div>
</div>
<script src="$!staticPrefix/wechat/js/plugs/jquery-1.12.0.min.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_vote.js"></script>

</body>
</html>