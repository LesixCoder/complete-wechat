#parse("base.jsp")
<html>
  <head>
    <title>活动管理-保存</title>
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
	
	<!-- <link rel="stylesheet" type="text/css" href="$!path/css/seller/imagesUp_css/H-ui.min.css" />
	<link href="$!path/css/seller/imagesUp_css/webuploader.css" rel="stylesheet" type="text/css" /> -->

	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/bootstrap.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-core.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-forms.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-components.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-skins.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/custom.css">

    <script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/kindeditor-all.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/zh-CN.js"></script>

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
		<div class="sidebar-menu toggle-others fixed">
			
			<div class="sidebar-menu-inner">	
				
				<header class="logo-env">
					
					<!-- logo -->
					<div class="logo">
						<a href="javascript:void(0);" class="logo-expanded">
							<img src="$!path/image/admin_images/logo@2x.png" width="130" alt="" />
						</a>
						
						<a href="javascript:void(0);" class="logo-collapsed">
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
					
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>
					
				</ul>
				
			</nav>
			<div class="page-title">
				
				<div class="title-env">
					<h1 class="title">活动管理-保存</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
						<ol class="breadcrumb bc-1">
							<li>
								<a href="javascript:void(0);"><i class="fa-home"></i>Home</a>
							</li>
							<li>
								<a href="javascript:void(0);">Dashboard</a>
							</li>
							<li class="active">
								<strong>活动管理</strong>
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
						<input type="hidden" id="activityId" value="$!{activity.id}" />
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">活动名称</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" value="$!{activity.name}" data-validate="required" data-message-required="活动名称为必填项" placeholder="输入活动名称" aria-required="true" aria-describedby="name-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">活动简介</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="content" name="content" value="$!{activity.content}" data-validate="required" data-message-required="活动简介为必填项" placeholder="输入活动简介" aria-required="true" aria-describedby="content-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">简写地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="specificAddress" name="specificAddress" value="$!{activity.specificAddress}" data-validate="required" placeholder="输入简写地址" data-message-required="简写地址为必填项" aria-required="true" aria-describedby="specificAddress-error" aria-invalid="true" maxlength="50">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">具体地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="address" name="address" value="$!{activity.address}" data-validate="required" placeholder="输入具体地址" data-message-required="具体地址为必填项" aria-required="true" aria-describedby="address-error" aria-invalid="true" maxlength="200">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">标签</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="tag" name="tag" value="$!{activity.tag}" data-validate="required" placeholder="输入标签" data-message-required="标签为必填项" aria-required="true" aria-describedby="tag-error" aria-invalid="true" maxlength="5">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">价格</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="price" name="price" value="$!{activity.price}" data-validate="required" data-message-required="价格为必填项" placeholder="输入价格" aria-required="true" aria-describedby="price-error" aria-invalid="true" maxlength="30" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">市场价格</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="marketPrice" name="marketPrice" value="$!{activity.marketPrice}" data-validate="required" data-message-required="市场价格为必填项" placeholder="输入市场价格" aria-required="true" aria-describedby="marketPrice-error" aria-invalid="true" maxlength="30" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label">报名人数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="enrollNum" name="sort" value="$!{activity.enrollNum}" placeholder="默认0，无限制" aria-required="true" aria-describedby="name-error" aria-invalid="true" maxlength="5">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">排序</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="sort" name="sort" value="$!{activity.sort}" placeholder="默认99，如不排序，无需填写" aria-required="true" aria-describedby="name-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						#if($activity)
						<div class="form-group" name="check-div">
						<label class="col-sm-2 control-label" >活动时间</label>
						<div class="col-sm-4" style="color: #cc3f44;width:27.5%">
							<div class="input-group">
							<input type="text" id="activityDate" name="activityDate" value="$!{beginTime},$!{endTime}" class="form-control daterange" data-validate="required" data-message-required="活动时间为必填项" data-time-picker="true" data-time-picker-increment="5" data-format="YYYY-MM-DD HH:mm" data-separator="," readonly="readonly"  placeholder="选择日期" aria-required="true" aria-describedby="activityDate-error" style="display: block; visibility: visible; width: 400px; height: 25px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 0px; -webkit-appearance: menulist-button;"/>
								<div class="input-group-addon">
									<a href="javascript:void(0);"><i class="linecons-calendar"></i></a>
								</div>
							</div>		
						</div>
						</div>
						#else
						<div class="form-group" name="check-div">
						<label class="col-sm-2 control-label" >活动时间</label>
						<div class="col-sm-4" style="color: #cc3f44;width:27.5%">
							<div class="input-group">
							<input type="text" id="activityDate" name="activityDate" class="form-control daterange" data-validate="required" data-message-required="活动时间为必填项" data-time-picker="true" data-time-picker-increment="5" data-format="YYYY-MM-DD HH:mm" data-separator="," readonly="readonly"  placeholder="选择日期" aria-required="true" aria-describedby="activityDate-error" style="display: block; visibility: visible; width: 400px; height: 25px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 0px; -webkit-appearance: menulist-button;"/>
								<div class="input-group-addon">
									<a href="javascript:void(0);"><i class="linecons-calendar"></i></a>
								</div>
							</div>		
						</div>
						</div>
						#end
						<!-- 图片 -->
<!-- 						<div class="form-group-separator" ></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" >展示首图</label>
							<div class="col-sm-10">
								<input type="file" class="form-control" id="imgUrl" name="myfiles" accept="image/*" aria-describedby="mealPictures-error" aria-invalid="true" >
							</div>
						</div>	 -->
						<!-- 首图 -->
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">首图</label>
							<div class="col-sm-4">
							#if($!{activity.id})
							    <input type="text" readonly="readonly" class="form-control" id="url" name="url" value="" style="width: 300px;"/>
							#else
							    <input type="text" readonly="readonly" class="form-control" id="url" name="url" value="" style="width: 300px;" data-validate="required" data-message-required="首图为必填项" aria-required="true" aria-describedby="url-error" aria-invalid="true"/>
							#end
							</div>
							<input type="button" id="image" value="选择图片" style="margin-top: 5px;"/>
						</div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="mealsPictures"></label>
							<div class="col-sm-10" style="text-align: center;">
								<img  id = "img" src="$!{activity.imgUrl}" style="width: 330px;height: auto;">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" name="check-div">
									<label class="control-label" for="about">图文编辑</label>
									<textarea class="form-control autogrow" name="imgText" id="imgText"  rows="5" style="height: 500px;">$!{activity.imgText}</textarea>
								</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group">
							<button type="button" class="btn btn-success" id="activity-save">保存</button>
							<button type="reset" class="btn btn-white">重置</button>
						</div>
					</form> 
				</div>
			</div>
			
			<footer class="main-footer sticky footer-type-1">
				<div class="footer-inner">
					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; 2016 
						<strong>Perfit</strong> 
					</div>
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
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
	
	<!--_footer 作为公共模版分离出去
	<script type="text/javascript" src="$!path/js/seller/imgLoad_js/jquery.min.js"></script> 
	<script type="text/javascript" src="$!path/js/seller/imgLoad_js/jquery.icheck.min.js"></script> 
	-->
	<!--请在下方写此页面业务相关的脚本
	<script type="text/javascript" src="$!path/js/seller/imgLoad_js/webuploader.min.js"></script> 
	<script type="text/javascript" src="$!path/js/seller/imgLoad_js/imgLoad.js"></script>
	-->
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
		 var imgSrc = "";
        // 关闭过滤模式，保留所有标签
        KindEditor.options.filterMode = false;
        KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="imgText"]', {
				cssPath : '$!path/css/admin_activity/prettify.css',
				uploadJson : 'image/upload?type=activity',
				fileManagerJson : 'kindeditor-master/jsp/file_manager_json.jsp',
				allowFileManager : true,
				allowImageRemote: false,
				items : [
				'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
				'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
				'anchor', 'link', 'unlink'
	             ],
	            urlType:'domain' ,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			K('input[name=getHtml]').click(function(e) {
					alert(editor1.html());//取得HTML
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor1.isEmpty());//判断是否为空
				});
				K('input[name=getText]').click(function(e) {
					alert(editor1.text());//取得文本(包含img,embed)
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor1.selectedHtml());//取得选中HTML
				});
				K('input[name=setHtml]').click(function(e) {
					editor1.html('<h3>Hello KindEditor</h3>');//设置HTML
				});
				K('input[name=setText]').click(function(e) {
					editor1.text('<h3>Hello KindEditor</h3>');//设置文本
				});
				K('input[name=insertHtml]').click(function(e) {
					editor1.insertHtml('<strong>插入HTML</strong>');//插入HTML
				});
				K('input[name=appendHtml]').click(function(e) {
					editor1.appendHtml('<strong>添加HTML</strong>');//添加HTML
				});
				K('#reset').click(function(e) {
					editor1.html('');//清空内容
				});
	//保存				
	show_loading_bar(100);
	K("#activity-save").click(function(){
		show_loading_bar(50);
		jQuery("#restaurant-form").submit();
		var checknum = jQuery("div[name='check-div'][class='form-group validate-has-error']").length;
		if(checknum == 0){
/* 		        if(jQuery(editor1.text()).attr("src") != undefined){
		        	  alert("1111111111");
		              imgSrc = jQuery(editor1.text()).attr("src");     
		        } */
				K.ajax('saveActivity', function(data) {
				        show_loading_bar(100);
	  					if(data.flag == "success"){
	  					 	var  success = "保存成功！";
		  					if(data.ditu == "no"){
		  						success=success+"地址坐标未能获取到，建议修改。";
		  					}
		  					alert(success);
		  					location = "goActivityList";
	  					}else{
	  						alert("保存失败!");
	  						return false;
	  					}
                   },'POST',{
                      id : jQuery("#activityId").val(),
				    name : jQuery("#name").val(),
				 address : jQuery("#address").val(),
				   price : jQuery("#price").val(),
		    activityDate : jQuery("#activityDate").val(),
				 imgText : editor1.html(),
				 content : jQuery("#content").val(),
				 sort    : jQuery("#sort").val(),
			  enrollNum  : jQuery("#enrollNum").val(),
				 imgUrl  : imgSrc,
		specificAddress  : jQuery("#specificAddress").val(),
		    marketPrice  : jQuery("#marketPrice").val(),
		   			tag  : jQuery("#tag").val()
            });
		}
	});
			K('#image').click(function() {
			editor1.loadPlugin('image', function() {
                 editor1.plugin.imageDialog({
                    showRemote : false,
					imageUrl : K('#url').val(),
					clickFn : function(url, title, width,height, border, align) {
							  imgSrc = url;
							  jQuery("#img").attr("src", url) ;
						      K('#url').val(url);
						      editor1.hideDialog();
					}																																																																																																																																																																																																																																																																																																																																																																																																																									
				});
			});
		});
});

	jQuery("#imgUrl").change(function(){
		if(imgUrl == "") imgUrl = jQuery("#img").attr("src"); 
		if(jQuery(this).val() != ""){
			var objUrl = getObjectURL(this.files[0]) ;
			console.log("objUrl = "+objUrl) ;
			if (objUrl) {
				jQuery("#img").attr("src", objUrl) ;
			}
		}else{
			jQuery("#img").attr("src", imgUrl) ;
		}
	}) ;
	
	//建立一个输出流
	function getObjectURL(file) {
		var url = null ; 
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url; 
	}
</script>
</body>
</html>
