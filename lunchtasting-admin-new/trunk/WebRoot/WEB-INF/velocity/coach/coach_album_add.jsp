#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>课程添加</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
  <link href="$!staticPrefix/admin/css/adminCm.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div class="wrap">
    <div class="page-title">
      <span class="modular fl">
        <i class="add"></i>
        <em>教练相册添加-$!coach.name</em>
      </span>
      <span class="modular fr">
        <a href="/coach/album/list?coach_id=$!coach.id" class="pt-link-btn">教练相册列表</a>
      </span>
    </div>
    <table class="list-style">
      <tr>
        <td style="text-align:right;width:20%;">$!coach.name</td>
        <input type="hidden" id="js-coachId" value="$!coach.id" />
      </tr>
      <tr>
        <td style="text-align:right;">
          <i class="color-red">*</i> 图片：</td>
        <td>
          <iframe name="iframe-coach_photos" style="display: none;"></iframe>
          <form method="post" id="coach_form_photos" action="/image/upload" enctype="multipart/form-data" target="iframe-coach_photos">
            <input type="hidden" name="type" id="type" value="4" />
            <input name="fileName" type="file" id="coach_photos_file" class="hide" accept="image/*" />
            <label for="coach_photos_file" class="labelBtn2">点击上传</label>
          </form>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"></td>
        <td class="coach_photos_img"></td>
      </tr>

      <tr>
        <td style="text-align:right;"></td>
        <td>
          <input id="coach_pub_btn" type="submit" value="确认发布" class="tdBtn" />
          <a href="/course/list">
            <input type="button" value="取消" class="tdBtn" />
          </a>
        </td>
      </tr>
    </table>
    </form>
  </div>
  <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script src="$!staticPrefix/admin/js/coach/coach_album_add.js"></script>
  <script>
    function setImage(imgName, imgUrl, type, code, msg) {
      if (code == 100) {
        //逻辑处理
        if (type == 4) {
          var cover_html = "<img data-name='" + imgName + "' src='" + imgUrl + "' class='mlr5' width='100' />";
          $('.coach_photos_img').children().remove().end().append(cover_html);
        }
      } else {
        alert(msg);
      }
    }
  </script>
</body>

</html>