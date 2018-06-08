#parse("./global/base.jsp")
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>PERFIT</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="$pathStatic/css/wap/wap_index/venue.css" />
</head>

<body style="overflow-x: hidden;">
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>场馆详情</h1>
            </nav> 
        </header>
        <section class="content-com">
            <header class="header-details">
                <img src="$!seller.img_url" alt="">
                <span>$!seller.name</span>  
            </header>
            <div class="body-details">
                <p>$!seller.introduction </p>
                <div class="venue-intro">
                    <h3>$!seller.name</h3>
                    <p><a href="/venue/map?venue_id=$!seller.seller_id"><span>$!seller.specific_address</span><i></i></a></p>
                    <p><a href="tel:$!seller.phone"><span>$!seller.phone</span><i></i></a></p>
                </div>
                <div class="venue-intro venue-act">
                    <h3><span>活动</span></h3>
                    #if("$!activityList" != "" && $!activityList.size()>0)
                    #foreach($!bean in $!activityList)
                    <figure> 
                        <img src="$!bean.img_url" alt="">
                        <figcaption>
                            <h4>$!bean.name</h4>
                            <div class="fig-div">
                                <div class="fig-top">
                                    <time>$!bean.time</time>
                                </div>
                                <div class="fig-bottom">
                                    <span><i>$!bean.price</i>元/次</span>
                                    <span>￥<i>$!bean.market_price</i></span>
                                </div>
                            </div>    
                        </figcaption>
                    </figure>
                    #end
                    #end
                </div>
            </div>        
        </section>
    </div>  
</body>

</html>
