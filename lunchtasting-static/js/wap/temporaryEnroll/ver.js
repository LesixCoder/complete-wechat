$(function () {
    //验证码倒计时 
    var InterValObj; //timer变量，控制时间 
    var count = 60; //间隔函数，1秒执行 
    var curCount; //当前剩余秒数 
    var code = ""; //验证码 
    var regType;
    var phoneTemplet;
    var codeLength = 4; //验证码长度 
    jQuery(".getCode").click(function () {
        curCount = count;
        var phone = jQuery.trim($("#telphone").val()); //手机号码 
        var isMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        var jtel = jQuery(".j-telphone");
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
			    url:'/wechat/smsCode',
			    data: {phone:phone,type:110},
			    type:'post',
			    dataType:'json',
			    success:function(data){
			    if(data.result == 3){//success
			        alert(data.msg);
			    }else if(data.result == 4){
			    	alert(data.msg);
			    	return;
			    }else if(data.result == 1){
			    	alert(data.msg);
			    	return;
			    }else if(data.result == 2){
			    	alert(data.msg);
			    	return;
			       }
			    },
			     error : function() {
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
            jQuery("#verNum").val("重新发送");
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效 
        } else {
            curCount--;
            jQuery("#verNum").val(curCount + "秒");
        }
    }

});

