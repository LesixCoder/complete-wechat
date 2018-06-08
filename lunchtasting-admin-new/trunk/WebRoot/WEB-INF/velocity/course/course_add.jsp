#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>课程添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="add"></i><em>课程添加</em></span>
    <span class="modular fr"><a href="/course/list" class="pt-link-btn">课程列表</a></span>
  </div>
  <table class="list-style">
   <tr>
    <td style="text-align:right;width:8%;">名称：</td>
    <td><input id="name" name="name" type="text" class="textBox" /></td>
   </tr>
   <tr>
    <td style="text-align:right;width:8%;">类型：</td>
    <td><input id="mold" name="mold" type="text" class="textBox length-long" /></td>
   </tr>
   <tr>
    <td style="text-align:right;width:8%;">特征：</td>
    <td><textarea id="characteristic" name="characteristic" style="width: 400px;height: 100px"></textarea></td>
   </tr>
   <tr>
    <td style="text-align:right;width:8%;">标签：</td>
    <td>
    <input id="tag" name="tag" type="text" class="textBox length-long" />
	<span>英文逗号分隔 ',' 例：帅气,漂亮</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;width:8%;">亮点：</td>
    <td><textarea id="highlight" name="highlight" style="width: 400px;height: 100px"></textarea></td>
   </tr>
   <tr>
    <td style="text-align:right;width:8%;">流程：</td>
    <td>
    	<textarea id="process" name="process" style="width: 600px;height: 150px"></textarea>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">头图：</td>
    <td>
     <input type="file" multiple="multiple" id="chanpinzhutu" class="hide"/>
     <label for="chanpinzhutu" class="labelBtn2">点击上传</label>
    </td>
   </tr>
     <tr>
    <td style="text-align:right;"></td>
    <td>
     <img src="#" width="80" height="80" id="imgUrl" name="imgUrl"/>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">(最多3张)图片：</td>
    <td>
     <input type="file" id="suoluetu" class="hide"/>
     <label for="suoluetu" class="labelBtnAdd">+</label>
     <img src="#" width="80" height="80" class="mlr5"/>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <td>
    	<input type="submit" value="确认发布" class="tdBtn"/>
    	<a href="/course/list"><input type="button" value="取消" class="tdBtn"/></a>
    </td>
   </tr>
  </table>
  </form>
 </div>
</body>
</html>