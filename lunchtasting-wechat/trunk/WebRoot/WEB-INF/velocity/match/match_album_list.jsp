#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <script>
        (function (doc, win) {
            var docEl = doc.documentElement,
                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                recalc = function () {
                    var clientWidth = docEl.clientWidth;
                    if (!clientWidth) return;
                    if (clientWidth >= 640) {
                        docEl.style.fontSize = '100px';
                    } else {
                        docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
                    }
                };

            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);
        setTimeout(function () {
            document.body.style.display = 'block';
        }, 100)
    </script>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="full-screen" content="true">
    <meta name="x5-screen" content="true">
    <title>赛事列表</title>

    <link rel="stylesheet" href="$!staticPrefix/wechat/css/goods/common.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_photos.css" />
</head>

<body>
    <div class="wrapper-box">
        <section class="photo-search match-list">
            <from class="box-search">
                <input type="hidden" name="album_id">
                <input type="text" id="code" placeholder="输入赛事名称">
                <input type="submit" value="搜索">
            </from>
        </section>
        <section class="match-listBox">
        	#foreach($bean in $list)
            <figure class="listBox-list">
                <a href="/match/album/image/list?album_id=$!bean.id">
                    <span><img src="$!imgPrefix$!bean.img_url" alt=""></span>
                    <figcaption>
                        <h3>$!bean.name</h3>
                        <address>$!bean.address</address>
                        <p><span>$!bean.image_count张</span></p>
                    </figcaption>
                </a>
            </figure>
            #end
        </section>
    </div>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="$!staticPrefix/wechat/js/plugs/jquery.waterfall.min.js"></script>
    <script src="$!staticPrefix/wechat/js/plugs/photoswipe.min.js"></script>
    <script src="$!staticPrefix/wechat/js/plugs/photoswipe-ui-default.min.js"></script>
    <script src="$!staticPrefix/wechat/js/match/match_photos.js"></script>
</body>

</html>