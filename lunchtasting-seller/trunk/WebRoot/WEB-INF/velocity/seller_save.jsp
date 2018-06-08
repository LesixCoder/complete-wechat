#parse("base.jsp")
<html>
  <head>
    <title>商户信息</title>
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
	<link rel="stylesheet" href="$!path/css/admin_activity/bootstrap.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-core.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-forms.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-components.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-skins.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/custom.css">
	
    <script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>
    <script src="$!path/js/common_js/ajaxfileupload.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/kindeditor-all.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/zh-CN.js"></script>
	
	<link rel="stylesheet" href="$!path/js/admin_activity/kindeditor/themes/default/default.css">
	<script src="$!path/js/admin_activity/kindeditor/kindeditor-all.js"></script>
	<script src="$!path/js/admin_activity/kindeditor/zh-CN.js"></script>
	<link rel="stylesheet" href="$!path/js/admin_activity/ssi-uploader/ssi-uploader.css">
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
					<h1 class="title">商家信息 -保存</h1>
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
								<strong>商家管理</strong>
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
						<input type="hidden" id="sellerId" value="$!{seller.id}" />
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" >商家名称</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" value="$!{seller.name}" data-validate="required" data-message-required="商家名称为必填项" placeholder="输入商家名称" aria-required="true" aria-describedby="name-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" >手机号</label>
							<div class="col-sm-10">
							<input type="text" class="form-control" id="phone" name="phone" value="$!{seller.phone}" data-validate="required" data-message-required="手机号为必填项" placeholder="输入联系人手机号" aria-required="true" aria-describedby="content-error" aria-invalid="true" maxlength="20">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" >简写地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="address" name="address" value="$!{seller.address}" data-validate="required" placeholder="输入简写地址" data-message-required="简写地址为必填项" aria-required="true" aria-describedby="specificAddress-error" aria-invalid="true" maxlength="20">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">具体地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="specificAddress"  value="$!{seller.specificAddress}" data-validate="required" placeholder="输入具体地址" data-message-required="具体地址为必填项" aria-required="true" aria-describedby="address-error" aria-invalid="true" maxlength="200">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">营业时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="businessHours" name="businessHours" value="$!{seller.businessHours}" data-validate="required" placeholder="输入营业时间" data-message-required="营业时间为必填项" aria-required="true" aria-describedby="businessHours-error" aria-invalid="true" maxlength="200">
							</div>
						</div>		
						<div class="form-group-separator"></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="restaurant.restaurantName">标签</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="tag" name="tag" value="$!{seller.tag}" data-validate="required" data-message-required="标签为必填项" placeholder="输入标签" aria-required="true" aria-describedby="tag-error" aria-invalid="true" maxlength="30">
							</div>
						</div>
						
						<!-- 图片 -->
						<div class="form-group-separator" ></div>
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" >展示首图</label>
							<div class="col-sm-10">
							<!-- accept="image/*"  -->
								<input type="file" class="form-control" id="imgUrl" name="myfiles" aria-describedby="mealPictures-error" aria-invalid="true" >
							</div>
						</div>	
						<div class="form-group" name="check-div">
							<label class="col-sm-2 control-label" for="mealsPictures"></label>
							<div class="col-sm-10" style="text-align: center;">
								<img  id = "img" src="$!seller.imgUrl" style="width: 330px;height: auto;">
							</div>
						</div>
						<div class="row">
			             <div class="col-md-12" id="moreImg">
			            </div>
		                </div>
						<div class="form-group" name="check-div" style="margin-top: 15px;">
							<label class="col-sm-2 control-label" for="restaurant.restaurantAddress">多图</label>
							<div class="col-sm-4">
							    <input type="text" readonly="readonly" class="form-control" id="urlArray" name="urlArray" value="" style="width: 300px;"/>
							</div>
							<input type="button" id="image1" value="选择图片" style="margin-top: 5px;"/>
						</div>
						<div class="form-group-separator"></div>
						<div class="row" >
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">商家简介</label>
									<textarea class="form-control autogrow"  id="introduction" data-validate="minlength[10]" rows="5" placeholder="输入商家简介" aria-required="true"   aria-invalid="true" maxlength="1000" >$!seller.introduction</textarea>
								</div>
							</div>
						</div>	
						<a href='javascript:void(0);' onclick='password1();' class='btn btn-orange btn-sm btn-icon icon-left'>修改密码</a>			
						<div class="form-group-separator"></div>
						<div class="form-group">
							<button type="button" class="btn btn-success" id="seller-save" onclick="doUpload();">保存</button>
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
	
	<script type="text/javascript" src="$!path/js/common_js/md5.js"></script>


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
		var imgurl="";	
		function savehehe(){
		show_loading_bar(50);
		   var telephone;
/* 	       var phoneCheck = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	        if(phoneCheck.test(jQuery.trim(jQuery("#phone").val())) == true){
	            telephone = jQuery.trim(jQuery("#phone").val());
	        }else{
		        alert("请输入正确的手机号!");
		        return false;
	        } */
	       var imgArr = [];
			jQuery("#moreImg table.ssi-imgToUploadTable").each(function(){
				imgArr.push(jQuery(this).find('td[class=ssi-upImgTd]').children('img').attr('src'));
			})
	        telephone = jQuery.trim(jQuery("#phone").val());
			var data = {
                      id : jQuery("#sellerId").val(),
				    name : jQuery("#name").val(),
				 address : jQuery("#address").val(),
		 specificAddress : jQuery("#specificAddress").val(),
		    introduction : jQuery("#introduction").val(),
		   businessHours : jQuery("#businessHours").val(),
		           phone : telephone,
		          imgimg : imgurl,
		          areaId : jQuery("#areaId").val(),
				  imgArr : imgArr.toString(),
				     tag : jQuery("#tag").val()
				    	
            }
            
				jQuery.ajax({
				    url:'saveSeller', 
				    data:data,
				    type:'post',
				    async: false,  
				    dataType:'json',
				    success:function(data){
				        if(data.code == 0){
				        	//成功
					         alert(data.message);
					         window.location.href="goSeller";
				        }else{
				        	//失败
				        	alert(data.message);
				        }
				    },
				     error : function() {
				          alert("服务器连接失败！");
				     }    
				});
				//ceshi
				
			show_loading_bar(100);
 	}
 	
	var imgUrl = "";
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
	});
	
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
<script type="text/javascript">
	function doUpload(){  
		var heheh = jQuery("#imgUrl").val();
		if(heheh=="" || heheh=="null" ||heheh==null){
			savehehe();
			return;
		} 
	    jQuery.ajaxFileUpload({  
            url : "upImgSrc",   //submit to UploadFileServlet  
            secureuri : false, 
            async: false, 
            fileElementId : 'imgUrl',  
            dataType : 'json', //or json xml whatever you like~  
            data: {//加入的文本参数  
            },  
            success : function(data, status) {  
              imgurl = imgurl+data.data.name;
              if(data.code==1){
              	if(confirm("图片上传失败，是否继续提交文本信息？")){
              		savehehe();
              		return;
              	}
              }
              savehehe();
            },  
            error : function(data,status,e){  
             	if(confirm("图片上传失败，是否继续提交文本信息？")){
              		savehehe();
              		return;
              	}
            }  	
        });
	}  
	function password1(){
	 jQuery('#modal-1').modal('show', {backdrop: 'fade'});
	}
	
	function changepasswrodfx(){
		var oldpassword = hex_md5(jQuery.trim(jQuery("#oldpassword").val()));
		var newpassword = jQuery("#newpassword").val();
		var newpassword2 = jQuery("#newpassword2").val();
		if(oldpassword==""){
			alert("请输入原密码！");
			return;
		}
		if(newpassword==""){
			alert("请输入新密码！");
			return;
		}
		if(newpassword!=newpassword2){
			alert("2次输入密码不一样。");
			return;
		}
		if(newpassword.length>20||newpassword.length<6){
			alert("密码长度不合法。");
			return;
		}
		newpassword = hex_md5(jQuery.trim(newpassword));
		var data = {
			oldpassword:oldpassword,
			newpassword:newpassword
		};
		jQuery.ajax({
			    url:'uppwdSeller',
			    data: data,
			    type:'post',
			    dataType:'json',
			    success:function(data){
				    if(data.code == 0){
				    	alert(data.message);
				    	window.location.href="login";
				    }else{
				        alert(data.message);
				    }
			    },
			     error : function() {
			          alert("服务器连接失败！");    
			     }    
			});
	}
</script>
<script type="text/javascript">
	var type = "seller";
	var imgArr = "$!{seller.imgArray}";
	var obj;
	var basepath = "http://image.mperfit.com/";
       KindEditor.ready(function(K){
		var editor1 = K.editor({
			cssPath : '$!path/css/admin_activity/prettify.css',
			uploadJson : 'image/upload?type='+type,
			fileManagerJson : 'kindeditor-master/jsp/file_manager_json.jsp',
			allowFileManager : true,
			allowImageRemote: false,
            urlType:'domain'
		 }); 
	
		 if(imgArr != null && imgArr != "" && imgArr != "null"){
			 var arr = imgArr.split(',');
			 for(var i=0;i<arr.length;i++){
			   obj = "<table class='ssi-imgToUploadTable ssi-pending'>"+
		             " <tr><td class='ssi-upImgTd'><img class='ssi-imgToUpload' src='"+arr[i]+"'/></td></tr>"+
		             " <tr lang='del'><td><button data-delete class='ssi-button error ssi-removeBtn'><span class='trash10 trash'></span></button></td></tr>" +
		             " </table>";
		       K("#moreImg").append(obj);
			}
			jQuery("tr[lang='del'] td button").on('click',function(){
			   $(this).parent('td').parent('tr[lang=del]').parent('tbody').parent('table.ssi-imgToUploadTable').remove();
		  	});
		}
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
	  var html;
	  K('#image1').click(function() {
			editor1.loadPlugin('image', function() {
                 editor1.plugin.imageDialog({
                    showRemote : false,
					imageUrl : K('#urlArray').val(),
					clickFn : function(url, title, width,height, border, align) {
						      K('#urlArray').val(url);
						      html = "<table class='ssi-imgToUploadTable ssi-pending'>"+
		                                 " <tr><td class='ssi-upImgTd'><img class='ssi-imgToUpload' src='"+url+"'/></td></tr>"+
		                                 " <tr lang='del'><td><button data-delete class='ssi-button error ssi-removeBtn'><span class='trash10 trash'></span></button></td></tr>" +
		                                 " </table>";
		                      K("#moreImg").append(html);
						      editor1.hideDialog();
				   removeTab : $("tr[lang='del'] td button").on('click',function(){
							$(this).parent('td').parent('tr[lang=del]').parent('tbody').parent('table.ssi-imgToUploadTable').remove();
						});
					}
				});
			});
		});
	});		
</script>
<!-- 修改密码  -->			
	<div class="modal fade" id="modal-1">
		<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><strong>修改密码</strong></h4>
			</div>
			<div class="wrapper-Box">
            	<label class="col-sm-2 control-label" >原密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="输入原密码" maxlength="20" id="oldpassword">
				</div>
				
				<label class="col-sm-2 control-label" >新密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" placeholder="输入新密码" maxlength="20" id ="newpassword">
				</div>
				<label class="col-sm-2 control-label" >新密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" placeholder="再输一次" maxlength="20" id ="newpassword2">
				</div>
		    </div>  
			<div class="modal-footer">
				<button type="button" class="btn btn-yellow" onclick="changepasswrodfx();">修改密码</button>
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</div>
		</div>
	</div>
	<div class="xenon-loading-bar"><span data-pct="0" style=""></span></div>
</body>
</html>
