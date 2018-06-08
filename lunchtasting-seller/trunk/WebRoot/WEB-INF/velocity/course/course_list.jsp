#parse("base.jsp")
<html>
  <head>
    <title>课程管理</title>
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
	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/bootstrap.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-core.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-forms.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-components.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/xenon-skins.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/custom.css">
	<link rel="stylesheet" href="$!path/css/admin_activity/style.css" />
	<script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>	
		<script src="System/assets/js/html5shiv.min.js"></script>
		<script src="System/assets/js/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.thead-tr-th{
			border-left-width: 0;
			position: relative;
			border-bottom: 1px solid #eeeeee;
			color: #979898;
			padding: 12px 15px;
			vertical-align: bottom;
			text-align: left;
			font-family: "Arimo", "Helvetica Neue", Helvetica, Arial, sans-serif;
			font-size: 13px;
		}
		a:hover{color:#f00;}
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
						<a href="#" data-toggle="sidebar"><i class="fa-bars"></i><i class="fa-bars"></i>
					</li>
				</ul>
			</nav>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">课程管理</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<li><a href="javascript:void(0);"><i class="fa-home"></i>Home</a></li>
						<li><a href="javascript:void(0);">Dashboard</a></li>
						<li class="active"><strong>课程管理</strong></li>
					</ol>
				</div>
			</div>
			<!-- Removing search and results count filter -->
			<div class="panel panel-default">
				<div style="padding-left: 85px;">
				<form class="form-horizontal" id="agents-form">
					<div class="form-group" align="left">
		<!-- 				<label class="col-sm-2 control-label" for="activityDate">报名日期</label>
						<div class="col-sm-2" style="color: #cc3f44;">
							<div class="input-group">
								<input type="text" class="form-control daterange" id="date" readonly="readonly" placeholder="选择日期" style="width: 170px;" />
							</div>
						</div> -->
						<label class="col-sm-2 control-label"
							for="restaurant.restaurantName">课程名称</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="name"
								style="width: 173px;">
						</div>
						<label class="col-sm-2 control-label">活动状态</label>
						<div class="col-sm-2">
							<select class="form-control" id="flag" style="width:173px;">
								<option value="">全部</option>
								<option value="0">已上线</option>
								<option value="1">已下线</option>
							</select>
						</div>
					</div>
				</form>
				</div>
				<div class="panel-heading" style="padding-left: 17px;">
					<h3 class="panel-title">
					    <a href="javascript:selectActivity();" class="btn btn-secondary btn-sm btn-icon icon-left" style="color:#ffffff;">查询</a>
						<a href="toSaveCourse" class="btn btn-info btn-sm btn-icon icon-left" style="color:#ffffff;">添加</a>
					</h3>
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
					<table class="table table-bordered table-striped " id="example-2" data-pattern="priority-columns">
						<thead>
							<tr>
								<th style="font-size: 13px;">操作</th>
								<th style="font-size: 13px;">课程名称</th>
								<th style="font-size: 13px;">课程内容</th>
								<th style="font-size: 13px;">价格</th>
								<th style="font-size: 13px;">对应订单</th>
							</tr>
						</thead>
						<!-- 分页 -->
						<tbody class="middle-align"></tbody>
					</table>
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
	<div class="xenon-loading-bar"><span data-pct="0" style=""></span></div>
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
	
	<script type="text/javascript">
	var courseList
	jQuery(document).ready(function($){
		    activityList = jQuery("#example-2").dataTable({
			sAjaxSource: "getCourseList",
			bServerSide: true,
			fnServerData: function (sSource, aoData, fnCallback ) {
				show_loading_bar(0);
				jQuery.ajax({
					dataType: 'json',	
					type: "POST",
					url: sSource,
					data: {
						aoData: JSON.stringify(aoData)
					},
					success: function(_data){
						if(_data.result == 0){
							fnCallback(_data);
							//alert(JSON.stringify(_data));
							show_loading_bar(100);
						}else{
							alert(_data.message);
						}
					},
					error: function(){
						alert("服务器连接超时!");
					}
				});
			},
			"fnRowCallback": function (nRow, aoData, iDisplayIndex) {
				jQuery('td:eq(2)', nRow).html(huanghang(aoData.content,20,100));
				jQuery('td:eq(1)', nRow).html(huanghang(aoData.name,20,100))
	        },
	        "fnServerParams" : function (aoData) {
				aoData.push(
				{ "name": "date", "value": ""},
				{ "name": "name", "value": jQuery("#name").val() },
				{ "name": "flag", "value": jQuery("#flag").val() }
				 );
			},
			aoColumns: [
				{"mDataProp": "id", "sClass": "thead-tr-th"},
				{"mDataProp": "name", "sClass": "thead-tr-th"},
				{"mDataProp": "content","sClass": "thead-tr-th"},
				{"mDataProp": "price","sClass": "thead-tr-th"},
				{"mDataProp": "id","sClass": "thead-tr-th"}
			],
			columnDefs: [{
				"targets": [0],
				"data": "id", //数据列名
				"render": function(data, type, row,full) { //返回自定义内容
						var html = "";
						html += "<a href='toSaveCourse?id="+data+"' class='btn btn-secondary btn-sm btn-icon icon-left'>修改</a><br>";
						html += "<a href='javascript:void(0);' onclick='deleteActivity(this);' id="+data+" class='btn btn-danger btn-sm btn-icon icon-left'>删除</a><br>";
						if(row.flag == 0){
						html += "<a href='javascript:void(0);' onclick='cancel(this);' id="+data+" class='btn btn-info btn-sm btn-icon icon-left'>撤销</a>";
						}else{
						html += "<a href='javascript:void(0);' onclick='publish(this);' id="+data+" class='btn btn-info btn-sm btn-icon icon-left'>发布</a><br>";
						}
				return html;
				}
			},
			{
				"targets": [4],
				"data": "id", //数据列名
				"render": function(data, type, row,full) { //返回自定义内容
					var html = "";
					html += "<a href='goOrdersList?courseId="+data+"' class='btn btn-secondary btn-sm btn-icon icon-left'>查看</a>";
				return html;
				}
			}
			],
			bAutoWidth: false,
			bSort: false,
			bStateSave: true,
			dom: "t" + "<'row'<'col-xs-4'i><'col-xs-8'p>>",
			bProcessing: true,
			bPaginate: true,
			sPaginationType: "full_numbers",
			sScrollX: "100%",
			oLanguage: {
				sLengthMenu: "每页显示 _MENU_ 条记录",
				sInfo: "",
				sInfoFiltered: "(从 _MAX_ 条数据中检索)",
				sInfoEmpty: "没有数据",
				sZeroRecords: "没有检索到数据",
				sProcessing: "正在分页加载...",
				oPaginate: {
					sFirst: "首页",
					sPrevious: "前一页",
					sNext: "后一页",
					sLast: "尾页"
				},
			}
		});
	});
	    //删除
		function deleteActivity(e){
			var id = e.id;
		    var data = {
			    id  : id
		    };
			if(confirm("确定要删除?")){
				jQuery.ajax({
				    url:'deleteCourse',
				    data: data,
				    type:'post',
				    dataType:'json',
				    success:function(data){
				    if(data =="success"){
				        activityList.fnPageChange(parseInt($(".pagination").find("li[class='paginate_button active'] a").html())-1);
				        activityList.fnReloadAjax();
				    }else{
				        alert("删除失败!");
				       }
				    },
				     error : function() {
				          alert("服务器连接失败！");    
				     }    
				});
			}
		}
		//发布
		function publish(e){
			var id = e.id;
		    var data = {
			    id  : id,
			    type:"pub"
		    };
			if(confirm("确定要发布?")){
				jQuery.ajax({
				    url:'pubCourse',
				    data: data,
				    type:'post',
				    dataType:'json',
				    success:function(data){
				    if(data == "success"){
				        alert("发布成功!");
				        activityList.fnPageChange(parseInt($(".pagination").find("li[class='paginate_button active'] a").html())-1);
				        activityList.fnReloadAjax();
				    }else{
				        alert("发布失败!");
				       }
				    },
				     error : function() {
				          alert("服务器连接失败！");    
				     }    
				});
			}
		}
		//撤销
		function cancel(e){
			var id= e.id;
		    var data = {
			    id  : id,
			    type:"cel"
		    };
			if(confirm("确定要撤销?")){
				jQuery.ajax({
				    url:'pubCourse',
				    data: data,
				    type:'post',
				    dataType:'json',
				    success:function(data){
				    if(data.flag = "success"){
				        alert("撤销成功!");
				        activityList.fnPageChange(parseInt($(".pagination").find("li[class='paginate_button active'] a").html())-1);
				        activityList.fnReloadAjax();
				    }else{
				        alert("撤销失败!");
				       }
				    },
				     error : function() {
				          alert("服务器连接失败！");    
				     }    
				});
			}
		}
		//模糊查询
		function selectActivity(){
			activityList.fnDraw();
		}
		//换行  ,20,100 = 5  huanghang(,20,103)
		function huanghang(e,c,b){
			var html = "";
			if(e.length<=c){
				return e;
			}
			if(e.length>b){
				e=e.substr(0,b-3)+"...";
			}
           	while(e.length>c){
				html=html+e.substr(0,c)+"<br>";
				e=e.substr(c,e.length); 	            	
           	}
           	html+=e;
           	return html;
		}
		
		function getLocalTime(date) {     
			   return new Date(parseInt(date)).toLocaleString().replace(/:d{1,2}$/,' ');     
		}
	</script>
</body>
</html>
