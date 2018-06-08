#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>左侧导航</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="$!staticPrefix/admin/js/jquery.js"></script>
<script src="$!staticPrefix/admin/js/public.js"></script>
</head>
<body style="background:#313131">
<div class="menu-list">
 <a href="main.html" target="mainCont" class="block menu-list-title center" style="border:none;margin-bottom:8px;color:#fff;">起始页</a>
 <ul>
  <li class="menu-list-title">
   <span>课程管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="/course/list" title="课程列表" target="mainCont">课程列表</a></li>
   </ul>
  </li>
 
  <li class="menu-list-title">
   <span>健身房管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="/gym/list" title="健身房列表" target="mainCont">健身房列表</a></li>
   </ul>
  </li>
  
  <li class="menu-list-title">
   <span>教练管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="/coach/list" title="教练列表" target="mainCont">教练列表</a></li>
   </ul>
  </li>
  
  <li class="menu-list-title">
   <span>订单管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="/course/order/list" title="课程订单列表" target="mainCont">课程订单列表</a></li>
   </ul>
  </li>
  
 </ul>
</div>
</body>
</html>