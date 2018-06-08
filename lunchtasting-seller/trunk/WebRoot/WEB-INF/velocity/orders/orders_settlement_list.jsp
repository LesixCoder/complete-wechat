#parse("base.jsp")
<html>
  <head>
    <title>结算管理</title>
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
	<link rel="stylesheet" href="$!path/css/admin_activity/daterangepicker.css">
	<script src="$!path/js/admin_activity/jquery-1.11.1.min.js"></script>
	<script src="$!path/js/admin_activity/jquerydaterangepicker/moment.min.js"></script>
	<script src="$!path/js/admin_activity/jquerydaterangepicker/jquery.daterangepicker.js"></script>

<!--     <link rel="stylesheet" href="$!path/css/admin_activity/datepicker.css"> -->
<!-- 	<script src="$!path/js/admin_activity/jquerydaterangepicker/datepicker.min.js"></script> -->
<!-- 	<script src="$!path/js/admin_activity/jquerydaterangepicker/datepicker.en.js"></script> -->

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
		#wrapper
	{
		width:800px;
		margin:0 auto;
		color:#333;
		font-family:Tahoma;
		line-height:1.5;
		font-size:14px;
	}
	.demo { margin:30px 0;}
	.date-picker-wrapper .month-wrapper table .day.lalala { background-color:orange; }
	.options { display:none; border-left:6px solid #8ae; padding:10px; font-size:12px; line-height:1.4; background-color:#eee; border-radius:4px;}
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
					<h1 class="title">结算管理</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="goToIndex"><i></i>Home</a><!-- class="fa-home" -->
						</li>
							<li class="active">
						
										<strong>结算管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<!-- Removing search and results count filter -->
			<div class="panel panel-default">
				<div id="hideDiv">
					<form class="form-horizontal">
						<div id="hideDiv">
							<div class="form-group">
							<label class="col-sm-2 control-label"
									for="restaurant.restaurantName">结算日期</label>
								<div class="col-sm-2">
									<div style="color: #cc3f44;width: 200px;">
										<div class="input-group">
												<input type="text" class="form-control" id="settDate1" size="30" style="width: 170px;" readonly="readonly" value="点击选择日期">
												<input type="text" class="form-control" id="settDate" size="30" style="width: 170px;" readonly="readonly">
<!--                              <div class="col-sm-2" style="color: #cc3f44;"> -->
<!-- 								<div class="input-group"> -->
<!-- 									<input type="text" class="form-control daterange" -->
<!-- 										id="settDate" name="settDate" readonly="readonly" -->
<!-- 										placeholder="选择日期" style="width: 170px;" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="panel-heading" style="padding-left: 17px;">
					<h3 class="panel-title">
					    <a href="javascript:selectSettlement();" class="btn btn-secondary btn-sm btn-icon icon-left" style="color:#ffffff;">查询</a>
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
					
				<script type="text/javascript">
					var settlementList;
					var ext;
					var sellerName;
					jQuery(document).ready(function($){
						    settlementList = jQuery("#example-2").dataTable({
							sAjaxSource: "getOrdersSettlementList",
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
							    if(aoData.beginTime != ""){
					                jQuery('td:eq(5)', nRow).html(aoData.beginTime);
							    }
								if(aoData.endTime != ""){
					                /* jQuery('td:eq(6)', nRow).html(format(parseInt(aoData.endTime))); */
					                jQuery('td:eq(6)', nRow).html(aoData.endTime);
								}
								if(aoData.playTime != "" && aoData.playTime != null && aoData.playTime != "null"){
					                jQuery('td:eq(7)', nRow).html(aoData.playTime);
								}
								if(aoData.remark == "" || aoData.remark=="null" || aoData.remark==null){
					                jQuery('td:eq(8)', nRow).html("无");
								}
					        },
							/* "fnServerParams" : function (aoData) {
								aoData.push(
								{ "name": "sellerId", "value": jQuery("#sellerId").val() },
								{ "name": "status", "value": jQuery("#status").val() }
								    );
							}, */
							aoColumns: [
								{"mDataProp": "id", "sClass": "thead-tr-th"},
								{"mDataProp": "pay_price", "sClass": "thead-tr-th"},
								{"mDataProp": "original_price","sClass": "thead-tr-th"},
								{"mDataProp": "play_price","sClass": "thead-tr-th"},
								{"mDataProp": "profit_price","sClass": "thead-tr-th"},
								{"mDataProp": "begin_time","sClass": "thead-tr-th"},
								{"mDataProp": "end_time","sClass": "thead-tr-th"},
								{"mDataProp": "play_time","sClass": "thead-tr-th"},
								{"mDataProp": "remark","sClass": "thead-tr-th"},
								{"mDataProp": "is_receipt","sClass": "thead-tr-th"},
								{"mDataProp": "status","sClass": "thead-tr-th"}
							],
							columnDefs: [
							{
								"targets": [0],
								"data": "newId", //数据列名
								"render": function(data, type, row,full) { //返回自定义内容
									var html = "";
									html += "<a href='#' class='btn btn-secondary btn-sm btn-icon icon-left'>暂无操作</a>";
								return html;
								}
							},
							{
								"targets": [9],
								"data": "isReceipt", //数据列名
								"render": function(data, type, row,full) { //返回自定义内容
								if(data == 0){
								   return "<span>否</span>";
								}else if(data == 1){
								   return "<span style='color:red;'>是</span>";
								}
							  }
							},
							{
								"targets": [10],
								"data": "status", //数据列名
								"render": function(data, type, row,full) { //返回自定义内容
								if(data == 1){
								   return "<span>商家未确认</span>";
								}else if(data == 2){
								   return "<span>商家已确认</span>";
								}else if(data == 3){
								   return "<span>平台已打款</span>";
								}
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
							sScrollX: "120%",
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
						
						jQuery("#settDate1").dateRangePicker({
						    format: 'YYYY-MM-DD',
						    separator: ' - ',
					        batchMode: 'week', 
	                        showShortcuts: false,
	                        startDate: false,
                            endDate: false,
                       /*      beforeShowDay: function(t){
								var valid = !(t.getDay() == 0 || t.getDay() == 6);  //disable saturday and sunday
								var _class = '';
								var _tooltip = valid ? '' : 'weekends are disabled';
							return [valid,_class,_tooltip];
						   } */
					    });
						
					});
					//模糊查询
				function selectSettlement(){
					settlementList.fnDraw();
				}
			    function add0(m){
	                            return m<10?'0'+m:m;
	                     }
				function format(shijianchuo){
					//shijianchuo是整数，否则要parseInt转换
					var time = new Date(shijianchuo);
					var y = time.getFullYear();
					var m = time.getMonth()+1;
					var d = time.getDate();
					var h = time.getHours();
					var mm = time.getMinutes();
					var s = time.getSeconds();
					return y+'-'+add0(m)+'-'+add0(d);
				}
					</script>
					
					<table class="table table-bordered table-striped " id="example-2" data-pattern="priority-columns">
							<thead>
								<tr>
									<th style="font-size: 13px;">操作</th>
									<th style="font-size: 13px;">实际收款</th>
									<th style="font-size: 13px;">原价总价</th>
									<th style="font-size: 13px;">实际商家打款</th>
									<th style="font-size: 13px;">盈利价格</th>
									<th style="font-size: 13px;">结算开始</th>
									<th style="font-size: 13px;">结算结束</th>
									<th style="font-size: 13px;">打款时间</th>
									<th style="font-size: 13px;">备注</th>
									<th style="font-size: 13px;">发票</th>
									<th style="font-size: 13px;">状态</th>
								</tr>
						</thead>
						<tbody class="middle-align">
						</tbody>
					</table>
					
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
						&copy; 2016 
						<strong>Perfit</strong> 
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
<!-- 	<script src="$!path/js/admin_activity/moment.min.js"></script> -->

	<!-- Imported scripts on this page -->
<!-- 	<script src="$!path/js/admin_activity/daterangepicker/daterangepicker.js"></script> -->
	
	<!-- Imported scripts on this page -->
	<script src="$!path/js/admin_activity/jquery-validate/jquery.validate.min.js"></script>

	<!-- JavaScripts initializations and stuff -->
	<script src="$!path/js/admin_activity/xenon-custom.js"></script>
	
	<!-- Modal 1 (Basic)-->
	<div class="xenon-loading-bar"><span data-pct="0" style=""></span></div>
</body>
</html>
