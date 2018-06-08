<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
#parse("./common/global_helper.jsp")
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="nofollow"/>
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
<style>
body{width:100%;height:100%;overflow:hidden;background:url($!staticPrefix/admin/images/pc_loginBg2.jpg) no-repeat;background-size:cover;position:absolute;}
</style>
<script src="$!staticPrefix/admin/js/jquery.js"></script>
<script src="$!staticPrefix/admin/js/Particleground.js"></script>
<script src="$!staticPrefix/admin/js/md5.js"></script>
<!--
<script>
$(document).ready(function() {
  $('body').particleground({
    dotColor:'green',
    lineColor:'#c9ec6e'
  });
  $('.intro').css({
    'margin-top': -($('.intro').height() /2)
  });
});
</script>  -->

</head>
<body>
  <section class="loginform">
   <h1>后台管理系统</h1>
   <ul>
    <li>
     <label>账号：</label>
     <input id="account" type="text" class="textBox" placeholder="管理员账号" />
    </li>
    <li>
     <label>密码：</label>
     <input id="password" type="password" class="textBox" placeholder="登陆密码"/>
    </li>
    <li>
     <input id="loginBtn" type="button" value="立即登陆"/>
    </li>
   </ul>
  </section>
</body>
<script type="text/javascript">
	
jQuery("#loginBtn").click(function(){
            var account;
			if(jQuery.trim($("#account").val()) != ""){
			    account = jQuery.trim($("#account").val());
		    }else{
		    	alert("用户名不能为空!");
		    	jQuery("#account").focus();
		    	return;
		    }
  			var password;
  			if(jQuery.trim($("#password").val()) != ""){
  			    password = hex_md5(hex_md5(jQuery.trim($("#password").val())));
  			}else{
  			    alert("密码不能为空!");
  			    jQuery("#password").focus();
  			    return;
  			}
  			
  			//alert(account +"///" + password);
  			
			jQuery.ajax({
			    url:'/doLogin',
			    data:{password:password,account:account},
			    type:'post',
			    async: false,    
			    dataType:'json',
			    success:function(data){
					if(data.code == 100){
						location.href = "/";
					}else{
						alert('账号或密码错误！');
					}
			    }  
			});
	});

</script>
</html>