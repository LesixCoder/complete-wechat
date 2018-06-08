<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no">
    <title>é¦é¡µ</title>
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
    <link rel="stylesheet" href="http://static.lunchtasting.com:8080/css/wap/wap_index/style.css" />   
</head>

<body>
    <div class="wrapper-Box">
        <header>
            <nav class="nav">
                <h1>Logo</h1>
                <button id="nav-btn" type="button"></button>
                <ul>
                    <li><a href="javascript:void(0)">æç« </a></li>
                    <li><a href="javascript:void(0)">æ´»å¨</a></li>
                    <li><a href="javascript:void(0)">å¨é¨</a></li>
                </ul>
            </nav>
        </header>
        <section class="content">
         #foreach($!ele in $!list)
            <figure>
                    <a href="getOne?type=$!ele.TABLEtype&id=$!ele.id">
                       <img src="http://static.lunchtasting.com:8080/image/wap/wap_index/3.jpg" alt="">
                       #if($!ele.TABLEtype==1)
                       <em class="actIcon">æ´»å¨</em> 
                       #elseif ($!ele.TABLEtype==2)
                       <em class="actIcon">æç« </em> 
                       #end
                       <p><span class="label" lang="label">å¥èº«ãå¹´è½»<i></i></span></p>
                    </a>
                    <figcaption>
                        <h3>$!ele.name</h3>
                        <p>$!ele.content</p>
                       #if($!ele.TABLEtype==1)
                        <div class="fig-div">
                            <div class="fig-left">
                                <time>$!ele.begin_time - $!ele.end_time</time>&nbsp;
                                <address>$!ele.address</address>
                            </div>
                            <div class="fig-right">
                                <span><i> $!ele.price</i>å/æ¬¡</span>
                                <span>ï¿¥<i>$!ele.market_price</i></span>
                            </div>
                        </div>
                        #end    
                    </figcaption>
            </figure>
            #end
        </section>
        <div class="filter"></div>
    </div>  
</body>
<script type="text/javascript" src="http://static.lunchtasting.com:8080/js/wap/wap_index/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="http://static.lunchtasting.com:8080/js/wap/wap_index/js.js"></script>

</html>
