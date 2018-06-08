#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
<link href="$!staticPrefix/admin/css/adminCm.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="user"></i><em>教练列表</em></span>
    <span class="modular fr"><a href="/coach/add" class="pt-link-btn">+添加新教练</a></span>
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
     <th>编号</th>
     <th width="10%">名称</th>
     <th>图片</th>
     <th>性别</th>
     <th>年龄</th>
     <th>电话</th>
     <th>证书</th>
     <th>执教年限</th>
     <th>相册数</th>
     <th>状态</th>
     <th>操作</th>
   </tr>
   
   #foreach($bean in $list)
   <tr>
    <td>
     <input type="checkbox"/>
     <span class="middle">$!bean.id</span>
    </td>
    <td class="center">$!bean.name</td>
    <td class="center"><img src="$!imgPrefix$!bean.img_url" height="50" /></td>
    <td class="center">#if("$!bean.sex" == "1")男 #else 女 #end</td>
    <td class="center">$!bean.age</td>
    <td class="center">$!bean.phone</td>
    <td class="center">$!bean.certificate</td>
    <td class="center">$!bean.coach_year</td>
    <td class="center">
    	<a href="/coach/album/list?coach_id=$!bean.id" style="color:#000;text-decoration:underline;">
    	$!bean.album_count
    	</a>
    </td>
    <td class="center centerStatus">#if("$!bean.flag" == "0")上线 #else 下线 #end</td>
    <td class="center handle">
     <input type="hidden" class="flag" value="0">
     <a href="/coach/edit?coach_id=$!bean.id" class="">编辑</a>
     <a href="javascript:;" class="lineBtn btn-Success" data-id='2' data-str=''>上线</a>
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
 <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="$!staticPrefix/admin/js/coach/coach_list.js"></script>
</body>
</html>