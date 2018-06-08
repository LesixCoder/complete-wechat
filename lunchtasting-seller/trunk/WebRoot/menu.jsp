<html>
  <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
  <body>
		<li class="opened">
			<a href="javascript:void(0);">
				
				<span class="title" >基本管理</span>
			</a>
			<ul>
				<li ${param.menuId==1.1 || param.menuId==null ? "class='active'" : ""}>
					<a href="goSeller?menuId=1.1">
						<span class="title">商家信息</span>
					</a>
				</li>
				<li ${param.menuId==1.2 ? "class='active'" : ""}>
					<a href="goActivityList?menuId=1.2">
						<span class="title">活动管理</span>
					</a>
				</li>
				<li ${param.menuId==1.3 ? "class='active'" : ""}>
					<a href="toCourseList?menuId=1.3">
						<span class="title">课程管理</span>
					</a>
				</li>
				
	<!-- 			<li>
					<a href="goArticleList">
						<span class="title">文章管理</span>
					</a>
				</li> -->
			</ul>
		</li>
		<li class="opened">
		<a href="javascript:void(0);">
			
			<span  class='active'>订单管理</span>
		</a>
		<ul>
			<li ${param.menuId==3.1 ? "class='active'" : ""}>
				<a href="goOrdersList">
					<span class="title">订单查询</span>
				</a>
			</li>
		</ul>
		<ul>
			<li ${param.menuId==3.2 ? "class='active'" : ""}>
				<a href="goOrdersSettlementList">
					<span class="title">结算查询</span>
				</a>
			</li>
		</ul>
		</li>
		<li class="opened">
			<a href="javascript:void(0);">
				
				<span  class='active'>消费验证</span>
			</a>
			<ul>
				<li ${param.menuId==3.1 ? "class='active'" : ""}>
					<a href="goOpenCode" target="view_window">
						<span class="title">消费验证</span>
					</a>
				</li>
			</ul>
		</li>
  </body>
</html>
