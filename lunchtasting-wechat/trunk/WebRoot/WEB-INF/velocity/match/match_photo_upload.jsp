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
    <title>赛事云风采</title>

    <link rel="stylesheet" href="$!staticPrefix/wechat/css/goods/common.css" />
    <link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_photos.css" />
</head>

<body style="min-height: 0;">
    <div class="wrapper-box">
        <section class="match-style">
            <div class="style_header">
                <p class="style_headT">长按图片可下载</p>
                <img class="style_BigImg" src="$!image.img_url_big" alt="">
            </div>
            <div class="style_main">
                <h4 class="main_tit"><span class="style_icon">￥</span>支持赛事云</h4>
                <p class="main_btnG" id="main_btnG">
                    <a href="javascript:;" data-href="/wxpay/album_image?image_id=$!image.id&price=1">1元</a>
                    <a href="javascript:;" data-href="/wxpay/album_image?image_id=$!image.id&price=2">2元</a>
                    <a href="javascript:;" data-href="/wxpay/album_image?image_id=$!image.id&price=5">5元</a>
                </p>
                <a class="payBtn" id="payBtn" href="javascript:;">支持一下</a>
            </div>
            <div class="style_footer">
                    <h4 class="footer_tit"><span class="styleF_icon">★</span>新品推荐</h4>
                    <div class="footer_img">
                        <a href="javascript:;">
                            <img src="$!image.img_url_big" alt="">
                            <span>新款T恤</span>
                        </a>
                        <a href="javascript:;">
                            <img src="$!image.img_url_big" alt="">
                            <span>新款T恤</span>
                        </a>
                    </div>
                </div>
        </section>
    </div>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="$!staticPrefix/wechat/js/match/match_photos.js"></script>
</body>

</html>