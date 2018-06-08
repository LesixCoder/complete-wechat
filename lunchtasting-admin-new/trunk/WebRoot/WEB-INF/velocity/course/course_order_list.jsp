#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="$!staticPrefix/admin/js/jquery.js"></script>
<script src="$!staticPrefix/admin/js/public.js"></script>
</head>
<body>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="user"></i><em>课程订单列表</em></span>
  </div>
  <div class="operate">
   <!--  
   <form>
    <select class="inline-select">
     <option>选择会员等级</option>
     <option>白金会员</option>
     <option>黄金会员</option>
    </select>
    <input type="text" class="textBox length-long" placeholder="输入教练姓名"/>
    <input type="button" value="查询" class="tdBtn"/>
   </form>-->
  </div>
  <table class="list-style Interlaced">
   <tr>
     <th width="14%">编号</th>
     <th>课程名</th>
     <th>套餐名</th>
     <th>场馆名</th>
     <th>用户名</th>
     <th>性别</th>
     <th>电话</th>
     <th>费用</th>
     <th>开课时间</th>
     <th>下单时间</th>
     <th>状态</th>
     <th>操作</th>
   </tr>
   
   #foreach($bean in $list)
   <tr>
    <td>
     <input type="checkbox"/>
     <span class="middle">$!bean.order_id</span>
    </td>
    <td class="center">$!bean.course_name</td>
    <td class="center">$!bean.meal_name</td>
    <td class="center">$!bean.gym_name</td>
    <td class="center">$!bean.user_name</td>
    <td class="center">#if("$!bean.sex" == "1")男  #elseif("$!bean.sex" == "2") 女 #else 无 #end</td>
    <td class="center">$!bean.phone</td>
    <td class="center">$!bean.pay_price</td>
    <td class="center">$!bean.meal_str_time</td>
    <td class="center">$!bean.create_time</td>
    <td class="center">
    	#if("$!bean.status" == "2") 
    	已付款
    	#elseif("$!bean.status" == "3") 
    	已退款
    	#elseif("$!bean.status" == "4") 
    	进行中
    	#elseif("$!bean.status" == "5") 
    	已完成
    	#end
    </td>
    <td class="center">
     <!-- 
     <a href="/coach/edit?coach_id=$!bean.id" class="inline-block" title="编辑"><img src="$!staticPrefix/admin/images/icon_edit.gif"/></a>
     <a href="account.html" class="inline-block" title="资金管理"><img src="$!staticPrefix/admin/images/icon_account.gif"/></a>
     <a class="inline-block" title="删除"><img src="$!staticPrefix/admin/images/icon_drop.gif"/></a> -->
    </td>
   </tr>
   #end
  
  </table>
  <!-- BatchOperation -->
  <div style="overflow:hidden;">
      <!-- Operation 
	  <div class="BatchOperation fl">
	   <input type="checkbox" id="del"/>
	   <label for="del" class="btnStyle middle">全选</label>
	   <input type="button" value="批量删除" class="btnStyle"/>
	  </div>-->
	  <!-- turn page -->
	  <div class="turnPage center fr">
	   <a>第一页</a>
	   <a>1</a>
	   <a>最后一页</a>
	  </div>
  </div>
 </div>
</body>
</html>