#parse("/common/global_helper.jsp")
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
    <meta name="msapplication-tap-highlight" content="no">
    <title>亲！还没有报名信息哦</title>
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

    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            box-sizing: border-box;
            -webkit-overflow-scrolling: touch;
            overflow-scrolling: touch;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
            tap-highlight-color: rgba(0, 0, 0, 0)
        }
        
        html {
            font-size: 625%;
        }
        
        @font-face {
            font-family: 'hyshuaixian';
            src: url('$!staticPrefix/wechat/fonts/HYSHUAIXIANTIW.TTF') format('truetype');
            font-weight: normal;
            font-style: normal;
        }
        
        body {
            position: relative;
            font-family: "hyshuaixian", "Microsoft YaHei", "SimSun", Verdana, Arial, Helvetica, sans-serif;
            background-color: #ffdb2b;
            color: #e8342f;
            font-size: .5rem;
        }
        img{
            display: block;
            max-width: 100%;
            margin: 0 auto;
        }
        .wrapper-Box {
            position: relative;
            width: 100%;
            height: 100%;
            max-width: 7.5rem;
            margin: 0 auto;
            text-align: center;
            overflow: hidden;
        }
        .header{
            padding-top: 5%;
        }
        .header img{
            width: 100%;
        }
        .error p{
            line-height: .7rem;
            margin: 12% 0 0%;
        }
        .error img{
            margin: 8% auto 12%;
        }
        .btn-group{
        }
        .btn-group a{
            display: block;
            width: 80%;
            height: .8rem;
            line-height: .8rem;
            margin: 0 auto 3%;
            background: #272827;
            color: #ffda2a;
            text-decoration: none;
            font-size: .4rem;
        }
    </style>
</head>

<body>
	<div class="wrapper-Box">
        <header class="header">
            <img src="$!staticPrefix/wechat/images/error/logo.png" alt="">
        </header>
        <section class="error">
            <p>您还没有报名<br>&nbsp;快去报名吧！</p>
            <img src="$!staticPrefix/wechat/images/error/error-icon.png" alt="">
            <div class="btn-group">
                <a href="/match/signup?match_id=840043772388573183">参赛入口</a>
                <a href="/match/signup/watch?match_id=840043772388573183">观赛入口</a>
            </div>
        </section>
    </div>
</body>
<script>
    window.onload = function() {
        let wH = window.innerHeight > 0 ? window.innerHeight : document.documentElement.clientHeight;
        document.body.style.height = (wH-16) + 'px';
    }
</script>

</html>