#parse("base.jsp")
<html>
  <head>
    <title>文章管理-保存</title>
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
					<h1 class="title">文章管理-保存</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
						<ol class="breadcrumb bc-1">
							<li>
								<a href="goToIndex"><i></i>首页</a><!-- class="fa-home" -->
							</li>
							<li class="active">
								<strong>文章管理</strong>
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
						<input type="hidden" id="articleId" value="$!{article.id}" />
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">文章名称</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" value="$!{article.name}" data-validate="required" data-message-required="文章名称为必填项" placeholder="输入文章名称" aria-required="true" aria-describedby="name-error" aria-invalid="true">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">文章简介</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="content" name="content" value="$!{article.content}" data-validate="required" data-message-required="文章简介为必填项" placeholder="输入文章简介" aria-required="true" aria-describedby="content-error" aria-invalid="true">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="activity.name">文章类型</label>
							<div class="col-sm-10">
							<div class="col-sm-10">
<!--     								<select class="form-control" id="type" data-validate="required" data-message-required="文章类型为必填项" aria-required="true" aria-describedby="type-error" aria-invalid="true" style="display: block; visibility: visible; width: 795px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;"> -->
<!-- 										<option value="0" "#if(0 == $!{article.type}) selected="selected" #end">普通文章</option> -->
<!-- 										<option value="1" "#if(1 == $!{article.type}) selected="selected" #end">达人文章</option> -->
<!-- 									</select> -->
									 <select class="form-control" id="type" data-validate="required" data-message-required="文章类型为必填项" aria-required="true" aria-describedby="type-error" aria-invalid="true" style="display: block; visibility: visible; width: 795px; height: 35px; opacity: 100; position: absolute; top: 0px; left: 0px; cursor: pointer; z-index: 999999; margin: 0px; padding: 6px; -webkit-appearance: menulist-button;">
										<option value="1" "#if(1 == $!{article.categoryId}) selected="selected" #end">玩</option>
										<option value="2" "#if(2 == $!{article.categoryId}) selected="selected" #end">美</option>
									</select> 
							</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.areaName">标签</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="tag" name="tag" value="$!{article.tag}" data-validate="required" data-message-required="标签为必填项" placeholder="输入标签" aria-required="true" aria-describedby="tag-error" aria-invalid="true" maxlength="5">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">排序</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="sort" name="sort" value="$!{article.sort}" placeholder="默认99，如不排序，无需填写" aria-required="true" aria-describedby="name-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">首图</label>
							<div class="col-sm-4">
							#if($!{article.id})
							    <input type="text" readonly="readonly" class="form-control" id="url" name="url" value="" style="width: 300px;"/>
							#else
							    <input type="text" readonly="readonly" class="form-control" id="url" name="url" value="" style="width: 300px;" data-validate="required" data-message-required="首图为必填项" aria-required="true" aria-describedby="url-error" aria-invalid="true"/>
							#end
							</div>
							<input type="button" id="image" value="选择图片" style="margin-top: 5px;"/>
						</div>
						#if($!{imgUrl})
                        <div class="form-group-separator" ></div>
							<div class="form-group" name="check-div">	
								<div class="col-sm-10" style="text-align: center;">
									<img id="img" src="$!{imgUrl}" style="width: auto;height: auto;">
								</div>
						</div>
						#end
						<div class="form-group-separator"></div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" name="check-div">
									<label class="control-label" for="about">图文编辑</label>
									<textarea class="form-control autogrow" name="imgText" id="imgText"  rows="5" style="height: 500px;">$!{article.imgText}</textarea>
								</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group">
							<button type="button" class="btn btn-success" id="article-save">保存</button>
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
        // 关闭过滤模式，保留所有标签
        KindEditor.options.filterMode = false;
        KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="imgText"]', {
				cssPath : '$!path/css/admin_activity/prettify.css',
// 				uploadJson : 'kindeditor-master/jsp/upload_json.jsp?type='+type,
				uploadJson : 'image/upload?type='+type,
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
	K("#article-save").click(function(){
		show_loading_bar(50);
		jQuery("#restaurant-form").submit();
		var checknum = jQuery("div[name='check-div'][class='form-group validate-has-error']").length;
		if(checknum == 0){
				K.ajax('saveArticle', function(data) {
				        show_loading_bar(100);
	  					if(data.flag == "success"){
	  						alert("保存成功!");
	  						location = "goArticleList";
	  					}else{
	  						alert("保存失败!");
	  						return false;
	  					}
                   },'POST',{
                      id : jQuery("#articleId").val(),
				    name : jQuery("#name").val(),
				 imgText : editor1.html(),
				 content : jQuery("#content").val(),
				 sort    : jQuery("#sort").val(),
				    tag  : jQuery("#tag").val(),
				   type  : jQuery("#type").val(),
				    url  : jQuery("#url").val()
            });
		}
	});
	
	K('#image').click(function() {
			editor1.loadPlugin('image', function() {
                 editor1.plugin.imageDialog({
                    showRemote : false,
					imageUrl : K('#url').val(),
					clickFn : function(url, title, width,height, border, align) {
						      K('#url').val(url);
						      editor1.hideDialog();
					}
				});
			});
		});
});

var imgUrl = "";
	jQuery("#url").change(function(){
		if(imgUrl == "") imgUrl = $("#img").attr("src"); 
		alert($(this).val());
		if($(this).val() != ""){
			var objUrl = getObjectURL(this.files[0]) ;
			console.log("objUrl = "+objUrl) ;
			if (objUrl) {
				$("#img").attr("src", objUrl) ;
			}
		}else{
			$("#img").attr("src", imgUrl) ;
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
		return url ; 
	}
</script>
</body>
</html>
