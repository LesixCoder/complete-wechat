#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>健身房编辑</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="$!staticPrefix/admin/css/adminStyle.css" rel="stylesheet" type="text/css" />
  <link href="$!staticPrefix/admin/css/adminCm.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div class="wrap">
    <div class="page-title">
      <span class="modular fl">
        <i class="add"></i>
        <em>健身房编辑</em>
      </span>
      <span class="modular fr">
        <a href="/gym/list" class="pt-link-btn">健身房列表</a>
      </span>
    </div>
    <table class="list-style">
      <tr>
        <td style="text-align:right"><i class="color-red">*</i> 名称：</td>
        <td>
          <input type="text" class="textBox length-long" id="gym_name" value="$!gym.name" required />
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"><i class="color-red">*</i> 电话：</td>
        <td>
          <input type="text" class="textBox length-long" id="gym_phone" value="$!gym.phone" pattern="^([0-9])(,([0-9]))*$" required />
          <span>多个","号分隔（8888,777）</span>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;width:15%;"><i class="color-red">*</i> 地址：</td>
        <td>
          <input type="text" class="textBox length-long" id="gym_address" value="$!gym.address" required />
        </td>
      </tr>
      <tr>
        <td style="text-align:right;width:15%;"><i class="color-red">*</i> 缩写地址：</td>
        <td>
          <input type="text" class="textBox" id="gym_simple_address" value="$!gym.simple_address" required />
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"><i class="color-red">*</i> 简介：</td>
        <td>
          <textarea style="width: 500px;height: 100px" id="gym_introduce" required>$!gym.introduce</textarea>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"><i class="color-red">*</i> 封面：</td>
        <td>
          <iframe name="iframe-gym_cover" style="display: none;"></iframe>
          <form method="post" id="gym_form_cover" action="/image/upload" enctype="multipart/form-data" target="iframe-gym_cover">
            <input type="hidden" name="type" id="type" value="1" />
            <input name="fileName" type="file" id="gym_cover_file" class="hide" accept="image/*" />
            <label for="gym_cover_file" class="labelBtn2">点击上传</label>
            <!-- <input type="submit" value="点击上传" class="labelBtn2 disabled" id="gym_cover_upload" /> -->
          </form>
          <div id="cover_maskLayer" style="display:none;">
            <p>图片正在上传中...</p>
          </div>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"></td>
        <td class="gym_cover_img">
          <img src="$!gym.img_url" alt="" data-name="$!gym.img_name"/>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"><i class="color-red">*</i> 场馆图片：</td>
        <td class="gym-photos-td">
          <iframe name="iframe-gym_photos" style="display: none;"></iframe>
          <form method="post" id="gym_form_photos" action="/image/upload" enctype="multipart/form-data" target="iframe-gym_photos">
            <input type="hidden" name="type" id="type" value="2" />
            <input name="fileName" type="file" id="gym_photos_file" class="hide" accept="image/*" />
            <label for="gym_photos_file" class="labelBtnAdd" id="gym_photos_upload">+</label> 
          </form>
          <div class="gym_photos_box">
          #foreach($img in $gym.img_list)
          <div data-name="$!img.img_name">
            <img src="$!img.img_url" height="100" class="mlr5" js-imgName="$!img.img_name"/>
            <i class="fa fa-times-circle-o"></i>
          </div>
          #end
          </div>
        </td>
      </tr>
      <tr>
        <td style="text-align:right;"></td>
        <td>
          <input id="gym_pub_btn" type="submit" value="确认发布" class="tdBtn" />
          <a href="/gym/list" class="tdBtn" />取消发布</a>
        </td>
      </tr>
    </table>
  </div>
  <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=c2309cd978ac70749966f7c47ae6ce81"></script>
  <script src="$!staticPrefix/admin/js/gym/gym_add-001.js"></script>
  <script>
    function setImage(imgName, imgUrl, type, code, msg) {
      if (code == 100) {
        //逻辑处理
        if (type == 1) {
          var cover_html = "<img data-name='" + imgName + "' src='" + imgUrl + "' class='mlr5' height='100' />";
          jQuery('.gym_cover_img').children().remove();
          jQuery('.gym_cover_img').append(cover_html);
        }
        if (type == 2) {
          var photos_html = "<div data-name='" + imgName + "'>"+
            "<img src='" + imgUrl + "' class='mlr5' height='100' />"+
            "<i class='fa fa-times-circle-o'></i>"+
          "</div>";
          jQuery('.gym_photos_box').append(photos_html);
        }
      } else {
        alert(msg);
      }
    }
  </script>
</body>

</html>