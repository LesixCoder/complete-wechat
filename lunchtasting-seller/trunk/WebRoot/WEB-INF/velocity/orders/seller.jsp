#parse("base.jsp")
<html>
<head>
    <meta charset="utf-8">
    <!--Bootstrap 不支持 IE 古老的兼容模式。为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>perfit</title>
    <link rel="stylesheet" href="$!path/css/common_css/bootstrap.min.css">
    <link rel="stylesheet" href="$!path/css/seller/find_css/find.css">
</head>

<body style="padding:10px;">
    <div class="container" style="width:100%;">   
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" id="logoText">PERFIT商家中心</a>
                </div>
            </div>
        </nav>
        <div class="row judgeGro">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="input-group input-group-lg">
                    <input type="text" class="form-control" placeholder="请输入要查找的券码" id="quireInput">
                    <span class="input-group-btn"><button class="btn btn-success" id="inquire">查询</button></span>
                </div>
            </div>
        </div>
        <div class="row">
            <div>
                <div class="panel panel-default" style="100%">
                    <div class="panel-heading clearfix">
                        <div class="pull-left" style="margin-right:20px;">
                            <h4><strong>消费记录</strong></h4>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table id="List" class="table table-bordered table-responsive cc">
                            <thead>
                                <tr>
                                    <td>订单号</td>
                                    <td>课程名称</td>
                                    <td>总价(元)</td>
                                    <td>付款时间</td>
                                    <td>券码</td>
                                    <td>使用时间</td>
                                    <td>状态</td>
                                    <td>操作</td>
                                </tr>
                            </thead>
                        </table>        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--回到顶部-->
    <a class="to-top">Top ↑</a>
</body>
<script src="$!path/js/common_js/assets/ie-emulation-modes-warning.js"></script>
<script src="$!path/js/common_js/jquery-1.12.0.min.js"></script>
<script src="$!path/js/common_js/bootstrap.min.jsbootstrap.min.js"></script>
<script src="$!path/js/common_js/assets/ie10-viewport-bug-workaround.js"></script>
<script src="$!path/js/common_js/jquery.toTop.min.js"></script>
<script src="$!path/js/seller/find_js/seller.js"></script>
<script type="text/javascript">
    /*回到顶部初始化*/
    $('.to-top').toTop({
        //options with default values
        autohide: true, //返回顶部按钮是否自动隐藏。可以设置true或false。默认为true
        offset: 420, //页面滚动到距离顶部多少距离时隐藏返回顶部按钮。默认值为420
        speed: 200, //滚动和渐隐的持续时间，默认值为500
        right: 15, //返回顶部按钮距离屏幕右边的距离，默认值为15
        bottom: 30 //返回顶部按钮距离屏幕顶部的距离，默认值为30
    });
</script>
</html>
