#parse("./common/global_helper.jsp")
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=750, minimal-ui, user-scalable=no">
<meta name="msapplication-tap-highlight" content="no">
<title>成绩榜单</title>

<link rel="stylesheet" href="$!staticPrefix/wechat/css/match/match_score.css" />
</head>
<body>
<div class="wrapper-box">
    <header class="score-header">
        <img src="$!staticPrefix/wechat/images/score/score-logo.png" alt="">
        <img class="header-logo" src="$!staticPrefix/wechat/images/score/logo.png" alt="">
    </header>
    <section class="score-box">
        <div class="box-top">
            <time class="box-time">2017.6.25</time>
        </div>
        <div class="box-bottom">
	        <div class="box-btnG">
	        	<a class="btnG1 active" href="javascript:void(0)">男生队</a>
	            <a class="btnG2" href="javascript:void(0)">女生队</a>
	        </div>
	        <!--男生-->
	        <table class="score-table list1">
	            <thead>
	                <tr>
	                    <td>姓名</td>
	                    <td>参赛号</td>
	                    <td>时间</td>
	                    <td>名次</td>
	                </tr>
	            </thead>
	            <tbody>
	            	#set($num1 = 1)
	            	#foreach($bean in $manList)
	                <tr>
	                    <td>$!bean.name</td>
	                    <td>$!bean.code</td>
	                    <td>$!bean.time</td>
	                    <td>$!num1</td>
	                </tr>
	                #set($num1 = $num1+1)
	                #end
	            </tbody>  
	        </table>
	        <!--女生-->
	        <table class="score-table list2 none">
	            <thead>
	                <tr>
	                    <td>姓名</td>
	                    <td>参赛号</td>
	                    <td>时间</td>
	                    <td>名次</td>
	                </tr>
	            </thead>
	            <tbody>
	                #set($num2 = 1)
	            	#foreach($bean in $womenList)
	                <tr>
	                    <td>$!bean.name</td>
	                    <td>$!bean.code</td>
	                    <td>$!bean.time</td>
	                    <td>$!num2</td>
	                </tr>
	                #set($num2 = $num2+1)
	                #end
	            </tbody>  
	        </table>
        </div>
        <div class="box-footer"></div>
    </section>
</body>
<script src="$!staticPrefix/wechat/js/plugs/jquery-2.1.4.min.js"></script>
<script src="$!staticPrefix/wechat/js/match/match_score.js"></script>
</html>