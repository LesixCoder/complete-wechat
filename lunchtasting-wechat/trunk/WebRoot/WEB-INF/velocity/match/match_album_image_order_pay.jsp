<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>微信支付</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  </body>
  <script type="text/javascript">
	  function onBridgeReady(){
		   WeixinJSBridge.invoke(
		       'getBrandWCPayRequest', {
		           "appId" : "$!{appId}",//公众号名称，由商户传入     
		           "timeStamp" : "$!{timeStamp}",//时间戳，自1970年以来的秒数     
		           "nonceStr" : "$!{nonceStr}", //随机串    
		           "package" : "$!{package}",     
		           "signType" : "MD5",         //微信签名方式：     
		           "paySign" : "$!{paySign}" //微信签名 
		       },
		       function(res){
		           if(res.err_msg == "get_brand_wcpay_request:ok") {
		    		  window.location.replace("/match/album/image/upload?image_id="+"$!imageId");
		          	
		           }
		           if(res.err_msg == "get_brand_wcpay_request:cancel"){
		    		  window.location.replace("/match/album/image/upload?image_id="+"$!imageId");
		           }
		           if(res.err_msg == "get_brand_wcpay_request:fail"){
		        	   alert("支付失败！");
		           }
		       }
		   ); 
		}
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
  </script>
</html>
