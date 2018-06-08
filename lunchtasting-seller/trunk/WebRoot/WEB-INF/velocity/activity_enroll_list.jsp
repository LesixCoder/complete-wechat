#parse("base.jsp")
<html>
  <head>
    <title>活动管理</title>
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
	<script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>
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
				<!-- 导入菜单目录 -->
				<ul id="main-menu" class="main-menu">
				        #include("../../menu.jsp")
				</ul>
			</div>
		</div>
		
		<div class="main-content">
			<nav class="navbar user-info-navbar" role="navigation">
				<ul class="user-info-menu left-links list-inline list-unstyled">
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>
				</ul>
			</nav>
			<!-- 右侧目录显示  -->
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">活动报名列表</h1>
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
								<strong>活动报名列表</strong>
							</li>
						</ol>		
				</div>
			</div>
			<!-- 表格内容  -->
			<div class="panel panel-default">
				<div style="padding-left: 85px;">
					<form class="form-horizontal" id="agents-form">
						<div class="form-group" align="left">
						</div>
					</form>
				</div>
				<div class="panel-heading" style="padding-left: 17px;">
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
								<th style="font-size: 13px;">名字</th>
								<th style="font-size: 13px;">手机号</th>
								<th style="font-size: 13px;">年龄</th>
								<th style="font-size: 13px;">性别</th>
								<th style="font-size: 13px;">备注</th>
								<th style="font-size: 13px;">报名时间</th>
							</tr>
						</thead>
						<tbody class="middle-align"></tbody>
					</table>
				</div>
			</div>
			<!-- 尾部标识 -->
			<footer class="main-footer sticky footer-type-1">
				<div class="footer-inner">
					<div class="footer-text">
						&copy; 2016 
						<strong>Lunch Tasting</strong> 
					</div>
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
	<!-- JavaScripts initializations and stuff -->
	<script src="$!path/js/admin_activity/xenon-custom.js"></script>
	<script type="text/javascript">
		var activityList;
		jQuery(document).ready(function($){
			    activityList = jQuery("#example-2").dataTable({
				sAjaxSource: "queryActivityEnrollList",
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
								show_loading_bar(100);
							}else{
								alert("查询失败!");
							}
						},
						error: function(){
							alert("服务器连接超时!");
						}
					});
				},
				"fnRowCallback": function (nRow, aoData, iDisplayIndex) {
		        },
				aoColumns: [
					{"mDataProp": "name", "sClass": "thead-tr-th"},
					{"mDataProp": "phone", "sClass": "thead-tr-th"},
					{"mDataProp": "age", "sClass": "thead-tr-th"},
					{"mDataProp": "sex", "sClass": "thead-tr-th"},
					{"mDataProp": "remark", "sClass": "thead-tr-th"},
					{"mDataProp": "create_time", "sClass": "thead-tr-th"}
				],
				columnDefs: [
					{
					"targets": [3],
					"data": "sex", //数据列名
					"render": function(data, type, row,full) { //返回自定义内容
							var html = "";
							if(data==1){
								html="男";
							}else if(data==2){
								html+="女";
							}else{
								html+="未知";
							}
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
	</script>
</body>
</html>
