#parse("base.jsp")
<html>
  <head>
    <title>轮播管理-保存</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />

<!-- 	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/font.css"> -->
	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/bootstrap.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-core.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-forms.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-components.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-skins.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/custom.css">
	
	<link rel="stylesheet" href="$!path/js/admin_activity/kindeditor/themes/default/default.css">
    <script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/kindeditor-all.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/zh-CN.js"></script>

	<script src="$!path/js/admin_activity/bootstrap-select/bootstrap-select.js"></script>
	<link rel="stylesheet" href="$!path/js/admin_activity/bootstrap-select/bootstrap-select.css">
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="System/assets/js/html5shiv.min.js"></script>
		<script src="System/assets/js/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.table-condensed{
			font-size: 13px;
		}
	</style>
  </head>
  
<body class="page-body">

	<div class="settings-pane">
			
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times;
		</a>
		
		<div class="settings-pane-inner">
			
			<div class="row">
				
				<div class="col-md-4">
					
					<div class="user-info">
						
						<div class="user-image">
							<a href="extra-profile.html">
								<img src="$!path/image/admin_images/user-2.png" class="img-responsive img-circle" />
							</a>
						</div>
						
						<div class="user-details">
							
							<h3>
								<a href="extra-profile.html">John Smith</a>
								
								<!-- Available statuses: is-online, is-idle, is-busy and is-offline -->
								<span class="user-status is-online"></span>
							</h3>
							
							<p class="user-title">Web Developer</p>
							
							<div class="user-links">
								<a href="extra-profile.html" class="btn btn-primary">Edit Profile</a>
								<a href="extra-profile.html" class="btn btn-success">Upgrade</a>
							</div>
							
						</div>
						
					</div>
					
				</div>
				
				<div class="col-md-8 link-blocks-env">
					
					<div class="links-block left-sep">
						<h4>
							<span>Notifications</span>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk1" />
								<label for="sp-chk1">Messages</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk2" />
								<label for="sp-chk2">Events</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk3" />
								<label for="sp-chk3">Updates</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk4" />
								<label for="sp-chk4">Server Uptime</label>
							</li>
						</ul>
					</div>
					
					<div class="links-block left-sep">
						<h4>
							<a href="#">
								<span>Help Desk</span>
							</a>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Support Center
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Submit a Ticket
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Domains Protocol
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Terms of Service
								</a>
							</li>
						</ul>
					</div>
					
				</div>
				
			</div>
		
		</div>
		
	</div>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">
			
			<div class="sidebar-menu-inner">	
				
				<header class="logo-env">
					
					<!-- logo -->
					<div class="logo">
						<a href="goToIndex" class="logo-expanded">
							<img src="$!path/image/admin_images/logo@2x.png" width="130" alt="" />
						</a>
						
						<a href="goToIndex" class="logo-collapsed">
							<img src="$!path/image/admin_images/logo-collapsed@2x.png" width="40" alt="" />
						</a>
					</div>
					
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu">
							<i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span>
						</a>
						
						<a href="#" data-toggle="mobile-menu">
							<i class="fa-bars"></i>
						</a>
					</div>
					
					<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
<!-- 					<div class="settings-icon"> -->
<!-- 						<a href="#" data-toggle="settings-pane" data-animate="true"> -->
<!-- 							<i class="linecons-cog"></i> -->
<!-- 						</a> -->
<!-- 					</div> -->
								
				</header>
				
				<ul id="main-menu" class="main-menu">
				      #include("../../menu.jsp")
				</ul>
						
			</div>
			
		</div>
		
		<div class="main-content">
					
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation">
				
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
					
					<!-- <li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li> -->
					
				</ul>
				
			</nav>
			<div class="page-title">
				
				<div class="title-env">
					<h1 class="title">轮播管理-保存</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
						<ol class="breadcrumb bc-1">
							<li>
								<a href="goToIndex"><i></i>首页</a>
							</li>
							<li class="active">
								<strong>轮播管理</strong>
							</li>
						</ol>
				</div>
					
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">基本信息</h3>
					<div class="panel-options">
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">&ndash;</span>
							<span class="expand-icon">+</span>
						</a>
						<a href="#" data-toggle="remove">
							&times;
						</a>
					</div>
				</div>
				<div class="panel-body">
					<iframe style="display: none;" name="ifa" id="ifa"></iframe>
					<form role="form" method="post" class="form-horizontal validate" id="restaurant-form" target="ifa">
						<input type="hidden" id="carouselId" value="$!{carousel.id}" />
						<div class="form-group" name="check-div" id="hideOrShow">
							<label class="col-sm-2 control-label" for="activity.name">类型</label>
							<div class="col-sm-10">
							<div class="col-sm-12">
    								<select class="form-control" id="bizType" onchange="changeType();" data-validate="required" aria-required="true" aria-describedby="seller-error" aria-invalid="true" style="display: block; visibility: visible; width: 100%; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
										<option value="1" "#if($carousel.bizType == 1) selected="selected" #end">活动</option>
										<option value="2" "#if($carousel.bizType == 2) selected="selected" #end">文章</option>
										<option value="3" "#if($carousel.bizType == 3) selected="selected" #end">课程</option>
										<option value="4" "#if($carousel.bizType == 4) selected="selected" #end">商家</option>
									</select>  
							</div>
							</div>
						</div>
						<!-- 活动 -->
						<div class="form-group-separator" id="separator1"></div>
						<div class="form-group" name="check-div" id="hd">
							<label class="col-sm-2 control-label" for="activity.name">活动</label>
							<div class="col-sm-10" lang="div1">
							<div class="col-sm-12">
    								<select class="form-control selectpicker bla bla bli" data-live-search="true" id="bizIdhd" data-validate="required" aria-required="true" aria-describedby="seller-error" aria-invalid="true" style="display: block; visibility: visible; width: 810px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
    								#foreach ($activity in $activityList)
										<option value="$!{activity.id}" "#if($activity.id == $!{carousel.bizId}) selected="selected" #end">$!{activity.name}</option>
									#end	
									</select>  
							</div>
							</div>
						</div>
						<!-- 文章 -->
						<div class="form-group-separator" id="separator2"></div>
						<div class="form-group" name="check-div" id="wz">
							<label class="col-sm-2 control-label" for="activity.name">文章</label>
							<div class="col-sm-10" lang="div1">
							<div class="col-sm-12">
    								<select class="form-control selectpicker bla bla bli" data-live-search="true" id="bizIdwz" data-validate="required" aria-required="true" aria-describedby="seller-error" aria-invalid="true" style="display: block; visibility: visible; width: 810px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
    								#foreach ($article in $articleList)
										<option value="$!{article.id}" "#if($article.id == $!{carousel.bizId}) selected="selected" #end">$!{article.name}</option>
									#end	
									</select>  
							</div>
							</div>
						</div>
						<!-- 课程 -->
						<div class="form-group-separator" id="separator3"></div>
						<div class="form-group" name="check-div" id="kc">
							<label class="col-sm-2 control-label" for="activity.name">课程</label>
							<div class="col-sm-10" lang="div1">
							<div class="col-sm-12">
    								<select class="form-control selectpicker bla bla bli" data-live-search="true" id="bizIdkc" data-validate="required" aria-required="true" aria-describedby="seller-error" aria-invalid="true" style="display: block; visibility: visible; width: 810px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
    								#foreach ($course in $courseList)
										<option value="$!{course.id}" "#if($course.id == $!{carousel.bizId}) selected="selected" #end">$!{course.name}</option>
									#end	
									</select>  
							</div>
							</div>
						</div>
						<!-- 商家 -->
						<div class="form-group-separator" id="separator4"></div>
						<div class="form-group" name="check-div" id="sj">
							<label class="col-sm-2 control-label" for="activity.name">商家</label>
							<div class="col-sm-10" lang="div1">
							<div class="col-sm-12">
    								<select class="form-control selectpicker bla bla bli" data-live-search="true" id="bizIdsj" data-validate="required" aria-required="true" aria-describedby="seller-error" aria-invalid="true" style="display: block; visibility: visible; width: 810px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
    								#foreach ($seller in $sellerList)
										<option value="$!{seller.id}" "#if($seller.id == $!{carousel.bizId}) selected="selected" #end">$!{seller.name}</option>
									#end	
									</select>  
							</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">描述</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="depict" name="depict" value="$!{carousel.depict}" placeholder="输入描述" aria-required="true" aria-describedby="depict-error" aria-invalid="true">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">链接</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="url" name="url" value="$!{carousel.url}"  placeholder="输入链接">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">排序</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="sort" name="sort" value="$!{carousel.sort}" placeholder="默认99，如不排序，无需填写" aria-required="true" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div" id="wz">
							<label class="col-sm-2 control-label" for="activity.name">是否跳转</label>
							<div class="col-sm-10" lang="div1">
							<div class="col-sm-12">
    								<select class="form-control selectpicker bla bla bli" data-live-search="false" id="isClick" data-validate="required" aria-required="true" aria-describedby="isClick-error" aria-invalid="true" style="display: block; visibility: visible; width: 810px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
										<option value="1" "#if(1 == $!{carousel.isClick}) selected="selected" #end">是</option>
										<option value="0" "#if(0 == $!{carousel.isClick}) selected="selected" #end">否</option>
									</select>  
							</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">图片</label>
							<div class="col-sm-4">
							#if($!{carousel.id})
							    <input type="text" readonly="readonly" class="form-control" id="urlImg" name="urlImg" value="" style="width: 300px;"/>
							#else
							    <input type="text" readonly="readonly" class="form-control" id="urlImg" name="urlImg" value="" style="width: 300px;" data-validate="required" data-message-required="图片为必填项" aria-required="true" aria-describedby="urlImg-error" aria-invalid="true"/>
							#end
							</div>
							<input type="button" id="image" value="选择图片" style="margin-top: 5px;"/>
						</div>
						#if($!{imgUrl})
                        <div class="form-group-separator" ></div>
							<div class="form-group" name="check-div">	
								<div class="col-sm-10" style="text-align: center;">
									<img id="img" src="$!{imgUrl}" style="width: 330px;height: auto;">
								</div>
						</div>
						#end
						<div class="form-group-separator"></div>
						<div class="form-group">
							<button type="button" class="btn btn-success" id="activity-save">保存</button>
							<button type="reset" class="btn btn-white">重置</button>
						</div>
					</form>
				</div>
			</div>
			
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">
				<div class="footer-inner">
					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; <script>document.write(new Date().getFullYear())</script> 
						<strong>PerFit</strong> 
					</div>
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
						<a href="#" rel="go-top">
							<i></i><!-- class="fa-angle-up" -->
						</a>
					</div>
				</div>
			</footer>
		</div>
		
	</div>

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="$!path/js/admin_activity/datatables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="$!path/js/admin_activity/daterangepicker/daterangepicker-bs3.css">
	
	<!-- Bottom Scripts -->
	<script src="$!path/js/admin_activity/bootstrap.min.js"></script>
	<script src="$!path/js/admin_activity/TweenMax.min.js"></script>
	<script src="$!path/js/admin_activity/resizeable.js"></script>
	<script src="$!path/js/admin_activity/joinable.js"></script>
	<script src="$!path/js/admin_activity/xenon-api.js"></script>
	<script src="$!path/js/admin_activity/xenon-toggles.js"></script>
	<script src="$!path/js/admin_activity/datatables/js/jquery.dataTables.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="$!path/js/admin_activity/datatables/dataTables.bootstrap.js"></script>
	<script src="$!path/js/admin_activity/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="$!path/js/admin_activity/datatables/tabletools/dataTables.tableTools.min.js"></script>
	
	<!-- Bottom Scripts -->
	<script src="$!path/js/admin_activity/moment.min.js"></script>

	<!-- Imported scripts on this page -->
	<script src="$!path/js/admin_activity/daterangepicker/daterangepicker.js"></script>
	
	<!-- Imported scripts on this page -->
	<script src="$!path/js/admin_activity/jquery-validate/jquery.validate.min.js"></script>
	
	<!-- JavaScripts initializations and stuff -->
	<script src="$!path/js/admin_activity/xenon-custom.js"></script>
	
	<div class="xenon-loading-bar"><span data-pct="0" style=""></span></div>
	<script type="text/javascript">
	    var role = $!session.getAttribute('login_session_user').roleId;
	    var userId = $!session.getAttribute('login_session_user').id;
	    var type = "activity";
        KindEditor.ready(function(K) {
			var editor1 = K.editor({
				cssPath : '$!path/css/admin_activity/prettify.css',
				uploadJson : 'kindeditor-master/jsp/upload_json.jsp?type='+type,
				fileManagerJson : 'kindeditor-master/jsp/file_manager_json.jsp',
				allowFileManager : true,
				allowImageRemote: false,
	            urlType:'domain'
	 });
	 
	 	K('#image').click(function() {
			editor1.loadPlugin('image', function() {
                 editor1.plugin.imageDialog({
                    showRemote : false,
					imageUrl : K('#urlImg').val(),
					clickFn : function(url, title, width,height, border, align) {
						      K('#urlImg').val(url);
// 						      K('#img').attr('src',url);
						      editor1.hideDialog();
					}
				});
			});
		});
	 
	 jQuery('.selectpicker').selectpicker({}); 
	 
	  if("$!{carousel.id}" == ""){
  	     jQuery("#separator2").hide();
	     jQuery("#wz").hide();
	     jQuery("#separator3").hide();
	     jQuery("#kc").hide();
	     jQuery("#separator4").hide();
	     jQuery("#sj").hide();
	  }else{
	   if("$!{carousel.bizType}" == 1){
	  	 jQuery("#separator2").hide();
	     jQuery("#wz").hide();
	     jQuery("#separator3").hide();
	     jQuery("#kc").hide();
	     jQuery("#separator4").hide();
	     jQuery("#sj").hide();
	  }else if("$!{carousel.bizType}" == 2){
	  	 jQuery("#separator1").hide();
	     jQuery("#hd").hide();
	     jQuery("#separator3").hide();
	     jQuery("#kc").hide();
	     jQuery("#separator4").hide();
	     jQuery("#sj").hide();	
	  }else if("$!{carousel.bizType}" == 3){
	  	 jQuery("#separator1").hide();
	     jQuery("#hd").hide();
	     jQuery("#separator2").hide();
	     jQuery("#wz").hide();
	     jQuery("#separator4").hide();
	     jQuery("#sj").hide();
	  }else if("$!{carousel.bizType}" == 4){
	     jQuery("#separator1").hide();
	     jQuery("#hd").hide();
	     jQuery("#separator2").hide();
	     jQuery("#wz").hide();
	     jQuery("#separator3").hide();
	     jQuery("#kc").hide();
	  }
  } 
	//保存				
	show_loading_bar(100);
	K("#activity-save").click(function(){
		show_loading_bar(50);
		jQuery("#restaurant-form").submit();
		var checknum = jQuery("div[name='check-div'][class='form-group validate-has-error']").length;
		if(checknum == 0){
		
		var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		var url;
// 		var re=new RegExp(strRegex);
// 		if(re.test(jQuery("#url").val()) == true){
			url = jQuery("#url").val();
// 		}else{
// 			alert("链接不合法,请重新输入!");
// 			jQuery("#url").focus();
// 		    return;
// 		}
		
		var bizId;
		if(jQuery("#bizType").val() == 1){
			bizId = jQuery("#bizIdhd").val();
		}else if(jQuery("#bizType").val() == 2){
		    bizId = jQuery("#bizIdwz").val();
		}else if(jQuery("#bizType").val() == 3){
		    bizId = jQuery("#bizIdkc").val();
		}else if(jQuery("#bizType").val() == 4){
		    bizId = jQuery("#bizIdsj").val();
		}
				K.ajax('saveCarousel', function(data) {
				        show_loading_bar(100);
	  					if(data.flag == "success"){
	  						alert("保存成功!");
	  						location = "goCarouselList";
	  					}else{
	  						alert("保存失败!");
	  						return false;
	  					}
                   },'POST',{
                      id : jQuery("#carouselId").val(),
				  depict : jQuery("#depict").val(),
				     url : url,
				    sort : jQuery("#sort").val(),
				  imgUrl : jQuery("#urlImg").val(),
				  bizId  : bizId,
				 bizType : jQuery("#bizType").val(),
				 isClick : jQuery("#isClick").val()
            });
		}
	});
});
	
	function changeType(){
		 if(jQuery("#bizType").val() == 1){
		   jQuery("#separator2").hide();
		   jQuery("#wz").hide();
		   jQuery("#separator3").hide();
		   jQuery("#kc").hide();
		   jQuery("#separator4").hide();
		   jQuery("#sj").hide();
		   jQuery("#separator1").show();
		   jQuery("#hd").show();
		 }else if(jQuery("#bizType").val() == 2){
		   jQuery("#separator1").hide();
		   jQuery("#hd").hide();
		   jQuery("#separator3").hide();
		   jQuery("#kc").hide();
		   jQuery("#separator4").hide();
		   jQuery("#sj").hide();
		   jQuery("#separator2").show();
		   jQuery("#wz").show();
		 }else if(jQuery("#bizType").val() == 3){
		   jQuery("#separator1").hide();
		   jQuery("#hd").hide();
		   jQuery("#separator2").hide();
		   jQuery("#wz").hide();
		   jQuery("#separator4").hide();
		   jQuery("#sj").hide();
		   jQuery("#separator3").show();
		   jQuery("#kc").show();
		 }else if(jQuery("#bizType").val() == 4){
		   jQuery("#separator1").hide();
		   jQuery("#hd").hide();
		   jQuery("#separator2").hide();
		   jQuery("#wz").hide();
		   jQuery("#separator3").hide();
		   jQuery("#kc").hide();
		   jQuery("#separator4").show();
		   jQuery("#sj").show();
		 }
	 }
	</script>
</body>
</html>
