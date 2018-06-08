jQuery(function() {
    //下拉初始化
    jQuery(document).ready(function() {
        jQuery('select').niceSelect();
    });
   
    //高度自适应
    jQuery(document).ready(function() {
        jQuery(".wrapper-Box").height(jQuery(window).height());
    });
});

//验证码倒计时
jQuery(function(){
    var InterValObj; //timer变量，控制时间 
    var count = 60; //间隔函数，1秒执行 
    var curCount; //当前剩余秒数 
    var code = ""; //验证码 
    var regType;
    var phoneTemplet;
    var codeLength = 6; //验证码长度 
    jQuery(".getCode").click(function() {
        curCount = count;
        var phone = jQuery.trim(jQuery("#tel").val()); //手机号码 
        var matchId = jQuery("#matchId").val();
        
        var isMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        if (phone != "" && isMobile.test(phone) && phone.length == 11) {
            //设置button效果，开始计时 
            jQuery("#verNum").attr("disabled", "true");
            jQuery("#verNum").val(curCount + "秒");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次 
            //产生验证码 
            /*for (var i = 0; i < codeLength; i++) {
                code += parseInt(Math.random() * 9).toString();
            }*/
            //向后台获验证码 
            jQuery.ajax({
                url: '/match/smsCode',
                data: {
                    phone: phone,
                    match_id: matchId,
                    type:202
                },
                type: 'post',
                dataType: 'json',
                success: function(data) {

                },
                error: function() {
                    alert("服务器连接失败！");
                }
            });
        } else {
            alert('请输入有效的手机号码');
        }
    });
    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj); //停止计时器 
            jQuery("#verNum").removeAttr("disabled"); //启用按钮 
            jQuery("#verNum").val("重新发送").removeClass('active');
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效 
        } else {
            curCount--;
            jQuery("#verNum").val(curCount + "秒");
        }
    }
})

//提交报名信息
jQuery(function(){
//    // 根据生日的月份和日期，计算星座。
//    function getAstro(m, d) {
//        return "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯".substr(m * 2 - (d < "102123444543".charAt(m - 1) - -19) * 2, 2);
//    }
//    let star = {
//        timeStr: '',
//        f: function(timeStr) {
//            //let timeStr = jQuery("#user-age").val();
//            let timeS = timeStr.indexOf('-');
//            let timeE = timeStr.lastIndexOf('-');
//            let month = timeStr.substring(timeS + 1, timeE);
//            let day = timeStr.substring(timeE + 1);
//            return getAstro(month, day);
//
//        }
//    }
    //提交报名信息
    jQuery("#signBtn").click(function() {
        let data = {
		        name: jQuery("#nickname").val(),//姓名
		        sex: jQuery(".wide option:selected").val(),//性别
		        phone: jQuery("#tel").val(),//手机号
		        code: jQuery("#verCode").val(),//验证码
		        match_id:jQuery("#matchId").val()
			};
        let reg1 = /^[\u4e00-\u9fa5]{1,5}$|^[\dA-Za-z_]{1,10}$/; //匹配姓名
        let reg2 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; //手机号
        if (!reg1.test(data.name)) {
            return alert("姓名格式不正确！");
        } else if (!reg2.test(data.phone)) {
            return alert("手机号格式不正确！");
        }
        
        if (data.name !== '' && data.sex !== '' && data.phone !== '' && data.code !== '') {
            if (confirm("确定要提交信息吗?")) {
                jQuery.ajax({
                    url: '/match/signup/watch/do',
                    data: data,
                    type: 'post',
                    dataType: 'json',
                    success: function(result) {
            			var data = JSON.parse(result);
            			if(data.code == 100){
            				location.href = '/match/signup/info?match_id='+jQuery("#matchId").val()+"&type=2";
            			}else{
            				alert(data.message);
            			}
                    },
                    error: function() {
                        alert("报名失败！");
                    }
                });
            }
        } else {
            alert('请补全信息!');
        }
        return false;
    });

})