<html>
  <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
  <body>
	<li class='active opened active opened'>
		<ul id="menuByRole">
		</ul>
	</li>
	<script type="text/javascript">
$(function(){
	$.ajax({
	    url:'queryMenuByRole',
	    data:{role:role,userId:userId},
	    type:'post',    
	    async: true,
	    dataType:'json',
	    success:function(data){
	    	if(data.result == 0){
	    		$.each(data.menu,function(key,value){
		    		var cmt="<li><a href='"+value.action+"'><span class='title'>"+value.menuName+"</span></a></li>";
		    		$("#menuByRole").append(cmt);
		    	});
	    		
	    	}else{
	    	   alert(data.descript);
	    	}
	    },
	     error : function() {
	     }    
	});
});
	</script>
  </body>
</html>
