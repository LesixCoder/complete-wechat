#parse("./global/base.jsp")
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>美女&野兽综合体能挑战赛</title>
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telphone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <meta name="MobileOptimized" content="320">
    <meta name="browsermode" content="application">
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="http://static.mperfit.com/css/wap/temporaryEnroll/sign.css" />
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
    <div class="wrapper-Box">
        <section id="content">
            <div class="content-inner">
                <h2 class="yellow">报名信息</h2>
                <form>
                    <p>
                        <input id="nickname" type="text" placeholder="昵称(最多5个字符)" value="" maxlength="5" required pattern="^[\u4e00-\u9fa5a-zA-Z0-9_]+$">
                    </p>
                    <p class="sex">
                        <label>
                            <input type="radio" name="sex" value="1" checked>野兽</label>
                        <label>
                            <input type="radio" name="sex" value="2">美女</label>
                    </p>
                    <p>
                        <input id="age" type="text" value="" maxlength="2" max="99" placeholder="年龄" required pattern="120|((1[0-1]|\d)?\d)">
                    </p>
                    <p class="tel clearfix">
                        <input id="telphone" type="text" placeholder="手机号" required pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$">
                        <input id="verNum" type="button" value="获取验证码" class="getCode">
                    </p>
                    <p>
                        <input id="verCode" type="text" placeholder="验证码" value="" required maxlength="6" pattern="^[0-9]*$">
                    </p>
                    <p lang="invi">
                        <input id="inviCode" type="text" placeholder="邀请码" value="">
                        <span>（选填）</span>
                    </p>
                    <button class="save" type="submit">提交</button>
                </form>
            </div>
        </section>
    </div>
    <input type="hidden" id="orderId" value="$!orderId">
    <input type="hidden" id="type" value="$!type">
</body>
<script src="http://static.mperfit.com/js/common_js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="http://static.mperfit.com/js/wap/temporaryEnroll/ver.js"></script>
<script type="text/javascript">
    jQuery(".save").click(function() {
        /* 				if(jQuery.trim(jQuery("#nickname").val()) == ""){
        				     return;
        				}
        				if(jQuery.trim(jQuery("#age").val()) == ""){
        				     return;
        				}
        				if(jQuery.trim(jQuery("#telphone").val()) == ""){
        				     return;
        				}
        				if(jQuery.trim(jQuery("#verCode").val()) == ""){
        				     return;
        				} */

        var data = {
            name: jQuery("#nickname").val(),
            sex: jQuery("input[type='radio']:checked").val(),
            age: jQuery("#age").val(),
            telphone: jQuery("#telphone").val(),
            verCode: jQuery("#verCode").val(),
            inviCode: jQuery("#inviCode").val()
        };
        if(data.name!='' && data.sex!='' && data.age!='' && telphone!='' && verCode!=''){
            jQuery.ajax({
                url: '/wechat/checkCode',
                data: data,
                type: 'post',
                dataType: 'json',
                success: function(data) {
                    if (data.result == 3) { //success
                        var name = jQuery("#nickname").val();
                        var sex = jQuery("input[type='radio']:checked").val();
                        var age = jQuery("#age").val();
                        var tel = jQuery("#telphone").val();
                        var type = jQuery("#type").val();
                        var code = "&code="+jQuery("#inviCode").val();
                        var otherId = "";
                        if (jQuery("#orderId").val() != "undefined" && jQuery("#orderId").val() != "" && jQuery("#orderId").val() != null) {
                            otherId = "&otherId=" + jQuery("#orderId").val();
                        }
                        window.location.replace("/wxpay/temporaryEnroll/pay?type=" + type + "&name=" + name + "&sex=" + sex + "&age=" + age + "&tel=" + tel + otherId+code);
                    } else if (data.result == 1) {
                        alert(data.msg);
                        return;
                    }
                },
                error: function() {
                    alert("服务器连接失败！");
                }
            });
        }else{
            alert('请补全信息');
        }   
        return false;
    });

</script>

</html>
