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
    <span class="modular fl"><i class="add"></i><em>课程健身房关联添加</em></span>
    <span class="modular fr"><a href="/course/gym/list" class="pt-link-btn">课程健身房关联列表</a></span>
  </div>
  <table class="list-style">
  <tr>
    <td style="text-align:right;width:20%;">$!course.name</td>
    <input type="hidden" id="js-courseId" value="$!course.id"/>
   </tr>
   <tr>
    <td style="text-align:right;">健身房：</td>
    <td>
     <select id="js-gymId" name="js-gymId" class="textBox">
      #foreach($bean in $gymList)
      <option value="$!bean.id">$!bean.name</option>
      #end
     </select>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <td>
    <input id="js-course-gym-add" type="button" value="确认关联" class="tdBtn"/>
    <a href="/course/gym/list?course_id=$!course.id"><input type="button" value="取消" class="tdBtn"/></a>
    </td>
   </tr>
  </table>
  </form>
 </div>
 
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="$!staticPrefix/admin/js/course/course_gym.js"></script>
</body>
</html>