#parse("./global/base.jsp")
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>$title</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="$description" name="description">
<meta content="$keywords" name="keywords">
<meta name="baidu-site-verification" content="haWsTVW5CP" />
<link rel="icon" href="http://static.guihua.com/images/ico/favicon.ico" type="image/x-icon" />
<link href="$!csspath?t=#timestamp().css" rel="stylesheet" type="text/css" />
</head>
	<div class="wap">
		<!-- 头部 -->
		<div class="header">
			<!-- <div class="hd_top">&nbsp;</div> -->
			<div class="head">
				<h3><a href="http://www.guihua.com/"><img src="http://static.guihua.com/images/logos.png" alt="" width="164" height="48"/></a></h3>
				<div class="hd_right">
					<ul>
						<li><a href="/">首页</a></li>
						<li><a href="/about">关于</a></li>
						<li><a href="/plan/preview">规划书示例</a></li>
						<!-- <li><a href="/data">数据中心</a></li> -->
						<li><a href="/plan/qa">理财师答疑</a></li>
					</ul>
					<div class="user_hdnav">
					
						#if("$!loginUserId" != "" || "$!user" != "")
							#if("$!loginUserId" != "")
								<a href="/plan">$!loginUserName</a>
							#elseif("$!user" != "")
								<a href="/plan">$!user.name</a>
							#end
							
							<a href="/user/info/set" target="_blank" style="padding-top:2px;">
								<img src="http://static.guihua.com/images/index/f_set.jpg" alt="" width="18" height="18"/>
							</a>
							<a href="/exit">退出</a>
						#else
							<a href="/login">登录</a> <a href="/register">注册</a> 
						#end
						
					</div>
				</div>
			</div>
		</div>
			