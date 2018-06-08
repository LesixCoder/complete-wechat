#parse("base.jsp")
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<!-- 	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/fonts/font.css"> -->
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/bootstrap.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/xenon-core.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/xenon-components.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="$!path/js/admin_login_js/assets/css/custom.css">

	<script src="$!path/js/admin_login_js/assets/js/jquery-1.11.1.min.js"></script>
	<script src="$!path/js/admin_activity/md5.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="System/assets/js/html5shiv.min.js"></script>
		<script src="System/assets/js/respond.min.js"></script>
	<![endif]-->
	
  </head>
  
<body class="page-body login-page login-light">
	<div class="login-container">
		<div class="row">
			<div class="col-sm-6">
				<script type="text/javascript">
					jQuery(document).ready(function($){
						// Reveal Login form
						setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
						// Validation and Ajax action
						$("form#login").validate({
							rules: {
								username: {
									required: true
								},
								passwd: {
									required: true
								}
							},
							messages: {
								username: {
									required: '请输入用户名.'
								},
								
								passwd: {
									required: '请输入密码.'
								}
							},
							
							// Form Processing via AJAX
							submitHandler: function(form){
								show_loading_bar(70); // Fill progress bar to 70% (just a given value)
								
								var opts = {
									"closeButton": true,
									"debug": false,
									"positionClass": "toast-top-full-width",
									"onclick": null,
									"showDuration": "300",
									"hideDuration": "1000",
									"timeOut": "5000",
									"extendedTimeOut": "1000",
									"showEasing": "swing",
									"hideEasing": "linear",
									"showMethod": "fadeIn",
									"hideMethod": "fadeOut"
								};
							}
						});
						// Set Form focus
						jQuery("form#login .form-group:has(.form-control):first .form-control").focus();
					});
				</script>
				
				<!-- Errors container -->
				<div class="errors-container">
				
									
				</div>
				
				<!-- Add class "fade-in-effect" for login form effect -->
				<form method="post" role="form" id="login" class="login-form fade-in-effect">
					
					<div class="login-header">
						<a href="javascript:void(0);" class="logo">
							<img src="$!path/js/admin_login_js/assets/images/logo-white-bg@2x.png" alt="" width="80" />
							<span>登陆</span>
						</a>
						
						<p>亲爱的用户,登录进入管理领域!</p>
					</div>
	
					
					<div class="form-group">
						<label class="control-label" for="username">用户名</label>
						<input type="text" class="form-control" value="admin" name="account" id="account" autocomplete="off" />
					</div>
					
					<div class="form-group">
						<label class="control-label" for="passwd">密码</label>
						<input type="password" class="form-control" value="123456" name="passwd" id="passwd" autocomplete="off" />
					</div>
					
					<div class="form-group">
						<button type="submit" id="loginClick" class="btn btn-primary  btn-block text-left">
<!-- 							<i class="fa-lock"></i> -->
							&nbsp;&nbsp;&nbsp;&nbsp;登陆
						</button>
					</div>
					
					<div class="login-footer">
						<a href="#">忘记密码？</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Bottom Scripts -->
	<script src="$!path/js/admin_login_js/assets/js/bootstrap.min.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/TweenMax.min.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/resizeable.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/joinable.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/xenon-api.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/xenon-toggles.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="$!path/js/admin_login_js/assets/js/toastr/toastr.min.js"></script>

	<!-- JavaScripts initializations and stuff -->
	<script src="$!path/js/admin_login_js/assets/js/xenon-custom.js"></script>
</body>
<script type="text/javascript">
	
	jQuery("#loginClick").click(function(){
	            var account;
				if(jQuery.trim($("#account").val()) != ""){
				    account = jQuery.trim($("#account").val());
			    }else{
			    	alert("用户名不能为空!");
			    	jQuery("#account").focus();
			    	return;
			    }
	  			var password;
	  			if(jQuery.trim($("#passwd").val()) != ""){
	  			    password = hex_md5(jQuery.trim($("#passwd").val()));
	  			}else{
	  			    alert("密码不能为空!");
	  			    jQuery("#passwd").focus();
	  			    return;
	  			}
				jQuery.ajax({
				    url:'adminUserLogin',
				    data:{password:password,account:account},
				    type:'post',
				    async: false,    
				    dataType:'json',
				    success:function(data){
				        if(data.result == 0){
				        	//成功
					         alert(data.descript);
					         show_loading_bar(100);
					         location = "goToIndex";
				        }else{
				        	//失败
				        	alert(data.descript);
				        	show_loading_bar(50);
				        }
				    },
				     error : function() {
				          alert("服务器连接失败！");
				     }    
				});
		});

</script>
</html>
