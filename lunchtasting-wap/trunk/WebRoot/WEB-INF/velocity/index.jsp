#parse("./global/base.jsp")
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>PERFIT-只为不可思议</title>
    <script type="text/javascript">
        /*判断是否在手机或者PC中打开*/
        function browserRedirect() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
                //window.location.href= 'http://wap.mperfit.com/'; //手机站链接
            } else {
                window.location = 'http://www.mperfit.com/'; //PC站链接
            }
        }
        browserRedirect();
    </script> 
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="http://static.mperfit.com/image/wap/index1209/images/title_icon.png" type="image/x-icon">
    <link rel="icon" href="http://static.mperfit.com/image/wap/index1209/images/title_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/index1209/css/bootstrap.min.css" />
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/index1209/css/jquery.fullPage.css" />
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/index1209/css/main.css" />
</head>

<body style="position:relative;" id="navbarExample" data-spy="scroll" data-target="#selfNav">
    <!--导航-->
    <!--主屏-->
    <div id="dowebok">
        <!--第一屏-->
        <section class="section" id="pageOne">
            <div class="container fixW fixW1">
                <div class="pageOne-tit">
                    <h2 id="index"><img src="http://static.mperfit.com/image/wap/index1209/images/logo.png" alt=""></h2>
                    <h3>—只为不可思议—</h3>
                </div>
                <div class="pageOne-download">
                    <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.mperfit.perfit">下载APP</a>
                </div>
                <div class="pageOne-img">
                    <img src="http://static.mperfit.com/image/wap/index1209/images/page1-img.png" alt="">
                </div>
            </div>
        </section>
        <!--第二屏-->
        <section id="pageTwo">
            <div class="container fixW">
                <div class="pageTwo-tit">
                    <h2 id="introduction">跨出无畏的第一步</h2>
                    <h3>PERFIT玩美障碍赛</h3>
                </div>
                <div class="pageTwo-text">
                    <p>PERFIT玩美障碍赛是玩美平台打造的原创户外挑战赛，要求参赛者具备综合的身体素质，此外融合运动、娱乐、音乐、社交等当下潮流、时尚元素，打造属于年轻人的运动嘉年华。</p>
                </div>
            </div>
        </section>
        <!--第三屏-->
        <section id="pageTh">
            <div class="container fixW">
                <div class="pageTwo-tit">
                    <h2 id="introduction">APP全新改版</h2>
                </div>
                <div class="pageTh-text">
                    <figure class="figure1">
                        <img src="http://static.mperfit.com/image/wap/index1209/images/page3_img1.png" alt="">
                        <figcaption>
                            <p>PERFIT玩美APP吸引众多年轻人在平台中聚集，通过交流，找到你最玩美的朋友。</p>
                        </figcaption>
                    </figure>
                    <figure class="figure2">
                        <img src="http://static.mperfit.com/image/wap/index1209/images/page3_img2.png" alt="">
                        <figcaption>
                            <p>多种赛事玩法，不同难度的挑战，结合线上活动，使你在比赛中发觉更多的乐趣。</p>
                        </figcaption>
                    </figure>
                    <figure class="figure3">
                        <img src="http://static.mperfit.com/image/wap/index1209/images/page3_img3.png" alt="">
                        <figcaption>
                            <p>新奇的内容，专业的知道，让你领略荷尔蒙带来的冲击。</p>
                        </figcaption>
                    </figure>
                </div>
            </div>
        </section>
        <!--第四屏-->
        <section id="pageThree">
            <div class="pageThree-tit">
                <h2 id="about"><span>ABOUT</span></h2>
            </div>
            <div class="pageThree-text">
                <div class="container fixW">
                    <p>PERFIT，2016年成立于北京。</p>
                    <p>专注竞技赛事领域，主打“原创精品系列赛事”及“赛事周边文娱活动”。</p>
                    <p>致力于打造国内领先的，集赛事、娱乐、内容为一体的综合服务平台。</p>
                    <p>PERFIT是建立于传统运动赛事文化基础之上，秉承价值发现、价值创造、价值提升的核心理念，为全民体育事业，搭建一个从行业升级、产业升级到内容升级、服务升级的开放、多元、个性化的体育文化平台。</p>
                    <p>PERFIT积极发展全民体育事业，传递独立、正义、活泼、勇敢的核心价值观。号召让全民运动发展成为全民皆可轻松参与的一项娱乐狂欢派对。</p>
                </div>
            </div>
            <!--页脚-->
            <div class="container fixW">
                <div class="pageThree-footer clearfix row">
                    <div class="footer-right col-lg-12">
                        <a class="email" href="javascript:void(0)"></a>
                        <a class="weibo" href="http://weibo.com/u/6000286078"></a>
                    </div>
                    <div class="footer-download">
                        <img src="http://static.mperfit.com/image/wap/index1209/images/qrcode-weixin.png" alt="">
                        <span>扫描二维码<br>下载&nbsp;PERFIT&nbsp;APP</span>
                    </div>
                </div>
            </div>
            <div class="pageThree-bottom">
                <p>Copyright &copy; 2016 北京稼优佳文化传媒有限公司 版权所有</p>
                <p> 京ICP备15042729号</p>
            </div>
        </section>
    </div>
    <footer class="footer"></footer>
</body>
<script src="http://static.mperfit.com/js/wap/index1209/js/jquery-1.12.0.min.js"></script>
<script src="http://static.mperfit.com/js/wap/index1209/js/bootstrap.min.js"></script>
<script src="http://static.mperfit.com/js/wap/index1209/js/jquery.fullPage.min.js"></script>
<script>
    $(".navbar-default .navbar-nav>li").on('click', function() {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    /*滚动监听*/
    $('#selfNav').scrollspy('refresh');
    /*初始化全屏滚动*/
    $(function() {
        $('#dowebok').fullpage({
            'navigation': false,
            resize: true, //字体是否随着窗口缩放而缩放
            autoScrolling: false, //是否使用插件的滚动方式，如果选择 false，则会出现浏览器自带的滚动条
            onLeave: function(anchorLink, index) {
                if (index == 1) {
                    $(".selfNav").css({
                        'background-color': 'rgba(32, 33, 37, 0.8)'
                    });
                } else {
                    $(".selfNav").css({
                        'background-color': 'rgba(32, 33, 37, 0.5)'
                    });
                }
            }
        });
    });

</script>

</html>
