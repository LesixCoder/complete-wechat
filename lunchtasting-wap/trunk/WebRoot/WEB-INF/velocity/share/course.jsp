#parse("./global/base.jsp")
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
    <meta name="renderer" content="webkit">
    <meta name='apple-itunes-app' content='app-id=1155981225'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=yes, email=yes">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="$pathStatic/css/common_css/swiper-3.4.0.min.css" />
    <link rel="stylesheet" href="$pathStatic/css/common_css/imageviewer.css" />
    <link rel="stylesheet" href="$pathStatic/css/common_css/star-rating-svg.css" />
    <link rel="stylesheet" href="$pathStatic/css/wap/wap_index/course.css" />
</head>

<body style="overflow-x: hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>课程详情 </h1>
            </nav>
        </header>
        <section class="content-com">
            <header class="header-details">
                <img src="$!bean.img_url" alt="">
            </header>
            <div class="body-details">
                <p lang="p">
                    $!bean.introduction
                    <span><i>×</i>不支持退款</span>
                </p>
                <div class="venue-intro">
                    <h3>$bean.seller_name<div class="courseR jq-stars" data-rating="$bean.score"></div><span lang="jRate">(<i id="jRateN">$!bean.score</i>)</span></h3>
                    <p><a href="/venue/map?venue_id=$!bean.seller_id"><span>$!bean.specific_address</span><i></i></a></p>
                    <p><a href="wtai://wp/mc;$!bean.phone"><span>$!bean.phone</span><i></i></a></p>
                </div>
                <div class="course-imgDet">
                    <h3>图文详情</h3>
                    <div class="imgDet-slide">
                        <!-- Swiper -->
                        <div class="swiper-container">
                            <div class="swiper-wrapper">
                             #foreach ($bean11 in $!bean.img_list)
                             <div class="swiper-slide">
                                 <img src="$bean11" data-high-res-src="$bean11" alt="" class="swiper-lazy gallery-items">
                                 <div class="swiper-lazy-preloader swiper-lazy-preloader-white"></div>
                             </div>
                             #end
                              
                            </div>
                            <!-- Add Pagination -->
                            <div class="swiper-scrollbar"></div>
                        </div>
                    </div>
                    <div class="imgDet-lesson">
                        <div class="lesson-left">
                            <span>$!bean.name</span>
                        </div>
                        <div class="lesson-right">
                            <p>一次<span><i>$!bean.market_price</i>元</span></p>
                            <p>团购价<span><i>$!bean.price</i>元</span></p>
                        </div>
                    </div>
                    <div class="imgDet-details">
						$!bean.content
                    </div>
                </div>
                <div class="venue-comment">
                    <h3>评论(<i>$!bean.count</i>)</h3>
                    #foreach ($bean22 in $bean2)
                    <figure>
                        <img src="$!bean22.img_url" alt="">
                        <figcaption>
                            <h4>$!bean22.name</h4>
                            <p><div class="venueR jq-stars" data-rating="$!bean22.score"></div>
                                <time>$!bean22.time</time>
                            </p>
                            <div>
                                <span>$!bean22.content</span>
                                <p>
                                	 #foreach ($bean222 in $!bean22.img_list)
                                	 	 <img src="$!bean222" alt="">
                                	 #end
                                </p>
                            </div>
                        </figcaption>
                    </figure>
                    #end
                    <a class="checkMore" href="javascript:void(0)">查看全部评价<i class="moreR"></i></a>
                </div>
            </div>
        </section>
        <footer class="footer">
            <span>￥$!bean.price<i>￥$!bean.market_price</i></span>
            <a href="javascript:void(0)">抢购</a>
        </footer>
    </div>
</body>
<script src="$pathStatic/js/common_js/jquery-1.12.0.min.js"></script>
<script src="$pathStatic/js/common_js/swiper-3.4.0.jquery.min.js"></script>
<script src="$pathStatic/js/common_js/imageviewer.min.js"></script>
<script src="$pathStatic/js/common_js/jquery.star-rating-svg.min.js"></script>
<script src="$pathStatic/js/wap/wap_index/venue.js"></script>
</html>
