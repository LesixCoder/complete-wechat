#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>教练添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="add"></i><em>课程套餐添加  课程：$!course.name</em></span>
    <span class="modular fr"><a href="/course/meal/list?course_id=$!course.id" class="pt-link-btn">课程套餐列表</a></span>
  </div>
  <table class="list-style">
   <tr>
    <td style="text-align:right;width:8%;">名称：</td>
    <td><input id="gymName" name="gymName" type="text" class="textBox" /></td>
   </tr>
   <tr>
    <td style="text-align:right;">健身房：</td>
    <td>
     <select id="gymId" name="gymId" class="textBox">
      #foreach($bean in $gymList)
      <option value="$!bean.id">$!bean.name</option>
      #end
     </select>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">教练：</td>
    <td>
     <select id="coachId" name="coachId" class="textBox">
      #foreach($bean in $coachList)
      <option value="$!bean.id">$!bean.name</option>
      #end
     </select>
    </td>
   </tr>
   
   <tr>
    <td style="text-align:right;">价格：</td>
    <td>
    	<input id="price" name="price" type="text" class="textBox" />
    	<span>数字</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">市场价：</td>
    <td>
    	<input id="marketPrice" name="marketPrice" type="text" class="textBox" />
    	<span>数字</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">上课人数：</td>
    <td>
    	<input id="peopleNumber" name="peopleNumber" type="text" class="textBox" />
    	<span>数字</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">课数：</td>
    <td>
    	<input id="courseNumber" name="courseNumber" type="text" class="textBox" />
    	<span>数字</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">类型：</td>
    <td>
     <select id="type" name="type" class="textBox">
      <option value="1">普通课（1节）</option>
      <option value="2">训练营（多节）</option>
     </select>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">开始日期：</td>
    <td>
    	<input id="calendarStar" name="calendarStar" type="text" class="textBox" />
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">结束日期：</td>
    <td>
    	<input id="calendarEnd" name="calendarEnd" type="text" class="textBox" />
    </td>
   </tr>
   
   <tr>
    <td style="text-align:right;"></td>
    <td>
     <input type="submit" value="确认发布" class="tdBtn"/>
     <a href="/course/meeal/list?course_id=$!course.id"><input type="button" value="取消" class="tdBtn"/></a>
    </td>
   </tr>
  </table>
  </form>
 </div>
 
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="$!staticPrefix/admin/js/calendar.js"></script>
<script src="$!staticPrefix/admin/js/course/gym_meal_add.js"></script>
  
</body>
</html>