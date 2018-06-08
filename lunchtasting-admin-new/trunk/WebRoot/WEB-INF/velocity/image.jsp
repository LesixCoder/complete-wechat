#parse("./common/global_helper.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>		

<iframe name="fileUpload" style="display: none;"></iframe> 
<form action="/image/upload" enctype="multipart/form-data" method="post" 
target="fileUpload">
<input type="file" name="fileName" id="fileName"/>
<input type="text" name="type" id="type" value="1"/>
<img id="js-img" src="" alt="" />
<input type="submit" value="提交"/>
</form> 

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>

function setImage(imgName,imgUrl,type,code,msg){
	alert(imgName + "//" + imgUrl + "//" + type + "//" + msg);
	
	if(code == 100){
		
		$("#js-img").attr('src',imgUrl); 
		
		//逻辑处理
		if(type == 1){
			
		}
		if(type == 2){
			
		}
	
	}else{
		alert(msg);
	}
	
	
}  


</script>
</body>


</html>