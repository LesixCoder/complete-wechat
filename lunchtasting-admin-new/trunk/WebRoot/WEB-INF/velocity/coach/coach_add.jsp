#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>教练添加</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
  <link href="$!staticPrefix/admin/css/adminCm.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div class="wrap">
    <div class="page-title">
      <span class="modular fl">
        <i class="add"></i>
        <em>教练添加</em>
      </span>
      <span class="modular fr">
        <a href="/coach/list" class="pt-link-btn">教练列表</a>
      </span>
    </div>
    <table class="list-style">
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 教练名称：</td>
        <td>
          <input id="coach_name" type="text" class="textBox" required />
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 健身房：</td>
        <td>
          <select id="coach_gymName" class="textBox" required>
            <option selected="selected" value="0">无</option>
            #foreach($bean in $gymList)
            <option value="$!bean.id">$!bean.name</option>
            #end
          </select>
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 性别：</td>
        <td>
          <select id="coach_gender" class="textBox" required>
            <option selected="selected" value="1">男</option>
            <option value="2">女</option>
          </select>
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 手机号：</td>
        <td>
          <input id="coach_phone" type="tel" class="textBox" required pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"
          />
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 年龄：</td>
        <td>
          <input id="coach_age" type="number" class="textBox length-short" min="0" required pattern="^\d+$" />
          <span>（数字）</span>
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 生日：</td>
        <td>
          <input id="coach_birth" type="text" class="textBox" required pattern="^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$"
          />
          <span>格式（1987-08-09）</span>
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 执教年限：</td>
        <td>
          <input id="coach_year" type="number" class="textBox length-short" min="0" required pattern="^\d+$" />
          <span>（数字）</span>
        </td>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 证书：</td>
        <textarea id="coach_cert" style="width: 400px;height: 150px" required></textarea>
      </tr>
      <tr>
        <td style="text-align:center;">
          <i class="color-red">*</i> 头像：</td>
        <td>
          <iframe name="iframe-coach_cover" style="display: none;"></iframe>
          <form method="post" id="coach_form_cover" action="/image/upload" enctype="multipart/form-data" target="iframe-coach_cover">
            <input type="hidden" name="type" id="type" value="3" />
            <input name="fileName" type="file" id="coach_cover_file" class="hide" accept="image/*" />
            <label for="coach_cover_file" class="labelBtn2">点击上传</label>
            <!-- <input type="submit" value="点击上传" class="labelBtn2 disabled" id="gym_cover_upload" /> -->
          </form>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"></td>
        <td class="coach_cover_img"></td>
      </tr>
      <tr>
        <td style="text-align:right;"></td>
        <td>
          <input id="coach_pub_btn" type="submit" value="确认发布" class="tdBtn" />
          <a href="/coach/list">
            <input type="button" value="取消" class="tdBtn" />
          </a>
        </td>
      </tr>
    </table>
    </form>
  </div>
  <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script src="$!staticPrefix/admin/js/coach/coach_add.js"></script>
  <script>
    function setImage(imgName, imgUrl, type, code, msg) {
      if (code == 100) {
        //逻辑处理
        if (type == 3) {
          var cover_html = "<img data-name='" + imgName + "' src='" + imgUrl + "' class='mlr5' width='100' />";
          $('.coach_cover_img').children().remove().end().append(cover_html);
        }
      } else {
        alert(msg);
      }
    }
  </script>
</body>

</html>