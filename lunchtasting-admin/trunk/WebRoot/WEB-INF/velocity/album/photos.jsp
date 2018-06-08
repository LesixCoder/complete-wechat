#parse("base.jsp")
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>PERFIT-相册</title>
    <link rel="shortcut icon" href="images/title_icon.png" type="image/x-icon">
    <link rel="icon" href="$!path/js/admin/title_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="$!path/js/admin/bootstrap.min.css" />
    <link rel="stylesheet" href="$!path/js/admin/chromagallery.min.css" />
    <link rel="stylesheet" href="$!path/js/admin/material-scrolltop.css" />
    <link rel="stylesheet" href="$!path/js/admin/photo.css" />
<!--     <link rel="stylesheet" href="http://static.lunchtasting.com/js/admin/photo.css" /> -->
</head>

<body style="position:relative;">
    <!--导航-->
    <nav class="navbar navbar-default navbar-fixed-top selfNav">
        <div class="container fixW">
            <div class="navbar-header">
                <button class="navbar-toggle" data-toggle="collapse" data-target="#myNav">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="index.html" class="navbar-brand"><img src="http://static.lunchtasting.com/js/admin/logo.png" width="200" height="50" alt=""></a>
            </div>
            <div class="collapse navbar-collapse" id="myNav">
                <ul class="nav navbar-nav navbar-right" role="tablist" id="menu">
                    <li>
                        <select class="form-control" id="photoList" onchange="queryAlbum();">
                        #foreach ($select in $selectList)
                        <option value="$!{select.newId}" selected='selected'>$!{select.bizName}</option>
                        #end
                        </select>
                    </li>
                    <li data-menuanchor="page1"><a href="http://www.mperfit.com">返回首页</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div>
        <!--第一屏-->
        <section class="section" id="pageOne">
            <img src="" alt="" id="imgUrl">
        </section>
        <!--第二屏-->
        <section class="section" id="pageTwo">
            <div class="fixW fixW1">
<!--                 <div class="chroma-gallery mygallery" id="imgArray"> -->
<!--                 </div> -->
            </div>
        </section>
    </div>
    <div class="longB"></div>
    <footer class="footer">
        <!--第三屏-->
        <section class="section" id="pageThree">
            <!--页脚-->
            <div class="container fixW">
                <div class="pageThree-footer clearfix">
                    <div class="footer-left pull-left">
                        <a href="http://www.mperfit.com">回到首页</a>
                    </div>
                    <div class="footer-right pull-right">
                        <a class="email" href="javascript:void(0)"></a>
                        <a class="weibo" href="http://weibo.com/u/6000286078"></a>
                    </div>

                </div>
            </div>
            <div class="pageThree-bottom">
                <p>Copyright &copy; 2016 京ICP备15042729号 北京稼优佳文化传媒有限公司 版权所有</p>
            </div>
        </section>
    </footer>
    <button class="material-scrolltop" type="button"></button>
</body>
<script src="$!path/js/admin/jquery-1.12.0.min.js"></script>
<script src="$!path/js/admin/bootstrap.min.js"></script>
<script src="$!path/js/admin/material-scrolltop.js"></script>
<script src="$!path/js/admin/chromagallery.pkgd.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('body').materialScrollTop({
            duration: 400
        });
//         getSelect();
       queryAlbum();
    });
    	 //相册
         function queryAlbum(){
		    var id = jQuery("#photoList").val();
				jQuery.ajax({
				    url:'match/byId?id=' + id,
				    data: null,
				    type:'POST',
				    dataType:'JSON',
				    async: true,
				    success:function(data){
				    jQuery(".fixW1").empty();
// 				    jQuery("#imgArray").addClass("chroma-gallery mygallery");
				    var arr;
				    for(var i=0;i<data.length;i++){
				         var obj = data[i];
				         arr = obj.imgArray.split(",");
				         jQuery("#imgUrl").attr("src",obj.baseUrl+obj.imgUrl);
				         for(var i=0;i<arr.length;i++){
// 				            jQuery("#imgArray").append("<img src='"+obj.baseUrl+arr[i]+"' alt='' data-largesrc='"+obj.baseUrl+arr[i]+"' style='width: 300px;height: 300px;'>"); 
				            jQuery(".fixW1").append("<div class='chroma-gallery mygallery' id='imgArray'><img src='"+obj.baseUrl+arr[i]+"' alt='' data-largesrc='"+obj.baseUrl+arr[i]+"'></div>");
				            jQuery(".mygallery").chromaGallery({
					            color: '#000',
					            gridMargin: 6,
					            maxColumns: 3,
					            screenOpacity: 0.8
					        }); 
				         }
// 				         jQuery(".mygallery").chromaGallery({
// 					            color: '#000',
// 					            gridMargin: 6,
// 					            maxColumns: 3,
// 					            screenOpacity: 0.8
// 					     });
				       }
				    },
				     error : function() {
				          alert("服务器连接失败！");    
				     }    
				});
		}
		
		
	/* 	//下拉框
		function getSelect(){
	       jQuery.ajax({
			    url:'match/list',
			    data: null,
			    type:'POST',
			    dataType:'JSON',
			    async: false,
			    success:function(data){
			    for(var i=0;i<data.length;i++){
				     jQuery("#photoList").append("<option value='"+data[i].newId+"' selected='selected'>"+data[i].bizName+"</option>"); 
			    }
			    },
			     error : function() {
			          alert("服务器连接失败！");    
			     }    
			});
       } */
</script>

</html>